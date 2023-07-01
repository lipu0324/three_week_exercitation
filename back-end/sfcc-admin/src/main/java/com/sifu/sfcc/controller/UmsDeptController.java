package com.sifu.sfcc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sifu.sfcc.common.api.CommonPage;
import com.sifu.sfcc.common.api.CommonResult;
import com.sifu.sfcc.model.UmsDept;
import com.sifu.sfcc.service.UmsDeptService;

//import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags = "UmsDeptController", description = "部门管理")
@RequestMapping("/dept")
public class UmsDeptController
{
    @Autowired
    private UmsDeptService deptService;

    /**
     * 获取部门列表
     */
    //@PreAuthorize("@ss.hasPermi('system:dept:list')")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult list(@ApiParam("部门名称") @RequestParam(value = "deptName" , required = false) String deptName ,
			 @ApiParam("部门电话") @RequestParam(value = "deptPhone" , required = false) String deptPhone ,
			 @ApiParam("负责人") @RequestParam(value = "leader" , required = false) String leader ,
			 @ApiParam("负责人电话") @RequestParam(value = "phone" , required = false) String phone ,
			 @ApiParam("城市") @RequestParam(value = "city" , required = false) String city ,
			 @ApiParam("部门状态（0正常 1停用）") @RequestParam(value = "status" , defaultValue = "2") String status ,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum)
    {
        List<UmsDept> depts = deptService.selectDeptList(deptName,deptPhone,leader,phone,city,status,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(depts));
    }
    

    /**
     * 根据部门编号获取详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:dept:query')")
    @GetMapping(value = "getDept/{deptId}")
    public CommonResult getInfo(@PathVariable Long deptId)
    {
        return CommonResult.success(deptService.selectDeptById(deptId));
    }


    /**
     * 新增部门
     */
    //@PreAuthorize("@ss.hasPermi('system:dept:add')")
    //@Log(title = "部门管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public CommonResult add(@Validated @RequestBody UmsDept dept)
    {
    	if("".equals(dept.getDeptName()) || null == dept.getDeptName()) {
    		return CommonResult.failed("部门名称不能为空");
    	}
    	if("".equals(dept.getDeptPhone()) || null == dept.getDeptPhone()) {
    		return CommonResult.failed("部门电话不能为空");
    	}
    	
    	if(null != dept.getDeptName() && dept.getDeptName().length() > 30) {
    		return CommonResult.failed("部门名称字数不超过30个字符");
    	}
    	
        if ("1".equals(deptService.checkDeptNameUnique(dept)))
        {
            return CommonResult.failed("新增部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        return CommonResult.success(deptService.insertDept(dept), "成功");
    }
    
    @RequestMapping(value = "/batchBan", method = RequestMethod.POST)
    public CommonResult batchBan(@RequestParam("ids") List<Long> ids)
    {
    	 int rows = deptService.batchBan(ids);
         if(rows > 0){
             return CommonResult.success(null , "成功");
         }else{
             return CommonResult.failed();
         }
    }

    /**
     * 修改部门
     */
    //@PreAuthorize("@ss.hasPermi('system:dept:edit')")
    //@Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @PostMapping("/updateDept")
    public CommonResult edit(@Validated @RequestBody UmsDept dept)
    {
        if ("1".equals(deptService.checkDeptNameUnique(dept)))
        {
            return CommonResult.failed("修改部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        return CommonResult.success(deptService.updateDept(dept), "成功");
    }

    /**
     * 删除部门
     */
   // @PreAuthorize("@ss.hasPermi('system:dept:remove')")
    //@Log(title = "部门管理", businessType = BusinessType.DELETE)
    @PostMapping("delDept/{deptId}")
    public CommonResult remove(@PathVariable Long deptId)
    {
        return CommonResult.success(deptService.deleteDeptById(deptId), "成功");
    }
    
    @ApiOperation(value = "获取部门总量")
    @RequestMapping(value = "/total", method = RequestMethod.POST)
    public CommonResult list() {
    	return CommonResult.success(deptService.total());
    }
}
