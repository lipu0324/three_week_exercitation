package com.sifu.sfcc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
@ApiModel("工单创建")
public class CcWorkOrderParam implements Serializable {


    @ApiModelProperty(value = "档案编号", required = true)
    @NotBlank(message = "档案编号不能为空!")
    private String archivesCode;

    @ApiModelProperty(value = "工单类型 0:工单流转")
    @NotBlank(message = "工单类型不能为空!")
    private String  type;

    @ApiModelProperty(value = "工单内容", required = true)
//    @NotBlank(message = "工单内容不能为空!")
    private String content;

    @ApiModelProperty(value = "工单标题", required = true)
//    @NotBlank(message = "工单内容不能为空!")
    private String title;

    @ApiModelProperty(value = "发起人id 关联ums_member")
    private Long createId;

    @ApiModelProperty(value = "负责人/处理人id")
    private Long dealerId;

    @ApiModelProperty(value = "客户电话",required = true)
    @NotBlank(message = "客户电话不能为空!")
    private String phone;

    @ApiModelProperty(value = "客户姓名",required = true)
    @NotBlank(message = "客户姓名不能为空!")
    private String name;

    @ApiModelProperty(value = "客户地址",required = true)
    @NotBlank(message = "客户地址不能为空!")
    private String addr;

    @ApiModelProperty(value = "客户邮箱")
    private String email;

    @ApiModelProperty(value = "客户联系人")
    private String concat;

    @ApiModelProperty(value = "客户标签")
    private String label;

    @ApiModelProperty(value = "档案备份内容")
    private String archivesRemark;

    @ApiModelProperty(value = "工单备注")
    private String remark;

    @ApiModelProperty(value = "工单超时关闭时间")
    private String  submitTime;
    
    @ApiModelProperty(value = "sessionId")
    private String sessionId;
    
    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getArchivesCode() {
        return archivesCode;
    }

    public void setArchivesCode(String archivesCode) {
        this.archivesCode = archivesCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
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

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Long getDealerId() {
        return dealerId;
    }

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getConcat() {
        return concat;
    }

    public void setConcat(String concat) {
        this.concat = concat;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

}
