package com.sifu.sfcc.controller;

import com.github.pagehelper.PageHelper;
import com.sifu.sfcc.common.api.CommonResult;
import com.sifu.sfcc.dto.CcWorkOrderParam;
import com.sifu.sfcc.model.CcWorkOrder;
import com.sifu.sfcc.service.CcWorkOrderService;
import com.sifu.sfcc.vo.workorder.WorkOrderVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    public CommonResult list(
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer wstatus,
            @RequestParam(required = false) String toDoText) {
        PageHelper.startPage(pageNum,pageSize);
        List<CcWorkOrder> list = ccWorkOrderService.list(pageSize, pageNum, wstatus, toDoText);
        return CommonResult.success(list);
    }
    @GetMapping("/getByNo/{code}")
    public CommonResult getByNo(String code,@RequestParam String worderCode){
        WorkOrderVO workOrderVO = ccWorkOrderService.qryByOrderNo(worderCode);
        return CommonResult.success(1);
    }
}
