package com.sifu.sfcc.controller;

import com.alibaba.excel.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.sifu.sfcc.common.api.CommonResult;
import com.sifu.sfcc.model.CcLabel;
import com.sifu.sfcc.service.CcLabelService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
@RequestMapping("/lable")
public class CcLableController {
    @Resource
    CcLabelService ccLabelService;

    @PostMapping("/add")
    public CommonResult add(@RequestBody CcLabel ccLabel) {
        int res = ccLabelService.add(ccLabel);
        if (res>0)
            return CommonResult.success(null);
        else
            return CommonResult.failed("添加失败");
    }
    //标签列表
    @RequestMapping("/list")
    public CommonResult list(
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize,
            @RequestParam (required = false)String name
    ){
        PageHelper.startPage(pageNum,pageSize);
        return CommonResult.success(ccLabelService.list(name));
    }
    @RequestMapping("/delete/{id}")
    public CommonResult del(Long id) {
        int i = ccLabelService.delete(id);
        if (i>0)
            return CommonResult.success(i);
        else
            return CommonResult.failed("删除失败");
    }
    @RequestMapping(value = {"/update","/updateStatus{id}"})
    public CommonResult update(@RequestBody CcLabel ccLabel,Long id) {
        int i = ccLabelService.update(ccLabel);
        return i>0 ? CommonResult.success(i) : CommonResult.failed("更新失败");
    }
    @PostMapping("/batchDelete")
    public CommonResult bathDelete(@RequestParam ArrayList<Long> ids) {
        int i = ccLabelService.batchDelete(ids);
        return CommonResult.success(i);

    }
}

