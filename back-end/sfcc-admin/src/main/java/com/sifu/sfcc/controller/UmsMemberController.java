package com.sifu.sfcc.controller;

import com.sifu.sfcc.common.api.CommonPage;
import com.sifu.sfcc.common.api.CommonResult;
import com.sifu.sfcc.model.UmsMember;
import com.sifu.sfcc.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "UmsMemberController", description = "用户管理")
@RequestMapping("/memberInfo")
public class UmsMemberController  {

    @Autowired
    UmsMemberService memberService;

    @ApiOperation(value = "添加用户")
    @PostMapping(value = "/add")
    public CommonResult add(@RequestBody UmsMember member){
        int rows = memberService.add(member);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }
    
    @ApiOperation(value = "批量添加用户")
    @PostMapping(value = "/addBatch")
    public CommonResult addBatch(@RequestBody List<UmsMember> member){
        int rows = memberService.addBatch(member);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "修改用户")
    @PostMapping(value = "/update")
    public CommonResult update(@RequestBody UmsMember member){
        int rows = memberService.update(member);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }
    
    @ApiOperation(value = "合并档案用户")
    @PostMapping(value = "/updateBatch")
    public CommonResult updateBatch(@RequestBody List<UmsMember> member){
        int rows = memberService.updateBatch(member);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }
    
    @ApiOperation(value = "封禁和解封用户")
    @RequestMapping(value = "/ban/{id}", method = RequestMethod.POST)
    public CommonResult ban(@PathVariable Long id , @ApiParam("是否封禁 ： 0 - 禁用  1 - 启用 ") @RequestParam Integer status){
    
        int rows = memberService.ban(id,status);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }
    
    @ApiOperation(value = "批量封禁")
    @RequestMapping(value = "/batchBan", method = RequestMethod.POST)
    public CommonResult batchBan(@RequestParam("ids") List<Long> ids){
    
        int rows = memberService.batchBan(ids);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }


    @ApiOperation(value = "删除用户")
    @PostMapping(value = "/delete/{id}")
    public CommonResult delete(@PathVariable Long id){
        int rows = memberService.delete(id);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "获取某个用户")
    @GetMapping(value = "/get/{id}")
    public CommonResult get(@PathVariable Long id){
        return CommonResult.success(memberService.get(id));
    }
    
    
    @ApiOperation(value = "获取用户")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult list(@ApiParam("名称") @RequestParam(value = "name" , required = false) String name ,
    						 @ApiParam("电话") @RequestParam(value = "phone" , required = false) String phone ,
    						 @ApiParam("添加时间1") @RequestParam(value = "startTime" , required = false) String startTime ,
    						 @ApiParam("添加时间2") @RequestParam(value = "endTime" , required = false) String endTime ,
    						 @ApiParam("标签") @RequestParam(value = "label" , required = false) long label ,
    						 @ApiParam("会话次数1") @RequestParam(value = "startCount" , required = false) String startCount ,
    						 @ApiParam("会话次数2") @RequestParam(value = "endCount" , required = false) String endCount ,
    						 @ApiParam("业务员") @RequestParam(value = "salesman" , required = false) String salesman ,
    						 @ApiParam("状态") @RequestParam(value = "status" , required = false) Integer status ,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        return CommonResult.success(CommonPage.restPage(memberService.list(name ,phone, startTime, endTime, label, startCount, endCount,salesman,status, pageSize ,pageNum)));
    }
    
    @ApiOperation(value = "获取用户总量")
    @RequestMapping(value = "/total", method = RequestMethod.POST)
    public CommonResult list() {
    	return CommonResult.success(memberService.total());
    }

}
