package com.sifu.sfcc.dto;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class CcCallTaskParam implements Serializable {
    private Long id;

    @ApiModelProperty(value = "项目名称")
    private String title;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "0未开始，1进行中，2已结束")
    private Integer status;

    @ApiModelProperty(value = "外呼号")
    private String filter;

    @ApiModelProperty(value = "坐席号")
    private String agent;

    @ApiModelProperty(value = "是否去重 0是 1否 默认0是")
    private Integer removeRepetition;

    @ApiModelProperty(value = "ivr流程id")
    private Integer ivrId;

    @ApiModelProperty(value = "最大振铃时长 单位/秒（默认60）")
    private String maxRingTime;

    @ApiModelProperty(value = "问卷模板")
    private Long naireId;

    @ApiModelProperty(value = "创建人")
    private Long createdBy;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    
    @ApiModelProperty(value = "问卷名称")
    private String naireTitle;
    
    @ApiModelProperty(value = "任务id")
    private String actionId;
    
    @ApiModelProperty(value = "姓名")
    private String username;
    
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public Integer getRemoveRepetition() {
        return removeRepetition;
    }

    public void setRemoveRepetition(Integer removeRepetition) {
        this.removeRepetition = removeRepetition;
    }

    public Integer getIvrId() {
        return ivrId;
    }

    public void setIvrId(Integer ivrId) {
        this.ivrId = ivrId;
    }

    public String getMaxRingTime() {
        return maxRingTime;
    }

    public void setMaxRingTime(String maxRingTime) {
        this.maxRingTime = maxRingTime;
    }

    public Long getNaireId() {
        return naireId;
    }

    public void setNaireId(Long naireId) {
        this.naireId = naireId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
    

    public String getNaireTitle() {
		return naireTitle;
	}

	public void setNaireTitle(String naireTitle) {
		this.naireTitle = naireTitle;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	@Override
	public String toString() {
		return "CcCallTaskParam [id=" + id + ", title=" + title + ", remark=" + remark + ", status=" + status
				+ ", filter=" + filter + ", agent=" + agent + ", removeRepetition=" + removeRepetition + ", ivrId="
				+ ivrId + ", maxRingTime=" + maxRingTime + ", naireId=" + naireId + ", createdBy=" + createdBy
				+ ", createdTime=" + createdTime + ", naireTitle=" + naireTitle + "]";
	}

}