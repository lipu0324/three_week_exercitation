package com.sifu.sfcc.controller;

import com.sifu.sfcc.common.api.CommonPage;
import com.sifu.sfcc.common.api.CommonResult;
import com.sifu.sfcc.dto.CcScoreParam;
import com.sifu.sfcc.model.CcSessionRecord;
import com.sifu.sfcc.service.CcSessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "CcSessionController", description = "会话")
@RequestMapping("/session")
public class CcSessionController  {

    @Autowired
    CcSessionService sessionService;
    

    @ApiOperation(value = "添加")
    @PostMapping(value = "/add")
    public CommonResult add(@RequestBody CcSessionRecord record){
        int rows = sessionService.add(record);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }
    
    @ApiOperation(value = "人工未接电话，自动生成档案，工单")
    @GetMapping(value = "/createWorkOrder")
    public CommonResult createWorkOrder(
    		@ApiParam("电话") @RequestParam(value = "phone") String phone,
    		@ApiParam("sessionId") @RequestParam(value = "sessionId" ) String sessionId){
        int rows = sessionService.createWorkOrder(phone,sessionId);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }
    
    @ApiOperation(value = "挂机评分")
    @PostMapping(value = "/createScore")
    public CommonResult createScore(@RequestBody CcScoreParam param){
        int rows = sessionService.createScore(param);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }
    
    @ApiOperation(value = "获取列表")
    @GetMapping(value = "/list")
    public CommonResult list(@ApiParam("电话") @RequestParam(value = "phone" , required = false) String phone ,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        return CommonResult.success(CommonPage.restPage(sessionService.list(phone , pageSize ,pageNum)));
    } 
    
}
