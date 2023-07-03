package com.sifu.sfcc.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class CcWorkOrderTrace implements Serializable {
    private Long id;

    @ApiModelProperty(value = "关联工单id")
    private Long workOrderId;

    @ApiModelProperty(value = "发起人")
    private Long fromId;

    @ApiModelProperty(value = "发起人编号")
    private String fromCode;

    @ApiModelProperty(value = "发起人姓名")
    private String fromName;

    @ApiModelProperty(value = "发起人部门")
    private String fromDept;

    @ApiModelProperty(value = "发起人头像")
    private String fromHeadImg;

    @ApiModelProperty(value = "接收人")
    private Long toId;

    @ApiModelProperty(value = "j接收人编号")
    private String toCode;

    @ApiModelProperty(value = "接收人姓名")
    private String toName;

    @ApiModelProperty(value = "接收人部门")
    private String toDept;

    @ApiModelProperty(value = "接收人头像")
    private String toHeadImg;

    @ApiModelProperty(value = "状态 0 待接收、1接收、2退回、3已处理")
    private String approveStatus;

    @ApiModelProperty(value = "审批内容")
    private String approveText;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "时间")
    private Date createdTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(Long workOrderId) {
        this.workOrderId = workOrderId;
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public String getFromCode() {
        return fromCode;
    }

    public void setFromCode(String fromCode) {
        this.fromCode = fromCode;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromDept() {
        return fromDept;
    }

    public void setFromDept(String fromDept) {
        this.fromDept = fromDept;
    }

    public String getFromHeadImg() {
        return fromHeadImg;
    }

    public void setFromHeadImg(String fromHeadImg) {
        this.fromHeadImg = fromHeadImg;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    public String getToCode() {
        return toCode;
    }

    public void setToCode(String toCode) {
        this.toCode = toCode;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToDept() {
        return toDept;
    }

    public void setToDept(String toDept) {
        this.toDept = toDept;
    }

    public String getToHeadImg() {
        return toHeadImg;
    }

    public void setToHeadImg(String toHeadImg) {
        this.toHeadImg = toHeadImg;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getApproveText() {
        return approveText;
    }

    public void setApproveText(String approveText) {
        this.approveText = approveText;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", workOrderId=").append(workOrderId);
        sb.append(", fromId=").append(fromId);
        sb.append(", fromCode=").append(fromCode);
        sb.append(", fromName=").append(fromName);
        sb.append(", fromDept=").append(fromDept);
        sb.append(", fromHeadImg=").append(fromHeadImg);
        sb.append(", toId=").append(toId);
        sb.append(", toCode=").append(toCode);
        sb.append(", toName=").append(toName);
        sb.append(", toDept=").append(toDept);
        sb.append(", toHeadImg=").append(toHeadImg);
        sb.append(", approveStatus=").append(approveStatus);
        sb.append(", approveText=").append(approveText);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}