package com.sifu.sfcc.controller;

import com.sifu.sfcc.common.api.CommonPage;
import com.sifu.sfcc.common.api.CommonResult;
import com.sifu.sfcc.model.CcEnterprise;
import com.sifu.sfcc.service.CcEnterpriseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "CcEnterpriseController", description = "企业信息")
@RequestMapping("/enterprise")
public class CcEnterpriseController  {

    @Autowired
    CcEnterpriseService enterpriseService;

    @ApiOperation(value = "添加企业信息")
    @PostMapping(value = "/add")
    public CommonResult add(@RequestBody CcEnterprise enterprise){
        int rows = enterpriseService.add(enterprise);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "修改企业信息")
    @PostMapping(value = "/update")
    public CommonResult update(@RequestBody CcEnterprise enterprise){
        int rows = enterpriseService.update(enterprise);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }


    @ApiOperation(value = "删除企业信息")
    @PostMapping(value = "/delete/{id}")
    public CommonResult delete(@PathVariable Long id){
        int rows = enterpriseService.delete(id);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "获取某个企业信息")
    @GetMapping(value = "/get/{id}")
    public CommonResult get(@PathVariable Long id){
        return CommonResult.success(enterpriseService.get(id));
    }
    
    @ApiOperation(value = "获取企业信息")
    @GetMapping(value = "/list")
    public CommonResult list(@ApiParam("名称") @RequestParam(value = "name" , required = false) String name ,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        return CommonResult.success(CommonPage.restPage(enterpriseService.list(name , pageSize ,pageNum)));
    }


}
