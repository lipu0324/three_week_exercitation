package com.sifu.sfcc.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class CcWorkOrder implements Serializable, Delayed {
    private Long id;

    @ApiModelProperty(value = "工单标题")
    private String title;

    @ApiModelProperty(value = "工单编号")
    private String code;

    @ApiModelProperty(value = "档案编号")
    private String archivesCode;

    @ApiModelProperty(value = "工单类型 0:工单流转")
    private Integer type;

    @ApiModelProperty(value = "工单状态 0:初始化 1:处理中 2:已完成 3 作废")
    private String status;

    @ApiModelProperty(value = "工单内容")
    private String content;

    @ApiModelProperty(value = "发起人 id关联 ums_admin ")
    private Long createId;

    @ApiModelProperty(value = "工单发起人名称")
    private String creator;

    @ApiModelProperty(value = "发起人所在部门名称")
    private String createDept;

    @ApiModelProperty(value = "发起人编号")
    private String createCode;

    @ApiModelProperty(value = "负责人/处理人id")
    private Long dealerId;

    @ApiModelProperty(value = "负责人/处理人编号")
    private String dealerCode;

    @ApiModelProperty(value = "负责人/处理人名称")
    private String dealer;

    @ApiModelProperty(value = "负责人/处理人所在部门")
    private String dealerDept;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "任务待处理人")
    private String currentDealer;

    private Long currentDealerId;

    @ApiModelProperty(value = "客户联系人")
    private String concat;

    @ApiModelProperty(value = "客户电话")
    private String phone;

    @ApiModelProperty(value = "客户姓名")
    private String name;

    @ApiModelProperty(value = "客户邮箱")
    private String email;

    @ApiModelProperty(value = "档案备份内容")
    private String archivesRemark;

    @ApiModelProperty(value = "工单备注")
    private String remark;

    @ApiModelProperty(value = "工单提交时间")
    private Date submitTime;

    @ApiModelProperty(value = "session_id")
    private String sessionId;

    @ApiModelProperty(value = "客户地址")
    private String addr;

    @ApiModelProperty(value = "工单标签")
    private String label;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getArchivesCode() {
        return archivesCode;
    }

    public void setArchivesCode(String archivesCode) {
        this.archivesCode = archivesCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateDept() {
        return createDept;
    }

    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public String getCreateCode() {
        return createCode;
    }

    public void setCreateCode(String createCode) {
        this.createCode = createCode;
    }

    public Long getDealerId() {
        return dealerId;
    }

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getDealer() {
        return dealer;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    public String getDealerDept() {
        return dealerDept;
    }

    public void setDealerDept(String dealerDept) {
        this.dealerDept = dealerDept;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCurrentDealer() {
        return currentDealer;
    }

    public void setCurrentDealer(String currentDealer) {
        this.currentDealer = currentDealer;
    }

    public Long getCurrentDealerId() {
        return currentDealerId;
    }

    public void setCurrentDealerId(Long currentDealerId) {
        this.currentDealerId = currentDealerId;
    }

    public String getConcat() {
        return concat;
    }

    public void setConcat(String concat) {
        this.concat = concat;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArchivesRemark() {
        return archivesRemark;
    }

    public void setArchivesRemark(String archivesRemark) {
        this.archivesRemark = archivesRemark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", code=").append(code);
        sb.append(", archivesCode=").append(archivesCode);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", content=").append(content);
        sb.append(", createId=").append(createId);
        sb.append(", creator=").append(creator);
        sb.append(", createDept=").append(createDept);
        sb.append(", createCode=").append(createCode);
        sb.append(", dealerId=").append(dealerId);
        sb.append(", dealerCode=").append(dealerCode);
        sb.append(", dealer=").append(dealer);
        sb.append(", dealerDept=").append(dealerDept);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", currentDealer=").append(currentDealer);
        sb.append(", currentDealerId=").append(currentDealerId);
        sb.append(", concat=").append(concat);
        sb.append(", phone=").append(phone);
        sb.append(", name=").append(name);
        sb.append(", email=").append(email);
        sb.append(", archivesRemark=").append(archivesRemark);
        sb.append(", remark=").append(remark);
        sb.append(", submitTime=").append(submitTime);
        sb.append(", sessionId=").append(sessionId);
        sb.append(", addr=").append(addr);
        sb.append(", label=").append(label);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return submitTime.getTime() - System.currentTimeMillis();
    }

    @Override
    public boolean equals(Object o) {
        CcWorkOrder order = (CcWorkOrder) o;
        return code.equals(order.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code);
    }

    @Override
    public int compareTo(Delayed o) {
        CcWorkOrder order = (CcWorkOrder) o;
        if(this.getSubmitTime().getTime() - order.getSubmitTime().getTime() < 0){
            return -1;
        }else{
            return 1;
        }
    }


}