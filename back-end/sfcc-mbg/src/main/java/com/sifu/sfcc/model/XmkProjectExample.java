package com.sifu.sfcc.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XmkProjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public XmkProjectExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSXmIdIsNull() {
            addCriterion("S_XM_ID is null");
            return (Criteria) this;
        }

        public Criteria andSXmIdIsNotNull() {
            addCriterion("S_XM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSXmIdEqualTo(String value) {
            addCriterion("S_XM_ID =", value, "sXmId");
            return (Criteria) this;
        }

        public Criteria andSXmIdNotEqualTo(String value) {
            addCriterion("S_XM_ID <>", value, "sXmId");
            return (Criteria) this;
        }

        public Criteria andSXmIdGreaterThan(String value) {
            addCriterion("S_XM_ID >", value, "sXmId");
            return (Criteria) this;
        }

        public Criteria andSXmIdGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_ID >=", value, "sXmId");
            return (Criteria) this;
        }

        public Criteria andSXmIdLessThan(String value) {
            addCriterion("S_XM_ID <", value, "sXmId");
            return (Criteria) this;
        }

        public Criteria andSXmIdLessThanOrEqualTo(String value) {
            addCriterion("S_XM_ID <=", value, "sXmId");
            return (Criteria) this;
        }

        public Criteria andSXmIdLike(String value) {
            addCriterion("S_XM_ID like", value, "sXmId");
            return (Criteria) this;
        }

        public Criteria andSXmIdNotLike(String value) {
            addCriterion("S_XM_ID not like", value, "sXmId");
            return (Criteria) this;
        }

        public Criteria andSXmIdIn(List<String> values) {
            addCriterion("S_XM_ID in", values, "sXmId");
            return (Criteria) this;
        }

        public Criteria andSXmIdNotIn(List<String> values) {
            addCriterion("S_XM_ID not in", values, "sXmId");
            return (Criteria) this;
        }

        public Criteria andSXmIdBetween(String value1, String value2) {
            addCriterion("S_XM_ID between", value1, value2, "sXmId");
            return (Criteria) this;
        }

        public Criteria andSXmIdNotBetween(String value1, String value2) {
            addCriterion("S_XM_ID not between", value1, value2, "sXmId");
            return (Criteria) this;
        }

        public Criteria andSXmNameIsNull() {
            addCriterion("S_XM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSXmNameIsNotNull() {
            addCriterion("S_XM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSXmNameEqualTo(String value) {
            addCriterion("S_XM_NAME =", value, "sXmName");
            return (Criteria) this;
        }

        public Criteria andSXmNameNotEqualTo(String value) {
            addCriterion("S_XM_NAME <>", value, "sXmName");
            return (Criteria) this;
        }

        public Criteria andSXmNameGreaterThan(String value) {
            addCriterion("S_XM_NAME >", value, "sXmName");
            return (Criteria) this;
        }

        public Criteria andSXmNameGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_NAME >=", value, "sXmName");
            return (Criteria) this;
        }

        public Criteria andSXmNameLessThan(String value) {
            addCriterion("S_XM_NAME <", value, "sXmName");
            return (Criteria) this;
        }

        public Criteria andSXmNameLessThanOrEqualTo(String value) {
            addCriterion("S_XM_NAME <=", value, "sXmName");
            return (Criteria) this;
        }

        public Criteria andSXmNameLike(String value) {
            addCriterion("S_XM_NAME like", value, "sXmName");
            return (Criteria) this;
        }

        public Criteria andSXmNameNotLike(String value) {
            addCriterion("S_XM_NAME not like", value, "sXmName");
            return (Criteria) this;
        }

        public Criteria andSXmNameIn(List<String> values) {
            addCriterion("S_XM_NAME in", values, "sXmName");
            return (Criteria) this;
        }

        public Criteria andSXmNameNotIn(List<String> values) {
            addCriterion("S_XM_NAME not in", values, "sXmName");
            return (Criteria) this;
        }

        public Criteria andSXmNameBetween(String value1, String value2) {
            addCriterion("S_XM_NAME between", value1, value2, "sXmName");
            return (Criteria) this;
        }

        public Criteria andSXmNameNotBetween(String value1, String value2) {
            addCriterion("S_XM_NAME not between", value1, value2, "sXmName");
            return (Criteria) this;
        }

        public Criteria andSXmAreaIsNull() {
            addCriterion("S_XM_AREA is null");
            return (Criteria) this;
        }

        public Criteria andSXmAreaIsNotNull() {
            addCriterion("S_XM_AREA is not null");
            return (Criteria) this;
        }

        public Criteria andSXmAreaEqualTo(String value) {
            addCriterion("S_XM_AREA =", value, "sXmArea");
            return (Criteria) this;
        }

        public Criteria andSXmAreaNotEqualTo(String value) {
            addCriterion("S_XM_AREA <>", value, "sXmArea");
            return (Criteria) this;
        }

        public Criteria andSXmAreaGreaterThan(String value) {
            addCriterion("S_XM_AREA >", value, "sXmArea");
            return (Criteria) this;
        }

        public Criteria andSXmAreaGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_AREA >=", value, "sXmArea");
            return (Criteria) this;
        }

        public Criteria andSXmAreaLessThan(String value) {
            addCriterion("S_XM_AREA <", value, "sXmArea");
            return (Criteria) this;
        }

        public Criteria andSXmAreaLessThanOrEqualTo(String value) {
            addCriterion("S_XM_AREA <=", value, "sXmArea");
            return (Criteria) this;
        }

        public Criteria andSXmAreaLike(String value) {
            addCriterion("S_XM_AREA like", value, "sXmArea");
            return (Criteria) this;
        }

        public Criteria andSXmAreaNotLike(String value) {
            addCriterion("S_XM_AREA not like", value, "sXmArea");
            return (Criteria) this;
        }

        public Criteria andSXmAreaIn(List<String> values) {
            addCriterion("S_XM_AREA in", values, "sXmArea");
            return (Criteria) this;
        }

        public Criteria andSXmAreaNotIn(List<String> values) {
            addCriterion("S_XM_AREA not in", values, "sXmArea");
            return (Criteria) this;
        }

        public Criteria andSXmAreaBetween(String value1, String value2) {
            addCriterion("S_XM_AREA between", value1, value2, "sXmArea");
            return (Criteria) this;
        }

        public Criteria andSXmAreaNotBetween(String value1, String value2) {
            addCriterion("S_XM_AREA not between", value1, value2, "sXmArea");
            return (Criteria) this;
        }

        public Criteria andSXmOrganIsNull() {
            addCriterion("S_XM_ORGAN is null");
            return (Criteria) this;
        }

        public Criteria andSXmOrganIsNotNull() {
            addCriterion("S_XM_ORGAN is not null");
            return (Criteria) this;
        }

        public Criteria andSXmOrganEqualTo(String value) {
            addCriterion("S_XM_ORGAN =", value, "sXmOrgan");
            return (Criteria) this;
        }

        public Criteria andSXmOrganNotEqualTo(String value) {
            addCriterion("S_XM_ORGAN <>", value, "sXmOrgan");
            return (Criteria) this;
        }

        public Criteria andSXmOrganGreaterThan(String value) {
            addCriterion("S_XM_ORGAN >", value, "sXmOrgan");
            return (Criteria) this;
        }

        public Criteria andSXmOrganGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_ORGAN >=", value, "sXmOrgan");
            return (Criteria) this;
        }

        public Criteria andSXmOrganLessThan(String value) {
            addCriterion("S_XM_ORGAN <", value, "sXmOrgan");
            return (Criteria) this;
        }

        public Criteria andSXmOrganLessThanOrEqualTo(String value) {
            addCriterion("S_XM_ORGAN <=", value, "sXmOrgan");
            return (Criteria) this;
        }

        public Criteria andSXmOrganLike(String value) {
            addCriterion("S_XM_ORGAN like", value, "sXmOrgan");
            return (Criteria) this;
        }

        public Criteria andSXmOrganNotLike(String value) {
            addCriterion("S_XM_ORGAN not like", value, "sXmOrgan");
            return (Criteria) this;
        }

        public Criteria andSXmOrganIn(List<String> values) {
            addCriterion("S_XM_ORGAN in", values, "sXmOrgan");
            return (Criteria) this;
        }

        public Criteria andSXmOrganNotIn(List<String> values) {
            addCriterion("S_XM_ORGAN not in", values, "sXmOrgan");
            return (Criteria) this;
        }

        public Criteria andSXmOrganBetween(String value1, String value2) {
            addCriterion("S_XM_ORGAN between", value1, value2, "sXmOrgan");
            return (Criteria) this;
        }

        public Criteria andSXmOrganNotBetween(String value1, String value2) {
            addCriterion("S_XM_ORGAN not between", value1, value2, "sXmOrgan");
            return (Criteria) this;
        }

        public Criteria andSXmOrganNameIsNull() {
            addCriterion("S_XM_ORGAN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSXmOrganNameIsNotNull() {
            addCriterion("S_XM_ORGAN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSXmOrganNameEqualTo(String value) {
            addCriterion("S_XM_ORGAN_NAME =", value, "sXmOrganName");
            return (Criteria) this;
        }

        public Criteria andSXmOrganNameNotEqualTo(String value) {
            addCriterion("S_XM_ORGAN_NAME <>", value, "sXmOrganName");
            return (Criteria) this;
        }

        public Criteria andSXmOrganNameGreaterThan(String value) {
            addCriterion("S_XM_ORGAN_NAME >", value, "sXmOrganName");
            return (Criteria) this;
        }

        public Criteria andSXmOrganNameGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_ORGAN_NAME >=", value, "sXmOrganName");
            return (Criteria) this;
        }

        public Criteria andSXmOrganNameLessThan(String value) {
            addCriterion("S_XM_ORGAN_NAME <", value, "sXmOrganName");
            return (Criteria) this;
        }

        public Criteria andSXmOrganNameLessThanOrEqualTo(String value) {
            addCriterion("S_XM_ORGAN_NAME <=", value, "sXmOrganName");
            return (Criteria) this;
        }

        public Criteria andSXmOrganNameLike(String value) {
            addCriterion("S_XM_ORGAN_NAME like", value, "sXmOrganName");
            return (Criteria) this;
        }

        public Criteria andSXmOrganNameNotLike(String value) {
            addCriterion("S_XM_ORGAN_NAME not like", value, "sXmOrganName");
            return (Criteria) this;
        }

        public Criteria andSXmOrganNameIn(List<String> values) {
            addCriterion("S_XM_ORGAN_NAME in", values, "sXmOrganName");
            return (Criteria) this;
        }

        public Criteria andSXmOrganNameNotIn(List<String> values) {
            addCriterion("S_XM_ORGAN_NAME not in", values, "sXmOrganName");
            return (Criteria) this;
        }

        public Criteria andSXmOrganNameBetween(String value1, String value2) {
            addCriterion("S_XM_ORGAN_NAME between", value1, value2, "sXmOrganName");
            return (Criteria) this;
        }

        public Criteria andSXmOrganNameNotBetween(String value1, String value2) {
            addCriterion("S_XM_ORGAN_NAME not between", value1, value2, "sXmOrganName");
            return (Criteria) this;
        }

        public Criteria andSXmHydmIsNull() {
            addCriterion("S_XM_HYDM is null");
            return (Criteria) this;
        }

        public Criteria andSXmHydmIsNotNull() {
            addCriterion("S_XM_HYDM is not null");
            return (Criteria) this;
        }

        public Criteria andSXmHydmEqualTo(String value) {
            addCriterion("S_XM_HYDM =", value, "sXmHydm");
            return (Criteria) this;
        }

        public Criteria andSXmHydmNotEqualTo(String value) {
            addCriterion("S_XM_HYDM <>", value, "sXmHydm");
            return (Criteria) this;
        }

        public Criteria andSXmHydmGreaterThan(String value) {
            addCriterion("S_XM_HYDM >", value, "sXmHydm");
            return (Criteria) this;
        }

        public Criteria andSXmHydmGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_HYDM >=", value, "sXmHydm");
            return (Criteria) this;
        }

        public Criteria andSXmHydmLessThan(String value) {
            addCriterion("S_XM_HYDM <", value, "sXmHydm");
            return (Criteria) this;
        }

        public Criteria andSXmHydmLessThanOrEqualTo(String value) {
            addCriterion("S_XM_HYDM <=", value, "sXmHydm");
            return (Criteria) this;
        }

        public Criteria andSXmHydmLike(String value) {
            addCriterion("S_XM_HYDM like", value, "sXmHydm");
            return (Criteria) this;
        }

        public Criteria andSXmHydmNotLike(String value) {
            addCriterion("S_XM_HYDM not like", value, "sXmHydm");
            return (Criteria) this;
        }

        public Criteria andSXmHydmIn(List<String> values) {
            addCriterion("S_XM_HYDM in", values, "sXmHydm");
            return (Criteria) this;
        }

        public Criteria andSXmHydmNotIn(List<String> values) {
            addCriterion("S_XM_HYDM not in", values, "sXmHydm");
            return (Criteria) this;
        }

        public Criteria andSXmHydmBetween(String value1, String value2) {
            addCriterion("S_XM_HYDM between", value1, value2, "sXmHydm");
            return (Criteria) this;
        }

        public Criteria andSXmHydmNotBetween(String value1, String value2) {
            addCriterion("S_XM_HYDM not between", value1, value2, "sXmHydm");
            return (Criteria) this;
        }

        public Criteria andSXmCyIsNull() {
            addCriterion("S_XM_CY is null");
            return (Criteria) this;
        }

        public Criteria andSXmCyIsNotNull() {
            addCriterion("S_XM_CY is not null");
            return (Criteria) this;
        }

        public Criteria andSXmCyEqualTo(String value) {
            addCriterion("S_XM_CY =", value, "sXmCy");
            return (Criteria) this;
        }

        public Criteria andSXmCyNotEqualTo(String value) {
            addCriterion("S_XM_CY <>", value, "sXmCy");
            return (Criteria) this;
        }

        public Criteria andSXmCyGreaterThan(String value) {
            addCriterion("S_XM_CY >", value, "sXmCy");
            return (Criteria) this;
        }

        public Criteria andSXmCyGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_CY >=", value, "sXmCy");
            return (Criteria) this;
        }

        public Criteria andSXmCyLessThan(String value) {
            addCriterion("S_XM_CY <", value, "sXmCy");
            return (Criteria) this;
        }

        public Criteria andSXmCyLessThanOrEqualTo(String value) {
            addCriterion("S_XM_CY <=", value, "sXmCy");
            return (Criteria) this;
        }

        public Criteria andSXmCyLike(String value) {
            addCriterion("S_XM_CY like", value, "sXmCy");
            return (Criteria) this;
        }

        public Criteria andSXmCyNotLike(String value) {
            addCriterion("S_XM_CY not like", value, "sXmCy");
            return (Criteria) this;
        }

        public Criteria andSXmCyIn(List<String> values) {
            addCriterion("S_XM_CY in", values, "sXmCy");
            return (Criteria) this;
        }

        public Criteria andSXmCyNotIn(List<String> values) {
            addCriterion("S_XM_CY not in", values, "sXmCy");
            return (Criteria) this;
        }

        public Criteria andSXmCyBetween(String value1, String value2) {
            addCriterion("S_XM_CY between", value1, value2, "sXmCy");
            return (Criteria) this;
        }

        public Criteria andSXmCyNotBetween(String value1, String value2) {
            addCriterion("S_XM_CY not between", value1, value2, "sXmCy");
            return (Criteria) this;
        }

        public Criteria andSXmZybkIsNull() {
            addCriterion("S_XM_ZYBK is null");
            return (Criteria) this;
        }

        public Criteria andSXmZybkIsNotNull() {
            addCriterion("S_XM_ZYBK is not null");
            return (Criteria) this;
        }

        public Criteria andSXmZybkEqualTo(String value) {
            addCriterion("S_XM_ZYBK =", value, "sXmZybk");
            return (Criteria) this;
        }

        public Criteria andSXmZybkNotEqualTo(String value) {
            addCriterion("S_XM_ZYBK <>", value, "sXmZybk");
            return (Criteria) this;
        }

        public Criteria andSXmZybkGreaterThan(String value) {
            addCriterion("S_XM_ZYBK >", value, "sXmZybk");
            return (Criteria) this;
        }

        public Criteria andSXmZybkGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_ZYBK >=", value, "sXmZybk");
            return (Criteria) this;
        }

        public Criteria andSXmZybkLessThan(String value) {
            addCriterion("S_XM_ZYBK <", value, "sXmZybk");
            return (Criteria) this;
        }

        public Criteria andSXmZybkLessThanOrEqualTo(String value) {
            addCriterion("S_XM_ZYBK <=", value, "sXmZybk");
            return (Criteria) this;
        }

        public Criteria andSXmZybkLike(String value) {
            addCriterion("S_XM_ZYBK like", value, "sXmZybk");
            return (Criteria) this;
        }

        public Criteria andSXmZybkNotLike(String value) {
            addCriterion("S_XM_ZYBK not like", value, "sXmZybk");
            return (Criteria) this;
        }

        public Criteria andSXmZybkIn(List<String> values) {
            addCriterion("S_XM_ZYBK in", values, "sXmZybk");
            return (Criteria) this;
        }

        public Criteria andSXmZybkNotIn(List<String> values) {
            addCriterion("S_XM_ZYBK not in", values, "sXmZybk");
            return (Criteria) this;
        }

        public Criteria andSXmZybkBetween(String value1, String value2) {
            addCriterion("S_XM_ZYBK between", value1, value2, "sXmZybk");
            return (Criteria) this;
        }

        public Criteria andSXmZybkNotBetween(String value1, String value2) {
            addCriterion("S_XM_ZYBK not between", value1, value2, "sXmZybk");
            return (Criteria) this;
        }

        public Criteria andSXmTzfsIsNull() {
            addCriterion("S_XM_TZFS is null");
            return (Criteria) this;
        }

        public Criteria andSXmTzfsIsNotNull() {
            addCriterion("S_XM_TZFS is not null");
            return (Criteria) this;
        }

        public Criteria andSXmTzfsEqualTo(String value) {
            addCriterion("S_XM_TZFS =", value, "sXmTzfs");
            return (Criteria) this;
        }

        public Criteria andSXmTzfsNotEqualTo(String value) {
            addCriterion("S_XM_TZFS <>", value, "sXmTzfs");
            return (Criteria) this;
        }

        public Criteria andSXmTzfsGreaterThan(String value) {
            addCriterion("S_XM_TZFS >", value, "sXmTzfs");
            return (Criteria) this;
        }

        public Criteria andSXmTzfsGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_TZFS >=", value, "sXmTzfs");
            return (Criteria) this;
        }

        public Criteria andSXmTzfsLessThan(String value) {
            addCriterion("S_XM_TZFS <", value, "sXmTzfs");
            return (Criteria) this;
        }

        public Criteria andSXmTzfsLessThanOrEqualTo(String value) {
            addCriterion("S_XM_TZFS <=", value, "sXmTzfs");
            return (Criteria) this;
        }

        public Criteria andSXmTzfsLike(String value) {
            addCriterion("S_XM_TZFS like", value, "sXmTzfs");
            return (Criteria) this;
        }

        public Criteria andSXmTzfsNotLike(String value) {
            addCriterion("S_XM_TZFS not like", value, "sXmTzfs");
            return (Criteria) this;
        }

        public Criteria andSXmTzfsIn(List<String> values) {
            addCriterion("S_XM_TZFS in", values, "sXmTzfs");
            return (Criteria) this;
        }

        public Criteria andSXmTzfsNotIn(List<String> values) {
            addCriterion("S_XM_TZFS not in", values, "sXmTzfs");
            return (Criteria) this;
        }

        public Criteria andSXmTzfsBetween(String value1, String value2) {
            addCriterion("S_XM_TZFS between", value1, value2, "sXmTzfs");
            return (Criteria) this;
        }

        public Criteria andSXmTzfsNotBetween(String value1, String value2) {
            addCriterion("S_XM_TZFS not between", value1, value2, "sXmTzfs");
            return (Criteria) this;
        }

        public Criteria andSXmTzjeIsNull() {
            addCriterion("S_XM_TZJE is null");
            return (Criteria) this;
        }

        public Criteria andSXmTzjeIsNotNull() {
            addCriterion("S_XM_TZJE is not null");
            return (Criteria) this;
        }

        public Criteria andSXmTzjeEqualTo(String value) {
            addCriterion("S_XM_TZJE =", value, "sXmTzje");
            return (Criteria) this;
        }

        public Criteria andSXmTzjeNotEqualTo(String value) {
            addCriterion("S_XM_TZJE <>", value, "sXmTzje");
            return (Criteria) this;
        }

        public Criteria andSXmTzjeGreaterThan(String value) {
            addCriterion("S_XM_TZJE >", value, "sXmTzje");
            return (Criteria) this;
        }

        public Criteria andSXmTzjeGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_TZJE >=", value, "sXmTzje");
            return (Criteria) this;
        }

        public Criteria andSXmTzjeLessThan(String value) {
            addCriterion("S_XM_TZJE <", value, "sXmTzje");
            return (Criteria) this;
        }

        public Criteria andSXmTzjeLessThanOrEqualTo(String value) {
            addCriterion("S_XM_TZJE <=", value, "sXmTzje");
            return (Criteria) this;
        }

        public Criteria andSXmTzjeLike(String value) {
            addCriterion("S_XM_TZJE like", value, "sXmTzje");
            return (Criteria) this;
        }

        public Criteria andSXmTzjeNotLike(String value) {
            addCriterion("S_XM_TZJE not like", value, "sXmTzje");
            return (Criteria) this;
        }

        public Criteria andSXmTzjeIn(List<String> values) {
            addCriterion("S_XM_TZJE in", values, "sXmTzje");
            return (Criteria) this;
        }

        public Criteria andSXmTzjeNotIn(List<String> values) {
            addCriterion("S_XM_TZJE not in", values, "sXmTzje");
            return (Criteria) this;
        }

        public Criteria andSXmTzjeBetween(String value1, String value2) {
            addCriterion("S_XM_TZJE between", value1, value2, "sXmTzje");
            return (Criteria) this;
        }

        public Criteria andSXmTzjeNotBetween(String value1, String value2) {
            addCriterion("S_XM_TZJE not between", value1, value2, "sXmTzje");
            return (Criteria) this;
        }

        public Criteria andSXmXssrIsNull() {
            addCriterion("S_XM_XSSR is null");
            return (Criteria) this;
        }

        public Criteria andSXmXssrIsNotNull() {
            addCriterion("S_XM_XSSR is not null");
            return (Criteria) this;
        }

        public Criteria andSXmXssrEqualTo(String value) {
            addCriterion("S_XM_XSSR =", value, "sXmXssr");
            return (Criteria) this;
        }

        public Criteria andSXmXssrNotEqualTo(String value) {
            addCriterion("S_XM_XSSR <>", value, "sXmXssr");
            return (Criteria) this;
        }

        public Criteria andSXmXssrGreaterThan(String value) {
            addCriterion("S_XM_XSSR >", value, "sXmXssr");
            return (Criteria) this;
        }

        public Criteria andSXmXssrGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_XSSR >=", value, "sXmXssr");
            return (Criteria) this;
        }

        public Criteria andSXmXssrLessThan(String value) {
            addCriterion("S_XM_XSSR <", value, "sXmXssr");
            return (Criteria) this;
        }

        public Criteria andSXmXssrLessThanOrEqualTo(String value) {
            addCriterion("S_XM_XSSR <=", value, "sXmXssr");
            return (Criteria) this;
        }

        public Criteria andSXmXssrLike(String value) {
            addCriterion("S_XM_XSSR like", value, "sXmXssr");
            return (Criteria) this;
        }

        public Criteria andSXmXssrNotLike(String value) {
            addCriterion("S_XM_XSSR not like", value, "sXmXssr");
            return (Criteria) this;
        }

        public Criteria andSXmXssrIn(List<String> values) {
            addCriterion("S_XM_XSSR in", values, "sXmXssr");
            return (Criteria) this;
        }

        public Criteria andSXmXssrNotIn(List<String> values) {
            addCriterion("S_XM_XSSR not in", values, "sXmXssr");
            return (Criteria) this;
        }

        public Criteria andSXmXssrBetween(String value1, String value2) {
            addCriterion("S_XM_XSSR between", value1, value2, "sXmXssr");
            return (Criteria) this;
        }

        public Criteria andSXmXssrNotBetween(String value1, String value2) {
            addCriterion("S_XM_XSSR not between", value1, value2, "sXmXssr");
            return (Criteria) this;
        }

        public Criteria andSXmFlagIsNull() {
            addCriterion("S_XM_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andSXmFlagIsNotNull() {
            addCriterion("S_XM_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andSXmFlagEqualTo(String value) {
            addCriterion("S_XM_FLAG =", value, "sXmFlag");
            return (Criteria) this;
        }

        public Criteria andSXmFlagNotEqualTo(String value) {
            addCriterion("S_XM_FLAG <>", value, "sXmFlag");
            return (Criteria) this;
        }

        public Criteria andSXmFlagGreaterThan(String value) {
            addCriterion("S_XM_FLAG >", value, "sXmFlag");
            return (Criteria) this;
        }

        public Criteria andSXmFlagGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_FLAG >=", value, "sXmFlag");
            return (Criteria) this;
        }

        public Criteria andSXmFlagLessThan(String value) {
            addCriterion("S_XM_FLAG <", value, "sXmFlag");
            return (Criteria) this;
        }

        public Criteria andSXmFlagLessThanOrEqualTo(String value) {
            addCriterion("S_XM_FLAG <=", value, "sXmFlag");
            return (Criteria) this;
        }

        public Criteria andSXmFlagLike(String value) {
            addCriterion("S_XM_FLAG like", value, "sXmFlag");
            return (Criteria) this;
        }

        public Criteria andSXmFlagNotLike(String value) {
            addCriterion("S_XM_FLAG not like", value, "sXmFlag");
            return (Criteria) this;
        }

        public Criteria andSXmFlagIn(List<String> values) {
            addCriterion("S_XM_FLAG in", values, "sXmFlag");
            return (Criteria) this;
        }

        public Criteria andSXmFlagNotIn(List<String> values) {
            addCriterion("S_XM_FLAG not in", values, "sXmFlag");
            return (Criteria) this;
        }

        public Criteria andSXmFlagBetween(String value1, String value2) {
            addCriterion("S_XM_FLAG between", value1, value2, "sXmFlag");
            return (Criteria) this;
        }

        public Criteria andSXmFlagNotBetween(String value1, String value2) {
            addCriterion("S_XM_FLAG not between", value1, value2, "sXmFlag");
            return (Criteria) this;
        }

        public Criteria andSXmYqhsqIsNull() {
            addCriterion("S_XM_YQHSQ is null");
            return (Criteria) this;
        }

        public Criteria andSXmYqhsqIsNotNull() {
            addCriterion("S_XM_YQHSQ is not null");
            return (Criteria) this;
        }

        public Criteria andSXmYqhsqEqualTo(String value) {
            addCriterion("S_XM_YQHSQ =", value, "sXmYqhsq");
            return (Criteria) this;
        }

        public Criteria andSXmYqhsqNotEqualTo(String value) {
            addCriterion("S_XM_YQHSQ <>", value, "sXmYqhsq");
            return (Criteria) this;
        }

        public Criteria andSXmYqhsqGreaterThan(String value) {
            addCriterion("S_XM_YQHSQ >", value, "sXmYqhsq");
            return (Criteria) this;
        }

        public Criteria andSXmYqhsqGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_YQHSQ >=", value, "sXmYqhsq");
            return (Criteria) this;
        }

        public Criteria andSXmYqhsqLessThan(String value) {
            addCriterion("S_XM_YQHSQ <", value, "sXmYqhsq");
            return (Criteria) this;
        }

        public Criteria andSXmYqhsqLessThanOrEqualTo(String value) {
            addCriterion("S_XM_YQHSQ <=", value, "sXmYqhsq");
            return (Criteria) this;
        }

        public Criteria andSXmYqhsqLike(String value) {
            addCriterion("S_XM_YQHSQ like", value, "sXmYqhsq");
            return (Criteria) this;
        }

        public Criteria andSXmYqhsqNotLike(String value) {
            addCriterion("S_XM_YQHSQ not like", value, "sXmYqhsq");
            return (Criteria) this;
        }

        public Criteria andSXmYqhsqIn(List<String> values) {
            addCriterion("S_XM_YQHSQ in", values, "sXmYqhsq");
            return (Criteria) this;
        }

        public Criteria andSXmYqhsqNotIn(List<String> values) {
            addCriterion("S_XM_YQHSQ not in", values, "sXmYqhsq");
            return (Criteria) this;
        }

        public Criteria andSXmYqhsqBetween(String value1, String value2) {
            addCriterion("S_XM_YQHSQ between", value1, value2, "sXmYqhsq");
            return (Criteria) this;
        }

        public Criteria andSXmYqhsqNotBetween(String value1, String value2) {
            addCriterion("S_XM_YQHSQ not between", value1, value2, "sXmYqhsq");
            return (Criteria) this;
        }

        public Criteria andSXmLrIsNull() {
            addCriterion("S_XM_LR is null");
            return (Criteria) this;
        }

        public Criteria andSXmLrIsNotNull() {
            addCriterion("S_XM_LR is not null");
            return (Criteria) this;
        }

        public Criteria andSXmLrEqualTo(String value) {
            addCriterion("S_XM_LR =", value, "sXmLr");
            return (Criteria) this;
        }

        public Criteria andSXmLrNotEqualTo(String value) {
            addCriterion("S_XM_LR <>", value, "sXmLr");
            return (Criteria) this;
        }

        public Criteria andSXmLrGreaterThan(String value) {
            addCriterion("S_XM_LR >", value, "sXmLr");
            return (Criteria) this;
        }

        public Criteria andSXmLrGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_LR >=", value, "sXmLr");
            return (Criteria) this;
        }

        public Criteria andSXmLrLessThan(String value) {
            addCriterion("S_XM_LR <", value, "sXmLr");
            return (Criteria) this;
        }

        public Criteria andSXmLrLessThanOrEqualTo(String value) {
            addCriterion("S_XM_LR <=", value, "sXmLr");
            return (Criteria) this;
        }

        public Criteria andSXmLrLike(String value) {
            addCriterion("S_XM_LR like", value, "sXmLr");
            return (Criteria) this;
        }

        public Criteria andSXmLrNotLike(String value) {
            addCriterion("S_XM_LR not like", value, "sXmLr");
            return (Criteria) this;
        }

        public Criteria andSXmLrIn(List<String> values) {
            addCriterion("S_XM_LR in", values, "sXmLr");
            return (Criteria) this;
        }

        public Criteria andSXmLrNotIn(List<String> values) {
            addCriterion("S_XM_LR not in", values, "sXmLr");
            return (Criteria) this;
        }

        public Criteria andSXmLrBetween(String value1, String value2) {
            addCriterion("S_XM_LR between", value1, value2, "sXmLr");
            return (Criteria) this;
        }

        public Criteria andSXmLrNotBetween(String value1, String value2) {
            addCriterion("S_XM_LR not between", value1, value2, "sXmLr");
            return (Criteria) this;
        }

        public Criteria andSXmSsIsNull() {
            addCriterion("S_XM_SS is null");
            return (Criteria) this;
        }

        public Criteria andSXmSsIsNotNull() {
            addCriterion("S_XM_SS is not null");
            return (Criteria) this;
        }

        public Criteria andSXmSsEqualTo(String value) {
            addCriterion("S_XM_SS =", value, "sXmSs");
            return (Criteria) this;
        }

        public Criteria andSXmSsNotEqualTo(String value) {
            addCriterion("S_XM_SS <>", value, "sXmSs");
            return (Criteria) this;
        }

        public Criteria andSXmSsGreaterThan(String value) {
            addCriterion("S_XM_SS >", value, "sXmSs");
            return (Criteria) this;
        }

        public Criteria andSXmSsGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_SS >=", value, "sXmSs");
            return (Criteria) this;
        }

        public Criteria andSXmSsLessThan(String value) {
            addCriterion("S_XM_SS <", value, "sXmSs");
            return (Criteria) this;
        }

        public Criteria andSXmSsLessThanOrEqualTo(String value) {
            addCriterion("S_XM_SS <=", value, "sXmSs");
            return (Criteria) this;
        }

        public Criteria andSXmSsLike(String value) {
            addCriterion("S_XM_SS like", value, "sXmSs");
            return (Criteria) this;
        }

        public Criteria andSXmSsNotLike(String value) {
            addCriterion("S_XM_SS not like", value, "sXmSs");
            return (Criteria) this;
        }

        public Criteria andSXmSsIn(List<String> values) {
            addCriterion("S_XM_SS in", values, "sXmSs");
            return (Criteria) this;
        }

        public Criteria andSXmSsNotIn(List<String> values) {
            addCriterion("S_XM_SS not in", values, "sXmSs");
            return (Criteria) this;
        }

        public Criteria andSXmSsBetween(String value1, String value2) {
            addCriterion("S_XM_SS between", value1, value2, "sXmSs");
            return (Criteria) this;
        }

        public Criteria andSXmSsNotBetween(String value1, String value2) {
            addCriterion("S_XM_SS not between", value1, value2, "sXmSs");
            return (Criteria) this;
        }

        public Criteria andSXmScjdIsNull() {
            addCriterion("S_XM_SCJD is null");
            return (Criteria) this;
        }

        public Criteria andSXmScjdIsNotNull() {
            addCriterion("S_XM_SCJD is not null");
            return (Criteria) this;
        }

        public Criteria andSXmScjdEqualTo(String value) {
            addCriterion("S_XM_SCJD =", value, "sXmScjd");
            return (Criteria) this;
        }

        public Criteria andSXmScjdNotEqualTo(String value) {
            addCriterion("S_XM_SCJD <>", value, "sXmScjd");
            return (Criteria) this;
        }

        public Criteria andSXmScjdGreaterThan(String value) {
            addCriterion("S_XM_SCJD >", value, "sXmScjd");
            return (Criteria) this;
        }

        public Criteria andSXmScjdGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_SCJD >=", value, "sXmScjd");
            return (Criteria) this;
        }

        public Criteria andSXmScjdLessThan(String value) {
            addCriterion("S_XM_SCJD <", value, "sXmScjd");
            return (Criteria) this;
        }

        public Criteria andSXmScjdLessThanOrEqualTo(String value) {
            addCriterion("S_XM_SCJD <=", value, "sXmScjd");
            return (Criteria) this;
        }

        public Criteria andSXmScjdLike(String value) {
            addCriterion("S_XM_SCJD like", value, "sXmScjd");
            return (Criteria) this;
        }

        public Criteria andSXmScjdNotLike(String value) {
            addCriterion("S_XM_SCJD not like", value, "sXmScjd");
            return (Criteria) this;
        }

        public Criteria andSXmScjdIn(List<String> values) {
            addCriterion("S_XM_SCJD in", values, "sXmScjd");
            return (Criteria) this;
        }

        public Criteria andSXmScjdNotIn(List<String> values) {
            addCriterion("S_XM_SCJD not in", values, "sXmScjd");
            return (Criteria) this;
        }

        public Criteria andSXmScjdBetween(String value1, String value2) {
            addCriterion("S_XM_SCJD between", value1, value2, "sXmScjd");
            return (Criteria) this;
        }

        public Criteria andSXmScjdNotBetween(String value1, String value2) {
            addCriterion("S_XM_SCJD not between", value1, value2, "sXmScjd");
            return (Criteria) this;
        }

        public Criteria andSXmKybgIsNull() {
            addCriterion("S_XM_KYBG is null");
            return (Criteria) this;
        }

        public Criteria andSXmKybgIsNotNull() {
            addCriterion("S_XM_KYBG is not null");
            return (Criteria) this;
        }

        public Criteria andSXmKybgEqualTo(String value) {
            addCriterion("S_XM_KYBG =", value, "sXmKybg");
            return (Criteria) this;
        }

        public Criteria andSXmKybgNotEqualTo(String value) {
            addCriterion("S_XM_KYBG <>", value, "sXmKybg");
            return (Criteria) this;
        }

        public Criteria andSXmKybgGreaterThan(String value) {
            addCriterion("S_XM_KYBG >", value, "sXmKybg");
            return (Criteria) this;
        }

        public Criteria andSXmKybgGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_KYBG >=", value, "sXmKybg");
            return (Criteria) this;
        }

        public Criteria andSXmKybgLessThan(String value) {
            addCriterion("S_XM_KYBG <", value, "sXmKybg");
            return (Criteria) this;
        }

        public Criteria andSXmKybgLessThanOrEqualTo(String value) {
            addCriterion("S_XM_KYBG <=", value, "sXmKybg");
            return (Criteria) this;
        }

        public Criteria andSXmKybgLike(String value) {
            addCriterion("S_XM_KYBG like", value, "sXmKybg");
            return (Criteria) this;
        }

        public Criteria andSXmKybgNotLike(String value) {
            addCriterion("S_XM_KYBG not like", value, "sXmKybg");
            return (Criteria) this;
        }

        public Criteria andSXmKybgIn(List<String> values) {
            addCriterion("S_XM_KYBG in", values, "sXmKybg");
            return (Criteria) this;
        }

        public Criteria andSXmKybgNotIn(List<String> values) {
            addCriterion("S_XM_KYBG not in", values, "sXmKybg");
            return (Criteria) this;
        }

        public Criteria andSXmKybgBetween(String value1, String value2) {
            addCriterion("S_XM_KYBG between", value1, value2, "sXmKybg");
            return (Criteria) this;
        }

        public Criteria andSXmKybgNotBetween(String value1, String value2) {
            addCriterion("S_XM_KYBG not between", value1, value2, "sXmKybg");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgnIsNull() {
            addCriterion("S_XM_NZDGN is null");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgnIsNotNull() {
            addCriterion("S_XM_NZDGN is not null");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgnEqualTo(String value) {
            addCriterion("S_XM_NZDGN =", value, "sXmNzdgn");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgnNotEqualTo(String value) {
            addCriterion("S_XM_NZDGN <>", value, "sXmNzdgn");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgnGreaterThan(String value) {
            addCriterion("S_XM_NZDGN >", value, "sXmNzdgn");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgnGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_NZDGN >=", value, "sXmNzdgn");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgnLessThan(String value) {
            addCriterion("S_XM_NZDGN <", value, "sXmNzdgn");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgnLessThanOrEqualTo(String value) {
            addCriterion("S_XM_NZDGN <=", value, "sXmNzdgn");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgnLike(String value) {
            addCriterion("S_XM_NZDGN like", value, "sXmNzdgn");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgnNotLike(String value) {
            addCriterion("S_XM_NZDGN not like", value, "sXmNzdgn");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgnIn(List<String> values) {
            addCriterion("S_XM_NZDGN in", values, "sXmNzdgn");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgnNotIn(List<String> values) {
            addCriterion("S_XM_NZDGN not in", values, "sXmNzdgn");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgnBetween(String value1, String value2) {
            addCriterion("S_XM_NZDGN between", value1, value2, "sXmNzdgn");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgnNotBetween(String value1, String value2) {
            addCriterion("S_XM_NZDGN not between", value1, value2, "sXmNzdgn");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgwIsNull() {
            addCriterion("S_XM_NZDGW is null");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgwIsNotNull() {
            addCriterion("S_XM_NZDGW is not null");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgwEqualTo(String value) {
            addCriterion("S_XM_NZDGW =", value, "sXmNzdgw");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgwNotEqualTo(String value) {
            addCriterion("S_XM_NZDGW <>", value, "sXmNzdgw");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgwGreaterThan(String value) {
            addCriterion("S_XM_NZDGW >", value, "sXmNzdgw");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgwGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_NZDGW >=", value, "sXmNzdgw");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgwLessThan(String value) {
            addCriterion("S_XM_NZDGW <", value, "sXmNzdgw");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgwLessThanOrEqualTo(String value) {
            addCriterion("S_XM_NZDGW <=", value, "sXmNzdgw");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgwLike(String value) {
            addCriterion("S_XM_NZDGW like", value, "sXmNzdgw");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgwNotLike(String value) {
            addCriterion("S_XM_NZDGW not like", value, "sXmNzdgw");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgwIn(List<String> values) {
            addCriterion("S_XM_NZDGW in", values, "sXmNzdgw");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgwNotIn(List<String> values) {
            addCriterion("S_XM_NZDGW not in", values, "sXmNzdgw");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgwBetween(String value1, String value2) {
            addCriterion("S_XM_NZDGW between", value1, value2, "sXmNzdgw");
            return (Criteria) this;
        }

        public Criteria andSXmNzdgwNotBetween(String value1, String value2) {
            addCriterion("S_XM_NZDGW not between", value1, value2, "sXmNzdgw");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwNameIsNull() {
            addCriterion("S_XM_ZRDW_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwNameIsNotNull() {
            addCriterion("S_XM_ZRDW_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwNameEqualTo(String value) {
            addCriterion("S_XM_ZRDW_NAME =", value, "sXmZrdwName");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwNameNotEqualTo(String value) {
            addCriterion("S_XM_ZRDW_NAME <>", value, "sXmZrdwName");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwNameGreaterThan(String value) {
            addCriterion("S_XM_ZRDW_NAME >", value, "sXmZrdwName");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwNameGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_ZRDW_NAME >=", value, "sXmZrdwName");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwNameLessThan(String value) {
            addCriterion("S_XM_ZRDW_NAME <", value, "sXmZrdwName");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwNameLessThanOrEqualTo(String value) {
            addCriterion("S_XM_ZRDW_NAME <=", value, "sXmZrdwName");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwNameLike(String value) {
            addCriterion("S_XM_ZRDW_NAME like", value, "sXmZrdwName");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwNameNotLike(String value) {
            addCriterion("S_XM_ZRDW_NAME not like", value, "sXmZrdwName");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwNameIn(List<String> values) {
            addCriterion("S_XM_ZRDW_NAME in", values, "sXmZrdwName");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwNameNotIn(List<String> values) {
            addCriterion("S_XM_ZRDW_NAME not in", values, "sXmZrdwName");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwNameBetween(String value1, String value2) {
            addCriterion("S_XM_ZRDW_NAME between", value1, value2, "sXmZrdwName");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwNameNotBetween(String value1, String value2) {
            addCriterion("S_XM_ZRDW_NAME not between", value1, value2, "sXmZrdwName");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxrIsNull() {
            addCriterion("S_XM_ZRDW_LXR is null");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxrIsNotNull() {
            addCriterion("S_XM_ZRDW_LXR is not null");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxrEqualTo(String value) {
            addCriterion("S_XM_ZRDW_LXR =", value, "sXmZrdwLxr");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxrNotEqualTo(String value) {
            addCriterion("S_XM_ZRDW_LXR <>", value, "sXmZrdwLxr");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxrGreaterThan(String value) {
            addCriterion("S_XM_ZRDW_LXR >", value, "sXmZrdwLxr");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxrGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_ZRDW_LXR >=", value, "sXmZrdwLxr");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxrLessThan(String value) {
            addCriterion("S_XM_ZRDW_LXR <", value, "sXmZrdwLxr");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxrLessThanOrEqualTo(String value) {
            addCriterion("S_XM_ZRDW_LXR <=", value, "sXmZrdwLxr");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxrLike(String value) {
            addCriterion("S_XM_ZRDW_LXR like", value, "sXmZrdwLxr");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxrNotLike(String value) {
            addCriterion("S_XM_ZRDW_LXR not like", value, "sXmZrdwLxr");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxrIn(List<String> values) {
            addCriterion("S_XM_ZRDW_LXR in", values, "sXmZrdwLxr");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxrNotIn(List<String> values) {
            addCriterion("S_XM_ZRDW_LXR not in", values, "sXmZrdwLxr");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxrBetween(String value1, String value2) {
            addCriterion("S_XM_ZRDW_LXR between", value1, value2, "sXmZrdwLxr");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxrNotBetween(String value1, String value2) {
            addCriterion("S_XM_ZRDW_LXR not between", value1, value2, "sXmZrdwLxr");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxdhIsNull() {
            addCriterion("S_XM_ZRDW_LXDH is null");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxdhIsNotNull() {
            addCriterion("S_XM_ZRDW_LXDH is not null");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxdhEqualTo(String value) {
            addCriterion("S_XM_ZRDW_LXDH =", value, "sXmZrdwLxdh");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxdhNotEqualTo(String value) {
            addCriterion("S_XM_ZRDW_LXDH <>", value, "sXmZrdwLxdh");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxdhGreaterThan(String value) {
            addCriterion("S_XM_ZRDW_LXDH >", value, "sXmZrdwLxdh");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxdhGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_ZRDW_LXDH >=", value, "sXmZrdwLxdh");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxdhLessThan(String value) {
            addCriterion("S_XM_ZRDW_LXDH <", value, "sXmZrdwLxdh");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxdhLessThanOrEqualTo(String value) {
            addCriterion("S_XM_ZRDW_LXDH <=", value, "sXmZrdwLxdh");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxdhLike(String value) {
            addCriterion("S_XM_ZRDW_LXDH like", value, "sXmZrdwLxdh");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxdhNotLike(String value) {
            addCriterion("S_XM_ZRDW_LXDH not like", value, "sXmZrdwLxdh");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxdhIn(List<String> values) {
            addCriterion("S_XM_ZRDW_LXDH in", values, "sXmZrdwLxdh");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxdhNotIn(List<String> values) {
            addCriterion("S_XM_ZRDW_LXDH not in", values, "sXmZrdwLxdh");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxdhBetween(String value1, String value2) {
            addCriterion("S_XM_ZRDW_LXDH between", value1, value2, "sXmZrdwLxdh");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwLxdhNotBetween(String value1, String value2) {
            addCriterion("S_XM_ZRDW_LXDH not between", value1, value2, "sXmZrdwLxdh");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwEmailIsNull() {
            addCriterion("S_XM_ZRDW_EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwEmailIsNotNull() {
            addCriterion("S_XM_ZRDW_EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwEmailEqualTo(String value) {
            addCriterion("S_XM_ZRDW_EMAIL =", value, "sXmZrdwEmail");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwEmailNotEqualTo(String value) {
            addCriterion("S_XM_ZRDW_EMAIL <>", value, "sXmZrdwEmail");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwEmailGreaterThan(String value) {
            addCriterion("S_XM_ZRDW_EMAIL >", value, "sXmZrdwEmail");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwEmailGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_ZRDW_EMAIL >=", value, "sXmZrdwEmail");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwEmailLessThan(String value) {
            addCriterion("S_XM_ZRDW_EMAIL <", value, "sXmZrdwEmail");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwEmailLessThanOrEqualTo(String value) {
            addCriterion("S_XM_ZRDW_EMAIL <=", value, "sXmZrdwEmail");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwEmailLike(String value) {
            addCriterion("S_XM_ZRDW_EMAIL like", value, "sXmZrdwEmail");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwEmailNotLike(String value) {
            addCriterion("S_XM_ZRDW_EMAIL not like", value, "sXmZrdwEmail");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwEmailIn(List<String> values) {
            addCriterion("S_XM_ZRDW_EMAIL in", values, "sXmZrdwEmail");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwEmailNotIn(List<String> values) {
            addCriterion("S_XM_ZRDW_EMAIL not in", values, "sXmZrdwEmail");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwEmailBetween(String value1, String value2) {
            addCriterion("S_XM_ZRDW_EMAIL between", value1, value2, "sXmZrdwEmail");
            return (Criteria) this;
        }

        public Criteria andSXmZrdwEmailNotBetween(String value1, String value2) {
            addCriterion("S_XM_ZRDW_EMAIL not between", value1, value2, "sXmZrdwEmail");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwNameIsNull() {
            addCriterion("S_XM_TBDW_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwNameIsNotNull() {
            addCriterion("S_XM_TBDW_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwNameEqualTo(String value) {
            addCriterion("S_XM_TBDW_NAME =", value, "sXmTbdwName");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwNameNotEqualTo(String value) {
            addCriterion("S_XM_TBDW_NAME <>", value, "sXmTbdwName");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwNameGreaterThan(String value) {
            addCriterion("S_XM_TBDW_NAME >", value, "sXmTbdwName");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwNameGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_TBDW_NAME >=", value, "sXmTbdwName");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwNameLessThan(String value) {
            addCriterion("S_XM_TBDW_NAME <", value, "sXmTbdwName");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwNameLessThanOrEqualTo(String value) {
            addCriterion("S_XM_TBDW_NAME <=", value, "sXmTbdwName");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwNameLike(String value) {
            addCriterion("S_XM_TBDW_NAME like", value, "sXmTbdwName");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwNameNotLike(String value) {
            addCriterion("S_XM_TBDW_NAME not like", value, "sXmTbdwName");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwNameIn(List<String> values) {
            addCriterion("S_XM_TBDW_NAME in", values, "sXmTbdwName");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwNameNotIn(List<String> values) {
            addCriterion("S_XM_TBDW_NAME not in", values, "sXmTbdwName");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwNameBetween(String value1, String value2) {
            addCriterion("S_XM_TBDW_NAME between", value1, value2, "sXmTbdwName");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwNameNotBetween(String value1, String value2) {
            addCriterion("S_XM_TBDW_NAME not between", value1, value2, "sXmTbdwName");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxrIsNull() {
            addCriterion("S_XM_TBDW_LXR is null");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxrIsNotNull() {
            addCriterion("S_XM_TBDW_LXR is not null");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxrEqualTo(String value) {
            addCriterion("S_XM_TBDW_LXR =", value, "sXmTbdwLxr");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxrNotEqualTo(String value) {
            addCriterion("S_XM_TBDW_LXR <>", value, "sXmTbdwLxr");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxrGreaterThan(String value) {
            addCriterion("S_XM_TBDW_LXR >", value, "sXmTbdwLxr");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxrGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_TBDW_LXR >=", value, "sXmTbdwLxr");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxrLessThan(String value) {
            addCriterion("S_XM_TBDW_LXR <", value, "sXmTbdwLxr");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxrLessThanOrEqualTo(String value) {
            addCriterion("S_XM_TBDW_LXR <=", value, "sXmTbdwLxr");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxrLike(String value) {
            addCriterion("S_XM_TBDW_LXR like", value, "sXmTbdwLxr");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxrNotLike(String value) {
            addCriterion("S_XM_TBDW_LXR not like", value, "sXmTbdwLxr");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxrIn(List<String> values) {
            addCriterion("S_XM_TBDW_LXR in", values, "sXmTbdwLxr");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxrNotIn(List<String> values) {
            addCriterion("S_XM_TBDW_LXR not in", values, "sXmTbdwLxr");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxrBetween(String value1, String value2) {
            addCriterion("S_XM_TBDW_LXR between", value1, value2, "sXmTbdwLxr");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxrNotBetween(String value1, String value2) {
            addCriterion("S_XM_TBDW_LXR not between", value1, value2, "sXmTbdwLxr");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxdhIsNull() {
            addCriterion("S_XM_TBDW_LXDH is null");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxdhIsNotNull() {
            addCriterion("S_XM_TBDW_LXDH is not null");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxdhEqualTo(String value) {
            addCriterion("S_XM_TBDW_LXDH =", value, "sXmTbdwLxdh");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxdhNotEqualTo(String value) {
            addCriterion("S_XM_TBDW_LXDH <>", value, "sXmTbdwLxdh");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxdhGreaterThan(String value) {
            addCriterion("S_XM_TBDW_LXDH >", value, "sXmTbdwLxdh");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxdhGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_TBDW_LXDH >=", value, "sXmTbdwLxdh");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxdhLessThan(String value) {
            addCriterion("S_XM_TBDW_LXDH <", value, "sXmTbdwLxdh");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxdhLessThanOrEqualTo(String value) {
            addCriterion("S_XM_TBDW_LXDH <=", value, "sXmTbdwLxdh");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxdhLike(String value) {
            addCriterion("S_XM_TBDW_LXDH like", value, "sXmTbdwLxdh");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxdhNotLike(String value) {
            addCriterion("S_XM_TBDW_LXDH not like", value, "sXmTbdwLxdh");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxdhIn(List<String> values) {
            addCriterion("S_XM_TBDW_LXDH in", values, "sXmTbdwLxdh");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxdhNotIn(List<String> values) {
            addCriterion("S_XM_TBDW_LXDH not in", values, "sXmTbdwLxdh");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxdhBetween(String value1, String value2) {
            addCriterion("S_XM_TBDW_LXDH between", value1, value2, "sXmTbdwLxdh");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwLxdhNotBetween(String value1, String value2) {
            addCriterion("S_XM_TBDW_LXDH not between", value1, value2, "sXmTbdwLxdh");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwEmailIsNull() {
            addCriterion("S_XM_TBDW_EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwEmailIsNotNull() {
            addCriterion("S_XM_TBDW_EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwEmailEqualTo(String value) {
            addCriterion("S_XM_TBDW_EMAIL =", value, "sXmTbdwEmail");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwEmailNotEqualTo(String value) {
            addCriterion("S_XM_TBDW_EMAIL <>", value, "sXmTbdwEmail");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwEmailGreaterThan(String value) {
            addCriterion("S_XM_TBDW_EMAIL >", value, "sXmTbdwEmail");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwEmailGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_TBDW_EMAIL >=", value, "sXmTbdwEmail");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwEmailLessThan(String value) {
            addCriterion("S_XM_TBDW_EMAIL <", value, "sXmTbdwEmail");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwEmailLessThanOrEqualTo(String value) {
            addCriterion("S_XM_TBDW_EMAIL <=", value, "sXmTbdwEmail");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwEmailLike(String value) {
            addCriterion("S_XM_TBDW_EMAIL like", value, "sXmTbdwEmail");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwEmailNotLike(String value) {
            addCriterion("S_XM_TBDW_EMAIL not like", value, "sXmTbdwEmail");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwEmailIn(List<String> values) {
            addCriterion("S_XM_TBDW_EMAIL in", values, "sXmTbdwEmail");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwEmailNotIn(List<String> values) {
            addCriterion("S_XM_TBDW_EMAIL not in", values, "sXmTbdwEmail");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwEmailBetween(String value1, String value2) {
            addCriterion("S_XM_TBDW_EMAIL between", value1, value2, "sXmTbdwEmail");
            return (Criteria) this;
        }

        public Criteria andSXmTbdwEmailNotBetween(String value1, String value2) {
            addCriterion("S_XM_TBDW_EMAIL not between", value1, value2, "sXmTbdwEmail");
            return (Criteria) this;
        }

        public Criteria andSXmJyrsIsNull() {
            addCriterion("S_XM_JYRS is null");
            return (Criteria) this;
        }

        public Criteria andSXmJyrsIsNotNull() {
            addCriterion("S_XM_JYRS is not null");
            return (Criteria) this;
        }

        public Criteria andSXmJyrsEqualTo(String value) {
            addCriterion("S_XM_JYRS =", value, "sXmJyrs");
            return (Criteria) this;
        }

        public Criteria andSXmJyrsNotEqualTo(String value) {
            addCriterion("S_XM_JYRS <>", value, "sXmJyrs");
            return (Criteria) this;
        }

        public Criteria andSXmJyrsGreaterThan(String value) {
            addCriterion("S_XM_JYRS >", value, "sXmJyrs");
            return (Criteria) this;
        }

        public Criteria andSXmJyrsGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_JYRS >=", value, "sXmJyrs");
            return (Criteria) this;
        }

        public Criteria andSXmJyrsLessThan(String value) {
            addCriterion("S_XM_JYRS <", value, "sXmJyrs");
            return (Criteria) this;
        }

        public Criteria andSXmJyrsLessThanOrEqualTo(String value) {
            addCriterion("S_XM_JYRS <=", value, "sXmJyrs");
            return (Criteria) this;
        }

        public Criteria andSXmJyrsLike(String value) {
            addCriterion("S_XM_JYRS like", value, "sXmJyrs");
            return (Criteria) this;
        }

        public Criteria andSXmJyrsNotLike(String value) {
            addCriterion("S_XM_JYRS not like", value, "sXmJyrs");
            return (Criteria) this;
        }

        public Criteria andSXmJyrsIn(List<String> values) {
            addCriterion("S_XM_JYRS in", values, "sXmJyrs");
            return (Criteria) this;
        }

        public Criteria andSXmJyrsNotIn(List<String> values) {
            addCriterion("S_XM_JYRS not in", values, "sXmJyrs");
            return (Criteria) this;
        }

        public Criteria andSXmJyrsBetween(String value1, String value2) {
            addCriterion("S_XM_JYRS between", value1, value2, "sXmJyrs");
            return (Criteria) this;
        }

        public Criteria andSXmJyrsNotBetween(String value1, String value2) {
            addCriterion("S_XM_JYRS not between", value1, value2, "sXmJyrs");
            return (Criteria) this;
        }

        public Criteria andSXmZdcyIsNull() {
            addCriterion("S_XM_ZDCY is null");
            return (Criteria) this;
        }

        public Criteria andSXmZdcyIsNotNull() {
            addCriterion("S_XM_ZDCY is not null");
            return (Criteria) this;
        }

        public Criteria andSXmZdcyEqualTo(String value) {
            addCriterion("S_XM_ZDCY =", value, "sXmZdcy");
            return (Criteria) this;
        }

        public Criteria andSXmZdcyNotEqualTo(String value) {
            addCriterion("S_XM_ZDCY <>", value, "sXmZdcy");
            return (Criteria) this;
        }

        public Criteria andSXmZdcyGreaterThan(String value) {
            addCriterion("S_XM_ZDCY >", value, "sXmZdcy");
            return (Criteria) this;
        }

        public Criteria andSXmZdcyGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_ZDCY >=", value, "sXmZdcy");
            return (Criteria) this;
        }

        public Criteria andSXmZdcyLessThan(String value) {
            addCriterion("S_XM_ZDCY <", value, "sXmZdcy");
            return (Criteria) this;
        }

        public Criteria andSXmZdcyLessThanOrEqualTo(String value) {
            addCriterion("S_XM_ZDCY <=", value, "sXmZdcy");
            return (Criteria) this;
        }

        public Criteria andSXmZdcyLike(String value) {
            addCriterion("S_XM_ZDCY like", value, "sXmZdcy");
            return (Criteria) this;
        }

        public Criteria andSXmZdcyNotLike(String value) {
            addCriterion("S_XM_ZDCY not like", value, "sXmZdcy");
            return (Criteria) this;
        }

        public Criteria andSXmZdcyIn(List<String> values) {
            addCriterion("S_XM_ZDCY in", values, "sXmZdcy");
            return (Criteria) this;
        }

        public Criteria andSXmZdcyNotIn(List<String> values) {
            addCriterion("S_XM_ZDCY not in", values, "sXmZdcy");
            return (Criteria) this;
        }

        public Criteria andSXmZdcyBetween(String value1, String value2) {
            addCriterion("S_XM_ZDCY between", value1, value2, "sXmZdcy");
            return (Criteria) this;
        }

        public Criteria andSXmZdcyNotBetween(String value1, String value2) {
            addCriterion("S_XM_ZDCY not between", value1, value2, "sXmZdcy");
            return (Criteria) this;
        }

        public Criteria andSXmZdcylNameIsNull() {
            addCriterion("S_XM_ZDCYL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSXmZdcylNameIsNotNull() {
            addCriterion("S_XM_ZDCYL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSXmZdcylNameEqualTo(String value) {
            addCriterion("S_XM_ZDCYL_NAME =", value, "sXmZdcylName");
            return (Criteria) this;
        }

        public Criteria andSXmZdcylNameNotEqualTo(String value) {
            addCriterion("S_XM_ZDCYL_NAME <>", value, "sXmZdcylName");
            return (Criteria) this;
        }

        public Criteria andSXmZdcylNameGreaterThan(String value) {
            addCriterion("S_XM_ZDCYL_NAME >", value, "sXmZdcylName");
            return (Criteria) this;
        }

        public Criteria andSXmZdcylNameGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_ZDCYL_NAME >=", value, "sXmZdcylName");
            return (Criteria) this;
        }

        public Criteria andSXmZdcylNameLessThan(String value) {
            addCriterion("S_XM_ZDCYL_NAME <", value, "sXmZdcylName");
            return (Criteria) this;
        }

        public Criteria andSXmZdcylNameLessThanOrEqualTo(String value) {
            addCriterion("S_XM_ZDCYL_NAME <=", value, "sXmZdcylName");
            return (Criteria) this;
        }

        public Criteria andSXmZdcylNameLike(String value) {
            addCriterion("S_XM_ZDCYL_NAME like", value, "sXmZdcylName");
            return (Criteria) this;
        }

        public Criteria andSXmZdcylNameNotLike(String value) {
            addCriterion("S_XM_ZDCYL_NAME not like", value, "sXmZdcylName");
            return (Criteria) this;
        }

        public Criteria andSXmZdcylNameIn(List<String> values) {
            addCriterion("S_XM_ZDCYL_NAME in", values, "sXmZdcylName");
            return (Criteria) this;
        }

        public Criteria andSXmZdcylNameNotIn(List<String> values) {
            addCriterion("S_XM_ZDCYL_NAME not in", values, "sXmZdcylName");
            return (Criteria) this;
        }

        public Criteria andSXmZdcylNameBetween(String value1, String value2) {
            addCriterion("S_XM_ZDCYL_NAME between", value1, value2, "sXmZdcylName");
            return (Criteria) this;
        }

        public Criteria andSXmZdcylNameNotBetween(String value1, String value2) {
            addCriterion("S_XM_ZDCYL_NAME not between", value1, value2, "sXmZdcylName");
            return (Criteria) this;
        }

        public Criteria andSXmYqIsNull() {
            addCriterion("S_XM_YQ is null");
            return (Criteria) this;
        }

        public Criteria andSXmYqIsNotNull() {
            addCriterion("S_XM_YQ is not null");
            return (Criteria) this;
        }

        public Criteria andSXmYqEqualTo(String value) {
            addCriterion("S_XM_YQ =", value, "sXmYq");
            return (Criteria) this;
        }

        public Criteria andSXmYqNotEqualTo(String value) {
            addCriterion("S_XM_YQ <>", value, "sXmYq");
            return (Criteria) this;
        }

        public Criteria andSXmYqGreaterThan(String value) {
            addCriterion("S_XM_YQ >", value, "sXmYq");
            return (Criteria) this;
        }

        public Criteria andSXmYqGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_YQ >=", value, "sXmYq");
            return (Criteria) this;
        }

        public Criteria andSXmYqLessThan(String value) {
            addCriterion("S_XM_YQ <", value, "sXmYq");
            return (Criteria) this;
        }

        public Criteria andSXmYqLessThanOrEqualTo(String value) {
            addCriterion("S_XM_YQ <=", value, "sXmYq");
            return (Criteria) this;
        }

        public Criteria andSXmYqLike(String value) {
            addCriterion("S_XM_YQ like", value, "sXmYq");
            return (Criteria) this;
        }

        public Criteria andSXmYqNotLike(String value) {
            addCriterion("S_XM_YQ not like", value, "sXmYq");
            return (Criteria) this;
        }

        public Criteria andSXmYqIn(List<String> values) {
            addCriterion("S_XM_YQ in", values, "sXmYq");
            return (Criteria) this;
        }

        public Criteria andSXmYqNotIn(List<String> values) {
            addCriterion("S_XM_YQ not in", values, "sXmYq");
            return (Criteria) this;
        }

        public Criteria andSXmYqBetween(String value1, String value2) {
            addCriterion("S_XM_YQ between", value1, value2, "sXmYq");
            return (Criteria) this;
        }

        public Criteria andSXmYqNotBetween(String value1, String value2) {
            addCriterion("S_XM_YQ not between", value1, value2, "sXmYq");
            return (Criteria) this;
        }

        public Criteria andSXmLllIsNull() {
            addCriterion("S_XM_LLL is null");
            return (Criteria) this;
        }

        public Criteria andSXmLllIsNotNull() {
            addCriterion("S_XM_LLL is not null");
            return (Criteria) this;
        }

        public Criteria andSXmLllEqualTo(String value) {
            addCriterion("S_XM_LLL =", value, "sXmLll");
            return (Criteria) this;
        }

        public Criteria andSXmLllNotEqualTo(String value) {
            addCriterion("S_XM_LLL <>", value, "sXmLll");
            return (Criteria) this;
        }

        public Criteria andSXmLllGreaterThan(String value) {
            addCriterion("S_XM_LLL >", value, "sXmLll");
            return (Criteria) this;
        }

        public Criteria andSXmLllGreaterThanOrEqualTo(String value) {
            addCriterion("S_XM_LLL >=", value, "sXmLll");
            return (Criteria) this;
        }

        public Criteria andSXmLllLessThan(String value) {
            addCriterion("S_XM_LLL <", value, "sXmLll");
            return (Criteria) this;
        }

        public Criteria andSXmLllLessThanOrEqualTo(String value) {
            addCriterion("S_XM_LLL <=", value, "sXmLll");
            return (Criteria) this;
        }

        public Criteria andSXmLllLike(String value) {
            addCriterion("S_XM_LLL like", value, "sXmLll");
            return (Criteria) this;
        }

        public Criteria andSXmLllNotLike(String value) {
            addCriterion("S_XM_LLL not like", value, "sXmLll");
            return (Criteria) this;
        }

        public Criteria andSXmLllIn(List<String> values) {
            addCriterion("S_XM_LLL in", values, "sXmLll");
            return (Criteria) this;
        }

        public Criteria andSXmLllNotIn(List<String> values) {
            addCriterion("S_XM_LLL not in", values, "sXmLll");
            return (Criteria) this;
        }

        public Criteria andSXmLllBetween(String value1, String value2) {
            addCriterion("S_XM_LLL between", value1, value2, "sXmLll");
            return (Criteria) this;
        }

        public Criteria andSXmLllNotBetween(String value1, String value2) {
            addCriterion("S_XM_LLL not between", value1, value2, "sXmLll");
            return (Criteria) this;
        }

        public Criteria andSPublishTimeIsNull() {
            addCriterion("S_PUBLISH_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSPublishTimeIsNotNull() {
            addCriterion("S_PUBLISH_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSPublishTimeEqualTo(Date value) {
            addCriterion("S_PUBLISH_TIME =", value, "sPublishTime");
            return (Criteria) this;
        }

        public Criteria andSPublishTimeNotEqualTo(Date value) {
            addCriterion("S_PUBLISH_TIME <>", value, "sPublishTime");
            return (Criteria) this;
        }

        public Criteria andSPublishTimeGreaterThan(Date value) {
            addCriterion("S_PUBLISH_TIME >", value, "sPublishTime");
            return (Criteria) this;
        }

        public Criteria andSPublishTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("S_PUBLISH_TIME >=", value, "sPublishTime");
            return (Criteria) this;
        }

        public Criteria andSPublishTimeLessThan(Date value) {
            addCriterion("S_PUBLISH_TIME <", value, "sPublishTime");
            return (Criteria) this;
        }

        public Criteria andSPublishTimeLessThanOrEqualTo(Date value) {
            addCriterion("S_PUBLISH_TIME <=", value, "sPublishTime");
            return (Criteria) this;
        }

        public Criteria andSPublishTimeIn(List<Date> values) {
            addCriterion("S_PUBLISH_TIME in", values, "sPublishTime");
            return (Criteria) this;
        }

        public Criteria andSPublishTimeNotIn(List<Date> values) {
            addCriterion("S_PUBLISH_TIME not in", values, "sPublishTime");
            return (Criteria) this;
        }

        public Criteria andSPublishTimeBetween(Date value1, Date value2) {
            addCriterion("S_PUBLISH_TIME between", value1, value2, "sPublishTime");
            return (Criteria) this;
        }

        public Criteria andSPublishTimeNotBetween(Date value1, Date value2) {
            addCriterion("S_PUBLISH_TIME not between", value1, value2, "sPublishTime");
            return (Criteria) this;
        }

        public Criteria andSFileUrlIsNull() {
            addCriterion("S_FILE_URL is null");
            return (Criteria) this;
        }

        public Criteria andSFileUrlIsNotNull() {
            addCriterion("S_FILE_URL is not null");
            return (Criteria) this;
        }

        public Criteria andSFileUrlEqualTo(String value) {
            addCriterion("S_FILE_URL =", value, "sFileUrl");
            return (Criteria) this;
        }

        public Criteria andSFileUrlNotEqualTo(String value) {
            addCriterion("S_FILE_URL <>", value, "sFileUrl");
            return (Criteria) this;
        }

        public Criteria andSFileUrlGreaterThan(String value) {
            addCriterion("S_FILE_URL >", value, "sFileUrl");
            return (Criteria) this;
        }

        public Criteria andSFileUrlGreaterThanOrEqualTo(String value) {
            addCriterion("S_FILE_URL >=", value, "sFileUrl");
            return (Criteria) this;
        }

        public Criteria andSFileUrlLessThan(String value) {
            addCriterion("S_FILE_URL <", value, "sFileUrl");
            return (Criteria) this;
        }

        public Criteria andSFileUrlLessThanOrEqualTo(String value) {
            addCriterion("S_FILE_URL <=", value, "sFileUrl");
            return (Criteria) this;
        }

        public Criteria andSFileUrlLike(String value) {
            addCriterion("S_FILE_URL like", value, "sFileUrl");
            return (Criteria) this;
        }

        public Criteria andSFileUrlNotLike(String value) {
            addCriterion("S_FILE_URL not like", value, "sFileUrl");
            return (Criteria) this;
        }

        public Criteria andSFileUrlIn(List<String> values) {
            addCriterion("S_FILE_URL in", values, "sFileUrl");
            return (Criteria) this;
        }

        public Criteria andSFileUrlNotIn(List<String> values) {
            addCriterion("S_FILE_URL not in", values, "sFileUrl");
            return (Criteria) this;
        }

        public Criteria andSFileUrlBetween(String value1, String value2) {
            addCriterion("S_FILE_URL between", value1, value2, "sFileUrl");
            return (Criteria) this;
        }

        public Criteria andSFileUrlNotBetween(String value1, String value2) {
            addCriterion("S_FILE_URL not between", value1, value2, "sFileUrl");
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