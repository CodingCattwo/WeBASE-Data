package com.webank.webase.data.collect.dao.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbEventInfoExample {

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_event_info
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_event_info
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_event_info
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_event_info
     *
     * @mbg.generated
     */
    protected long start = -1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_event_info
     *
     * @mbg.generated
     */
    protected long count = -1;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_event_info
     *
     * @mbg.generated
     */
    public TbEventInfoExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_event_info
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_event_info
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_event_info
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_event_info
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_event_info
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_event_info
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_event_info
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_event_info
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_event_info
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_event_info
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_event_info
     *
     * @mbg.generated
     */
    public void setStart(long start) {
        this.start = start;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_event_info
     *
     * @mbg.generated
     */
    public long getStart() {
        return start;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_event_info
     *
     * @mbg.generated
     */
    public void setCount(long count) {
        this.count = count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_event_info
     *
     * @mbg.generated
     */
    public long getCount() {
        return count;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_event_info
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andContractNameIsNull() {
            addCriterion("contract_name is null");
            return (Criteria) this;
        }

        public Criteria andContractNameIsNotNull() {
            addCriterion("contract_name is not null");
            return (Criteria) this;
        }

        public Criteria andContractNameEqualTo(String value) {
            addCriterion("contract_name =", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameNotEqualTo(String value) {
            addCriterion("contract_name <>", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameGreaterThan(String value) {
            addCriterion("contract_name >", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameGreaterThanOrEqualTo(String value) {
            addCriterion("contract_name >=", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameLessThan(String value) {
            addCriterion("contract_name <", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameLessThanOrEqualTo(String value) {
            addCriterion("contract_name <=", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameLike(String value) {
            addCriterion("contract_name like", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameNotLike(String value) {
            addCriterion("contract_name not like", value, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameIn(List<String> values) {
            addCriterion("contract_name in", values, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameNotIn(List<String> values) {
            addCriterion("contract_name not in", values, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameBetween(String value1, String value2) {
            addCriterion("contract_name between", value1, value2, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractNameNotBetween(String value1, String value2) {
            addCriterion("contract_name not between", value1, value2, "contractName");
            return (Criteria) this;
        }

        public Criteria andContractAddressIsNull() {
            addCriterion("contract_address is null");
            return (Criteria) this;
        }

        public Criteria andContractAddressIsNotNull() {
            addCriterion("contract_address is not null");
            return (Criteria) this;
        }

        public Criteria andContractAddressEqualTo(String value) {
            addCriterion("contract_address =", value, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressNotEqualTo(String value) {
            addCriterion("contract_address <>", value, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressGreaterThan(String value) {
            addCriterion("contract_address >", value, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressGreaterThanOrEqualTo(String value) {
            addCriterion("contract_address >=", value, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressLessThan(String value) {
            addCriterion("contract_address <", value, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressLessThanOrEqualTo(String value) {
            addCriterion("contract_address <=", value, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressLike(String value) {
            addCriterion("contract_address like", value, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressNotLike(String value) {
            addCriterion("contract_address not like", value, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressIn(List<String> values) {
            addCriterion("contract_address in", values, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressNotIn(List<String> values) {
            addCriterion("contract_address not in", values, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressBetween(String value1, String value2) {
            addCriterion("contract_address between", value1, value2, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressNotBetween(String value1, String value2) {
            addCriterion("contract_address not between", value1, value2, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressIsNull() {
            addCriterion("user_address is null");
            return (Criteria) this;
        }

        public Criteria andUserAddressIsNotNull() {
            addCriterion("user_address is not null");
            return (Criteria) this;
        }

        public Criteria andUserAddressEqualTo(String value) {
            addCriterion("user_address =", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressNotEqualTo(String value) {
            addCriterion("user_address <>", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressGreaterThan(String value) {
            addCriterion("user_address >", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressGreaterThanOrEqualTo(String value) {
            addCriterion("user_address >=", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressLessThan(String value) {
            addCriterion("user_address <", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressLessThanOrEqualTo(String value) {
            addCriterion("user_address <=", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressLike(String value) {
            addCriterion("user_address like", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressNotLike(String value) {
            addCriterion("user_address not like", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressIn(List<String> values) {
            addCriterion("user_address in", values, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressNotIn(List<String> values) {
            addCriterion("user_address not in", values, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressBetween(String value1, String value2) {
            addCriterion("user_address between", value1, value2, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressNotBetween(String value1, String value2) {
            addCriterion("user_address not between", value1, value2, "userAddress");
            return (Criteria) this;
        }

        public Criteria andBlockNumberIsNull() {
            addCriterion("block_number is null");
            return (Criteria) this;
        }

        public Criteria andBlockNumberIsNotNull() {
            addCriterion("block_number is not null");
            return (Criteria) this;
        }

        public Criteria andBlockNumberEqualTo(Long value) {
            addCriterion("block_number =", value, "blockNumber");
            return (Criteria) this;
        }

        public Criteria andBlockNumberNotEqualTo(Long value) {
            addCriterion("block_number <>", value, "blockNumber");
            return (Criteria) this;
        }

        public Criteria andBlockNumberGreaterThan(Long value) {
            addCriterion("block_number >", value, "blockNumber");
            return (Criteria) this;
        }

        public Criteria andBlockNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("block_number >=", value, "blockNumber");
            return (Criteria) this;
        }

        public Criteria andBlockNumberLessThan(Long value) {
            addCriterion("block_number <", value, "blockNumber");
            return (Criteria) this;
        }

        public Criteria andBlockNumberLessThanOrEqualTo(Long value) {
            addCriterion("block_number <=", value, "blockNumber");
            return (Criteria) this;
        }

        public Criteria andBlockNumberIn(List<Long> values) {
            addCriterion("block_number in", values, "blockNumber");
            return (Criteria) this;
        }

        public Criteria andBlockNumberNotIn(List<Long> values) {
            addCriterion("block_number not in", values, "blockNumber");
            return (Criteria) this;
        }

        public Criteria andBlockNumberBetween(Long value1, Long value2) {
            addCriterion("block_number between", value1, value2, "blockNumber");
            return (Criteria) this;
        }

        public Criteria andBlockNumberNotBetween(Long value1, Long value2) {
            addCriterion("block_number not between", value1, value2, "blockNumber");
            return (Criteria) this;
        }

        public Criteria andTransHashIsNull() {
            addCriterion("trans_hash is null");
            return (Criteria) this;
        }

        public Criteria andTransHashIsNotNull() {
            addCriterion("trans_hash is not null");
            return (Criteria) this;
        }

        public Criteria andTransHashEqualTo(String value) {
            addCriterion("trans_hash =", value, "transHash");
            return (Criteria) this;
        }

        public Criteria andTransHashNotEqualTo(String value) {
            addCriterion("trans_hash <>", value, "transHash");
            return (Criteria) this;
        }

        public Criteria andTransHashGreaterThan(String value) {
            addCriterion("trans_hash >", value, "transHash");
            return (Criteria) this;
        }

        public Criteria andTransHashGreaterThanOrEqualTo(String value) {
            addCriterion("trans_hash >=", value, "transHash");
            return (Criteria) this;
        }

        public Criteria andTransHashLessThan(String value) {
            addCriterion("trans_hash <", value, "transHash");
            return (Criteria) this;
        }

        public Criteria andTransHashLessThanOrEqualTo(String value) {
            addCriterion("trans_hash <=", value, "transHash");
            return (Criteria) this;
        }

        public Criteria andTransHashLike(String value) {
            addCriterion("trans_hash like", value, "transHash");
            return (Criteria) this;
        }

        public Criteria andTransHashNotLike(String value) {
            addCriterion("trans_hash not like", value, "transHash");
            return (Criteria) this;
        }

        public Criteria andTransHashIn(List<String> values) {
            addCriterion("trans_hash in", values, "transHash");
            return (Criteria) this;
        }

        public Criteria andTransHashNotIn(List<String> values) {
            addCriterion("trans_hash not in", values, "transHash");
            return (Criteria) this;
        }

        public Criteria andTransHashBetween(String value1, String value2) {
            addCriterion("trans_hash between", value1, value2, "transHash");
            return (Criteria) this;
        }

        public Criteria andTransHashNotBetween(String value1, String value2) {
            addCriterion("trans_hash not between", value1, value2, "transHash");
            return (Criteria) this;
        }

        public Criteria andBlockTimestampIsNull() {
            addCriterion("block_timestamp is null");
            return (Criteria) this;
        }

        public Criteria andBlockTimestampIsNotNull() {
            addCriterion("block_timestamp is not null");
            return (Criteria) this;
        }

        public Criteria andBlockTimestampEqualTo(Date value) {
            addCriterion("block_timestamp =", value, "blockTimestamp");
            return (Criteria) this;
        }

        public Criteria andBlockTimestampNotEqualTo(Date value) {
            addCriterion("block_timestamp <>", value, "blockTimestamp");
            return (Criteria) this;
        }

        public Criteria andBlockTimestampGreaterThan(Date value) {
            addCriterion("block_timestamp >", value, "blockTimestamp");
            return (Criteria) this;
        }

        public Criteria andBlockTimestampGreaterThanOrEqualTo(Date value) {
            addCriterion("block_timestamp >=", value, "blockTimestamp");
            return (Criteria) this;
        }

        public Criteria andBlockTimestampLessThan(Date value) {
            addCriterion("block_timestamp <", value, "blockTimestamp");
            return (Criteria) this;
        }

        public Criteria andBlockTimestampLessThanOrEqualTo(Date value) {
            addCriterion("block_timestamp <=", value, "blockTimestamp");
            return (Criteria) this;
        }

        public Criteria andBlockTimestampIn(List<Date> values) {
            addCriterion("block_timestamp in", values, "blockTimestamp");
            return (Criteria) this;
        }

        public Criteria andBlockTimestampNotIn(List<Date> values) {
            addCriterion("block_timestamp not in", values, "blockTimestamp");
            return (Criteria) this;
        }

        public Criteria andBlockTimestampBetween(Date value1, Date value2) {
            addCriterion("block_timestamp between", value1, value2, "blockTimestamp");
            return (Criteria) this;
        }

        public Criteria andBlockTimestampNotBetween(Date value1, Date value2) {
            addCriterion("block_timestamp not between", value1, value2, "blockTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_event_info
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_event_info
     *
     * @mbg.generated
     */
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