package com.sifu.sfcc.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.sifu.sfcc.model.CcArchivesContent;
import com.sifu.sfcc.model.UmsMember;

import io.swagger.annotations.ApiModelProperty;

public class CcArchivesExport implements Serializable {
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "档案编号")
    private String code;

    @ApiModelProperty(value = "公司名称")
    private String name;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "公司地址")
    private String address;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "紧迫性")
    private String urgency;

    @ApiModelProperty(value = "电话")
    private Long phone;
    
    @ApiModelProperty(value = "标签")
    private String label;
    
    @ApiModelProperty(value = "创建人")
    private Long createdBy;
    
    @ApiModelProperty(value = "创建人部门")
    private String deptName;
    
    @ApiModelProperty(value = "坐席")
    private String sitDown;
    
    @ApiModelProperty(value = "创建时间")
    private String createdTime;

    @ApiModelProperty(value = "更新人")
    private Long updatedBy;

    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;
    
    @ApiModelProperty(value = "服务内容")
    private List<CcArchivesContent> contents;
    
    @ApiModelProperty(value = "来电记录")
    private List<UmsMember> calls;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    } 
    
    public String getSitDown() {
		return sitDown;
	}

	public void setSitDown(String sitDown) {
		this.sitDown = sitDown;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

	public List<CcArchivesContent> getContents() {
		return contents;
	}

	public void setContents(List<CcArchivesContent> contents) {
		this.contents = contents;
	}

	public List<UmsMember> getCalls() {
		return calls;
	}

	public void setCalls(List<UmsMember> calls) {
		this.calls = calls;
	}

}
