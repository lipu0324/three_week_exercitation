package com.sifu.sfcc.service;

import com.sifu.sfcc.dto.CcApproveParam;
import com.sifu.sfcc.dto.CcWorkOrderCount;
import com.sifu.sfcc.dto.CcWorkOrderParam;
import com.sifu.sfcc.enums.ApproveStatusEnum;
import com.sifu.sfcc.model.CcWorkOrder;
import com.sifu.sfcc.model.CcWorkOrderTrace;
import com.sifu.sfcc.vo.workorder.WorkOrderVO;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

public interface CcWorkOrderService {

    /***
     * 分页查询工单列表
     * @param name
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<CcWorkOrder> list( Integer pageSize , Integer pageNum,Integer wstatus,String toDoText);

    /***
     * 查看工单详情
     * @param workOrderNO 工单编号
     * @return
     */
    WorkOrderVO qryByOrderNo(String workOrderNO );

    /***
     * 创建工单
     * @param model
     * @return
     */
    CcWorkOrder create(CcWorkOrderParam model);

    /***
     * 工单流转
     * @param fromId
     * @param toId
     */
    void transferWOrder(String worderCode,String toCode);


    /***
     * 代办事项
     * @param accountId
     * @return
     */
    List<CcWorkOrder> toDOList();

    /***
     *处理待办
     * @param traceId 待办节点
     * @param approveText
     * @param status
     */
    void processToDO(Long traceId,String approveText, ApproveStatusEnum status);

    /***
     * 作废/终结工单
     * @param worderNo
     * @param status
     */
    void processWorder(CcApproveParam approveParam);

    /***
     * 通过工单编号查询 工单流程
     * @param worderNo 工单编号
     * @return
     */
    List<CcWorkOrderTrace> qryWorderFlow(String worderNo);

    /***
     * 修改工单
     * @param workOrder
     * @return
     */
    int updWorder(CcWorkOrder workOrder);

    /***
     * 打标签
     * @param ccWorkOrder
     * @return
     */
    int makeLabel(CcWorkOrder ccWorkOrder);

    /***
     * 作废工单
     * @param ccWorkOrder
     * @return
     */
    int cancelWorder(String worderCode);

    /***
     * 获取工单总数
     * @return
     */
    int worderTotalNum();

    List<CcWorkOrderCount> getCount(String startTime,String endTime, Long dealerId);
}
