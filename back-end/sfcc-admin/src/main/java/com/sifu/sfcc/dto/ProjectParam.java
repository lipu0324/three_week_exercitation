package com.sifu.sfcc.dto;

import io.swagger.annotations.ApiModelProperty;

public class ProjectParam {
	 @ApiModelProperty(value = "项目个数")
	 private long count;
	 
	 @ApiModelProperty(value = "项目名称")
	 private String projectName;
	 
	 @ApiModelProperty(value = "项目类型")
	 private String projectType;
	 
	 @ApiModelProperty(value = "项目总个数")
	 private long totalCount;
	 
	 @ApiModelProperty(value = "地区")
	 private String location;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
