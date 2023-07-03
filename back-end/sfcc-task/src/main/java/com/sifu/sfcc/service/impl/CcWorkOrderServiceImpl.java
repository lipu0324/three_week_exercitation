package com.sifu.sfcc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sifu.sfcc.dao.CcWorkOrderDao;
import com.sifu.sfcc.mapper.*;
import com.sifu.sfcc.model.CcWorkOrderTrace;
import com.sifu.sfcc.model.UmsAdmin;
import com.sifu.sfcc.model.UmsAdminExample;
import com.sifu.sfcc.service.CcWorkOrderService;
import com.sifu.sfcc.utils.HttpClientUtil;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class CcWorkOrderServiceImpl implements CcWorkOrderService {
	
    Logger logger = LoggerFactory.getLogger(CcWorkOrderServiceImpl.class);
    
    @Autowired
    private CcWorkOrderDao orderDao;
    
    @Autowired
    private UmsAdminMapper adminMapper;
    
    @Scheduled(cron = "0 0 10 ? * MON-FRI")
	@Override
	public void AICall() {
    	List<CcWorkOrderTrace> trace = orderDao.listTask();
    	
    	if(trace.size() > 0) {
    		//订单处理人id:dealer_id
    		
	    	List<Long> dealerId = trace.stream().map(CcWorkOrderTrace::getToId).collect(Collectors.toList());
	    	UmsAdminExample example1 = new UmsAdminExample();
	    	UmsAdminExample.Criteria criteria2 = example1.createCriteria();
			
			criteria2.andIdIn(dealerId);
	    	List<UmsAdmin> members = adminMapper.selectByExample(example1);
	    	List<String> phones = members.stream().map(UmsAdmin::getPhone).collect(Collectors.toList());
	    	List<String> listWithoutDuplicates = phones.stream().distinct().collect(Collectors.toList());
	    	String filter = StringUtils.join(listWithoutDuplicates.toArray(), ",");
            
        	String str = "{\"name\":\"催办\",\"agent\":\"10000_50000\",\"tenantId\":\"10000\",\"isVgc\":\"1\",\"maxRingTime\":\"60\",\"callType\":1,\"ivrId\":\"11\",\"actionID\":\"\"}";
        	String result = HttpClientUtil.postX("http://192.168.38.12:8098/6.0.x/config/addOutboundInstance", str);
        	JSONObject res= JSON.parseObject(result);
        	int code = res.getIntValue("code");
        	if(code == 200) {
        		JSONObject data = res.getJSONObject("data");
            	String id = data.getString("id");
            	
            	String append = "{\"outboundId\":\""+ id +"  \",\"filter\":\""+ filter +"\",\"actionID\":\"\"}";
            	String appendResult = HttpClientUtil.postX("http://192.168.38.12:8098/6.0.x/config/appendOutboundInstance", append);
            	JSONObject appRes= JSON.parseObject(appendResult);
            	int appCode = appRes.getIntValue("code");
            	if(appCode == 200) {
            		 String start = "{\"outboundId\":\""+ id +"\",\"tenantId\":\"10000\",\"dialMode\":\"1\"}";
	                 HttpClientUtil.httpPostHeadWithJSON("http://192.168.38.12:8099/config/StartOutbound", start,null);
            	}
        	}
    	}
	}
    
}
