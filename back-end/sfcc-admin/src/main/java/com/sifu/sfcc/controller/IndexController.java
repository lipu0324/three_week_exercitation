package com.sifu.sfcc.controller;

import com.sifu.sfcc.common.api.CommonResult;
import com.sifu.sfcc.dto.IndexParam;
import com.sifu.sfcc.model.CcLabel;
import com.sifu.sfcc.service.CcArchivesService;
import com.sifu.sfcc.service.CcKnowledgeService;
import com.sifu.sfcc.service.CcScoreService;
import com.sifu.sfcc.service.CcWorkOrderService;
import com.sifu.sfcc.service.UmsDeptService;
import com.sifu.sfcc.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "IndexController", description = "首页")
@RequestMapping("/index")
public class IndexController  {

    @Autowired
    CcArchivesService archivesService;
    
    @Autowired
    CcWorkOrderService workService;
    
    @Autowired
    UmsMemberService memberService;
    
    @Autowired
    UmsDeptService deptService;
    
    @Autowired
    CcKnowledgeService knowService;
    
    @Autowired
    CcScoreService scoreService;

    @ApiOperation(value = "首页信息")
    @PostMapping(value = "/")
    public CommonResult index(@ApiParam("标签") @RequestParam(value = "label",required = false ) String label){
    	IndexParam ip = new IndexParam();
    	ip.setArchivesCount(archivesService.total());
    	ip.setAiCount(50);
    	ip.setDeptCount(deptService.total());
    	ip.setMemberCount(memberService.total());
    	ip.setKnowledgeCount(knowService.total());
    	ip.setWorkOrderCount(workService.worderTotalNum());
    	if(null != label && "" != label) {
    		ip.setTotalCount(archivesService.labelCount(label));
    	}
    	ip.setScoreCount(scoreService.scoreCount());
        return CommonResult.success(ip , "成功");
    }



}
