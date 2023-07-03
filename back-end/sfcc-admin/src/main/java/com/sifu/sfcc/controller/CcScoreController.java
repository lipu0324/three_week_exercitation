package com.sifu.sfcc.controller;

import com.sifu.sfcc.common.api.CommonResult;
import com.sifu.sfcc.service.CcScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "CcScoreController")
@RequestMapping("/score")
public class CcScoreController  {

  
    @Autowired
    CcScoreService scoreService;

    @ApiOperation(value = "返回1-5分各多少人评价 ")
    @PostMapping(value = "/getScore")
    public CommonResult index(@ApiParam("开始时间") @RequestParam(value = "startTime",required = false ) String startTime,
    		@ApiParam("结束时间") @RequestParam(value = "endTime",required = false ) String endTime,
    		@ApiParam("坐席号") @RequestParam(value = "code",required = false ) String code){
    	
        return CommonResult.success(scoreService.count(startTime, endTime, code), "成功");
    }

}
