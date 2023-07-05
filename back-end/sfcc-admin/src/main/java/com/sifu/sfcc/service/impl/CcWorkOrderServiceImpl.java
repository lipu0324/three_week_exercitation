package com.sifu.sfcc.service.impl;

import com.alibaba.excel.util.CollectionUtils;
import com.alibaba.excel.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.sifu.sfcc.common.exception.ApiException;
import com.sifu.sfcc.common.utils.DateUtil;
import com.sifu.sfcc.dao.CcWorkOrderDao;
import com.sifu.sfcc.dao.UmsDeptDao;
import com.sifu.sfcc.dto.CcApproveParam;
import com.sifu.sfcc.dto.CcWorkOrderCount;
import com.sifu.sfcc.dto.CcWorkOrderParam;
import com.sifu.sfcc.entity.ws.MsgPackage;
import com.sifu.sfcc.enums.ApproveStatusEnum;
import com.sifu.sfcc.enums.WorderStatusEnum;
import com.sifu.sfcc.enums.ws.MsgTypeEnum;
import com.sifu.sfcc.mapper.*;
import com.sifu.sfcc.model.*;
import com.sifu.sfcc.service.CCWebSocket;
import com.sifu.sfcc.service.CcWorkOrderService;
import com.sifu.sfcc.service.UmsAdminService;
import com.sifu.sfcc.task.WOrderTimeProcesser;
import com.sifu.sfcc.util.CodeGeneratorUtil;
import com.sifu.sfcc.util.MsgUtils;
import com.sifu.sfcc.vo.workorder.WorkOrderVO;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class CcWorkOrderServiceImpl implements CcWorkOrderService {
    Logger logger = LoggerFactory.getLogger(CcWorkOrderServiceImpl.class);
    @Autowired
    private CcWorkOrderMapper ccWorkOrderMapper;

    @Autowired
    private CcWorkOrderTraceMapper ccWorkOrderTraceMapper;

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Autowired
    private UmsDeptDao umsDeptDao;

    @Autowired
    private UmsAdminService umsAdminService;

    @Autowired
    private CcOperateLogMapper operateLogMapper;

    public static final String ORDER_PERFIX = "WO";

    @Autowired
    private CCWebSocket ccWebSocket;

    @Autowired
    private CcWorkOrderDao ccWorkOrderDao;

    @Override
    public List<CcWorkOrder> list(Integer pageSize, Integer pageNum, Integer wstatus, String toDoText) {
        PageHelper.startPage(pageNum, pageSize);
        UmsAdmin umsAdmin = umsAdminService.getCurrentUserAdmin();
        if (!StringUtils.isEmpty(toDoText) && !toDoText.equals("\"\"")) {
            //工单待办人过滤
            UmsAdminExample adminFilter = new UmsAdminExample();
            adminFilter.createCriteria().andUsernameLike(toDoText);
            List<UmsAdmin> umsAdmins = umsAdminMapper.selectByExample(adminFilter);
            if (!CollectionUtils.isEmpty(umsAdmins)) {
                Long currentDlearId = umsAdmins.get(0).getId();
                return ccWorkOrderDao.listWorkOrder(umsAdmin.getId(), wstatus, currentDlearId);
            } else {//当前待办人不存在

                return new ArrayList<>();
            }
        }
        return ccWorkOrderDao.listWorkOrder(umsAdmin.getId(), wstatus, null);
//        CcWorkOrderExample ccWorkOrderExample = new CcWorkOrderExample();
//        ccWorkOrderExample.setOrderByClause("create_time desc");
//        //当前工单登录人
//        UmsAdmin umsAdmin = umsAdminService.getCurrentUserAdmin();
//        CcWorkOrderExample.Criteria whereCreate = buildOrderQueryFilter(ccWorkOrderExample, wstatus, toDoText);
//        whereCreate.andCreateIdEqualTo(umsAdmin.getId());
//
//        CcWorkOrderExample.Criteria whereTo = ccWorkOrderExample.createCriteria();
//        whereTo.andCurrentDealerIdEqualTo(umsAdmin.getId());
//        ccWorkOrderExample.or(whereTo);
//        //relation.crea
//
//
//        return ccWorkOrderMapper.selectByExampleWithBLOBs(ccWorkOrderExample);
    }

    public CcWorkOrderExample.Criteria buildOrderQueryFilter(CcWorkOrderExample ccWorkOrderExample, Integer wstatus, String toDoText) {
        CcWorkOrderExample.Criteria where = ccWorkOrderExample.createCriteria();
        //根据工单状态过滤
        Optional<WorderStatusEnum> optional = Arrays.stream(WorderStatusEnum.values()).filter(e -> e.getCode() == wstatus).findFirst();
        if (Optional.<WorderStatusEnum>empty() != optional) {
            where.andStatusEqualTo(optional.get().getCode().toString());
        }

        if (!StringUtils.isEmpty(toDoText) && !toDoText.equals("\"\"")) {
            //工单待办人过滤
            UmsAdminExample adminFilter = new UmsAdminExample();
            adminFilter.createCriteria().andUsernameLike(toDoText);
            List<UmsAdmin> umsAdmins = umsAdminMapper.selectByExample(adminFilter);
            if (!CollectionUtils.isEmpty(umsAdmins)) {
                Long currentDlearId = umsAdmins.get(0).getId();
                where.andCurrentDealerIdEqualTo(currentDlearId);
            } else {//当前待办人不存在
                where.andIdEqualTo(null);
            }
        }
        return where;
    }

    @Override
    public WorkOrderVO qryByOrderNo(String workOrderNO) {
        WorkOrderVO result = new WorkOrderVO();
        CcWorkOrderExample worderParam = new CcWorkOrderExample();
        CcWorkOrderExample.Criteria criteria = worderParam.createCriteria();
        criteria.andCodeEqualTo(workOrderNO);
        List<CcWorkOrder> workOrders = ccWorkOrderMapper.selectByExampleWithBLOBs(worderParam);
        if (CollectionUtils.isEmpty(workOrders)) {
            throw new ApiException("查询记录不存在!");
        }
        if (!CollectionUtils.isEmpty(workOrders)) {
            CcWorkOrder workOrder = workOrders.get(0);
            CcWorkOrderTraceExample traceFilter = new CcWorkOrderTraceExample();
            traceFilter.setOrderByClause("created_time desc");
            traceFilter.createCriteria()
                    .andWorkOrderIdEqualTo(workOrder.getId());
            List<CcWorkOrderTrace> traceList = ccWorkOrderTraceMapper.selectByExample(traceFilter);
            BeanUtils.copyProperties(workOrder, result);
            result.setApproveList(traceList);
        }

        CcOperateLogExample oplFilter = new CcOperateLogExample();
        oplFilter.setOrderByClause("create_time desc");
        oplFilter.createCriteria().andRecordIdEqualTo(workOrders.get(0).getCode());
        List<CcOperateLog> operateLogs = operateLogMapper.selectByExample(oplFilter);
        if (!CollectionUtils.isEmpty(operateLogs)) {
            //查询人员信息 更新操作日志人员信息
            UmsAdminExample adminFilter = new UmsAdminExample();
            Set<Long> ids = operateLogs.stream().collect(Collectors.toMap(CcOperateLog::getMemberId, e -> e, (k1, k2) -> k1)).keySet();
            adminFilter.createCriteria().andIdIn(new ArrayList<>(ids));
            List<UmsAdmin> umsAdmins = umsAdminMapper.selectByExample(adminFilter);
            Map<Long, UmsAdmin> umsAdminMap = umsAdmins.stream().collect(Collectors.toMap(UmsAdmin::getId, e -> e));
            for (CcOperateLog operateLog : operateLogs) {
                operateLog.setHeadImg(ObjectUtils.isEmpty(operateLog.getMemberId()) ? Strings.EMPTY : umsAdminMap.get(operateLog.getMemberId()).getHeadImg());
            }
            result.setOperateLogs(operateLogs);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CcWorkOrder create(CcWorkOrderParam param) {

        long creatorId = param.getCreateId();
        long seatId = param.getDealerId();
        //发起人
        UmsAdmin createMember = umsAdminMapper.selectByPrimaryKey(creatorId);
        UmsDept createDept = umsDeptDao.selectDeptById(createMember.getDeptId());

        //负责人
        UmsAdmin dealerMember = umsAdminMapper.selectByPrimaryKey(seatId);
        UmsDept dealerDept = umsDeptDao.selectDeptById(dealerMember.getDeptId());

        CcWorkOrder ccworkOrder = new CcWorkOrder();
        ccworkOrder.setCode(CodeGeneratorUtil.generatorCode(CcWorkOrderServiceImpl.ORDER_PERFIX));
        BeanUtils.copyProperties(param, ccworkOrder);
        ccworkOrder.setType(Integer.valueOf(param.getType()));


        //发起人
        ccworkOrder.setCreateDept(createDept.getDeptName());
        ccworkOrder.setCreator(createMember.getUsername());
        ccworkOrder.setCreateCode(createMember.getCode());
        ccworkOrder.setCreateId(param.getCreateId());
        //负责人
        ccworkOrder.setDealerId(param.getDealerId());
        ccworkOrder.setDealer(dealerMember.getUsername());
        ccworkOrder.setDealerCode(dealerMember.getCode());
        ccworkOrder.setDealerDept(dealerDept.getDeptName());

        //当前待办人
        ccworkOrder.setCurrentDealer(ccworkOrder.getDealer());
        ccworkOrder.setCurrentDealerId(ccworkOrder.getDealerId());

        Date currentDate = new Date();
        ccworkOrder.setCreateTime(currentDate);
        ccworkOrder.setStatus(WorderStatusEnum.INIT.getCode().toString());

        //超时时间
        String submitTimeStr = param.getSubmitTime();
        Date submitDate = DateUtil.getDateByString(submitTimeStr);
        ccworkOrder.setSubmitTime(submitDate);

        if (!StringUtils.isEmpty(param.getLabel())) {
            ccworkOrder.setLabel(param.getLabel());
        }
        ccWorkOrderMapper.insertSelective(ccworkOrder);
        if (!ObjectUtils.isEmpty(submitDate)) {//插入队列
            WOrderTimeProcesser.getSingle().addWorder(ccworkOrder);
        }

        CcWorkOrderTrace ccWorkOrderTrace = new CcWorkOrderTrace();
        ccWorkOrderTrace.setFromId(createMember.getId());
        ccWorkOrderTrace.setFromCode(createMember.getCode());
        ccWorkOrderTrace.setFromName(createMember.getUsername());
        ccWorkOrderTrace.setFromDept(createDept.getDeptName());
        ccWorkOrderTrace.setFromHeadImg(createMember.getHeadImg());

        ccWorkOrderTrace.setToId(dealerMember.getId());
        ccWorkOrderTrace.setToCode(dealerMember.getCode());
        ccWorkOrderTrace.setToName(dealerMember.getUsername());
        ccWorkOrderTrace.setToDept(dealerDept.getDeptName());
        ccWorkOrderTrace.setToHeadImg(dealerMember.getHeadImg());
        ccWorkOrderTrace.setApproveStatus(ApproveStatusEnum.TO_RECIEVE.getCode().toString());
        ccWorkOrderTrace.setWorkOrderId(ccworkOrder.getId());


        ccWorkOrderTrace.setCreatedTime(currentDate);


        //发送消息
        MsgPackage msgPkg = MsgUtils.buildMsg(ccWorkOrderTrace, MsgTypeEnum.TYPE_SINGLE, MsgTypeEnum.TYPE_QRY_TODO, ApproveStatusEnum.TO_RECIEVE, "创建订单后生成待办，请查收");
        ccWebSocket.sendMessage(msgPkg);

        ccWorkOrderTraceMapper.insertSelective(ccWorkOrderTrace);

        return ccworkOrder;


    }

    @Override
    public void transferWOrder(String worderCode, String toCode) {
        CcApproveParam approveParam = new CcApproveParam();
        approveParam.setApproveAction(ApproveStatusEnum.MOVED.getCode());
        approveParam.setToCode(toCode);
        approveParam.setApproveText("变更负责人".concat(toCode));
        processWorder(approveParam);
    }


    @Override
    public List<CcWorkOrder> toDOList() {
        UmsAdmin loginAdmin = umsAdminService.getCurrentUserAdmin();
        //查询待办或者已经接收的工单
        CcWorkOrderTraceExample traceFilter = new CcWorkOrderTraceExample();
        traceFilter.setOrderByClause("created_time desc");
        traceFilter.createCriteria()
                .andToCodeEqualTo(loginAdmin.getCode())
                .andApproveStatusIn(Arrays.asList(ApproveStatusEnum.TO_RECIEVE.getCode().toString(), ApproveStatusEnum.RECIEVED.getCode().toString()))
        ;
        //订单状态不能为无效的
        CcWorkOrderExample workOrderExample = new CcWorkOrderExample();
        workOrderExample.createCriteria().andStatusNotEqualTo(WorderStatusEnum.INVALID.getCode().toString());
        List<CcWorkOrder> ccWorkOrders = ccWorkOrderMapper.selectByExample(workOrderExample);
        List<CcWorkOrderTrace> traceList = ccWorkOrderTraceMapper.selectByExample(traceFilter);
        if (CollectionUtils.isEmpty(ccWorkOrders) || CollectionUtils.isEmpty(traceList)) {
            return new ArrayList<CcWorkOrder>(1);
        }
        Map<Long, Long> traceMap = traceList.stream().collect(Collectors.toMap(CcWorkOrderTrace::getWorkOrderId, CcWorkOrderTrace::getId, (key1, key2) -> key1));
        ccWorkOrders = ccWorkOrders.stream().filter(e -> traceMap.keySet().contains(e.getId())).collect(Collectors.toList());
        return ccWorkOrders;
    }

    @Override
    public void processToDO(Long traceId, String approveText, ApproveStatusEnum status) {

    }

    @Override
    public void processWorder(CcApproveParam approveParam) {

        Optional<ApproveStatusEnum> optionalApproveStatusEnum = Arrays.stream(ApproveStatusEnum.values()).filter(e -> approveParam.getApproveAction() == e.getCode())
                .findFirst();
        if (optionalApproveStatusEnum == Optional.<ApproveStatusEnum>empty()) {
            throw new ApiException("当前工单审批动作解析错误");
        }
        CcWorkOrderExample orderFilter = new CcWorkOrderExample();
        orderFilter.createCriteria().andCodeEqualTo(approveParam.getwOrderNO());
        List<CcWorkOrder> ccWorkOrders = ccWorkOrderMapper.selectByExample(orderFilter);
        if (CollectionUtils.isEmpty(ccWorkOrders)) {
            throw new ApiException("工单单编号有误，查询错误！");
        }
        CcWorkOrder workOrder = ccWorkOrders.get(0);
        AtomicBoolean isTimeOut = WorderStatusEnum.TIME_OUT.getCode().toString().equals(workOrder.getStatus()) ? new AtomicBoolean(true) : new AtomicBoolean(false);
        UmsAdmin loginAdmin = umsAdminService.getCurrentUserAdmin();
        //查询待办或者已经接收的工单
        CcWorkOrderTraceExample traceFilter = new CcWorkOrderTraceExample();
        traceFilter.setOrderByClause("created_time desc");

        List<String> approveStatusList =
                Arrays.asList(ApproveStatusEnum.TO_RECIEVE.getCode().toString(), ApproveStatusEnum.RECIEVED.getCode().toString());

        Boolean isCreatedWitchDraw = false;
        if (optionalApproveStatusEnum.get() == ApproveStatusEnum.WITHDRAW) {//撤回过滤条件
            if (loginAdmin.getId().equals(workOrder.getCreateId())) {//发起工单人 撤回工单
                isCreatedWitchDraw = true;
                approveStatusList = Arrays.asList(ApproveStatusEnum.TO_RECIEVE.getCode().toString());
                traceFilter.createCriteria().andWorkOrderIdEqualTo(workOrder.getId())
                        .andFromCodeEqualTo(loginAdmin.getCode())
                        .andApproveStatusIn(approveStatusList);
            } else {
                approveStatusList = Arrays.asList(ApproveStatusEnum.MOVED.getCode().toString());
                traceFilter.createCriteria().andWorkOrderIdEqualTo(workOrder.getId())
                        .andToCodeEqualTo(loginAdmin.getCode())
                        .andApproveStatusIn(approveStatusList);
            }


        } else if (optionalApproveStatusEnum.get() == ApproveStatusEnum.REMID) {//催办过滤条件
            approveStatusList = Arrays.asList(ApproveStatusEnum.TO_RECIEVE.getCode().toString());
            traceFilter.createCriteria().andWorkOrderIdEqualTo(workOrder.getId())
                    .andFromCodeEqualTo(loginAdmin.getCode())
                    .andToCodeEqualTo(approveParam.getToCode())
                    .andApproveStatusIn(approveStatusList);
        } else {
            traceFilter.createCriteria().andWorkOrderIdEqualTo(workOrder.getId())
                    .andToCodeEqualTo(loginAdmin.getCode())
                    .andApproveStatusIn(approveStatusList)
            ;

        }

        List<CcWorkOrderTrace> traces = ccWorkOrderTraceMapper.selectByExample(traceFilter);
        if (CollectionUtils.isEmpty(traces)) {
            throw new ApiException(String.format("%s,%s 当前工单人，不存在待办工单！", loginAdmin.getCode(), optionalApproveStatusEnum.get().getName()));
        }
        CcWorkOrderTrace toDO = traces.get(0);
        //所有审批动作都需要更新当前轨迹状态和审批意见
        toDO.setApproveStatus(optionalApproveStatusEnum.get().getCode().toString());
        toDO.setApproveText(approveParam.getApproveText());
        toDO.setUpdateTime(new Date());
        try {
            if (optionalApproveStatusEnum.get() == ApproveStatusEnum.WITHDRAW) { //撤回工单 需要处理 转办产生的工单轨迹 如果已经接收 无法撤回
                if (isCreatedWitchDraw) {
                    //更新创建人-》负责人之间的审批记录
                    ccWorkOrderTraceMapper.updateByPrimaryKeySelective(toDO);

                    //产生 创建人到创建人的审批记录
                    toDO.setToCode(toDO.getFromCode());
                    toDO.setToId(toDO.getFromId());
                    toDO.setToName(toDO.getFromName());

                } else {
                    CcWorkOrderTraceExample withDrawFilter = new CcWorkOrderTraceExample();

                    withDrawFilter.createCriteria()
                            .andWorkOrderIdEqualTo(toDO.getWorkOrderId())
                            .andFromCodeEqualTo(toDO.getToCode())
                            .andApproveStatusEqualTo(ApproveStatusEnum.TO_RECIEVE.getCode().toString());
                    List<CcWorkOrderTrace> withDrawTODOS = ccWorkOrderTraceMapper.selectByExample(withDrawFilter);
                    if (CollectionUtils.isEmpty(withDrawTODOS)) {
                        throw new ApiException("转办工单已经处理无法撤回!");
                    }
                    CcWorkOrderTrace witchDrawTODO = withDrawTODOS.get(0);
                    witchDrawTODO.setApproveStatus(ApproveStatusEnum.WITHDRAW.getCode().toString());
                    witchDrawTODO.setApproveText(approveParam.getApproveText());
                    witchDrawTODO.setUpdateTime(new Date());
                    toDO = witchDrawTODO;
                    ccWorkOrderTraceMapper.updateByPrimaryKeySelective(toDO);
                }

            } else if (optionalApproveStatusEnum.get() == ApproveStatusEnum.REMID) {

            } else {
                ccWorkOrderTraceMapper.updateByPrimaryKeySelective(toDO);
            }


            switch (optionalApproveStatusEnum.get()) {
                case RECIEVED://接收
                    workOrder.setCurrentDealer(toDO.getToName());//更新当前待办人
                    workOrder.setCurrentDealerId(toDO.getToId());
                    if (!isTimeOut.get()) {//非超时状态 才 更新工单状态
                        workOrder.setStatus(WorderStatusEnum.PENDING.getCode().toString());
                    }
                    break;
                //return;
                case REJECT://拒收 产生退回待办工单
                    CcWorkOrderTrace rejectNode = buildTrace(toDO, workOrder.getId());
                    workOrder.setCurrentDealer(rejectNode.getToName());//更新当前待办人
                    workOrder.setCurrentDealerId(rejectNode.getToId());
                    if (!isTimeOut.get()) {
                        workOrder.setStatus(WorderStatusEnum.PENDING.getCode().toString());
                    }
                    //发送待办查询消息
                    MsgPackage msgPkg = MsgUtils.buildMsg(rejectNode, MsgTypeEnum.TYPE_SINGLE, MsgTypeEnum.TYPE_QRY_TODO, ApproveStatusEnum.REJECT, "拒收请查收待办");
                    ccWebSocket.sendMessage(msgPkg);

                    ccWorkOrderTraceMapper.insertSelective(rejectNode);
                    break;
                case PASSED://处理  已完成工单主表
                    workOrder.setUpdateTime(new Date());
                    workOrder.setStatus(WorderStatusEnum.FINISH.getCode().toString());
                    ccWorkOrderMapper.updateByPrimaryKeySelective(workOrder);
                    break;
                case MOVED://转办 新增待办节点
                    //查询转办人
                    if (!isTimeOut.get()) {
                        workOrder.setStatus(WorderStatusEnum.PENDING.getCode().toString());
                    }
                    UmsAdminExample adminFilter = new UmsAdminExample();
                    adminFilter.createCriteria().andCodeEqualTo(approveParam.getToCode());
                    List<UmsAdmin> umsAdmins = umsAdminMapper.selectByExample(adminFilter);
                    if (!CollectionUtils.isEmpty(umsAdmins) && !StringUtils.isEmpty(umsAdmins.get(0).getDeptId())) {
                        UmsDept toDept = umsDeptDao.selectDeptById(umsAdmins.get(0).getDeptId());
                        UmsAdmin toAdmin = umsAdmins.get(0);

                        CcWorkOrderTrace move = buildTrace(toDO, workOrder.getId());
                        move.setToId(toAdmin.getId());
                        move.setToCode(toAdmin.getCode());
                        move.setToName(toAdmin.getUsername());
                        move.setToDept(toDept.getDeptName());

                        workOrder.setCurrentDealer(move.getToName());
                        workOrder.setCurrentDealerId(move.getToId());
                        //发送待办查询消息
                        MsgPackage msgMovePkg = MsgUtils.buildMsg(move, MsgTypeEnum.TYPE_SINGLE, MsgTypeEnum.TYPE_QRY_TODO, ApproveStatusEnum.MOVED, "转办请查收待办");
                        ccWebSocket.sendMessage(msgMovePkg);
                        ccWorkOrderTraceMapper.insertSelective(move);
                    }

                    break;
                case WITHDRAW://撤回 轨迹默认设置为 已接收
                    CcWorkOrderTrace withDrawNode = buildTrace(toDO, workOrder.getId());
                    //轨迹默认设置为 已接收
                    withDrawNode.setApproveStatus(ApproveStatusEnum.RECIEVED.getCode().toString());
                    workOrder.setCurrentDealerId(withDrawNode.getToId());//更新当前待办人
                    workOrder.setCurrentDealer(withDrawNode.getToName());
                    if (!isTimeOut.get()) {//非超时状态 才 更新工单状态
                        workOrder.setStatus(WorderStatusEnum.PENDING.getCode().toString());
                    }

                    //发送待办查询消息
                    if (!isCreatedWitchDraw) {//非 创建人撤回 才发送撤回消息给to
                        MsgPackage msgWithPkg = MsgUtils.buildMsg(withDrawNode, MsgTypeEnum.TYPE_SINGLE, MsgTypeEnum.TYPE_QRY_TODO, ApproveStatusEnum.WITHDRAW, "撤回请查收待办！");
                        //撤回消息反了交换
                        String exchange = msgWithPkg.getChannelFrom();
                        msgWithPkg.setChannelFrom(msgWithPkg.getChannelTo());
                        msgWithPkg.setChannelTo(exchange);
                        ccWebSocket.sendMessage(msgWithPkg);
                    }
                    ccWorkOrderTraceMapper.insertSelective(withDrawNode);
                    break;
                case REMID:
                    CcWorkOrderTrace remidNode = buildTrace(toDO, workOrder.getId());
                    //发送待办查询消息
                    MsgPackage remidPkg = MsgUtils.buildMsg(remidNode, MsgTypeEnum.TYPE_SINGLE, MsgTypeEnum.TYPE_REMIND_TODO, ApproveStatusEnum.REMID, "催办请查收待办！");
                    //催办人消息反了交换
                    String remindExchange = remidPkg.getChannelFrom();
                    remidPkg.setChannelFrom(remidPkg.getChannelTo());
                    remidPkg.setChannelTo(remindExchange);
                    ccWebSocket.sendMessage(remidPkg);
                    break;

            }
            //更新工单的待办人
            ccWorkOrderMapper.updateByPrimaryKeySelective(workOrder);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("process work order error:{}", e);
            if (e instanceof ApiException) {
                throw e;
            }
        }
    }

    @Override
    public List<CcWorkOrderTrace> qryWorderFlow(String worderNo) {
        return null;
    }

    @Override
    public int updWorder(CcWorkOrder workOrder) {
        CcWorkOrderExample whereFilter = new CcWorkOrderExample();
        whereFilter.createCriteria().andCodeEqualTo(workOrder.getCode());
        int row = ccWorkOrderMapper.updateByExampleSelective(workOrder, whereFilter);
        if (!ObjectUtils.isEmpty(workOrder.getSubmitTime())) {
            WOrderTimeProcesser.getSingle().editWorkder(workOrder);
        }
        return row;
    }

    @Override
    public int makeLabel(CcWorkOrder ccWorkOrder) {
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int cancelWorder(String worderCode) {
        UmsAdmin umsAdmin = umsAdminService.getCurrentUserAdmin();
        UmsDept dept = umsDeptDao.selectDeptById(umsAdmin.getDeptId());

        WorkOrderVO workOrderVO = this.qryByOrderNo(worderCode);
        List<CcWorkOrderTrace> workOrderTraces = workOrderVO.getApproveList();
        //最后一条流程记录 设置为 作废
        CcWorkOrderTrace lastTrace = workOrderTraces.get(0);
        lastTrace.setApproveStatus(ApproveStatusEnum.CANCEl.getCode().toString());

        CcWorkOrderTrace finalTrace = new CcWorkOrderTrace();
        finalTrace.setFromId(umsAdmin.getId());
        finalTrace.setFromCode(umsAdmin.getCode());
        finalTrace.setFromName(umsAdmin.getUsername());
        finalTrace.setFromDept(dept.getDeptName());
        finalTrace.setFromHeadImg(umsAdmin.getHeadImg());

        finalTrace.setToId(umsAdmin.getId());
        finalTrace.setToCode(umsAdmin.getCode());
        finalTrace.setToName(umsAdmin.getUsername());
        finalTrace.setToDept(dept.getDeptName());
        finalTrace.setToHeadImg(umsAdmin.getHeadImg());

        finalTrace.setApproveStatus(ApproveStatusEnum.PASSED.getCode().toString());
        finalTrace.setUpdateTime(new Date());
        finalTrace.setCreatedTime(new Date());
        finalTrace.setApproveText("作废产生");
        finalTrace.setWorkOrderId(lastTrace.getWorkOrderId());

        ccWorkOrderTraceMapper.updateByPrimaryKeySelective(lastTrace);
        ccWorkOrderTraceMapper.insertSelective(finalTrace);

        CcWorkOrder workOrder = new CcWorkOrder();
        workOrder.setCode(worderCode);
        workOrder.setStatus(WorderStatusEnum.INVALID.getCode().toString());
        workOrder.setUpdateTime(new Date());
        return this.updWorder(workOrder);
    }

    @Override
    public int worderTotalNum() {
        CcWorkOrderExample example = new CcWorkOrderExample();
        example.createCriteria().andCodeIsNotNull();
        List<CcWorkOrder> totals = ccWorkOrderMapper.selectByExample(example);
        return totals.size();
    }

    /***
     * 产生和待办相反的轨迹
     * @param toDO
     * @param worderId
     * @return
     */
    private CcWorkOrderTrace buildTrace(CcWorkOrderTrace toDO, Long worderId) {
        CcWorkOrderTrace traceNode = new CcWorkOrderTrace();
        traceNode.setWorkOrderId(worderId);

        traceNode.setApproveStatus(ApproveStatusEnum.TO_RECIEVE.getCode().toString());
        traceNode.setFromId(toDO.getToId());
        traceNode.setFromCode(toDO.getToCode());
        traceNode.setFromDept(toDO.getToDept());
        traceNode.setFromName(toDO.getToName());
        traceNode.setFromHeadImg(toDO.getToHeadImg());

        traceNode.setToId(toDO.getFromId());
        traceNode.setToCode(toDO.getFromCode());
        traceNode.setToDept(toDO.getFromDept());
        traceNode.setToName(toDO.getFromName());
        traceNode.setToHeadImg(toDO.getFromHeadImg());
        traceNode.setCreatedTime(new Date());
        return traceNode;
    }

	@Override
	public List<CcWorkOrderCount> getCount(String startTime, String endTime, Long dealerId) {
		List<CcWorkOrderCount> wo = ccWorkOrderDao.getCount(startTime, endTime, dealerId);
		long total = wo.stream().collect(Collectors.summingLong(CcWorkOrderCount::getCount));
		wo.forEach(v->{
			v.setTotal(total);
		});
		return wo;
	}
}
