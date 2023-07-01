package com.sifu.sfcc.search.controller;

import com.sifu.sfcc.common.api.CommonPage;
import com.sifu.sfcc.common.api.CommonResult;
import com.sifu.sfcc.model.XmkKeyword;
import com.sifu.sfcc.search.domain.CcKnowledge;
import com.sifu.sfcc.search.domain.XmkPolicy;
import com.sifu.sfcc.search.domain.XmkProject;
import com.sifu.sfcc.search.domain.XmkTels;
import com.sifu.sfcc.search.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 搜索项目库与政策相关Controller
 * Created by cc on 2020/10/24.
 */
@Controller
@Api(tags = "EsXmkProjectController", description = "搜索项目库与政策相关")
@RequestMapping("/esXmkProject")
public class EsXmkProjectController {

    @Autowired
    private EsXmkProjectService esXmkProjectService;

    @Autowired
    private EsXmkPolicyService esXmkPolicyService;

    @Autowired
    private EsCcKnowledgeService ccKnowledgeService;

    @Autowired
    XmkKeywordService xmkKeywordService;

    @Autowired
    EsXmkTelsService esXmkTelsService;

    @ApiOperation(value = "导入所有数据库中相关信息到ES")
    @RequestMapping(value = "/importAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> importAllList() {
        int countProject = esXmkProjectService.importAll();
        int countPolicy = esXmkPolicyService.importAll();
        int countKnowledge = ccKnowledgeService.importAll();
        int countTels = esXmkTelsService.importAll();
        return CommonResult.success(countProject + countPolicy + countKnowledge + countTels);
    }


    @ApiOperation(value = "ES分词检索 综合搜索、筛选、排序 TOP10")

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult search(@RequestParam(required = false) String keyword,
                                                      @ApiParam("二次查询的搜索关键词")@RequestParam(required = false) String keywords,
                                                      @ApiParam("类型: 1 - 项目库 2 - 政策库 3 - 电话表 4 - 知识库") @RequestParam(value = "type" , required = false , defaultValue = "4") Integer type,
                                                      @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                      @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                      @RequestParam(required = false, defaultValue = "0") Integer sort) {
        keyword = xmkKeywordService.getKeywordStr(keyword) + keywords;
        switch (type){
            case 1:
                Page<XmkProject> xmkProjectPage = esXmkProjectService.search(keyword , pageNum - 1 > 0 ? pageNum - 1 : 0, pageSize, sort);
                return CommonResult.success(CommonPage.restPage(xmkProjectPage));
            case 2:
                Page<XmkPolicy> xmkPolicyPage = esXmkPolicyService.search(keyword , pageNum - 1 > 0 ? pageNum - 1 : 0, pageSize, sort);
                return CommonResult.success(CommonPage.restPage(xmkPolicyPage));
            case 3:
                Page<XmkTels> xmkTelsPage = esXmkTelsService.search(keyword , pageNum - 1 > 0 ? pageNum - 1 : 0, pageSize, sort);
                return CommonResult.success(CommonPage.restPage(xmkTelsPage));
            default:
                Page<CcKnowledge> ccKnowledgePage = ccKnowledgeService.search(keyword , pageNum - 1 > 0 ? pageNum - 1 : 0, pageSize, sort);
                return CommonResult.success(CommonPage.restPage(ccKnowledgePage));
        }
    }

}
