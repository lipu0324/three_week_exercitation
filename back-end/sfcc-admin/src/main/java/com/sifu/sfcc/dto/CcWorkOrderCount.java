package com.sifu.sfcc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel("工单数量")
public class CcWorkOrderCount implements Serializable {

    @ApiModelProperty(value = "工单状态")
    private int status;
    
    @ApiModelProperty(value = "对应工单数量")
    private long count;
    
    @ApiModelProperty(value = "工单总数")
    private long total;
    
    @ApiModelProperty(value = "开始日期")
    private String startTime;
    
    @ApiModelProperty(value = "结束日期")
    private String endTime;
    
    @ApiModelProperty(value = "处理人")
    private Long dealerId;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}
    
    
    
}
