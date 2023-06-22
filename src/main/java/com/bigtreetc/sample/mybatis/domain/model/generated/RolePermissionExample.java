package com.bigtreetc.sample.mybatis.domain.model.generated;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RolePermissionExample {
  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  public RolePermissionExample() {
    oredCriteria = new ArrayList<Criteria>();
  }

  public void setOrderByClause(String orderByClause) {
    this.orderByClause = orderByClause;
  }

  public String getOrderByClause() {
    return orderByClause;
  }

  public void setDistinct(boolean distinct) {
    this.distinct = distinct;
  }

  public boolean isDistinct() {
    return distinct;
  }

  public List<Criteria> getOredCriteria() {
    return oredCriteria;
  }

  public void or(Criteria criteria) {
    oredCriteria.add(criteria);
  }

  public Criteria or() {
    Criteria criteria = createCriteriaInternal();
    oredCriteria.add(criteria);
    return criteria;
  }

  public Criteria createCriteria() {
    Criteria criteria = createCriteriaInternal();
    if (oredCriteria.size() == 0) {
      oredCriteria.add(criteria);
    }
    return criteria;
  }

  protected Criteria createCriteriaInternal() {
    Criteria criteria = new Criteria();
    return criteria;
  }

  public void clear() {
    oredCriteria.clear();
    orderByClause = null;
    distinct = false;
  }

  protected abstract static class GeneratedCriteria {
    protected List<Criterion> criteria;

    protected GeneratedCriteria() {
      super();
      criteria = new ArrayList<Criterion>();
    }

    public boolean isValid() {
      return criteria.size() > 0;
    }

    public List<Criterion> getAllCriteria() {
      return criteria;
    }

    public List<Criterion> getCriteria() {
      return criteria;
    }

    protected void addCriterion(String condition) {
      if (condition == null) {
        throw new RuntimeException("Value for condition cannot be null");
      }
      criteria.add(new Criterion(condition));
    }

    protected void addCriterion(String condition, Object value, String property) {
      if (value == null) {
        throw new RuntimeException("Value for " + property + " cannot be null");
      }
      criteria.add(new Criterion(condition, value));
    }

    protected void addCriterion(String condition, Object value1, Object value2, String property) {
      if (value1 == null || value2 == null) {
        throw new RuntimeException("Between values for " + property + " cannot be null");
      }
      criteria.add(new Criterion(condition, value1, value2));
    }

    public Criteria andIdIsNull() {
      addCriterion("role_permission_id is null");
      return (Criteria) this;
    }

    public Criteria andIdIsNotNull() {
      addCriterion("role_permission_id is not null");
      return (Criteria) this;
    }

    public Criteria andIdEqualTo(Long value) {
      addCriterion("role_permission_id =", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotEqualTo(Long value) {
      addCriterion("role_permission_id <>", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThan(Long value) {
      addCriterion("role_permission_id >", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThanOrEqualTo(Long value) {
      addCriterion("role_permission_id >=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThan(Long value) {
      addCriterion("role_permission_id <", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThanOrEqualTo(Long value) {
      addCriterion("role_permission_id <=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdIn(List<Long> values) {
      addCriterion("role_permission_id in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotIn(List<Long> values) {
      addCriterion("role_permission_id not in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdBetween(Long value1, Long value2) {
      addCriterion("role_permission_id between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotBetween(Long value1, Long value2) {
      addCriterion("role_permission_id not between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andRoleCodeIsNull() {
      addCriterion("role_code is null");
      return (Criteria) this;
    }

    public Criteria andRoleCodeIsNotNull() {
      addCriterion("role_code is not null");
      return (Criteria) this;
    }

    public Criteria andRoleCodeEqualTo(String value) {
      addCriterion("role_code =", value, "roleCode");
      return (Criteria) this;
    }

    public Criteria andRoleCodeNotEqualTo(String value) {
      addCriterion("role_code <>", value, "roleCode");
      return (Criteria) this;
    }

    public Criteria andRoleCodeGreaterThan(String value) {
      addCriterion("role_code >", value, "roleCode");
      return (Criteria) this;
    }

    public Criteria andRoleCodeGreaterThanOrEqualTo(String value) {
      addCriterion("role_code >=", value, "roleCode");
      return (Criteria) this;
    }

    public Criteria andRoleCodeLessThan(String value) {
      addCriterion("role_code <", value, "roleCode");
      return (Criteria) this;
    }

    public Criteria andRoleCodeLessThanOrEqualTo(String value) {
      addCriterion("role_code <=", value, "roleCode");
      return (Criteria) this;
    }

    public Criteria andRoleCodeLike(String value) {
      addCriterion("role_code like", value, "roleCode");
      return (Criteria) this;
    }

    public Criteria andRoleCodeNotLike(String value) {
      addCriterion("role_code not like", value, "roleCode");
      return (Criteria) this;
    }

    public Criteria andRoleCodeIn(List<String> values) {
      addCriterion("role_code in", values, "roleCode");
      return (Criteria) this;
    }

    public Criteria andRoleCodeNotIn(List<String> values) {
      addCriterion("role_code not in", values, "roleCode");
      return (Criteria) this;
    }

    public Criteria andRoleCodeBetween(String value1, String value2) {
      addCriterion("role_code between", value1, value2, "roleCode");
      return (Criteria) this;
    }

    public Criteria andRoleCodeNotBetween(String value1, String value2) {
      addCriterion("role_code not between", value1, value2, "roleCode");
      return (Criteria) this;
    }

    public Criteria andPermissionCodeIsNull() {
      addCriterion("permission_code is null");
      return (Criteria) this;
    }

    public Criteria andPermissionCodeIsNotNull() {
      addCriterion("permission_code is not null");
      return (Criteria) this;
    }

    public Criteria andPermissionCodeEqualTo(String value) {
      addCriterion("permission_code =", value, "permissionCode");
      return (Criteria) this;
    }

    public Criteria andPermissionCodeNotEqualTo(String value) {
      addCriterion("permission_code <>", value, "permissionCode");
      return (Criteria) this;
    }

    public Criteria andPermissionCodeGreaterThan(String value) {
      addCriterion("permission_code >", value, "permissionCode");
      return (Criteria) this;
    }

    public Criteria andPermissionCodeGreaterThanOrEqualTo(String value) {
      addCriterion("permission_code >=", value, "permissionCode");
      return (Criteria) this;
    }

    public Criteria andPermissionCodeLessThan(String value) {
      addCriterion("permission_code <", value, "permissionCode");
      return (Criteria) this;
    }

    public Criteria andPermissionCodeLessThanOrEqualTo(String value) {
      addCriterion("permission_code <=", value, "permissionCode");
      return (Criteria) this;
    }

    public Criteria andPermissionCodeLike(String value) {
      addCriterion("permission_code like", value, "permissionCode");
      return (Criteria) this;
    }

    public Criteria andPermissionCodeNotLike(String value) {
      addCriterion("permission_code not like", value, "permissionCode");
      return (Criteria) this;
    }

    public Criteria andPermissionCodeIn(List<String> values) {
      addCriterion("permission_code in", values, "permissionCode");
      return (Criteria) this;
    }

    public Criteria andPermissionCodeNotIn(List<String> values) {
      addCriterion("permission_code not in", values, "permissionCode");
      return (Criteria) this;
    }

    public Criteria andPermissionCodeBetween(String value1, String value2) {
      addCriterion("permission_code between", value1, value2, "permissionCode");
      return (Criteria) this;
    }

    public Criteria andPermissionCodeNotBetween(String value1, String value2) {
      addCriterion("permission_code not between", value1, value2, "permissionCode");
      return (Criteria) this;
    }

    public Criteria andIsEnabledIsNull() {
      addCriterion("is_enabled is null");
      return (Criteria) this;
    }

    public Criteria andIsEnabledIsNotNull() {
      addCriterion("is_enabled is not null");
      return (Criteria) this;
    }

    public Criteria andIsEnabledEqualTo(Boolean value) {
      addCriterion("is_enabled =", value, "isEnabled");
      return (Criteria) this;
    }

    public Criteria andIsEnabledNotEqualTo(Boolean value) {
      addCriterion("is_enabled <>", value, "isEnabled");
      return (Criteria) this;
    }

    public Criteria andIsEnabledGreaterThan(Boolean value) {
      addCriterion("is_enabled >", value, "isEnabled");
      return (Criteria) this;
    }

    public Criteria andIsEnabledGreaterThanOrEqualTo(Boolean value) {
      addCriterion("is_enabled >=", value, "isEnabled");
      return (Criteria) this;
    }

    public Criteria andIsEnabledLessThan(Boolean value) {
      addCriterion("is_enabled <", value, "isEnabled");
      return (Criteria) this;
    }

    public Criteria andIsEnabledLessThanOrEqualTo(Boolean value) {
      addCriterion("is_enabled <=", value, "isEnabled");
      return (Criteria) this;
    }

    public Criteria andIsEnabledIn(List<Boolean> values) {
      addCriterion("is_enabled in", values, "isEnabled");
      return (Criteria) this;
    }

    public Criteria andIsEnabledNotIn(List<Boolean> values) {
      addCriterion("is_enabled not in", values, "isEnabled");
      return (Criteria) this;
    }

    public Criteria andIsEnabledBetween(Boolean value1, Boolean value2) {
      addCriterion("is_enabled between", value1, value2, "isEnabled");
      return (Criteria) this;
    }

    public Criteria andIsEnabledNotBetween(Boolean value1, Boolean value2) {
      addCriterion("is_enabled not between", value1, value2, "isEnabled");
      return (Criteria) this;
    }

    public Criteria andCreatedByIsNull() {
      addCriterion("created_by is null");
      return (Criteria) this;
    }

    public Criteria andCreatedByIsNotNull() {
      addCriterion("created_by is not null");
      return (Criteria) this;
    }

    public Criteria andCreatedByEqualTo(String value) {
      addCriterion("created_by =", value, "createdBy");
      return (Criteria) this;
    }

    public Criteria andCreatedByNotEqualTo(String value) {
      addCriterion("created_by <>", value, "createdBy");
      return (Criteria) this;
    }

    public Criteria andCreatedByGreaterThan(String value) {
      addCriterion("created_by >", value, "createdBy");
      return (Criteria) this;
    }

    public Criteria andCreatedByGreaterThanOrEqualTo(String value) {
      addCriterion("created_by >=", value, "createdBy");
      return (Criteria) this;
    }

    public Criteria andCreatedByLessThan(String value) {
      addCriterion("created_by <", value, "createdBy");
      return (Criteria) this;
    }

    public Criteria andCreatedByLessThanOrEqualTo(String value) {
      addCriterion("created_by <=", value, "createdBy");
      return (Criteria) this;
    }

    public Criteria andCreatedByLike(String value) {
      addCriterion("created_by like", value, "createdBy");
      return (Criteria) this;
    }

    public Criteria andCreatedByNotLike(String value) {
      addCriterion("created_by not like", value, "createdBy");
      return (Criteria) this;
    }

    public Criteria andCreatedByIn(List<String> values) {
      addCriterion("created_by in", values, "createdBy");
      return (Criteria) this;
    }

    public Criteria andCreatedByNotIn(List<String> values) {
      addCriterion("created_by not in", values, "createdBy");
      return (Criteria) this;
    }

    public Criteria andCreatedByBetween(String value1, String value2) {
      addCriterion("created_by between", value1, value2, "createdBy");
      return (Criteria) this;
    }

    public Criteria andCreatedByNotBetween(String value1, String value2) {
      addCriterion("created_by not between", value1, value2, "createdBy");
      return (Criteria) this;
    }

    public Criteria andCreatedAtIsNull() {
      addCriterion("created_at is null");
      return (Criteria) this;
    }

    public Criteria andCreatedAtIsNotNull() {
      addCriterion("created_at is not null");
      return (Criteria) this;
    }

    public Criteria andCreatedAtEqualTo(LocalDateTime value) {
      addCriterion("created_at =", value, "createdAt");
      return (Criteria) this;
    }

    public Criteria andCreatedAtNotEqualTo(LocalDateTime value) {
      addCriterion("created_at <>", value, "createdAt");
      return (Criteria) this;
    }

    public Criteria andCreatedAtGreaterThan(LocalDateTime value) {
      addCriterion("created_at >", value, "createdAt");
      return (Criteria) this;
    }

    public Criteria andCreatedAtGreaterThanOrEqualTo(LocalDateTime value) {
      addCriterion("created_at >=", value, "createdAt");
      return (Criteria) this;
    }

    public Criteria andCreatedAtLessThan(LocalDateTime value) {
      addCriterion("created_at <", value, "createdAt");
      return (Criteria) this;
    }

    public Criteria andCreatedAtLessThanOrEqualTo(LocalDateTime value) {
      addCriterion("created_at <=", value, "createdAt");
      return (Criteria) this;
    }

    public Criteria andCreatedAtIn(List<LocalDateTime> values) {
      addCriterion("created_at in", values, "createdAt");
      return (Criteria) this;
    }

    public Criteria andCreatedAtNotIn(List<LocalDateTime> values) {
      addCriterion("created_at not in", values, "createdAt");
      return (Criteria) this;
    }

    public Criteria andCreatedAtBetween(LocalDateTime value1, LocalDateTime value2) {
      addCriterion("created_at between", value1, value2, "createdAt");
      return (Criteria) this;
    }

    public Criteria andCreatedAtNotBetween(LocalDateTime value1, LocalDateTime value2) {
      addCriterion("created_at not between", value1, value2, "createdAt");
      return (Criteria) this;
    }

    public Criteria andUpdatedByIsNull() {
      addCriterion("updated_by is null");
      return (Criteria) this;
    }

    public Criteria andUpdatedByIsNotNull() {
      addCriterion("updated_by is not null");
      return (Criteria) this;
    }

    public Criteria andUpdatedByEqualTo(String value) {
      addCriterion("updated_by =", value, "updatedBy");
      return (Criteria) this;
    }

    public Criteria andUpdatedByNotEqualTo(String value) {
      addCriterion("updated_by <>", value, "updatedBy");
      return (Criteria) this;
    }

    public Criteria andUpdatedByGreaterThan(String value) {
      addCriterion("updated_by >", value, "updatedBy");
      return (Criteria) this;
    }

    public Criteria andUpdatedByGreaterThanOrEqualTo(String value) {
      addCriterion("updated_by >=", value, "updatedBy");
      return (Criteria) this;
    }

    public Criteria andUpdatedByLessThan(String value) {
      addCriterion("updated_by <", value, "updatedBy");
      return (Criteria) this;
    }

    public Criteria andUpdatedByLessThanOrEqualTo(String value) {
      addCriterion("updated_by <=", value, "updatedBy");
      return (Criteria) this;
    }

    public Criteria andUpdatedByLike(String value) {
      addCriterion("updated_by like", value, "updatedBy");
      return (Criteria) this;
    }

    public Criteria andUpdatedByNotLike(String value) {
      addCriterion("updated_by not like", value, "updatedBy");
      return (Criteria) this;
    }

    public Criteria andUpdatedByIn(List<String> values) {
      addCriterion("updated_by in", values, "updatedBy");
      return (Criteria) this;
    }

    public Criteria andUpdatedByNotIn(List<String> values) {
      addCriterion("updated_by not in", values, "updatedBy");
      return (Criteria) this;
    }

    public Criteria andUpdatedByBetween(String value1, String value2) {
      addCriterion("updated_by between", value1, value2, "updatedBy");
      return (Criteria) this;
    }

    public Criteria andUpdatedByNotBetween(String value1, String value2) {
      addCriterion("updated_by not between", value1, value2, "updatedBy");
      return (Criteria) this;
    }

    public Criteria andUpdatedAtIsNull() {
      addCriterion("updated_at is null");
      return (Criteria) this;
    }

    public Criteria andUpdatedAtIsNotNull() {
      addCriterion("updated_at is not null");
      return (Criteria) this;
    }

    public Criteria andUpdatedAtEqualTo(LocalDateTime value) {
      addCriterion("updated_at =", value, "updatedAt");
      return (Criteria) this;
    }

    public Criteria andUpdatedAtNotEqualTo(LocalDateTime value) {
      addCriterion("updated_at <>", value, "updatedAt");
      return (Criteria) this;
    }

    public Criteria andUpdatedAtGreaterThan(LocalDateTime value) {
      addCriterion("updated_at >", value, "updatedAt");
      return (Criteria) this;
    }

    public Criteria andUpdatedAtGreaterThanOrEqualTo(LocalDateTime value) {
      addCriterion("updated_at >=", value, "updatedAt");
      return (Criteria) this;
    }

    public Criteria andUpdatedAtLessThan(LocalDateTime value) {
      addCriterion("updated_at <", value, "updatedAt");
      return (Criteria) this;
    }

    public Criteria andUpdatedAtLessThanOrEqualTo(LocalDateTime value) {
      addCriterion("updated_at <=", value, "updatedAt");
      return (Criteria) this;
    }

    public Criteria andUpdatedAtIn(List<LocalDateTime> values) {
      addCriterion("updated_at in", values, "updatedAt");
      return (Criteria) this;
    }

    public Criteria andUpdatedAtNotIn(List<LocalDateTime> values) {
      addCriterion("updated_at not in", values, "updatedAt");
      return (Criteria) this;
    }

    public Criteria andUpdatedAtBetween(LocalDateTime value1, LocalDateTime value2) {
      addCriterion("updated_at between", value1, value2, "updatedAt");
      return (Criteria) this;
    }

    public Criteria andUpdatedAtNotBetween(LocalDateTime value1, LocalDateTime value2) {
      addCriterion("updated_at not between", value1, value2, "updatedAt");
      return (Criteria) this;
    }

    public Criteria andVersionIsNull() {
      addCriterion("version is null");
      return (Criteria) this;
    }

    public Criteria andVersionIsNotNull() {
      addCriterion("version is not null");
      return (Criteria) this;
    }

    public Criteria andVersionEqualTo(Long value) {
      addCriterion("version =", value, "version");
      return (Criteria) this;
    }

    public Criteria andVersionNotEqualTo(Long value) {
      addCriterion("version <>", value, "version");
      return (Criteria) this;
    }

    public Criteria andVersionGreaterThan(Long value) {
      addCriterion("version >", value, "version");
      return (Criteria) this;
    }

    public Criteria andVersionGreaterThanOrEqualTo(Long value) {
      addCriterion("version >=", value, "version");
      return (Criteria) this;
    }

    public Criteria andVersionLessThan(Long value) {
      addCriterion("version <", value, "version");
      return (Criteria) this;
    }

    public Criteria andVersionLessThanOrEqualTo(Long value) {
      addCriterion("version <=", value, "version");
      return (Criteria) this;
    }

    public Criteria andVersionIn(List<Long> values) {
      addCriterion("version in", values, "version");
      return (Criteria) this;
    }

    public Criteria andVersionNotIn(List<Long> values) {
      addCriterion("version not in", values, "version");
      return (Criteria) this;
    }

    public Criteria andVersionBetween(Long value1, Long value2) {
      addCriterion("version between", value1, value2, "version");
      return (Criteria) this;
    }

    public Criteria andVersionNotBetween(Long value1, Long value2) {
      addCriterion("version not between", value1, value2, "version");
      return (Criteria) this;
    }
  }

  public static class Criteria extends GeneratedCriteria {

    protected Criteria() {
      super();
    }
  }

  public static class Criterion {
    private String condition;

    private Object value;

    private Object secondValue;

    private boolean noValue;

    private boolean singleValue;

    private boolean betweenValue;

    private boolean listValue;

    private String typeHandler;

    public String getCondition() {
      return condition;
    }

    public Object getValue() {
      return value;
    }

    public Object getSecondValue() {
      return secondValue;
    }

    public boolean isNoValue() {
      return noValue;
    }

    public boolean isSingleValue() {
      return singleValue;
    }

    public boolean isBetweenValue() {
      return betweenValue;
    }

    public boolean isListValue() {
      return listValue;
    }

    public String getTypeHandler() {
      return typeHandler;
    }

    protected Criterion(String condition) {
      super();
      this.condition = condition;
      this.typeHandler = null;
      this.noValue = true;
    }

    protected Criterion(String condition, Object value, String typeHandler) {
      super();
      this.condition = condition;
      this.value = value;
      this.typeHandler = typeHandler;
      if (value instanceof List<?>) {
        this.listValue = true;
      } else {
        this.singleValue = true;
      }
    }

    protected Criterion(String condition, Object value) {
      this(condition, value, null);
    }

    protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
      super();
      this.condition = condition;
      this.value = value;
      this.secondValue = secondValue;
      this.typeHandler = typeHandler;
      this.betweenValue = true;
    }

    protected Criterion(String condition, Object value, Object secondValue) {
      this(condition, value, secondValue, null);
    }
  }
}
