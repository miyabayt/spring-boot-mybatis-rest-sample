package com.bigtreetc.sample.mybatis.base.domain.dao;

import java.lang.reflect.Field;
import lombok.SneakyThrows;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@SuppressWarnings("rawtypes")
@Intercepts({
  @Signature(
      type = Executor.class,
      method = "update",
      args = {MappedStatement.class, Object.class})
})
public class AuditInterceptor implements Interceptor {

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
    Object entityObj = invocation.getArgs()[1];

    Class<?> clazz = entityObj.getClass();
    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      if (mappedStatement.getSqlCommandType() == SqlCommandType.INSERT
          && field.getAnnotation(CreatedDate.class) != null) {
        Object value = AuditInfoHolder.getAuditDateTime();
        setFieldValue(field, entityObj, value, false);
      } else if (field.getAnnotation(LastModifiedDate.class) != null) {
        Object value = AuditInfoHolder.getAuditDateTime();
        setFieldValue(field, entityObj, value, true);
      }
      if (mappedStatement.getSqlCommandType() == SqlCommandType.INSERT
          && field.getAnnotation(CreatedBy.class) != null) {
        Object value = AuditInfoHolder.getAuditUser();
        setFieldValue(field, entityObj, value, false);
      } else if (field.getAnnotation(LastModifiedBy.class) != null) {
        Object value = AuditInfoHolder.getAuditUser();
        setFieldValue(field, entityObj, value, true);
      }
    }

    return invocation.proceed();
  }

  @SneakyThrows
  private void setFieldValue(Field field, Object obj, Object value, boolean overwrite) {
    field.setAccessible(true);
    if (overwrite || field.get(obj) == null) {
      field.set(obj, value);
    }
  }
}
