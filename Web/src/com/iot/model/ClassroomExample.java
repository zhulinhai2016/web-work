package com.iot.model;

import java.util.ArrayList;
import java.util.List;

public class ClassroomExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ClassroomExample() {
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
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andClassIpIsNull() {
            addCriterion("CLASS_IP is null");
            return (Criteria) this;
        }

        public Criteria andClassIpIsNotNull() {
            addCriterion("CLASS_IP is not null");
            return (Criteria) this;
        }

        public Criteria andClassIpEqualTo(String value) {
            addCriterion("CLASS_IP =", value, "classIp");
            return (Criteria) this;
        }

        public Criteria andClassIpNotEqualTo(String value) {
            addCriterion("CLASS_IP <>", value, "classIp");
            return (Criteria) this;
        }

        public Criteria andClassIpGreaterThan(String value) {
            addCriterion("CLASS_IP >", value, "classIp");
            return (Criteria) this;
        }

        public Criteria andClassIpGreaterThanOrEqualTo(String value) {
            addCriterion("CLASS_IP >=", value, "classIp");
            return (Criteria) this;
        }

        public Criteria andClassIpLessThan(String value) {
            addCriterion("CLASS_IP <", value, "classIp");
            return (Criteria) this;
        }

        public Criteria andClassIpLessThanOrEqualTo(String value) {
            addCriterion("CLASS_IP <=", value, "classIp");
            return (Criteria) this;
        }

        public Criteria andClassIpLike(String value) {
            addCriterion("CLASS_IP like", value, "classIp");
            return (Criteria) this;
        }

        public Criteria andClassIpNotLike(String value) {
            addCriterion("CLASS_IP not like", value, "classIp");
            return (Criteria) this;
        }

        public Criteria andClassIpIn(List<String> values) {
            addCriterion("CLASS_IP in", values, "classIp");
            return (Criteria) this;
        }

        public Criteria andClassIpNotIn(List<String> values) {
            addCriterion("CLASS_IP not in", values, "classIp");
            return (Criteria) this;
        }

        public Criteria andClassIpBetween(String value1, String value2) {
            addCriterion("CLASS_IP between", value1, value2, "classIp");
            return (Criteria) this;
        }

        public Criteria andClassIpNotBetween(String value1, String value2) {
            addCriterion("CLASS_IP not between", value1, value2, "classIp");
            return (Criteria) this;
        }

        public Criteria andClassCenterIpIsNull() {
            addCriterion("CLASS_CENTER_IP is null");
            return (Criteria) this;
        }

        public Criteria andClassCenterIpIsNotNull() {
            addCriterion("CLASS_CENTER_IP is not null");
            return (Criteria) this;
        }

        public Criteria andClassCenterIpEqualTo(String value) {
            addCriterion("CLASS_CENTER_IP =", value, "classCenterIp");
            return (Criteria) this;
        }

        public Criteria andClassCenterIpNotEqualTo(String value) {
            addCriterion("CLASS_CENTER_IP <>", value, "classCenterIp");
            return (Criteria) this;
        }

        public Criteria andClassCenterIpGreaterThan(String value) {
            addCriterion("CLASS_CENTER_IP >", value, "classCenterIp");
            return (Criteria) this;
        }

        public Criteria andClassCenterIpGreaterThanOrEqualTo(String value) {
            addCriterion("CLASS_CENTER_IP >=", value, "classCenterIp");
            return (Criteria) this;
        }

        public Criteria andClassCenterIpLessThan(String value) {
            addCriterion("CLASS_CENTER_IP <", value, "classCenterIp");
            return (Criteria) this;
        }

        public Criteria andClassCenterIpLessThanOrEqualTo(String value) {
            addCriterion("CLASS_CENTER_IP <=", value, "classCenterIp");
            return (Criteria) this;
        }

        public Criteria andClassCenterIpLike(String value) {
            addCriterion("CLASS_CENTER_IP like", value, "classCenterIp");
            return (Criteria) this;
        }

        public Criteria andClassCenterIpNotLike(String value) {
            addCriterion("CLASS_CENTER_IP not like", value, "classCenterIp");
            return (Criteria) this;
        }

        public Criteria andClassCenterIpIn(List<String> values) {
            addCriterion("CLASS_CENTER_IP in", values, "classCenterIp");
            return (Criteria) this;
        }

        public Criteria andClassCenterIpNotIn(List<String> values) {
            addCriterion("CLASS_CENTER_IP not in", values, "classCenterIp");
            return (Criteria) this;
        }

        public Criteria andClassCenterIpBetween(String value1, String value2) {
            addCriterion("CLASS_CENTER_IP between", value1, value2, "classCenterIp");
            return (Criteria) this;
        }

        public Criteria andClassCenterIpNotBetween(String value1, String value2) {
            addCriterion("CLASS_CENTER_IP not between", value1, value2, "classCenterIp");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNull() {
            addCriterion("CLASS_ID is null");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNotNull() {
            addCriterion("CLASS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andClassIdEqualTo(String value) {
            addCriterion("CLASS_ID =", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotEqualTo(String value) {
            addCriterion("CLASS_ID <>", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThan(String value) {
            addCriterion("CLASS_ID >", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThanOrEqualTo(String value) {
            addCriterion("CLASS_ID >=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThan(String value) {
            addCriterion("CLASS_ID <", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThanOrEqualTo(String value) {
            addCriterion("CLASS_ID <=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLike(String value) {
            addCriterion("CLASS_ID like", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotLike(String value) {
            addCriterion("CLASS_ID not like", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdIn(List<String> values) {
            addCriterion("CLASS_ID in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotIn(List<String> values) {
            addCriterion("CLASS_ID not in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdBetween(String value1, String value2) {
            addCriterion("CLASS_ID between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotBetween(String value1, String value2) {
            addCriterion("CLASS_ID not between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andClassTouyIsNull() {
            addCriterion("CLASS_TOUY is null");
            return (Criteria) this;
        }

        public Criteria andClassTouyIsNotNull() {
            addCriterion("CLASS_TOUY is not null");
            return (Criteria) this;
        }

        public Criteria andClassTouyEqualTo(String value) {
            addCriterion("CLASS_TOUY =", value, "classTouy");
            return (Criteria) this;
        }

        public Criteria andClassTouyNotEqualTo(String value) {
            addCriterion("CLASS_TOUY <>", value, "classTouy");
            return (Criteria) this;
        }

        public Criteria andClassTouyGreaterThan(String value) {
            addCriterion("CLASS_TOUY >", value, "classTouy");
            return (Criteria) this;
        }

        public Criteria andClassTouyGreaterThanOrEqualTo(String value) {
            addCriterion("CLASS_TOUY >=", value, "classTouy");
            return (Criteria) this;
        }

        public Criteria andClassTouyLessThan(String value) {
            addCriterion("CLASS_TOUY <", value, "classTouy");
            return (Criteria) this;
        }

        public Criteria andClassTouyLessThanOrEqualTo(String value) {
            addCriterion("CLASS_TOUY <=", value, "classTouy");
            return (Criteria) this;
        }

        public Criteria andClassTouyLike(String value) {
            addCriterion("CLASS_TOUY like", value, "classTouy");
            return (Criteria) this;
        }

        public Criteria andClassTouyNotLike(String value) {
            addCriterion("CLASS_TOUY not like", value, "classTouy");
            return (Criteria) this;
        }

        public Criteria andClassTouyIn(List<String> values) {
            addCriterion("CLASS_TOUY in", values, "classTouy");
            return (Criteria) this;
        }

        public Criteria andClassTouyNotIn(List<String> values) {
            addCriterion("CLASS_TOUY not in", values, "classTouy");
            return (Criteria) this;
        }

        public Criteria andClassTouyBetween(String value1, String value2) {
            addCriterion("CLASS_TOUY between", value1, value2, "classTouy");
            return (Criteria) this;
        }

        public Criteria andClassTouyNotBetween(String value1, String value2) {
            addCriterion("CLASS_TOUY not between", value1, value2, "classTouy");
            return (Criteria) this;
        }

        public Criteria andBuildIdIsNull() {
            addCriterion("BUILD_ID is null");
            return (Criteria) this;
        }

        public Criteria andBuildIdIsNotNull() {
            addCriterion("BUILD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBuildIdEqualTo(Long value) {
            addCriterion("BUILD_ID =", value, "buildId");
            return (Criteria) this;
        }

        public Criteria andBuildIdNotEqualTo(Long value) {
            addCriterion("BUILD_ID <>", value, "buildId");
            return (Criteria) this;
        }

        public Criteria andBuildIdGreaterThan(Long value) {
            addCriterion("BUILD_ID >", value, "buildId");
            return (Criteria) this;
        }

        public Criteria andBuildIdGreaterThanOrEqualTo(Long value) {
            addCriterion("BUILD_ID >=", value, "buildId");
            return (Criteria) this;
        }

        public Criteria andBuildIdLessThan(Long value) {
            addCriterion("BUILD_ID <", value, "buildId");
            return (Criteria) this;
        }

        public Criteria andBuildIdLessThanOrEqualTo(Long value) {
            addCriterion("BUILD_ID <=", value, "buildId");
            return (Criteria) this;
        }

        public Criteria andBuildIdIn(List<Long> values) {
            addCriterion("BUILD_ID in", values, "buildId");
            return (Criteria) this;
        }

        public Criteria andBuildIdNotIn(List<Long> values) {
            addCriterion("BUILD_ID not in", values, "buildId");
            return (Criteria) this;
        }

        public Criteria andBuildIdBetween(Long value1, Long value2) {
            addCriterion("BUILD_ID between", value1, value2, "buildId");
            return (Criteria) this;
        }

        public Criteria andBuildIdNotBetween(Long value1, Long value2) {
            addCriterion("BUILD_ID not between", value1, value2, "buildId");
            return (Criteria) this;
        }

        public Criteria andFloorIdIsNull() {
            addCriterion("FLOOR_ID is null");
            return (Criteria) this;
        }

        public Criteria andFloorIdIsNotNull() {
            addCriterion("FLOOR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFloorIdEqualTo(Long value) {
            addCriterion("FLOOR_ID =", value, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorIdNotEqualTo(Long value) {
            addCriterion("FLOOR_ID <>", value, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorIdGreaterThan(Long value) {
            addCriterion("FLOOR_ID >", value, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorIdGreaterThanOrEqualTo(Long value) {
            addCriterion("FLOOR_ID >=", value, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorIdLessThan(Long value) {
            addCriterion("FLOOR_ID <", value, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorIdLessThanOrEqualTo(Long value) {
            addCriterion("FLOOR_ID <=", value, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorIdIn(List<Long> values) {
            addCriterion("FLOOR_ID in", values, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorIdNotIn(List<Long> values) {
            addCriterion("FLOOR_ID not in", values, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorIdBetween(Long value1, Long value2) {
            addCriterion("FLOOR_ID between", value1, value2, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorIdNotBetween(Long value1, Long value2) {
            addCriterion("FLOOR_ID not between", value1, value2, "floorId");
            return (Criteria) this;
        }

        public Criteria andTouytypeIsNull() {
            addCriterion("TOUYTYPE is null");
            return (Criteria) this;
        }

        public Criteria andTouytypeIsNotNull() {
            addCriterion("TOUYTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTouytypeEqualTo(String value) {
            addCriterion("TOUYTYPE =", value, "touytype");
            return (Criteria) this;
        }

        public Criteria andTouytypeNotEqualTo(String value) {
            addCriterion("TOUYTYPE <>", value, "touytype");
            return (Criteria) this;
        }

        public Criteria andTouytypeGreaterThan(String value) {
            addCriterion("TOUYTYPE >", value, "touytype");
            return (Criteria) this;
        }

        public Criteria andTouytypeGreaterThanOrEqualTo(String value) {
            addCriterion("TOUYTYPE >=", value, "touytype");
            return (Criteria) this;
        }

        public Criteria andTouytypeLessThan(String value) {
            addCriterion("TOUYTYPE <", value, "touytype");
            return (Criteria) this;
        }

        public Criteria andTouytypeLessThanOrEqualTo(String value) {
            addCriterion("TOUYTYPE <=", value, "touytype");
            return (Criteria) this;
        }

        public Criteria andTouytypeLike(String value) {
            addCriterion("TOUYTYPE like", value, "touytype");
            return (Criteria) this;
        }

        public Criteria andTouytypeNotLike(String value) {
            addCriterion("TOUYTYPE not like", value, "touytype");
            return (Criteria) this;
        }

        public Criteria andTouytypeIn(List<String> values) {
            addCriterion("TOUYTYPE in", values, "touytype");
            return (Criteria) this;
        }

        public Criteria andTouytypeNotIn(List<String> values) {
            addCriterion("TOUYTYPE not in", values, "touytype");
            return (Criteria) this;
        }

        public Criteria andTouytypeBetween(String value1, String value2) {
            addCriterion("TOUYTYPE between", value1, value2, "touytype");
            return (Criteria) this;
        }

        public Criteria andTouytypeNotBetween(String value1, String value2) {
            addCriterion("TOUYTYPE not between", value1, value2, "touytype");
            return (Criteria) this;
        }

        public Criteria andKongtypeIsNull() {
            addCriterion("KONGTYPE is null");
            return (Criteria) this;
        }

        public Criteria andKongtypeIsNotNull() {
            addCriterion("KONGTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andKongtypeEqualTo(String value) {
            addCriterion("KONGTYPE =", value, "kongtype");
            return (Criteria) this;
        }

        public Criteria andKongtypeNotEqualTo(String value) {
            addCriterion("KONGTYPE <>", value, "kongtype");
            return (Criteria) this;
        }

        public Criteria andKongtypeGreaterThan(String value) {
            addCriterion("KONGTYPE >", value, "kongtype");
            return (Criteria) this;
        }

        public Criteria andKongtypeGreaterThanOrEqualTo(String value) {
            addCriterion("KONGTYPE >=", value, "kongtype");
            return (Criteria) this;
        }

        public Criteria andKongtypeLessThan(String value) {
            addCriterion("KONGTYPE <", value, "kongtype");
            return (Criteria) this;
        }

        public Criteria andKongtypeLessThanOrEqualTo(String value) {
            addCriterion("KONGTYPE <=", value, "kongtype");
            return (Criteria) this;
        }

        public Criteria andKongtypeLike(String value) {
            addCriterion("KONGTYPE like", value, "kongtype");
            return (Criteria) this;
        }

        public Criteria andKongtypeNotLike(String value) {
            addCriterion("KONGTYPE not like", value, "kongtype");
            return (Criteria) this;
        }

        public Criteria andKongtypeIn(List<String> values) {
            addCriterion("KONGTYPE in", values, "kongtype");
            return (Criteria) this;
        }

        public Criteria andKongtypeNotIn(List<String> values) {
            addCriterion("KONGTYPE not in", values, "kongtype");
            return (Criteria) this;
        }

        public Criteria andKongtypeBetween(String value1, String value2) {
            addCriterion("KONGTYPE between", value1, value2, "kongtype");
            return (Criteria) this;
        }

        public Criteria andKongtypeNotBetween(String value1, String value2) {
            addCriterion("KONGTYPE not between", value1, value2, "kongtype");
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