package com.sifu.sfcc.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CcWorkOrderTraceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CcWorkOrderTraceExample() {
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

        public Criteria andWorkOrderIdIsNull() {
            addCriterion("work_order_id is null");
            return (Criteria) this;
        }

        public Criteria andWorkOrderIdIsNotNull() {
            addCriterion("work_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andWorkOrderIdEqualTo(Long value) {
            addCriterion("work_order_id =", value, "workOrderId");
            return (Criteria) this;
        }

        public Criteria andWorkOrderIdNotEqualTo(Long value) {
            addCriterion("work_order_id <>", value, "workOrderId");
            return (Criteria) this;
        }

        public Criteria andWorkOrderIdGreaterThan(Long value) {
            addCriterion("work_order_id >", value, "workOrderId");
            return (Criteria) this;
        }

        public Criteria andWorkOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("work_order_id >=", value, "workOrderId");
            return (Criteria) this;
        }

        public Criteria andWorkOrderIdLessThan(Long value) {
            addCriterion("work_order_id <", value, "workOrderId");
            return (Criteria) this;
        }

        public Criteria andWorkOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("work_order_id <=", value, "workOrderId");
            return (Criteria) this;
        }

        public Criteria andWorkOrderIdIn(List<Long> values) {
            addCriterion("work_order_id in", values, "workOrderId");
            return (Criteria) this;
        }

        public Criteria andWorkOrderIdNotIn(List<Long> values) {
            addCriterion("work_order_id not in", values, "workOrderId");
            return (Criteria) this;
        }

        public Criteria andWorkOrderIdBetween(Long value1, Long value2) {
            addCriterion("work_order_id between", value1, value2, "workOrderId");
            return (Criteria) this;
        }

        public Criteria andWorkOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("work_order_id not between", value1, value2, "workOrderId");
            return (Criteria) this;
        }

        public Criteria andFromIdIsNull() {
            addCriterion("from_id is null");
            return (Criteria) this;
        }

        public Criteria andFromIdIsNotNull() {
            addCriterion("from_id is not null");
            return (Criteria) this;
        }

        public Criteria andFromIdEqualTo(Long value) {
            addCriterion("from_id =", value, "fromId");
            return (Criteria) this;
        }

        public Criteria andFromIdNotEqualTo(Long value) {
            addCriterion("from_id <>", value, "fromId");
            return (Criteria) this;
        }

        public Criteria andFromIdGreaterThan(Long value) {
            addCriterion("from_id >", value, "fromId");
            return (Criteria) this;
        }

        public Criteria andFromIdGreaterThanOrEqualTo(Long value) {
            addCriterion("from_id >=", value, "fromId");
            return (Criteria) this;
        }

        public Criteria andFromIdLessThan(Long value) {
            addCriterion("from_id <", value, "fromId");
            return (Criteria) this;
        }

        public Criteria andFromIdLessThanOrEqualTo(Long value) {
            addCriterion("from_id <=", value, "fromId");
            return (Criteria) this;
        }

        public Criteria andFromIdIn(List<Long> values) {
            addCriterion("from_id in", values, "fromId");
            return (Criteria) this;
        }

        public Criteria andFromIdNotIn(List<Long> values) {
            addCriterion("from_id not in", values, "fromId");
            return (Criteria) this;
        }

        public Criteria andFromIdBetween(Long value1, Long value2) {
            addCriterion("from_id between", value1, value2, "fromId");
            return (Criteria) this;
        }

        public Criteria andFromIdNotBetween(Long value1, Long value2) {
            addCriterion("from_id not between", value1, value2, "fromId");
            return (Criteria) this;
        }

        public Criteria andFromCodeIsNull() {
            addCriterion("from_code is null");
            return (Criteria) this;
        }

        public Criteria andFromCodeIsNotNull() {
            addCriterion("from_code is not null");
            return (Criteria) this;
        }

        public Criteria andFromCodeEqualTo(String value) {
            addCriterion("from_code =", value, "fromCode");
            return (Criteria) this;
        }

        public Criteria andFromCodeNotEqualTo(String value) {
            addCriterion("from_code <>", value, "fromCode");
            return (Criteria) this;
        }

        public Criteria andFromCodeGreaterThan(String value) {
            addCriterion("from_code >", value, "fromCode");
            return (Criteria) this;
        }

        public Criteria andFromCodeGreaterThanOrEqualTo(String value) {
            addCriterion("from_code >=", value, "fromCode");
            return (Criteria) this;
        }

        public Criteria andFromCodeLessThan(String value) {
            addCriterion("from_code <", value, "fromCode");
            return (Criteria) this;
        }

        public Criteria andFromCodeLessThanOrEqualTo(String value) {
            addCriterion("from_code <=", value, "fromCode");
            return (Criteria) this;
        }

        public Criteria andFromCodeLike(String value) {
            addCriterion("from_code like", value, "fromCode");
            return (Criteria) this;
        }

        public Criteria andFromCodeNotLike(String value) {
            addCriterion("from_code not like", value, "fromCode");
            return (Criteria) this;
        }

        public Criteria andFromCodeIn(List<String> values) {
            addCriterion("from_code in", values, "fromCode");
            return (Criteria) this;
        }

        public Criteria andFromCodeNotIn(List<String> values) {
            addCriterion("from_code not in", values, "fromCode");
            return (Criteria) this;
        }

        public Criteria andFromCodeBetween(String value1, String value2) {
            addCriterion("from_code between", value1, value2, "fromCode");
            return (Criteria) this;
        }

        public Criteria andFromCodeNotBetween(String value1, String value2) {
            addCriterion("from_code not between", value1, value2, "fromCode");
            return (Criteria) this;
        }

        public Criteria andFromNameIsNull() {
            addCriterion("from_name is null");
            return (Criteria) this;
        }

        public Criteria andFromNameIsNotNull() {
            addCriterion("from_name is not null");
            return (Criteria) this;
        }

        public Criteria andFromNameEqualTo(String value) {
            addCriterion("from_name =", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameNotEqualTo(String value) {
            addCriterion("from_name <>", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameGreaterThan(String value) {
            addCriterion("from_name >", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameGreaterThanOrEqualTo(String value) {
            addCriterion("from_name >=", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameLessThan(String value) {
            addCriterion("from_name <", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameLessThanOrEqualTo(String value) {
            addCriterion("from_name <=", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameLike(String value) {
            addCriterion("from_name like", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameNotLike(String value) {
            addCriterion("from_name not like", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameIn(List<String> values) {
            addCriterion("from_name in", values, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameNotIn(List<String> values) {
            addCriterion("from_name not in", values, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameBetween(String value1, String value2) {
            addCriterion("from_name between", value1, value2, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameNotBetween(String value1, String value2) {
            addCriterion("from_name not between", value1, value2, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromDeptIsNull() {
            addCriterion("from_dept is null");
            return (Criteria) this;
        }

        public Criteria andFromDeptIsNotNull() {
            addCriterion("from_dept is not null");
            return (Criteria) this;
        }

        public Criteria andFromDeptEqualTo(String value) {
            addCriterion("from_dept =", value, "fromDept");
            return (Criteria) this;
        }

        public Criteria andFromDeptNotEqualTo(String value) {
            addCriterion("from_dept <>", value, "fromDept");
            return (Criteria) this;
        }

        public Criteria andFromDeptGreaterThan(String value) {
            addCriterion("from_dept >", value, "fromDept");
            return (Criteria) this;
        }

        public Criteria andFromDeptGreaterThanOrEqualTo(String value) {
            addCriterion("from_dept >=", value, "fromDept");
            return (Criteria) this;
        }

        public Criteria andFromDeptLessThan(String value) {
            addCriterion("from_dept <", value, "fromDept");
            return (Criteria) this;
        }

        public Criteria andFromDeptLessThanOrEqualTo(String value) {
            addCriterion("from_dept <=", value, "fromDept");
            return (Criteria) this;
        }

        public Criteria andFromDeptLike(String value) {
            addCriterion("from_dept like", value, "fromDept");
            return (Criteria) this;
        }

        public Criteria andFromDeptNotLike(String value) {
            addCriterion("from_dept not like", value, "fromDept");
            return (Criteria) this;
        }

        public Criteria andFromDeptIn(List<String> values) {
            addCriterion("from_dept in", values, "fromDept");
            return (Criteria) this;
        }

        public Criteria andFromDeptNotIn(List<String> values) {
            addCriterion("from_dept not in", values, "fromDept");
            return (Criteria) this;
        }

        public Criteria andFromDeptBetween(String value1, String value2) {
            addCriterion("from_dept between", value1, value2, "fromDept");
            return (Criteria) this;
        }

        public Criteria andFromDeptNotBetween(String value1, String value2) {
            addCriterion("from_dept not between", value1, value2, "fromDept");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgIsNull() {
            addCriterion("from_head_img is null");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgIsNotNull() {
            addCriterion("from_head_img is not null");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgEqualTo(String value) {
            addCriterion("from_head_img =", value, "fromHeadImg");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgNotEqualTo(String value) {
            addCriterion("from_head_img <>", value, "fromHeadImg");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgGreaterThan(String value) {
            addCriterion("from_head_img >", value, "fromHeadImg");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgGreaterThanOrEqualTo(String value) {
            addCriterion("from_head_img >=", value, "fromHeadImg");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgLessThan(String value) {
            addCriterion("from_head_img <", value, "fromHeadImg");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgLessThanOrEqualTo(String value) {
            addCriterion("from_head_img <=", value, "fromHeadImg");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgLike(String value) {
            addCriterion("from_head_img like", value, "fromHeadImg");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgNotLike(String value) {
            addCriterion("from_head_img not like", value, "fromHeadImg");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgIn(List<String> values) {
            addCriterion("from_head_img in", values, "fromHeadImg");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgNotIn(List<String> values) {
            addCriterion("from_head_img not in", values, "fromHeadImg");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgBetween(String value1, String value2) {
            addCriterion("from_head_img between", value1, value2, "fromHeadImg");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgNotBetween(String value1, String value2) {
            addCriterion("from_head_img not between", value1, value2, "fromHeadImg");
            return (Criteria) this;
        }

        public Criteria andToIdIsNull() {
            addCriterion("to_id is null");
            return (Criteria) this;
        }

        public Criteria andToIdIsNotNull() {
            addCriterion("to_id is not null");
            return (Criteria) this;
        }

        public Criteria andToIdEqualTo(Long value) {
            addCriterion("to_id =", value, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdNotEqualTo(Long value) {
            addCriterion("to_id <>", value, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdGreaterThan(Long value) {
            addCriterion("to_id >", value, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdGreaterThanOrEqualTo(Long value) {
            addCriterion("to_id >=", value, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdLessThan(Long value) {
            addCriterion("to_id <", value, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdLessThanOrEqualTo(Long value) {
            addCriterion("to_id <=", value, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdIn(List<Long> values) {
            addCriterion("to_id in", values, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdNotIn(List<Long> values) {
            addCriterion("to_id not in", values, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdBetween(Long value1, Long value2) {
            addCriterion("to_id between", value1, value2, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdNotBetween(Long value1, Long value2) {
            addCriterion("to_id not between", value1, value2, "toId");
            return (Criteria) this;
        }

        public Criteria andToCodeIsNull() {
            addCriterion("to_code is null");
            return (Criteria) this;
        }

        public Criteria andToCodeIsNotNull() {
            addCriterion("to_code is not null");
            return (Criteria) this;
        }

        public Criteria andToCodeEqualTo(String value) {
            addCriterion("to_code =", value, "toCode");
            return (Criteria) this;
        }

        public Criteria andToCodeNotEqualTo(String value) {
            addCriterion("to_code <>", value, "toCode");
            return (Criteria) this;
        }

        public Criteria andToCodeGreaterThan(String value) {
            addCriterion("to_code >", value, "toCode");
            return (Criteria) this;
        }

        public Criteria andToCodeGreaterThanOrEqualTo(String value) {
            addCriterion("to_code >=", value, "toCode");
            return (Criteria) this;
        }

        public Criteria andToCodeLessThan(String value) {
            addCriterion("to_code <", value, "toCode");
            return (Criteria) this;
        }

        public Criteria andToCodeLessThanOrEqualTo(String value) {
            addCriterion("to_code <=", value, "toCode");
            return (Criteria) this;
        }

        public Criteria andToCodeLike(String value) {
            addCriterion("to_code like", value, "toCode");
            return (Criteria) this;
        }

        public Criteria andToCodeNotLike(String value) {
            addCriterion("to_code not like", value, "toCode");
            return (Criteria) this;
        }

        public Criteria andToCodeIn(List<String> values) {
            addCriterion("to_code in", values, "toCode");
            return (Criteria) this;
        }

        public Criteria andToCodeNotIn(List<String> values) {
            addCriterion("to_code not in", values, "toCode");
            return (Criteria) this;
        }

        public Criteria andToCodeBetween(String value1, String value2) {
            addCriterion("to_code between", value1, value2, "toCode");
            return (Criteria) this;
        }

        public Criteria andToCodeNotBetween(String value1, String value2) {
            addCriterion("to_code not between", value1, value2, "toCode");
            return (Criteria) this;
        }

        public Criteria andToNameIsNull() {
            addCriterion("to_name is null");
            return (Criteria) this;
        }

        public Criteria andToNameIsNotNull() {
            addCriterion("to_name is not null");
            return (Criteria) this;
        }

        public Criteria andToNameEqualTo(String value) {
            addCriterion("to_name =", value, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameNotEqualTo(String value) {
            addCriterion("to_name <>", value, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameGreaterThan(String value) {
            addCriterion("to_name >", value, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameGreaterThanOrEqualTo(String value) {
            addCriterion("to_name >=", value, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameLessThan(String value) {
            addCriterion("to_name <", value, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameLessThanOrEqualTo(String value) {
            addCriterion("to_name <=", value, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameLike(String value) {
            addCriterion("to_name like", value, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameNotLike(String value) {
            addCriterion("to_name not like", value, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameIn(List<String> values) {
            addCriterion("to_name in", values, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameNotIn(List<String> values) {
            addCriterion("to_name not in", values, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameBetween(String value1, String value2) {
            addCriterion("to_name between", value1, value2, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameNotBetween(String value1, String value2) {
            addCriterion("to_name not between", value1, value2, "toName");
            return (Criteria) this;
        }

        public Criteria andToDeptIsNull() {
            addCriterion("to_dept is null");
            return (Criteria) this;
        }

        public Criteria andToDeptIsNotNull() {
            addCriterion("to_dept is not null");
            return (Criteria) this;
        }

        public Criteria andToDeptEqualTo(String value) {
            addCriterion("to_dept =", value, "toDept");
            return (Criteria) this;
        }

        public Criteria andToDeptNotEqualTo(String value) {
            addCriterion("to_dept <>", value, "toDept");
            return (Criteria) this;
        }

        public Criteria andToDeptGreaterThan(String value) {
            addCriterion("to_dept >", value, "toDept");
            return (Criteria) this;
        }

        public Criteria andToDeptGreaterThanOrEqualTo(String value) {
            addCriterion("to_dept >=", value, "toDept");
            return (Criteria) this;
        }

        public Criteria andToDeptLessThan(String value) {
            addCriterion("to_dept <", value, "toDept");
            return (Criteria) this;
        }

        public Criteria andToDeptLessThanOrEqualTo(String value) {
            addCriterion("to_dept <=", value, "toDept");
            return (Criteria) this;
        }

        public Criteria andToDeptLike(String value) {
            addCriterion("to_dept like", value, "toDept");
            return (Criteria) this;
        }

        public Criteria andToDeptNotLike(String value) {
            addCriterion("to_dept not like", value, "toDept");
            return (Criteria) this;
        }

        public Criteria andToDeptIn(List<String> values) {
            addCriterion("to_dept in", values, "toDept");
            return (Criteria) this;
        }

        public Criteria andToDeptNotIn(List<String> values) {
            addCriterion("to_dept not in", values, "toDept");
            return (Criteria) this;
        }

        public Criteria andToDeptBetween(String value1, String value2) {
            addCriterion("to_dept between", value1, value2, "toDept");
            return (Criteria) this;
        }

        public Criteria andToDeptNotBetween(String value1, String value2) {
            addCriterion("to_dept not between", value1, value2, "toDept");
            return (Criteria) this;
        }

        public Criteria andToHeadImgIsNull() {
            addCriterion("to_head_img is null");
            return (Criteria) this;
        }

        public Criteria andToHeadImgIsNotNull() {
            addCriterion("to_head_img is not null");
            return (Criteria) this;
        }

        public Criteria andToHeadImgEqualTo(String value) {
            addCriterion("to_head_img =", value, "toHeadImg");
            return (Criteria) this;
        }

        public Criteria andToHeadImgNotEqualTo(String value) {
            addCriterion("to_head_img <>", value, "toHeadImg");
            return (Criteria) this;
        }

        public Criteria andToHeadImgGreaterThan(String value) {
            addCriterion("to_head_img >", value, "toHeadImg");
            return (Criteria) this;
        }

        public Criteria andToHeadImgGreaterThanOrEqualTo(String value) {
            addCriterion("to_head_img >=", value, "toHeadImg");
            return (Criteria) this;
        }

        public Criteria andToHeadImgLessThan(String value) {
            addCriterion("to_head_img <", value, "toHeadImg");
            return (Criteria) this;
        }

        public Criteria andToHeadImgLessThanOrEqualTo(String value) {
            addCriterion("to_head_img <=", value, "toHeadImg");
            return (Criteria) this;
        }

        public Criteria andToHeadImgLike(String value) {
            addCriterion("to_head_img like", value, "toHeadImg");
            return (Criteria) this;
        }

        public Criteria andToHeadImgNotLike(String value) {
            addCriterion("to_head_img not like", value, "toHeadImg");
            return (Criteria) this;
        }

        public Criteria andToHeadImgIn(List<String> values) {
            addCriterion("to_head_img in", values, "toHeadImg");
            return (Criteria) this;
        }

        public Criteria andToHeadImgNotIn(List<String> values) {
            addCriterion("to_head_img not in", values, "toHeadImg");
            return (Criteria) this;
        }

        public Criteria andToHeadImgBetween(String value1, String value2) {
            addCriterion("to_head_img between", value1, value2, "toHeadImg");
            return (Criteria) this;
        }

        public Criteria andToHeadImgNotBetween(String value1, String value2) {
            addCriterion("to_head_img not between", value1, value2, "toHeadImg");
            return (Criteria) this;
        }

        public Criteria andApproveStatusIsNull() {
            addCriterion("approve_status is null");
            return (Criteria) this;
        }

        public Criteria andApproveStatusIsNotNull() {
            addCriterion("approve_status is not null");
            return (Criteria) this;
        }

        public Criteria andApproveStatusEqualTo(String value) {
            addCriterion("approve_status =", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusNotEqualTo(String value) {
            addCriterion("approve_status <>", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusGreaterThan(String value) {
            addCriterion("approve_status >", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusGreaterThanOrEqualTo(String value) {
            addCriterion("approve_status >=", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusLessThan(String value) {
            addCriterion("approve_status <", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusLessThanOrEqualTo(String value) {
            addCriterion("approve_status <=", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusLike(String value) {
            addCriterion("approve_status like", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusNotLike(String value) {
            addCriterion("approve_status not like", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusIn(List<String> values) {
            addCriterion("approve_status in", values, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusNotIn(List<String> values) {
            addCriterion("approve_status not in", values, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusBetween(String value1, String value2) {
            addCriterion("approve_status between", value1, value2, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusNotBetween(String value1, String value2) {
            addCriterion("approve_status not between", value1, value2, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveTextIsNull() {
            addCriterion("approve_text is null");
            return (Criteria) this;
        }

        public Criteria andApproveTextIsNotNull() {
            addCriterion("approve_text is not null");
            return (Criteria) this;
        }

        public Criteria andApproveTextEqualTo(String value) {
            addCriterion("approve_text =", value, "approveText");
            return (Criteria) this;
        }

        public Criteria andApproveTextNotEqualTo(String value) {
            addCriterion("approve_text <>", value, "approveText");
            return (Criteria) this;
        }

        public Criteria andApproveTextGreaterThan(String value) {
            addCriterion("approve_text >", value, "approveText");
            return (Criteria) this;
        }

        public Criteria andApproveTextGreaterThanOrEqualTo(String value) {
            addCriterion("approve_text >=", value, "approveText");
            return (Criteria) this;
        }

        public Criteria andApproveTextLessThan(String value) {
            addCriterion("approve_text <", value, "approveText");
            return (Criteria) this;
        }

        public Criteria andApproveTextLessThanOrEqualTo(String value) {
            addCriterion("approve_text <=", value, "approveText");
            return (Criteria) this;
        }

        public Criteria andApproveTextLike(String value) {
            addCriterion("approve_text like", value, "approveText");
            return (Criteria) this;
        }

        public Criteria andApproveTextNotLike(String value) {
            addCriterion("approve_text not like", value, "approveText");
            return (Criteria) this;
        }

        public Criteria andApproveTextIn(List<String> values) {
            addCriterion("approve_text in", values, "approveText");
            return (Criteria) this;
        }

        public Criteria andApproveTextNotIn(List<String> values) {
            addCriterion("approve_text not in", values, "approveText");
            return (Criteria) this;
        }

        public Criteria andApproveTextBetween(String value1, String value2) {
            addCriterion("approve_text between", value1, value2, "approveText");
            return (Criteria) this;
        }

        public Criteria andApproveTextNotBetween(String value1, String value2) {
            addCriterion("approve_text not between", value1, value2, "approveText");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("created_time is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("created_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(Date value) {
            addCriterion("created_time =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(Date value) {
            addCriterion("created_time <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(Date value) {
            addCriterion("created_time >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("created_time >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(Date value) {
            addCriterion("created_time <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("created_time <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<Date> values) {
            addCriterion("created_time in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<Date> values) {
            addCriterion("created_time not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(Date value1, Date value2) {
            addCriterion("created_time between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("created_time not between", value1, value2, "createdTime");
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