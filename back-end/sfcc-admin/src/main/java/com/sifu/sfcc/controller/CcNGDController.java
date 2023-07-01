package com.sifu.sfcc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sifu.sfcc.common.api.CommonResult;
import com.sifu.sfcc.mapper.CcCallTaskMapper;
import com.sifu.sfcc.model.CcCallTask;
import com.sifu.sfcc.model.CcCallTaskExample;
import com.sifu.sfcc.model.CcNaireAnswer;
import com.sifu.sfcc.security.service.RedisService;
import com.sifu.sfcc.service.CcCallTaskService;
import com.sifu.sfcc.service.CcNGDService;
import com.sifu.sfcc.service.CcNaireService;
import com.sifu.sfcc.service.XmkPolicyService;
import com.sifu.sfcc.service.XmkProjectService;
import com.sifu.sfcc.service.XmkTelsService;
import com.sifu.sfcc.util.HttpClientUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "CcNGDController", description = "NGD相关接口应用")
@RequestMapping("/ngd")
public class CcNGDController {
    Logger log = LoggerFactory.getLogger(CcNGDController.class);


    @Autowired
    CcNGDService ccNGDService;
    
    @Autowired
    XmkProjectService projectService;
    
    @Autowired
    XmkTelsService telService;
    
    @Autowired
    XmkPolicyService policyService;
    
    @Autowired
    CcNaireService naireService;
    
    @Autowired
    CcCallTaskService taskService;
    
    @Autowired
    CcCallTaskMapper taskMapper;

    @ApiOperation(value = "处理字符串接口")
    @PostMapping(value = "/dealMobile")
    public CommonResult dealMobile(@ApiParam("要处理的字符串") @RequestParam(value = "ngdStr") String ngdStr,
                            @ApiParam("电话号重复次数,默认不重复") @RequestParam(value = "count" , required = false , defaultValue = "1") Integer count){
        return CommonResult.success(ccNGDService.dealMobile(ngdStr , count));
    }
    
    @ApiOperation(value = "发送短信接口")
    @PostMapping(value = "/sendUrl")
    public CommonResult sendUrl(@ApiParam("电话号") @RequestParam(value = "mobile") String mobile,
    		                    @ApiParam("类型") @RequestParam(value = "type") String type,
    		                    @ApiParam("关键字") @RequestParam(value = "keyword") String keyword){
    	String result = ccNGDService.sendSms(mobile,type, keyword);
    	if("OK".equals(result)) {
    		return CommonResult.success(result);
    	}else {
    		return CommonResult.failed(result);
    	}
    }

    @ApiOperation(value = "存储当前会话问提内容")
    @PostMapping(value = "/saveQ")
    public CommonResult saveQ(@ApiParam("电话号") @RequestParam(value = "mobile") String mobile,
                                @ApiParam("类型") @RequestParam(value = "type") String type,
                                @ApiParam("关键字") @RequestParam(value = "keyword") String keyword,
                                @ApiParam("campaignId") @RequestParam(value = "campaignId") String campaignId){

        int result = ccNGDService.saveQ(mobile,type, keyword,campaignId);
        if(result > 0) {
            return CommonResult.success("存储成功");
        } else {
            return CommonResult.failed();
        }

    }

    @ApiOperation(value = "前端搜索接口发短信")
    @PostMapping(value = "/getSendInfo")
    public CommonResult getSendInfo(@ApiParam("类型") @RequestParam(value = "type" , required = false , defaultValue = "4") String type){

        List list = ccNGDService.getSendInfo(type);
        if(list.size() >= 0) {
            return CommonResult.success(list,"查询成功");
        } else {
            return CommonResult.failed();
        }

    }


    @ApiOperation(value = "NGD批量发送短信接口")
    @PostMapping(value = "/NGDSendSms")
    public CommonResult NGDSendSms(@RequestBody String ivr){
        log.info("ivr参数：{}",JSON.toJSONString(ivr));
        System.out.println(ivr);
        String sessionId = "8888888";
        String result = ccNGDService.NGDSendSms(sessionId);
        if("OK".equals(result)) return CommonResult.success("存储成功");
        else return CommonResult.failed();

    }
    
    @ApiOperation(value = "查询某地有多少个项目信息")
    @PostMapping(value = "/getCountByLocation")
    public CommonResult getCountByLocation(@ApiParam("地区") @RequestParam(value = "location") String location){
    	return CommonResult.success(projectService.getCountByLocation(location));
    }
    
    @ApiOperation(value = "根据地区查询推荐项目类型")
    @PostMapping(value = "/getRecommendType")
    public CommonResult getRecommendType(@ApiParam("地区") @RequestParam(value = "location") String location,
    		 @ApiParam("返回个数默认5") @RequestParam(value = "pageNum" , required = false , defaultValue = "5") Integer pageNum){
    	
    	return CommonResult.success(projectService.getRecommendType(location,pageNum));
    }
    
    @ApiOperation(value = "根据某地和项目类型，查询出项目总数")
    @PostMapping(value = "/getTotal")
    public CommonResult getTotal(@ApiParam("地区") @RequestParam(value = "location") String location){
    	
    	return CommonResult.success(projectService.getTotal(location));
    }

    @ApiOperation(value = "根据地点查询推荐项目名称。")
    @PostMapping(value = "/getRecommend")
    public CommonResult getRecommend(@ApiParam("地区") @RequestParam(value = "location") String location,
    		@ApiParam("返回个数默认2") @RequestParam(value = "pageNum" , required = false , defaultValue = "2") Integer pageNum){
    	
    	return CommonResult.success(projectService.getRecommend(location,pageNum));
    }
    
    @ApiOperation(value = "根据项目名称查地区")
    @PostMapping(value = "/getLocation")
    public CommonResult getLocation(@ApiParam("name") @RequestParam(value = "name") String name,
    		@ApiParam("返回个数默认2") @RequestParam(value = "pageNum" , required = false , defaultValue = "5") Integer pageNum){
    	
    	return CommonResult.success(projectService.getLocation(name,pageNum));
    }
    
    @ApiOperation(value = "根据某地，查询出电话")
    @PostMapping(value = "/getTel")
    public CommonResult getTel(@ApiParam("地区") @RequestParam(value = "location") String location){
    	return CommonResult.success(telService.getTels(location));
    }

    @ApiOperation(value = "根据某地市局，查询出信息")
    @PostMapping(value = "/getTelsByCompany")
    public CommonResult getTelsByCompany(@ApiParam("地市局") @RequestParam(value = "company") String company){
        return CommonResult.success(telService.getTelsByCompany(company));
    }
    
    @ApiOperation(value = "查询某地有多少个政策信息")
    @PostMapping(value = "/getPolicyCount")
    public CommonResult getPolicyCount(@ApiParam("地区") @RequestParam(value = "location") String location){
    	return CommonResult.success(policyService.getPolicyCount(location));
    }
    
    @ApiOperation(value = "根据地区查询推荐政策")
    @PostMapping(value = "/getPolicyRecommend")
    public CommonResult getPolicyRecommend(@ApiParam("地区") @RequestParam(value = "location") String location,
    		 @ApiParam("返回个数默认5") @RequestParam(value = "pageNum" , required = false , defaultValue = "5") Integer pageNum){
    	
    	return CommonResult.success(policyService.getPolicyRecommend(location,pageNum));
    }
    
    //获取欢迎语 和  第一个问题
    @ApiOperation(value = "问卷系统，返回开头语和第一个问题")
    @PostMapping(value = "/getQuestion")
    public CommonResult getQuestion(@ApiParam("phone") @RequestParam(value = "phone") String phone){
    	return CommonResult.success(naireService.getQuestion(phone));
    }
    
    
    //获取问题，如果是最后一个问题，给出标识和结束语
    @ApiOperation(value = "问卷系统，返回问题和结束语")
    @PostMapping(value = "/getNextQuestion")
    public CommonResult getNextQuestion(@ApiParam("phone") @RequestParam(value = "phone") String phone,
    		@ApiParam("问题id") @RequestParam(value = "id") String id,
    		@ApiParam("问题type") @RequestParam(value = "type") String type,
    		@ApiParam("回答答案") @RequestParam(value = "answer") String answer,
    		@ApiParam("是否") @RequestParam(value = "isNo") String isNo){
    	return CommonResult.success(naireService.getNextQuestion(phone,id,type,answer,isNo));
    }
    
    //获取问题，如果是最后一个问题，给出标识和结束语
    @ApiOperation(value = "发布任务测试接口")
    @PostMapping(value = "/test")
    public CommonResult test(@ApiParam("phone") @RequestParam(value = "phone") String phone){	
    		
    		CcCallTaskExample example = new CcCallTaskExample();
    		taskMapper.deleteByExample(example);
    		
    		CcCallTask task = new CcCallTask();
    		task.setIvrId(4);
    		task.setTitle("测试");
    		task.setNaireId(16L);
    		task.setMaxRingTime("60");
    		task.setFilter(phone);
    		task.setStatus(1);
    		task.setCreatedBy(13L);
    		task.setAgent("10000_50000");
    		task.setCreatedTime(new Date());
    		taskMapper.insertSelective(task);
    		
	    	String filter = phone;
			String str = "{\"name\":\"测试\",\"agent\":\"10000_50000\",\"tenantId\":\"10000\",\"isVgc\":\"1\",\"maxRingTime\":\"60\",\"callType\":1,\"ivrId\":\"4\",\"actionID\":\"\"}";
	    	String result = HttpClientUtil.postX("http://123.56.85.102:8098/6.0.x/config/addOutboundInstance", str);
	    	JSONObject res= JSON.parseObject(result);
	    	int code = res.getIntValue("code");
	    	String id = "";
	    	if(code == 200) {
	    		JSONObject data = res.getJSONObject("data");
	        	id = data.getString("id");
	        	
	        	String append = "{\"outboundId\":\""+ id +"  \",\"filter\":\""+ filter +"\",\"actionID\":\"\"}";
	        	String appendResult = HttpClientUtil.postX("http://123.56.85.102:8098/6.0.x/config/appendOutboundInstance", append);
	        	JSONObject appRes= JSON.parseObject(appendResult);
	        	int appCode = appRes.getIntValue("code");
	        	if(appCode == 200) {
	        		 String start = "{\"outboundId\":\""+ id +"\",\"tenantId\":\"10000\",\"dialMode\":\"1\"}";
	                 HttpClientUtil.httpPostHeadWithJSON("http://123.56.85.102:8099/config/StartOutbound", start,null);
	        	}
    	}
    	return CommonResult.success("1");
    }
    
    
    
}
