package com.sifu.sfcc.controller;

import com.sifu.sfcc.common.api.CommonPage;
import com.sifu.sfcc.common.api.CommonResult;
import com.sifu.sfcc.model.UmsMemberLevel;
import com.sifu.sfcc.service.UmsMemberLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "UmsMemberLevelController", description = "用户等级管理")
@RequestMapping("/memberLevel")
public class UmsMemberLevelController  {

    @Autowired
    UmsMemberLevelService memberLevelService;

    @ApiOperation(value = "添加配置用户等级")
    @PostMapping(value = "/add")
    public CommonResult add(@RequestBody UmsMemberLevel memberLevel){
        int rows = memberLevelService.add(memberLevel);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "修改用户等级")
    @PostMapping(value = "/update")
    public CommonResult update(@RequestBody UmsMemberLevel memberLevel){
        int rows = memberLevelService.update(memberLevel);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }


    @ApiOperation(value = "删除用户等级")
    @PostMapping(value = "/delete/{id}")
    public CommonResult delete(@PathVariable Long id){
        int rows = memberLevelService.delete(id);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "获取某个用户等级")
    @GetMapping(value = "/get/{id}")
    public CommonResult get(@PathVariable Long id){
        return CommonResult.success(memberLevelService.get(id));
    }
    @ApiOperation(value = "获取等级列表")
    @GetMapping(value = "/list")
    public CommonResult list(@ApiParam("等级名称") @RequestParam(value = "levelName" , required = false) String levelName ,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        return CommonResult.success(CommonPage.restPage(memberLevelService.list( levelName , pageSize ,pageNum)));
    }


}
