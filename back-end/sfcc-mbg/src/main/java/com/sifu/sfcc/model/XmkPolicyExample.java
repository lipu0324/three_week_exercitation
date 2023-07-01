package com.sifu.sfcc.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XmkPolicyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public XmkPolicyExample() {
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

        public Criteria andSYszyIsNull() {
            addCriterion("S_YSZY is null");
            return (Criteria) this;
        }

        public Criteria andSYszyIsNotNull() {
            addCriterion("S_YSZY is not null");
            return (Criteria) this;
        }

        public Criteria andSYszyEqualTo(String value) {
            addCriterion("S_YSZY =", value, "sYszy");
            return (Criteria) this;
        }

        public Criteria andSYszyNotEqualTo(String value) {
            addCriterion("S_YSZY <>", value, "sYszy");
            return (Criteria) this;
        }

        public Criteria andSYszyGreaterThan(String value) {
            addCriterion("S_YSZY >", value, "sYszy");
            return (Criteria) this;
        }

        public Criteria andSYszyGreaterThanOrEqualTo(String value) {
            addCriterion("S_YSZY >=", value, "sYszy");
            return (Criteria) this;
        }

        public Criteria andSYszyLessThan(String value) {
            addCriterion("S_YSZY <", value, "sYszy");
            return (Criteria) this;
        }

        public Criteria andSYszyLessThanOrEqualTo(String value) {
            addCriterion("S_YSZY <=", value, "sYszy");
            return (Criteria) this;
        }

        public Criteria andSYszyLike(String value) {
            addCriterion("S_YSZY like", value, "sYszy");
            return (Criteria) this;
        }

        public Criteria andSYszyNotLike(String value) {
            addCriterion("S_YSZY not like", value, "sYszy");
            return (Criteria) this;
        }

        public Criteria andSYszyIn(List<String> values) {
            addCriterion("S_YSZY in", values, "sYszy");
            return (Criteria) this;
        }

        public Criteria andSYszyNotIn(List<String> values) {
            addCriterion("S_YSZY not in", values, "sYszy");
            return (Criteria) this;
        }

        public Criteria andSYszyBetween(String value1, String value2) {
            addCriterion("S_YSZY between", value1, value2, "sYszy");
            return (Criteria) this;
        }

        public Criteria andSYszyNotBetween(String value1, String value2) {
            addCriterion("S_YSZY not between", value1, value2, "sYszy");
            return (Criteria) this;
        }

        public Criteria andSInfoPathIsNull() {
            addCriterion("S_INFO_PATH is null");
            return (Criteria) this;
        }

        public Criteria andSInfoPathIsNotNull() {
            addCriterion("S_INFO_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andSInfoPathEqualTo(String value) {
            addCriterion("S_INFO_PATH =", value, "sInfoPath");
            return (Criteria) this;
        }

        public Criteria andSInfoPathNotEqualTo(String value) {
            addCriterion("S_INFO_PATH <>", value, "sInfoPath");
            return (Criteria) this;
        }

        public Criteria andSInfoPathGreaterThan(String value) {
            addCriterion("S_INFO_PATH >", value, "sInfoPath");
            return (Criteria) this;
        }

        public Criteria andSInfoPathGreaterThanOrEqualTo(String value) {
            addCriterion("S_INFO_PATH >=", value, "sInfoPath");
            return (Criteria) this;
        }

        public Criteria andSInfoPathLessThan(String value) {
            addCriterion("S_INFO_PATH <", value, "sInfoPath");
            return (Criteria) this;
        }

        public Criteria andSInfoPathLessThanOrEqualTo(String value) {
            addCriterion("S_INFO_PATH <=", value, "sInfoPath");
            return (Criteria) this;
        }

        public Criteria andSInfoPathLike(String value) {
            addCriterion("S_INFO_PATH like", value, "sInfoPath");
            return (Criteria) this;
        }

        public Criteria andSInfoPathNotLike(String value) {
            addCriterion("S_INFO_PATH not like", value, "sInfoPath");
            return (Criteria) this;
        }

        public Criteria andSInfoPathIn(List<String> values) {
            addCriterion("S_INFO_PATH in", values, "sInfoPath");
            return (Criteria) this;
        }

        public Criteria andSInfoPathNotIn(List<String> values) {
            addCriterion("S_INFO_PATH not in", values, "sInfoPath");
            return (Criteria) this;
        }

        public Criteria andSInfoPathBetween(String value1, String value2) {
            addCriterion("S_INFO_PATH between", value1, value2, "sInfoPath");
            return (Criteria) this;
        }

        public Criteria andSInfoPathNotBetween(String value1, String value2) {
            addCriterion("S_INFO_PATH not between", value1, value2, "sInfoPath");
            return (Criteria) this;
        }

        public Criteria andSUpdateUserIsNull() {
            addCriterion("S_UPDATE_USER is null");
            return (Criteria) this;
        }

        public Criteria andSUpdateUserIsNotNull() {
            addCriterion("S_UPDATE_USER is not null");
            return (Criteria) this;
        }

        public Criteria andSUpdateUserEqualTo(String value) {
            addCriterion("S_UPDATE_USER =", value, "sUpdateUser");
            return (Criteria) this;
        }

        public Criteria andSUpdateUserNotEqualTo(String value) {
            addCriterion("S_UPDATE_USER <>", value, "sUpdateUser");
            return (Criteria) this;
        }

        public Criteria andSUpdateUserGreaterThan(String value) {
            addCriterion("S_UPDATE_USER >", value, "sUpdateUser");
            return (Criteria) this;
        }

        public Criteria andSUpdateUserGreaterThanOrEqualTo(String value) {
            addCriterion("S_UPDATE_USER >=", value, "sUpdateUser");
            return (Criteria) this;
        }

        public Criteria andSUpdateUserLessThan(String value) {
            addCriterion("S_UPDATE_USER <", value, "sUpdateUser");
            return (Criteria) this;
        }

        public Criteria andSUpdateUserLessThanOrEqualTo(String value) {
            addCriterion("S_UPDATE_USER <=", value, "sUpdateUser");
            return (Criteria) this;
        }

        public Criteria andSUpdateUserLike(String value) {
            addCriterion("S_UPDATE_USER like", value, "sUpdateUser");
            return (Criteria) this;
        }

        public Criteria andSUpdateUserNotLike(String value) {
            addCriterion("S_UPDATE_USER not like", value, "sUpdateUser");
            return (Criteria) this;
        }

        public Criteria andSUpdateUserIn(List<String> values) {
            addCriterion("S_UPDATE_USER in", values, "sUpdateUser");
            return (Criteria) this;
        }

        public Criteria andSUpdateUserNotIn(List<String> values) {
            addCriterion("S_UPDATE_USER not in", values, "sUpdateUser");
            return (Criteria) this;
        }

        public Criteria andSUpdateUserBetween(String value1, String value2) {
            addCriterion("S_UPDATE_USER between", value1, value2, "sUpdateUser");
            return (Criteria) this;
        }

        public Criteria andSUpdateUserNotBetween(String value1, String value2) {
            addCriterion("S_UPDATE_USER not between", value1, value2, "sUpdateUser");
            return (Criteria) this;
        }

        public Criteria andSCreateUserNameIsNull() {
            addCriterion("S_CREATE_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSCreateUserNameIsNotNull() {
            addCriterion("S_CREATE_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSCreateUserNameEqualTo(String value) {
            addCriterion("S_CREATE_USER_NAME =", value, "sCreateUserName");
            return (Criteria) this;
        }

        public Criteria andSCreateUserNameNotEqualTo(String value) {
            addCriterion("S_CREATE_USER_NAME <>", value, "sCreateUserName");
            return (Criteria) this;
        }

        public Criteria andSCreateUserNameGreaterThan(String value) {
            addCriterion("S_CREATE_USER_NAME >", value, "sCreateUserName");
            return (Criteria) this;
        }

        public Criteria andSCreateUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("S_CREATE_USER_NAME >=", value, "sCreateUserName");
            return (Criteria) this;
        }

        public Criteria andSCreateUserNameLessThan(String value) {
            addCriterion("S_CREATE_USER_NAME <", value, "sCreateUserName");
            return (Criteria) this;
        }

        public Criteria andSCreateUserNameLessThanOrEqualTo(String value) {
            addCriterion("S_CREATE_USER_NAME <=", value, "sCreateUserName");
            return (Criteria) this;
        }

        public Criteria andSCreateUserNameLike(String value) {
            addCriterion("S_CREATE_USER_NAME like", value, "sCreateUserName");
            return (Criteria) this;
        }

        public Criteria andSCreateUserNameNotLike(String value) {
            addCriterion("S_CREATE_USER_NAME not like", value, "sCreateUserName");
            return (Criteria) this;
        }

        public Criteria andSCreateUserNameIn(List<String> values) {
            addCriterion("S_CREATE_USER_NAME in", values, "sCreateUserName");
            return (Criteria) this;
        }

        public Criteria andSCreateUserNameNotIn(List<String> values) {
            addCriterion("S_CREATE_USER_NAME not in", values, "sCreateUserName");
            return (Criteria) this;
        }

        public Criteria andSCreateUserNameBetween(String value1, String value2) {
            addCriterion("S_CREATE_USER_NAME between", value1, value2, "sCreateUserName");
            return (Criteria) this;
        }

        public Criteria andSCreateUserNameNotBetween(String value1, String value2) {
            addCriterion("S_CREATE_USER_NAME not between", value1, value2, "sCreateUserName");
            return (Criteria) this;
        }

        public Criteria andSOrganIsNull() {
            addCriterion("S_ORGAN is null");
            return (Criteria) this;
        }

        public Criteria andSOrganIsNotNull() {
            addCriterion("S_ORGAN is not null");
            return (Criteria) this;
        }

        public Criteria andSOrganEqualTo(String value) {
            addCriterion("S_ORGAN =", value, "sOrgan");
            return (Criteria) this;
        }

        public Criteria andSOrganNotEqualTo(String value) {
            addCriterion("S_ORGAN <>", value, "sOrgan");
            return (Criteria) this;
        }

        public Criteria andSOrganGreaterThan(String value) {
            addCriterion("S_ORGAN >", value, "sOrgan");
            return (Criteria) this;
        }

        public Criteria andSOrganGreaterThanOrEqualTo(String value) {
            addCriterion("S_ORGAN >=", value, "sOrgan");
            return (Criteria) this;
        }

        public Criteria andSOrganLessThan(String value) {
            addCriterion("S_ORGAN <", value, "sOrgan");
            return (Criteria) this;
        }

        public Criteria andSOrganLessThanOrEqualTo(String value) {
            addCriterion("S_ORGAN <=", value, "sOrgan");
            return (Criteria) this;
        }

        public Criteria andSOrganLike(String value) {
            addCriterion("S_ORGAN like", value, "sOrgan");
            return (Criteria) this;
        }

        public Criteria andSOrganNotLike(String value) {
            addCriterion("S_ORGAN not like", value, "sOrgan");
            return (Criteria) this;
        }

        public Criteria andSOrganIn(List<String> values) {
            addCriterion("S_ORGAN in", values, "sOrgan");
            return (Criteria) this;
        }

        public Criteria andSOrganNotIn(List<String> values) {
            addCriterion("S_ORGAN not in", values, "sOrgan");
            return (Criteria) this;
        }

        public Criteria andSOrganBetween(String value1, String value2) {
            addCriterion("S_ORGAN between", value1, value2, "sOrgan");
            return (Criteria) this;
        }

        public Criteria andSOrganNotBetween(String value1, String value2) {
            addCriterion("S_ORGAN not between", value1, value2, "sOrgan");
            return (Criteria) this;
        }

        public Criteria andSCreateDateIsNull() {
            addCriterion("S_CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSCreateDateIsNotNull() {
            addCriterion("S_CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSCreateDateEqualTo(Date value) {
            addCriterion("S_CREATE_DATE =", value, "sCreateDate");
            return (Criteria) this;
        }

        public Criteria andSCreateDateNotEqualTo(Date value) {
            addCriterion("S_CREATE_DATE <>", value, "sCreateDate");
            return (Criteria) this;
        }

        public Criteria andSCreateDateGreaterThan(Date value) {
            addCriterion("S_CREATE_DATE >", value, "sCreateDate");
            return (Criteria) this;
        }

        public Criteria andSCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("S_CREATE_DATE >=", value, "sCreateDate");
            return (Criteria) this;
        }

        public Criteria andSCreateDateLessThan(Date value) {
            addCriterion("S_CREATE_DATE <", value, "sCreateDate");
            return (Criteria) this;
        }

        public Criteria andSCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("S_CREATE_DATE <=", value, "sCreateDate");
            return (Criteria) this;
        }

        public Criteria andSCreateDateIn(List<Date> values) {
            addCriterion("S_CREATE_DATE in", values, "sCreateDate");
            return (Criteria) this;
        }

        public Criteria andSCreateDateNotIn(List<Date> values) {
            addCriterion("S_CREATE_DATE not in", values, "sCreateDate");
            return (Criteria) this;
        }

        public Criteria andSCreateDateBetween(Date value1, Date value2) {
            addCriterion("S_CREATE_DATE between", value1, value2, "sCreateDate");
            return (Criteria) this;
        }

        public Criteria andSCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("S_CREATE_DATE not between", value1, value2, "sCreateDate");
            return (Criteria) this;
        }

        public Criteria andSUpdateDateIsNull() {
            addCriterion("S_UPDATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSUpdateDateIsNotNull() {
            addCriterion("S_UPDATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSUpdateDateEqualTo(Date value) {
            addCriterion("S_UPDATE_DATE =", value, "sUpdateDate");
            return (Criteria) this;
        }

        public Criteria andSUpdateDateNotEqualTo(Date value) {
            addCriterion("S_UPDATE_DATE <>", value, "sUpdateDate");
            return (Criteria) this;
        }

        public Criteria andSUpdateDateGreaterThan(Date value) {
            addCriterion("S_UPDATE_DATE >", value, "sUpdateDate");
            return (Criteria) this;
        }

        public Criteria andSUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("S_UPDATE_DATE >=", value, "sUpdateDate");
            return (Criteria) this;
        }

        public Criteria andSUpdateDateLessThan(Date value) {
            addCriterion("S_UPDATE_DATE <", value, "sUpdateDate");
            return (Criteria) this;
        }

        public Criteria andSUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("S_UPDATE_DATE <=", value, "sUpdateDate");
            return (Criteria) this;
        }

        public Criteria andSUpdateDateIn(List<Date> values) {
            addCriterion("S_UPDATE_DATE in", values, "sUpdateDate");
            return (Criteria) this;
        }

        public Criteria andSUpdateDateNotIn(List<Date> values) {
            addCriterion("S_UPDATE_DATE not in", values, "sUpdateDate");
            return (Criteria) this;
        }

        public Criteria andSUpdateDateBetween(Date value1, Date value2) {
            addCriterion("S_UPDATE_DATE between", value1, value2, "sUpdateDate");
            return (Criteria) this;
        }

        public Criteria andSUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("S_UPDATE_DATE not between", value1, value2, "sUpdateDate");
            return (Criteria) this;
        }

        public Criteria andSBmTypeIsNull() {
            addCriterion("S_BM_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSBmTypeIsNotNull() {
            addCriterion("S_BM_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSBmTypeEqualTo(String value) {
            addCriterion("S_BM_TYPE =", value, "sBmType");
            return (Criteria) this;
        }

        public Criteria andSBmTypeNotEqualTo(String value) {
            addCriterion("S_BM_TYPE <>", value, "sBmType");
            return (Criteria) this;
        }

        public Criteria andSBmTypeGreaterThan(String value) {
            addCriterion("S_BM_TYPE >", value, "sBmType");
            return (Criteria) this;
        }

        public Criteria andSBmTypeGreaterThanOrEqualTo(String value) {
            addCriterion("S_BM_TYPE >=", value, "sBmType");
            return (Criteria) this;
        }

        public Criteria andSBmTypeLessThan(String value) {
            addCriterion("S_BM_TYPE <", value, "sBmType");
            return (Criteria) this;
        }

        public Criteria andSBmTypeLessThanOrEqualTo(String value) {
            addCriterion("S_BM_TYPE <=", value, "sBmType");
            return (Criteria) this;
        }

        public Criteria andSBmTypeLike(String value) {
            addCriterion("S_BM_TYPE like", value, "sBmType");
            return (Criteria) this;
        }

        public Criteria andSBmTypeNotLike(String value) {
            addCriterion("S_BM_TYPE not like", value, "sBmType");
            return (Criteria) this;
        }

        public Criteria andSBmTypeIn(List<String> values) {
            addCriterion("S_BM_TYPE in", values, "sBmType");
            return (Criteria) this;
        }

        public Criteria andSBmTypeNotIn(List<String> values) {
            addCriterion("S_BM_TYPE not in", values, "sBmType");
            return (Criteria) this;
        }

        public Criteria andSBmTypeBetween(String value1, String value2) {
            addCriterion("S_BM_TYPE between", value1, value2, "sBmType");
            return (Criteria) this;
        }

        public Criteria andSBmTypeNotBetween(String value1, String value2) {
            addCriterion("S_BM_TYPE not between", value1, value2, "sBmType");
            return (Criteria) this;
        }

        public Criteria andSFileNameIsNull() {
            addCriterion("S_FILE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSFileNameIsNotNull() {
            addCriterion("S_FILE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSFileNameEqualTo(String value) {
            addCriterion("S_FILE_NAME =", value, "sFileName");
            return (Criteria) this;
        }

        public Criteria andSFileNameNotEqualTo(String value) {
            addCriterion("S_FILE_NAME <>", value, "sFileName");
            return (Criteria) this;
        }

        public Criteria andSFileNameGreaterThan(String value) {
            addCriterion("S_FILE_NAME >", value, "sFileName");
            return (Criteria) this;
        }

        public Criteria andSFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("S_FILE_NAME >=", value, "sFileName");
            return (Criteria) this;
        }

        public Criteria andSFileNameLessThan(String value) {
            addCriterion("S_FILE_NAME <", value, "sFileName");
            return (Criteria) this;
        }

        public Criteria andSFileNameLessThanOrEqualTo(String value) {
            addCriterion("S_FILE_NAME <=", value, "sFileName");
            return (Criteria) this;
        }

        public Criteria andSFileNameLike(String value) {
            addCriterion("S_FILE_NAME like", value, "sFileName");
            return (Criteria) this;
        }

        public Criteria andSFileNameNotLike(String value) {
            addCriterion("S_FILE_NAME not like", value, "sFileName");
            return (Criteria) this;
        }

        public Criteria andSFileNameIn(List<String> values) {
            addCriterion("S_FILE_NAME in", values, "sFileName");
            return (Criteria) this;
        }

        public Criteria andSFileNameNotIn(List<String> values) {
            addCriterion("S_FILE_NAME not in", values, "sFileName");
            return (Criteria) this;
        }

        public Criteria andSFileNameBetween(String value1, String value2) {
            addCriterion("S_FILE_NAME between", value1, value2, "sFileName");
            return (Criteria) this;
        }

        public Criteria andSFileNameNotBetween(String value1, String value2) {
            addCriterion("S_FILE_NAME not between", value1, value2, "sFileName");
            return (Criteria) this;
        }

        public Criteria andSCreateUserIsNull() {
            addCriterion("S_CREATE_USER is null");
            return (Criteria) this;
        }

        public Criteria andSCreateUserIsNotNull() {
            addCriterion("S_CREATE_USER is not null");
            return (Criteria) this;
        }

        public Criteria andSCreateUserEqualTo(String value) {
            addCriterion("S_CREATE_USER =", value, "sCreateUser");
            return (Criteria) this;
        }

        public Criteria andSCreateUserNotEqualTo(String value) {
            addCriterion("S_CREATE_USER <>", value, "sCreateUser");
            return (Criteria) this;
        }

        public Criteria andSCreateUserGreaterThan(String value) {
            addCriterion("S_CREATE_USER >", value, "sCreateUser");
            return (Criteria) this;
        }

        public Criteria andSCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("S_CREATE_USER >=", value, "sCreateUser");
            return (Criteria) this;
        }

        public Criteria andSCreateUserLessThan(String value) {
            addCriterion("S_CREATE_USER <", value, "sCreateUser");
            return (Criteria) this;
        }

        public Criteria andSCreateUserLessThanOrEqualTo(String value) {
            addCriterion("S_CREATE_USER <=", value, "sCreateUser");
            return (Criteria) this;
        }

        public Criteria andSCreateUserLike(String value) {
            addCriterion("S_CREATE_USER like", value, "sCreateUser");
            return (Criteria) this;
        }

        public Criteria andSCreateUserNotLike(String value) {
            addCriterion("S_CREATE_USER not like", value, "sCreateUser");
            return (Criteria) this;
        }

        public Criteria andSCreateUserIn(List<String> values) {
            addCriterion("S_CREATE_USER in", values, "sCreateUser");
            return (Criteria) this;
        }

        public Criteria andSCreateUserNotIn(List<String> values) {
            addCriterion("S_CREATE_USER not in", values, "sCreateUser");
            return (Criteria) this;
        }

        public Criteria andSCreateUserBetween(String value1, String value2) {
            addCriterion("S_CREATE_USER between", value1, value2, "sCreateUser");
            return (Criteria) this;
        }

        public Criteria andSCreateUserNotBetween(String value1, String value2) {
            addCriterion("S_CREATE_USER not between", value1, value2, "sCreateUser");
            return (Criteria) this;
        }

        public Criteria andSSpDateIsNull() {
            addCriterion("S_SP_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSSpDateIsNotNull() {
            addCriterion("S_SP_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSSpDateEqualTo(Date value) {
            addCriterion("S_SP_DATE =", value, "sSpDate");
            return (Criteria) this;
        }

        public Criteria andSSpDateNotEqualTo(Date value) {
            addCriterion("S_SP_DATE <>", value, "sSpDate");
            return (Criteria) this;
        }

        public Criteria andSSpDateGreaterThan(Date value) {
            addCriterion("S_SP_DATE >", value, "sSpDate");
            return (Criteria) this;
        }

        public Criteria andSSpDateGreaterThanOrEqualTo(Date value) {
            addCriterion("S_SP_DATE >=", value, "sSpDate");
            return (Criteria) this;
        }

        public Criteria andSSpDateLessThan(Date value) {
            addCriterion("S_SP_DATE <", value, "sSpDate");
            return (Criteria) this;
        }

        public Criteria andSSpDateLessThanOrEqualTo(Date value) {
            addCriterion("S_SP_DATE <=", value, "sSpDate");
            return (Criteria) this;
        }

        public Criteria andSSpDateIn(List<Date> values) {
            addCriterion("S_SP_DATE in", values, "sSpDate");
            return (Criteria) this;
        }

        public Criteria andSSpDateNotIn(List<Date> values) {
            addCriterion("S_SP_DATE not in", values, "sSpDate");
            return (Criteria) this;
        }

        public Criteria andSSpDateBetween(Date value1, Date value2) {
            addCriterion("S_SP_DATE between", value1, value2, "sSpDate");
            return (Criteria) this;
        }

        public Criteria andSSpDateNotBetween(Date value1, Date value2) {
            addCriterion("S_SP_DATE not between", value1, value2, "sSpDate");
            return (Criteria) this;
        }

        public Criteria andSXqkfqIsNull() {
            addCriterion("S_XQKFQ is null");
            return (Criteria) this;
        }

        public Criteria andSXqkfqIsNotNull() {
            addCriterion("S_XQKFQ is not null");
            return (Criteria) this;
        }

        public Criteria andSXqkfqEqualTo(String value) {
            addCriterion("S_XQKFQ =", value, "sXqkfq");
            return (Criteria) this;
        }

        public Criteria andSXqkfqNotEqualTo(String value) {
            addCriterion("S_XQKFQ <>", value, "sXqkfq");
            return (Criteria) this;
        }

        public Criteria andSXqkfqGreaterThan(String value) {
            addCriterion("S_XQKFQ >", value, "sXqkfq");
            return (Criteria) this;
        }

        public Criteria andSXqkfqGreaterThanOrEqualTo(String value) {
            addCriterion("S_XQKFQ >=", value, "sXqkfq");
            return (Criteria) this;
        }

        public Criteria andSXqkfqLessThan(String value) {
            addCriterion("S_XQKFQ <", value, "sXqkfq");
            return (Criteria) this;
        }

        public Criteria andSXqkfqLessThanOrEqualTo(String value) {
            addCriterion("S_XQKFQ <=", value, "sXqkfq");
            return (Criteria) this;
        }

        public Criteria andSXqkfqLike(String value) {
            addCriterion("S_XQKFQ like", value, "sXqkfq");
            return (Criteria) this;
        }

        public Criteria andSXqkfqNotLike(String value) {
            addCriterion("S_XQKFQ not like", value, "sXqkfq");
            return (Criteria) this;
        }

        public Criteria andSXqkfqIn(List<String> values) {
            addCriterion("S_XQKFQ in", values, "sXqkfq");
            return (Criteria) this;
        }

        public Criteria andSXqkfqNotIn(List<String> values) {
            addCriterion("S_XQKFQ not in", values, "sXqkfq");
            return (Criteria) this;
        }

        public Criteria andSXqkfqBetween(String value1, String value2) {
            addCriterion("S_XQKFQ between", value1, value2, "sXqkfq");
            return (Criteria) this;
        }

        public Criteria andSXqkfqNotBetween(String value1, String value2) {
            addCriterion("S_XQKFQ not between", value1, value2, "sXqkfq");
            return (Criteria) this;
        }

        public Criteria andSBmTypeNameIsNull() {
            addCriterion("S_BM_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSBmTypeNameIsNotNull() {
            addCriterion("S_BM_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSBmTypeNameEqualTo(String value) {
            addCriterion("S_BM_TYPE_NAME =", value, "sBmTypeName");
            return (Criteria) this;
        }

        public Criteria andSBmTypeNameNotEqualTo(String value) {
            addCriterion("S_BM_TYPE_NAME <>", value, "sBmTypeName");
            return (Criteria) this;
        }

        public Criteria andSBmTypeNameGreaterThan(String value) {
            addCriterion("S_BM_TYPE_NAME >", value, "sBmTypeName");
            return (Criteria) this;
        }

        public Criteria andSBmTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("S_BM_TYPE_NAME >=", value, "sBmTypeName");
            return (Criteria) this;
        }

        public Criteria andSBmTypeNameLessThan(String value) {
            addCriterion("S_BM_TYPE_NAME <", value, "sBmTypeName");
            return (Criteria) this;
        }

        public Criteria andSBmTypeNameLessThanOrEqualTo(String value) {
            addCriterion("S_BM_TYPE_NAME <=", value, "sBmTypeName");
            return (Criteria) this;
        }

        public Criteria andSBmTypeNameLike(String value) {
            addCriterion("S_BM_TYPE_NAME like", value, "sBmTypeName");
            return (Criteria) this;
        }

        public Criteria andSBmTypeNameNotLike(String value) {
            addCriterion("S_BM_TYPE_NAME not like", value, "sBmTypeName");
            return (Criteria) this;
        }

        public Criteria andSBmTypeNameIn(List<String> values) {
            addCriterion("S_BM_TYPE_NAME in", values, "sBmTypeName");
            return (Criteria) this;
        }

        public Criteria andSBmTypeNameNotIn(List<String> values) {
            addCriterion("S_BM_TYPE_NAME not in", values, "sBmTypeName");
            return (Criteria) this;
        }

        public Criteria andSBmTypeNameBetween(String value1, String value2) {
            addCriterion("S_BM_TYPE_NAME between", value1, value2, "sBmTypeName");
            return (Criteria) this;
        }

        public Criteria andSBmTypeNameNotBetween(String value1, String value2) {
            addCriterion("S_BM_TYPE_NAME not between", value1, value2, "sBmTypeName");
            return (Criteria) this;
        }

        public Criteria andSFilePathIsNull() {
            addCriterion("S_FILE_PATH is null");
            return (Criteria) this;
        }

        public Criteria andSFilePathIsNotNull() {
            addCriterion("S_FILE_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andSFilePathEqualTo(String value) {
            addCriterion("S_FILE_PATH =", value, "sFilePath");
            return (Criteria) this;
        }

        public Criteria andSFilePathNotEqualTo(String value) {
            addCriterion("S_FILE_PATH <>", value, "sFilePath");
            return (Criteria) this;
        }

        public Criteria andSFilePathGreaterThan(String value) {
            addCriterion("S_FILE_PATH >", value, "sFilePath");
            return (Criteria) this;
        }

        public Criteria andSFilePathGreaterThanOrEqualTo(String value) {
            addCriterion("S_FILE_PATH >=", value, "sFilePath");
            return (Criteria) this;
        }

        public Criteria andSFilePathLessThan(String value) {
            addCriterion("S_FILE_PATH <", value, "sFilePath");
            return (Criteria) this;
        }

        public Criteria andSFilePathLessThanOrEqualTo(String value) {
            addCriterion("S_FILE_PATH <=", value, "sFilePath");
            return (Criteria) this;
        }

        public Criteria andSFilePathLike(String value) {
            addCriterion("S_FILE_PATH like", value, "sFilePath");
            return (Criteria) this;
        }

        public Criteria andSFilePathNotLike(String value) {
            addCriterion("S_FILE_PATH not like", value, "sFilePath");
            return (Criteria) this;
        }

        public Criteria andSFilePathIn(List<String> values) {
            addCriterion("S_FILE_PATH in", values, "sFilePath");
            return (Criteria) this;
        }

        public Criteria andSFilePathNotIn(List<String> values) {
            addCriterion("S_FILE_PATH not in", values, "sFilePath");
            return (Criteria) this;
        }

        public Criteria andSFilePathBetween(String value1, String value2) {
            addCriterion("S_FILE_PATH between", value1, value2, "sFilePath");
            return (Criteria) this;
        }

        public Criteria andSFilePathNotBetween(String value1, String value2) {
            addCriterion("S_FILE_PATH not between", value1, value2, "sFilePath");
            return (Criteria) this;
        }

        public Criteria andSTypeIsNull() {
            addCriterion("S_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSTypeIsNotNull() {
            addCriterion("S_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSTypeEqualTo(String value) {
            addCriterion("S_TYPE =", value, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeNotEqualTo(String value) {
            addCriterion("S_TYPE <>", value, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeGreaterThan(String value) {
            addCriterion("S_TYPE >", value, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeGreaterThanOrEqualTo(String value) {
            addCriterion("S_TYPE >=", value, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeLessThan(String value) {
            addCriterion("S_TYPE <", value, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeLessThanOrEqualTo(String value) {
            addCriterion("S_TYPE <=", value, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeLike(String value) {
            addCriterion("S_TYPE like", value, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeNotLike(String value) {
            addCriterion("S_TYPE not like", value, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeIn(List<String> values) {
            addCriterion("S_TYPE in", values, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeNotIn(List<String> values) {
            addCriterion("S_TYPE not in", values, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeBetween(String value1, String value2) {
            addCriterion("S_TYPE between", value1, value2, "sType");
            return (Criteria) this;
        }

        public Criteria andSTypeNotBetween(String value1, String value2) {
            addCriterion("S_TYPE not between", value1, value2, "sType");
            return (Criteria) this;
        }

        public Criteria andSTitleIsNull() {
            addCriterion("S_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andSTitleIsNotNull() {
            addCriterion("S_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andSTitleEqualTo(String value) {
            addCriterion("S_TITLE =", value, "sTitle");
            return (Criteria) this;
        }

        public Criteria andSTitleNotEqualTo(String value) {
            addCriterion("S_TITLE <>", value, "sTitle");
            return (Criteria) this;
        }

        public Criteria andSTitleGreaterThan(String value) {
            addCriterion("S_TITLE >", value, "sTitle");
            return (Criteria) this;
        }

        public Criteria andSTitleGreaterThanOrEqualTo(String value) {
            addCriterion("S_TITLE >=", value, "sTitle");
            return (Criteria) this;
        }

        public Criteria andSTitleLessThan(String value) {
            addCriterion("S_TITLE <", value, "sTitle");
            return (Criteria) this;
        }

        public Criteria andSTitleLessThanOrEqualTo(String value) {
            addCriterion("S_TITLE <=", value, "sTitle");
            return (Criteria) this;
        }

        public Criteria andSTitleLike(String value) {
            addCriterion("S_TITLE like", value, "sTitle");
            return (Criteria) this;
        }

        public Criteria andSTitleNotLike(String value) {
            addCriterion("S_TITLE not like", value, "sTitle");
            return (Criteria) this;
        }

        public Criteria andSTitleIn(List<String> values) {
            addCriterion("S_TITLE in", values, "sTitle");
            return (Criteria) this;
        }

        public Criteria andSTitleNotIn(List<String> values) {
            addCriterion("S_TITLE not in", values, "sTitle");
            return (Criteria) this;
        }

        public Criteria andSTitleBetween(String value1, String value2) {
            addCriterion("S_TITLE between", value1, value2, "sTitle");
            return (Criteria) this;
        }

        public Criteria andSTitleNotBetween(String value1, String value2) {
            addCriterion("S_TITLE not between", value1, value2, "sTitle");
            return (Criteria) this;
        }

        public Criteria andSUuidIsNull() {
            addCriterion("S_UUID is null");
            return (Criteria) this;
        }

        public Criteria andSUuidIsNotNull() {
            addCriterion("S_UUID is not null");
            return (Criteria) this;
        }

        public Criteria andSUuidEqualTo(String value) {
            addCriterion("S_UUID =", value, "sUuid");
            return (Criteria) this;
        }

        public Criteria andSUuidNotEqualTo(String value) {
            addCriterion("S_UUID <>", value, "sUuid");
            return (Criteria) this;
        }

        public Criteria andSUuidGreaterThan(String value) {
            addCriterion("S_UUID >", value, "sUuid");
            return (Criteria) this;
        }

        public Criteria andSUuidGreaterThanOrEqualTo(String value) {
            addCriterion("S_UUID >=", value, "sUuid");
            return (Criteria) this;
        }

        public Criteria andSUuidLessThan(String value) {
            addCriterion("S_UUID <", value, "sUuid");
            return (Criteria) this;
        }

        public Criteria andSUuidLessThanOrEqualTo(String value) {
            addCriterion("S_UUID <=", value, "sUuid");
            return (Criteria) this;
        }

        public Criteria andSUuidLike(String value) {
            addCriterion("S_UUID like", value, "sUuid");
            return (Criteria) this;
        }

        public Criteria andSUuidNotLike(String value) {
            addCriterion("S_UUID not like", value, "sUuid");
            return (Criteria) this;
        }

        public Criteria andSUuidIn(List<String> values) {
            addCriterion("S_UUID in", values, "sUuid");
            return (Criteria) this;
        }

        public Criteria andSUuidNotIn(List<String> values) {
            addCriterion("S_UUID not in", values, "sUuid");
            return (Criteria) this;
        }

        public Criteria andSUuidBetween(String value1, String value2) {
            addCriterion("S_UUID between", value1, value2, "sUuid");
            return (Criteria) this;
        }

        public Criteria andSUuidNotBetween(String value1, String value2) {
            addCriterion("S_UUID not between", value1, value2, "sUuid");
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