package com.sifu.sfcc.vo.workorder;

import com.sifu.sfcc.model.CcOperateLog;
import com.sifu.sfcc.model.CcWorkOrder;
import com.sifu.sfcc.model.CcWorkOrderTrace;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "工单")
public class WorkOrderVO  extends CcWorkOrder {

    @ApiModelProperty("审批列表")
    private List<CcWorkOrderTrace> approveList;

    @ApiModelProperty("修改列表")
    private List<CcOperateLog> operateLogs;

    public List<CcWorkOrderTrace> getApproveList() {
        return approveList;
    }

    public void setApproveList(List<CcWorkOrderTrace> approveList) {
        this.approveList = approveList;
    }

    public List<CcOperateLog> getOperateLogs() {
        return operateLogs;
    }

    public void setOperateLogs(List<CcOperateLog> operateLogs) {
        this.operateLogs = operateLogs;
    }
}
