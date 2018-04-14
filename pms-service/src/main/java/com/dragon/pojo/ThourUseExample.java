package com.dragon.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ThourUseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ThourUseExample() {
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

        public Criteria andHelectUseIsNull() {
            addCriterion("helect_use is null");
            return (Criteria) this;
        }

        public Criteria andHelectUseIsNotNull() {
            addCriterion("helect_use is not null");
            return (Criteria) this;
        }

        public Criteria andHelectUseEqualTo(BigDecimal value) {
            addCriterion("helect_use =", value, "helectUse");
            return (Criteria) this;
        }

        public Criteria andHelectUseNotEqualTo(BigDecimal value) {
            addCriterion("helect_use <>", value, "helectUse");
            return (Criteria) this;
        }

        public Criteria andHelectUseGreaterThan(BigDecimal value) {
            addCriterion("helect_use >", value, "helectUse");
            return (Criteria) this;
        }

        public Criteria andHelectUseGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("helect_use >=", value, "helectUse");
            return (Criteria) this;
        }

        public Criteria andHelectUseLessThan(BigDecimal value) {
            addCriterion("helect_use <", value, "helectUse");
            return (Criteria) this;
        }

        public Criteria andHelectUseLessThanOrEqualTo(BigDecimal value) {
            addCriterion("helect_use <=", value, "helectUse");
            return (Criteria) this;
        }

        public Criteria andHelectUseIn(List<BigDecimal> values) {
            addCriterion("helect_use in", values, "helectUse");
            return (Criteria) this;
        }

        public Criteria andHelectUseNotIn(List<BigDecimal> values) {
            addCriterion("helect_use not in", values, "helectUse");
            return (Criteria) this;
        }

        public Criteria andHelectUseBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("helect_use between", value1, value2, "helectUse");
            return (Criteria) this;
        }

        public Criteria andHelectUseNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("helect_use not between", value1, value2, "helectUse");
            return (Criteria) this;
        }

        public Criteria andDateIsNull() {
            addCriterion("date is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("date is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(Date value) {
            addCriterion("date =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(Date value) {
            addCriterion("date <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(Date value) {
            addCriterion("date >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(Date value) {
            addCriterion("date >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(Date value) {
            addCriterion("date <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(Date value) {
            addCriterion("date <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<Date> values) {
            addCriterion("date in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<Date> values) {
            addCriterion("date not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(Date value1, Date value2) {
            addCriterion("date between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(Date value1, Date value2) {
            addCriterion("date not between", value1, value2, "date");
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