package com.sifu.sfcc.controller;

import com.sifu.sfcc.common.api.CommonPage;
import com.sifu.sfcc.common.api.CommonResult;
import com.sifu.sfcc.model.CcCallTask;
import com.sifu.sfcc.service.CcCallTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "CcCallTaskController", description = "外呼任务")
@RequestMapping("/task")
public class CcCallTaskController  {

    @Autowired
    CcCallTaskService taskService;
    
    @ApiOperation(value = "添加外呼任务")
    @PostMapping(value = "/add")
    public CommonResult add(@RequestBody CcCallTask naire){
        int rows = taskService.add(naire);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "修改外呼任务")
    @PostMapping(value = "/update")
    public CommonResult update(@RequestBody CcCallTask naire){
        int rows = taskService.update(naire);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }
    
    @ApiOperation(value = "修改外呼任务状态")
    @PostMapping(value = "/updateStatus")
    public CommonResult updateStatus(@RequestBody CcCallTask naire){
        int rows = taskService.updateStatus(naire);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed("有其他正在进行的任务,请先关闭任务");
        }
    }

    @ApiOperation(value = "删除外呼任务")
    @PostMapping(value = "/delete/{id}")
    public CommonResult delete(@PathVariable Long id){
        int rows = taskService.delete(id);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "获取某个外呼任务")
    @GetMapping(value = "/get/{id}")
    public CommonResult get(@PathVariable Long id){
        return CommonResult.success(taskService.get(id));
    }
    
    @ApiOperation(value = "获取外呼任务列表")
    @GetMapping(value = "/list")
    public CommonResult list(@ApiParam("title") @RequestParam(value = "title" , required = false) String title ,
    						@ApiParam("状态") @RequestParam(value = "status",required = false) String status ,
    						@ApiParam("开始时间") @RequestParam(value = "startTime",required = false) String startTime ,
    						@ApiParam("结束时间") @RequestParam(value = "endTime",required = false) String endTime ,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        return CommonResult.success(CommonPage.restPage(taskService.list(title ,status,startTime, endTime, pageSize ,pageNum)));
    }
}
