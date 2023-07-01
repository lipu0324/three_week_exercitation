package com.sifu.sfcc.controller;

import com.sifu.sfcc.common.api.CommonPage;
import com.sifu.sfcc.common.api.CommonResult;
import com.sifu.sfcc.model.CcArchives;
import com.sifu.sfcc.model.CcArchivesContent;
import com.sifu.sfcc.service.CcArchivesContentService;
import com.sifu.sfcc.service.CcArchivesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "CcArchivesController", description = "档案")
@RequestMapping("/archives")
public class CcArchivesController  {

    @Autowired
    CcArchivesService archivesService;
    
    @Autowired
    CcArchivesContentService contentService;
    
    @ApiOperation(value = "添加档案")
    @PostMapping(value = "/add")
    public CommonResult add(@RequestBody CcArchives archives){
        int rows = archivesService.add(archives);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "修改档案")
    @PostMapping(value = "/update")
    public CommonResult update(@RequestBody CcArchives archives){
        int rows = archivesService.update(archives);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "删除档案")
    @PostMapping(value = "/delete/{id}")
    public CommonResult delete(@PathVariable Long id){
        int rows = archivesService.delete(id);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }
    
    @ApiOperation(value = "合并档案内容")
    @PostMapping(value = "/updateBatch")
    public CommonResult updateBatch(@RequestBody List<CcArchivesContent> content){
        int rows = contentService.updateBatch(content);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "获取某个档案")
    @GetMapping(value = "/get/{id}")
    public CommonResult get(@PathVariable Long id){
        return CommonResult.success(archivesService.get(id));
    }
    
    @ApiOperation(value = "根据code获取某个档案")
    @GetMapping(value = "/getArchives/{code}")
    public CommonResult getArchives(@PathVariable String code){
        return CommonResult.success(archivesService.getArchives(code));
    }
    
    @ApiOperation(value = "获取档案")
    @GetMapping(value = "/list")
    public CommonResult list(@ApiParam("名称") @RequestParam(value = "name" , required = false) String name ,
    						@ApiParam("编号") @RequestParam(value = "code" , required = false) String code ,
    						@ApiParam("紧迫性") @RequestParam(value = "urgency" , required = false) String urgency ,
    						@ApiParam("名称或编号") @RequestParam(value = "nameOrUrgency" , required = false) String nameOrUrgency ,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        return CommonResult.success(CommonPage.restPage(archivesService.list(name ,code,urgency, nameOrUrgency,pageSize ,pageNum)));
    }
    
    @ApiOperation(value = "根据客户电话查询档案")
    @GetMapping(value = "/getArchivesByPhone")
    public CommonResult getArchivesByPhone(@ApiParam("客户电话") @RequestParam(value = "phone") String phone){
        return CommonResult.success(archivesService.getArchivesByPhone(phone));
    }
    
    @ApiOperation(value = "添加档案内容")
    @PostMapping(value = "/addContent")
    public CommonResult addContent(@RequestBody CcArchivesContent content){
        int rows = contentService.add(content);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }
    
    @ApiOperation("档案导出")
  	@PostMapping("/export")
      public CommonResult export(@ApiParam("名称") @RequestParam(value = "name" , required = false) String name ,
				@ApiParam("编号") @RequestParam(value = "code" , required = false) String code ,
				@ApiParam("紧迫性") @RequestParam(value = "urgency" , required = false) String urgency ,
				@ApiParam("名称或编号") @RequestParam(value = "nameOrUrgency" , required = false) String nameOrUrgency ) {
    	
          return CommonResult.success(archivesService.export(name,code,urgency,nameOrUrgency) , "成功");

      }
    
    @ApiOperation(value = "获取档案总量")
    @RequestMapping(value = "/total", method = RequestMethod.POST)
    public CommonResult list() {
    	return CommonResult.success(archivesService.total());
    }
    
    @ApiOperation(value = "通过固定标签筛选档案,返回每天的档案数量")
    @RequestMapping(value = "/getArchivesCount", method = RequestMethod.POST)
    public CommonResult getArchivesCount(
    		@ApiParam("标签") @RequestParam(value = "label") String label ,
			@ApiParam("开始时间") @RequestParam(value = "startTime") String startTime ,
			@ApiParam("结束时间") @RequestParam(value = "endTime") String endTime) {
    	return CommonResult.success(archivesService.getArchivesCount(label,startTime,endTime));
    }
    
    

}
