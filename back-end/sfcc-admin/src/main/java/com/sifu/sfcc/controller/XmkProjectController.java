package com.sifu.sfcc.controller;

import com.sifu.sfcc.common.api.CommonPage;
import com.sifu.sfcc.common.api.CommonResult;
import com.sifu.sfcc.common.utils.FileUtil;
import com.sifu.sfcc.model.XmkProject;
import com.sifu.sfcc.service.XmkProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "XmkProjectController", description = "项目库")
@RequestMapping("/xmkProject")
public class XmkProjectController {

    @Autowired
    XmkProjectService xmkProjectService;
    @ApiOperation(value = "同步项目库")
    @PostMapping(value = "/export")
    public CommonResult exportProject(){
        boolean result = xmkProjectService.exportProject();
        if(result){
            return CommonResult.success(null , "导入成功！");
        }else{
            return CommonResult.failed("导入失败！");
        }
    }

    @ApiOperation(value = "根据名称查询项目库")
    @PostMapping(value = "/getProjectByName")
    public CommonResult getProjectByName(
            @RequestParam(value = "pageSize", defaultValue = "5" ) Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum ,
            @ApiParam("项目名称") @RequestParam(value = "projectName" , required = false) String projectName){
        List<XmkProject> xmkProjectList = xmkProjectService.getProjectByName(pageSize ,pageNum , projectName);
        return CommonResult.success(CommonPage.restPage(xmkProjectList));
    }

    @ApiOperation(value = "增量同步项目库")
    @PostMapping(value = "/zlExport")
    public CommonResult zlExportProject(){
        boolean result = xmkProjectService.zlExportProject();
        if(result){
            return CommonResult.success(null , "增量导入成功！");
        }else{
            return CommonResult.failed("增量导入失败！");
        }
    }

    @ApiOperation(value = "导入数据生成.xsl文件")
    @GetMapping(value = "/importXmkProject")
    public CommonResult importXmkProject(){
        String filePath = xmkProjectService.importXmkProject();
        return CommonResult.success(filePath);
    }

}
