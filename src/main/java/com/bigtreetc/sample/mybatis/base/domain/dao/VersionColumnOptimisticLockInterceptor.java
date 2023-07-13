package com.bigtreetc.sample.mybatis.base.domain.dao;

import com.bigtreetc.sample.mybatis.base.domain.model.BaseEntity;
import com.bigtreetc.sample.mybatis.base.exception.OptimisticLockException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

@Intercepts({
  @Signature(
      type = Executor.class,
      method = "update",
      args = {MappedStatement.class, Object.class})
})
public class VersionColumnOptimisticLockInterceptor implements Interceptor {

  private static final String METHOD_PARAMETER_RECORD = "record";
  private static final String METHOD_PARAMETER_EXAMPLE = "example";
  private static final String STATEMENT_SUFFIX_PRIMARY_KEY = "PrimaryKey";
  private static final String VERSION_PROPERTY_NAME = "version";

  private static final List<String> TARGET_STATEMENT_SUFFIX_LIST =
      List.of(
          "Mapper.updateByExample",
          "Mapper.updateByExampleSelective",
          "Mapper.updateByPrimaryKey",
          "Mapper.updateByPrimaryKeySelective");

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    Executor executor = (Executor) invocation.getTarget();
    Object[] args = invocation.getArgs();
    MappedStatement mappedStatement = (MappedStatement) args[0];
    Object parameter = args[1];

    if (mappedStatement.getSqlCommandType() == SqlCommandType.UPDATE) {
      Configuration configuration = mappedStatement.getConfiguration();

      // インターセプト対象のメソッドの場合に排他制御を行う
      if (isTargetMappedStatement(mappedStatement)) {
        // マッパー定義からselectByExampleのステートメントを取り出す
        String selectStatementId = getSelectStatementId(mappedStatement);
        MappedStatement selectMappedStatement = configuration.getMappedStatement(selectStatementId);

        Object queryParam = null;
        Object recordParam = null;
        if (isUpdateByPrimaryKey(mappedStatement)) {
          queryParam = parameter;
          recordParam = parameter;
        } else {
          queryParam = getMapperMethodParameter(parameter, METHOD_PARAMETER_EXAMPLE);
          recordParam = getMapperMethodParameter(parameter, METHOD_PARAMETER_RECORD);
        }

        // 更新対象のレコードを取得する
        try (Cursor<Object> cursor =
            executor.queryCursor(selectMappedStatement, queryParam, RowBounds.DEFAULT)) {

          List<Object> resultSets = new ArrayList<>();
          for (Object resultSet : cursor) {
            resultSets.add(resultSet);
            if (cursor.getCurrentIndex() == 2) {
              // 2行まで読み取る（indexが0と1はリストに追加される）
              break;
            }
          }

          if (resultSets.size() == 1) {
            // 1件の場合のみチェックする
            Object current = resultSets.get(0);
            Object currentVersion = getPropertyValue(current, VERSION_PROPERTY_NAME);
            Object instanceVersion = getPropertyValue(recordParam, VERSION_PROPERTY_NAME);

            // 更新対象のレコードの改定番号がNULLの場合は、排他制御しない
            if (currentVersion != null && !Objects.equals(currentVersion, instanceVersion)) {
              throw new OptimisticLockException("すでに更新されています。");
            }
          }
        }

        if (recordParam instanceof BaseEntity entity) {
          Long version = entity.getVersion();
          entity.setVersion(version + 1);
        }
      }
    } else if (mappedStatement.getSqlCommandType() == SqlCommandType.INSERT) {
      if (parameter instanceof BaseEntity entity) {
        entity.setVersion(1L);
      }
    }

    return invocation.proceed();
  }

  private Object getPropertyValue(Object obj, String propertyName) {
    try {
      PropertyDescriptor pd = new PropertyDescriptor(propertyName, obj.getClass());
      Method getterMethod = pd.getReadMethod();
      return getterMethod.invoke(obj);
    } catch (Exception e) {
      // ignore
    }
    return null;
  }

  private String getSelectStatementId(MappedStatement mappedStatement) {
    return mappedStatement
        .getId()
        .replace("Mapper.update", "Mapper.select")
        .replace("Selective", "");
  }

  private boolean isTargetMappedStatement(MappedStatement mappedStatement) {
    for (String suffix : TARGET_STATEMENT_SUFFIX_LIST) {
      if (mappedStatement.getId().endsWith(suffix)) {
        return true;
      }
    }
    return false;
  }

  private boolean isUpdateByPrimaryKey(MappedStatement mappedStatement) {
    return mappedStatement.getId().endsWith(STATEMENT_SUFFIX_PRIMARY_KEY);
  }

  @SuppressWarnings("unchecked")
  private Object getMapperMethodParameter(Object parameter, String key) {
    return ((MapperMethod.ParamMap<Object>) parameter).get(key);
  }
}
