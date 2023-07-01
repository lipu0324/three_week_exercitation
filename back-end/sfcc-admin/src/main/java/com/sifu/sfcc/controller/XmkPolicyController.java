package com.sifu.sfcc.controller;


import com.sifu.sfcc.common.api.CommonPage;
import com.sifu.sfcc.common.api.CommonResult;
import com.sifu.sfcc.model.XmkPolicy;
import com.sifu.sfcc.service.XmkPolicyService;
import com.sifu.sfcc.service.XmkProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "XmkPolicyController", description = "政策相关")
@RequestMapping("/xmkPolicy")
public class XmkPolicyController {

    @Autowired
    XmkPolicyService xmkPolicyService;
    @ApiOperation(value = "同步所有政策")
    @PostMapping(value = "/export")
    public CommonResult exportPolicy(@RequestParam(value = "s_bm_type" , required = false , defaultValue = "3") Integer sBmType,
                                     @RequestParam(value = "s_type" , required = false , defaultValue = "2") Integer sType ,
                                     @RequestParam(value = "s_organ" , required = false , defaultValue = "23") Integer sOrgan){
        boolean result = xmkPolicyService.exportPolicy(sBmType , sType , sOrgan);
        if(result){
            return CommonResult.success(null , "导入成功！");
        }else{
            return CommonResult.failed("导入失败！");
        }
    }

    @ApiOperation(value = "清空政策数据")
    @GetMapping(value = "/clearPolicy")
    public CommonResult clearPolicy(){
        boolean result = xmkPolicyService.clearPolicy();
        if(result){
            return CommonResult.success(null , "清空成功！");
        }else{
            return CommonResult.failed("清空失败！");
        }
    }

    @ApiOperation(value = "根据名称查询项目政策库")
    @PostMapping(value = "/getPolicyByTitle")
    public CommonResult getPolicyByTitle(
            @ApiParam("政策名称标题") @RequestParam(value = "title" , required = false) String title){
        List<XmkPolicy> xmkPolicyList = xmkPolicyService.getPolicyByTitle(title);
        return CommonResult.success(CommonPage.restPage(xmkPolicyList));
    }

    @ApiOperation(value = "导入数据生成.xsl文件")
    @GetMapping(value = "/importXmkPolicy")
    public CommonResult importXmkPolicy(){
        String filePath = xmkPolicyService.importXmkPolicy();
        return CommonResult.success(filePath);
    }
}
