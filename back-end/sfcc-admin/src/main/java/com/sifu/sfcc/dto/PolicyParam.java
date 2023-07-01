package com.sifu.sfcc.dto;

import io.swagger.annotations.ApiModelProperty;

public class PolicyParam {
	 @ApiModelProperty(value = "政策个数")
	 private long count;
	 
	 @ApiModelProperty(value = "政策名称")
	 private String policyTitle;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public String getPolicyTitle() {
		return policyTitle;
	}

	public void setPolicyTitle(String policyTitle) {
		this.policyTitle = policyTitle;
	}

	
}
