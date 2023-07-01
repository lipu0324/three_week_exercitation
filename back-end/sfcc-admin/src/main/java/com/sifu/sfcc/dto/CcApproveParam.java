package com.sifu.sfcc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
@ApiModel
public class CcApproveParam {

    @ApiModelProperty("工单编号")
    @NotBlank(message = "工单编号不能为空！")
    private String wOrderNO;

    @ApiModelProperty("审批备注")
    private String approveText;

    @ApiModelProperty("1：接收 2：拒收（退回） 3:已处理 4：转办")
    @NotBlank(message = "工单动作不能为空！")
    private int approveAction;

    @ApiModelProperty("approveStatus == 4，该字段必填")
    private String toCode;

    public String getwOrderNO() {
        return wOrderNO;
    }

    public void setwOrderNO(String wOrderNO) {
        this.wOrderNO = wOrderNO;
    }

    public String getApproveText() {
        return approveText;
    }

    public void setApproveText(String approveText) {
        this.approveText = approveText;
    }

    public int getApproveAction() {
        return approveAction;
    }

    public void setApproveAction(int approveAction) {
        this.approveAction = approveAction;
    }

    public String getToCode() {
        return toCode;
    }

    public void setToCode(String toCode) {
        this.toCode = toCode;
    }
}
