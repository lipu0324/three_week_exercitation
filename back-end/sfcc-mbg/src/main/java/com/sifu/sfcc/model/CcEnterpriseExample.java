package com.sifu.sfcc.model;

import java.util.ArrayList;
import java.util.List;

public class CcEnterpriseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CcEnterpriseExample() {
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

        public Criteria andWebsiteLogoIsNull() {
            addCriterion("website_logo is null");
            return (Criteria) this;
        }

        public Criteria andWebsiteLogoIsNotNull() {
            addCriterion("website_logo is not null");
            return (Criteria) this;
        }

        public Criteria andWebsiteLogoEqualTo(String value) {
            addCriterion("website_logo =", value, "websiteLogo");
            return (Criteria) this;
        }

        public Criteria andWebsiteLogoNotEqualTo(String value) {
            addCriterion("website_logo <>", value, "websiteLogo");
            return (Criteria) this;
        }

        public Criteria andWebsiteLogoGreaterThan(String value) {
            addCriterion("website_logo >", value, "websiteLogo");
            return (Criteria) this;
        }

        public Criteria andWebsiteLogoGreaterThanOrEqualTo(String value) {
            addCriterion("website_logo >=", value, "websiteLogo");
            return (Criteria) this;
        }

        public Criteria andWebsiteLogoLessThan(String value) {
            addCriterion("website_logo <", value, "websiteLogo");
            return (Criteria) this;
        }

        public Criteria andWebsiteLogoLessThanOrEqualTo(String value) {
            addCriterion("website_logo <=", value, "websiteLogo");
            return (Criteria) this;
        }

        public Criteria andWebsiteLogoLike(String value) {
            addCriterion("website_logo like", value, "websiteLogo");
            return (Criteria) this;
        }

        public Criteria andWebsiteLogoNotLike(String value) {
            addCriterion("website_logo not like", value, "websiteLogo");
            return (Criteria) this;
        }

        public Criteria andWebsiteLogoIn(List<String> values) {
            addCriterion("website_logo in", values, "websiteLogo");
            return (Criteria) this;
        }

        public Criteria andWebsiteLogoNotIn(List<String> values) {
            addCriterion("website_logo not in", values, "websiteLogo");
            return (Criteria) this;
        }

        public Criteria andWebsiteLogoBetween(String value1, String value2) {
            addCriterion("website_logo between", value1, value2, "websiteLogo");
            return (Criteria) this;
        }

        public Criteria andWebsiteLogoNotBetween(String value1, String value2) {
            addCriterion("website_logo not between", value1, value2, "websiteLogo");
            return (Criteria) this;
        }

        public Criteria andEnterpriseLogoIsNull() {
            addCriterion("enterprise_logo is null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseLogoIsNotNull() {
            addCriterion("enterprise_logo is not null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseLogoEqualTo(String value) {
            addCriterion("enterprise_logo =", value, "enterpriseLogo");
            return (Criteria) this;
        }

        public Criteria andEnterpriseLogoNotEqualTo(String value) {
            addCriterion("enterprise_logo <>", value, "enterpriseLogo");
            return (Criteria) this;
        }

        public Criteria andEnterpriseLogoGreaterThan(String value) {
            addCriterion("enterprise_logo >", value, "enterpriseLogo");
            return (Criteria) this;
        }

        public Criteria andEnterpriseLogoGreaterThanOrEqualTo(String value) {
            addCriterion("enterprise_logo >=", value, "enterpriseLogo");
            return (Criteria) this;
        }

        public Criteria andEnterpriseLogoLessThan(String value) {
            addCriterion("enterprise_logo <", value, "enterpriseLogo");
            return (Criteria) this;
        }

        public Criteria andEnterpriseLogoLessThanOrEqualTo(String value) {
            addCriterion("enterprise_logo <=", value, "enterpriseLogo");
            return (Criteria) this;
        }

        public Criteria andEnterpriseLogoLike(String value) {
            addCriterion("enterprise_logo like", value, "enterpriseLogo");
            return (Criteria) this;
        }

        public Criteria andEnterpriseLogoNotLike(String value) {
            addCriterion("enterprise_logo not like", value, "enterpriseLogo");
            return (Criteria) this;
        }

        public Criteria andEnterpriseLogoIn(List<String> values) {
            addCriterion("enterprise_logo in", values, "enterpriseLogo");
            return (Criteria) this;
        }

        public Criteria andEnterpriseLogoNotIn(List<String> values) {
            addCriterion("enterprise_logo not in", values, "enterpriseLogo");
            return (Criteria) this;
        }

        public Criteria andEnterpriseLogoBetween(String value1, String value2) {
            addCriterion("enterprise_logo between", value1, value2, "enterpriseLogo");
            return (Criteria) this;
        }

        public Criteria andEnterpriseLogoNotBetween(String value1, String value2) {
            addCriterion("enterprise_logo not between", value1, value2, "enterpriseLogo");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNameIsNull() {
            addCriterion("enterprise_name is null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNameIsNotNull() {
            addCriterion("enterprise_name is not null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNameEqualTo(String value) {
            addCriterion("enterprise_name =", value, "enterpriseName");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNameNotEqualTo(String value) {
            addCriterion("enterprise_name <>", value, "enterpriseName");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNameGreaterThan(String value) {
            addCriterion("enterprise_name >", value, "enterpriseName");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNameGreaterThanOrEqualTo(String value) {
            addCriterion("enterprise_name >=", value, "enterpriseName");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNameLessThan(String value) {
            addCriterion("enterprise_name <", value, "enterpriseName");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNameLessThanOrEqualTo(String value) {
            addCriterion("enterprise_name <=", value, "enterpriseName");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNameLike(String value) {
            addCriterion("enterprise_name like", value, "enterpriseName");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNameNotLike(String value) {
            addCriterion("enterprise_name not like", value, "enterpriseName");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNameIn(List<String> values) {
            addCriterion("enterprise_name in", values, "enterpriseName");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNameNotIn(List<String> values) {
            addCriterion("enterprise_name not in", values, "enterpriseName");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNameBetween(String value1, String value2) {
            addCriterion("enterprise_name between", value1, value2, "enterpriseName");
            return (Criteria) this;
        }

        public Criteria andEnterpriseNameNotBetween(String value1, String value2) {
            addCriterion("enterprise_name not between", value1, value2, "enterpriseName");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTitleIsNull() {
            addCriterion("enterprise_title is null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTitleIsNotNull() {
            addCriterion("enterprise_title is not null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTitleEqualTo(String value) {
            addCriterion("enterprise_title =", value, "enterpriseTitle");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTitleNotEqualTo(String value) {
            addCriterion("enterprise_title <>", value, "enterpriseTitle");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTitleGreaterThan(String value) {
            addCriterion("enterprise_title >", value, "enterpriseTitle");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTitleGreaterThanOrEqualTo(String value) {
            addCriterion("enterprise_title >=", value, "enterpriseTitle");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTitleLessThan(String value) {
            addCriterion("enterprise_title <", value, "enterpriseTitle");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTitleLessThanOrEqualTo(String value) {
            addCriterion("enterprise_title <=", value, "enterpriseTitle");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTitleLike(String value) {
            addCriterion("enterprise_title like", value, "enterpriseTitle");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTitleNotLike(String value) {
            addCriterion("enterprise_title not like", value, "enterpriseTitle");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTitleIn(List<String> values) {
            addCriterion("enterprise_title in", values, "enterpriseTitle");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTitleNotIn(List<String> values) {
            addCriterion("enterprise_title not in", values, "enterpriseTitle");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTitleBetween(String value1, String value2) {
            addCriterion("enterprise_title between", value1, value2, "enterpriseTitle");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTitleNotBetween(String value1, String value2) {
            addCriterion("enterprise_title not between", value1, value2, "enterpriseTitle");
            return (Criteria) this;
        }

        public Criteria andCopyrightIsNull() {
            addCriterion("copyright is null");
            return (Criteria) this;
        }

        public Criteria andCopyrightIsNotNull() {
            addCriterion("copyright is not null");
            return (Criteria) this;
        }

        public Criteria andCopyrightEqualTo(String value) {
            addCriterion("copyright =", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightNotEqualTo(String value) {
            addCriterion("copyright <>", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightGreaterThan(String value) {
            addCriterion("copyright >", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightGreaterThanOrEqualTo(String value) {
            addCriterion("copyright >=", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightLessThan(String value) {
            addCriterion("copyright <", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightLessThanOrEqualTo(String value) {
            addCriterion("copyright <=", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightLike(String value) {
            addCriterion("copyright like", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightNotLike(String value) {
            addCriterion("copyright not like", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightIn(List<String> values) {
            addCriterion("copyright in", values, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightNotIn(List<String> values) {
            addCriterion("copyright not in", values, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightBetween(String value1, String value2) {
            addCriterion("copyright between", value1, value2, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightNotBetween(String value1, String value2) {
            addCriterion("copyright not between", value1, value2, "copyright");
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