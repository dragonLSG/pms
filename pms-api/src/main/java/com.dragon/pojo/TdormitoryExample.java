package com.dragon.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TdormitoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TdormitoryExample() {
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

        public Criteria andDormitoryidIsNull() {
            addCriterion("dormitoryid is null");
            return (Criteria) this;
        }

        public Criteria andDormitoryidIsNotNull() {
            addCriterion("dormitoryid is not null");
            return (Criteria) this;
        }

        public Criteria andDormitoryidEqualTo(Integer value) {
            addCriterion("dormitoryid =", value, "dormitoryid");
            return (Criteria) this;
        }

        public Criteria andDormitoryidNotEqualTo(Integer value) {
            addCriterion("dormitoryid <>", value, "dormitoryid");
            return (Criteria) this;
        }

        public Criteria andDormitoryidGreaterThan(Integer value) {
            addCriterion("dormitoryid >", value, "dormitoryid");
            return (Criteria) this;
        }

        public Criteria andDormitoryidGreaterThanOrEqualTo(Integer value) {
            addCriterion("dormitoryid >=", value, "dormitoryid");
            return (Criteria) this;
        }

        public Criteria andDormitoryidLessThan(Integer value) {
            addCriterion("dormitoryid <", value, "dormitoryid");
            return (Criteria) this;
        }

        public Criteria andDormitoryidLessThanOrEqualTo(Integer value) {
            addCriterion("dormitoryid <=", value, "dormitoryid");
            return (Criteria) this;
        }

        public Criteria andDormitoryidIn(List<Integer> values) {
            addCriterion("dormitoryid in", values, "dormitoryid");
            return (Criteria) this;
        }

        public Criteria andDormitoryidNotIn(List<Integer> values) {
            addCriterion("dormitoryid not in", values, "dormitoryid");
            return (Criteria) this;
        }

        public Criteria andDormitoryidBetween(Integer value1, Integer value2) {
            addCriterion("dormitoryid between", value1, value2, "dormitoryid");
            return (Criteria) this;
        }

        public Criteria andDormitoryidNotBetween(Integer value1, Integer value2) {
            addCriterion("dormitoryid not between", value1, value2, "dormitoryid");
            return (Criteria) this;
        }

        public Criteria andDormitorynameIsNull() {
            addCriterion("dormitoryName is null");
            return (Criteria) this;
        }

        public Criteria andDormitorynameIsNotNull() {
            addCriterion("dormitoryName is not null");
            return (Criteria) this;
        }

        public Criteria andDormitorynameEqualTo(String value) {
            addCriterion("dormitoryName =", value, "dormitoryname");
            return (Criteria) this;
        }

        public Criteria andDormitorynameNotEqualTo(String value) {
            addCriterion("dormitoryName <>", value, "dormitoryname");
            return (Criteria) this;
        }

        public Criteria andDormitorynameGreaterThan(String value) {
            addCriterion("dormitoryName >", value, "dormitoryname");
            return (Criteria) this;
        }

        public Criteria andDormitorynameGreaterThanOrEqualTo(String value) {
            addCriterion("dormitoryName >=", value, "dormitoryname");
            return (Criteria) this;
        }

        public Criteria andDormitorynameLessThan(String value) {
            addCriterion("dormitoryName <", value, "dormitoryname");
            return (Criteria) this;
        }

        public Criteria andDormitorynameLessThanOrEqualTo(String value) {
            addCriterion("dormitoryName <=", value, "dormitoryname");
            return (Criteria) this;
        }

        public Criteria andDormitorynameLike(String value) {
            addCriterion("dormitoryName like", value, "dormitoryname");
            return (Criteria) this;
        }

        public Criteria andDormitorynameNotLike(String value) {
            addCriterion("dormitoryName not like", value, "dormitoryname");
            return (Criteria) this;
        }

        public Criteria andDormitorynameIn(List<String> values) {
            addCriterion("dormitoryName in", values, "dormitoryname");
            return (Criteria) this;
        }

        public Criteria andDormitorynameNotIn(List<String> values) {
            addCriterion("dormitoryName not in", values, "dormitoryname");
            return (Criteria) this;
        }

        public Criteria andDormitorynameBetween(String value1, String value2) {
            addCriterion("dormitoryName between", value1, value2, "dormitoryname");
            return (Criteria) this;
        }

        public Criteria andDormitorynameNotBetween(String value1, String value2) {
            addCriterion("dormitoryName not between", value1, value2, "dormitoryname");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNull() {
            addCriterion("balance is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("balance is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(BigDecimal value) {
            addCriterion("balance =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(BigDecimal value) {
            addCriterion("balance <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(BigDecimal value) {
            addCriterion("balance >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("balance >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(BigDecimal value) {
            addCriterion("balance <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("balance <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<BigDecimal> values) {
            addCriterion("balance in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<BigDecimal> values) {
            addCriterion("balance not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance not between", value1, value2, "balance");
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