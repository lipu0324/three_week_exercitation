package com.sifu.sfcc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
@ApiModel
public class CcDealerParam {
    @ApiModelProperty(value = "工单编号")
    @NotBlank(message = "工单编号必填！")
    private String worderNo;

    @ApiModelProperty(value = "负责人编号")
    @NotBlank(message = "负责人编号必填！")
    private String dealerCode;

    public String getWorderNo() {
        return worderNo;
    }

    public void setWorderNo(String worderNo) {
        this.worderNo = worderNo;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }
}
