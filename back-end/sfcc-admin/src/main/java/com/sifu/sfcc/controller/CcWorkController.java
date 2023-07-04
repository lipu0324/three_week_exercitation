package com.sifu.sfcc.controller;

import com.sifu.sfcc.common.api.CommonResult;
import com.sifu.sfcc.dto.CcWorkOrderParam;
import com.sifu.sfcc.model.CcWorkOrder;
import com.sifu.sfcc.service.CcWorkOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/wonder")
public class CcWorkController {
    @Resource
    CcWorkOrderService ccWorkOrderService;
    @PostMapping("/create")
    public CommonResult create(@RequestBody CcWorkOrderParam ccWorkOrderParam){

        CcWorkOrder ccWorkOrder = ccWorkOrderService.create(ccWorkOrderParam);
        return CommonResult.success(1);
    }

}
