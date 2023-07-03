package com.sifu.sfcc.task;

import com.sifu.sfcc.enums.WorderStatusEnum;
import com.sifu.sfcc.mapper.CcWorkOrderMapper;
import com.sifu.sfcc.model.CcWorkOrder;
import com.sifu.sfcc.model.CcWorkOrderExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class WOrderTimeProcesser implements Runnable, InitializingBean, ApplicationContextAware {

    private WOrderTimeProcesser() {
    }

    private ApplicationContext applicationContext;

    public static WOrderTimeProcesser single = new WOrderTimeProcesser();

    private  Logger logger = LoggerFactory.getLogger(WOrderTimeProcesser.class);

    public static WOrderTimeProcesser getSingle() {
        return single;
    }

    /***
     * 处理中 或者初始化的工单 可以 做超时处理
     * @param order
     * @return
     */
    private boolean filter(CcWorkOrder order) {
        Optional<WorderStatusEnum> optional = Arrays.asList(WorderStatusEnum.values()).stream().filter(e -> e.getCode().toString().equals(order.getStatus())).findFirst();
        if (optional == Optional.<WorderStatusEnum>empty() || ObjectUtils.isEmpty(order.getSubmitTime())) {
            return false;
        }
        switch (optional.get()) {
            case INVALID:
                return false;
            case FINISH:
                return false;
            case TIME_OUT:
                return false;
            default:
                return true;
        }
    }

    public void addWorder(CcWorkOrder order) {
        if (filter(order)) {
            this.synchronousQueue.put(order);
        }
    }

    public void editWorkder(CcWorkOrder order) {
        if (filter(order)) {
            boolean isRemove = this.synchronousQueue.remove(order);
//        if(!isRemove){
//            throw new ApiException("无法修改 延迟时间，当前订单 已经超时");
//        }
            this.synchronousQueue.add(order);
        }


    }

    private final DelayQueue<CcWorkOrder> synchronousQueue = new DelayQueue<>();

    private final ExecutorService exe = Executors.newFixedThreadPool(2);

    private CcWorkOrderMapper ccWorkOrderMapper;

    public CcWorkOrderMapper getCcWorkOrderMapper() {
        return ccWorkOrderMapper;
    }

    public void setCcWorkOrderMapper(CcWorkOrderMapper ccWorkOrderMapper) {
        this.ccWorkOrderMapper = ccWorkOrderMapper;
    }

    public void setContext(ApplicationContext context) {
        this.applicationContext = context;
    }

    public void init() {
        this.ccWorkOrderMapper = applicationContext.getBean(CcWorkOrderMapper.class);
        //超时时间不为空 并且 不是作废工单 并且不是超时工单
        CcWorkOrderExample orderFilter = new CcWorkOrderExample();
        orderFilter.createCriteria().andSubmitTimeIsNotNull()
                .andStatusNotEqualTo(WorderStatusEnum.FINISH.getCode().toString())
                .andStatusNotEqualTo(WorderStatusEnum.INVALID.getCode().toString())
                .andStatusNotEqualTo(WorderStatusEnum.TIME_OUT.getCode().toString());
        List<CcWorkOrder> orderList = ccWorkOrderMapper.selectByExample(orderFilter);
        synchronousQueue.addAll(orderList);
    }

    @Override
    public void run() {
        //TimeOrderTask task = new TimeOrderTask();
        while (true) {
            CcWorkOrder order = null;
            try {
                order = synchronousQueue.take();
                logger.info("time out order={}",order);
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error("超时处理异常:{}",e);
            }
            Date killDate = order.getSubmitTime();
            if(killDate.after(Calendar.getInstance().getTime())){
                logger.info("repeat offer queue:order={}",order);
                synchronousQueue.add(order);
                continue;
            }
            CcWorkOrderExample orderFilter = new CcWorkOrderExample();
            orderFilter.createCriteria().andCodeEqualTo(order.getCode());
            List<CcWorkOrder> ccWorkOrders = ccWorkOrderMapper.selectByExample(orderFilter);
            if(CollectionUtils.isEmpty(ccWorkOrders)){
                continue;
            }
            CcWorkOrder currentOrder = ccWorkOrders.get(0);
            if(!filter(currentOrder)){
                continue;
            }

            CcWorkOrder newOrder = new CcWorkOrder();
            newOrder.setStatus(WorderStatusEnum.TIME_OUT.getCode().toString());
            newOrder.setUpdateTime(Calendar.getInstance().getTime());
            CcWorkOrderExample example = new CcWorkOrderExample();
            example.createCriteria().andCodeEqualTo(order.getCode());
            ccWorkOrderMapper.updateByExampleSelective(newOrder, example);
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        WOrderTimeProcesser.getSingle().init();
        exe.execute(WOrderTimeProcesser.getSingle());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        WOrderTimeProcesser.getSingle().setContext(applicationContext);
    }


}
