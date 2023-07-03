package com.sifu.sfcc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel
public class CcWorderLabelParam {
    @ApiModelProperty(value = "工单标签")
    @NotBlank(message = "工单标签不能为空！")
    private String label;
    @ApiModelProperty(value = "工单编号")
    @NotBlank(message = "工单编号不能为空！")
    private String worderNo;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getWorderNo() {
        return worderNo;
    }

    public void setWorderNo(String worderNo) {
        this.worderNo = worderNo;
    }
}
