package com.sifu.sfcc.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.sifu.sfcc.common.api.CommonPage;
import com.sifu.sfcc.common.api.CommonResult;
import com.sifu.sfcc.model.CcKnowledge;
import com.sifu.sfcc.service.CcKnowledgeService;
import com.sifu.sfcc.util.KnowledgeDataListener;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "CcKnowledgeController", description = "知识库")
@RequestMapping("/knowledge")
public class CcKnowledgeController  {

	private static final String template = "template.xlsx";
	
    @Autowired
    CcKnowledgeService knowledgeService;
    
    @Autowired
    KnowledgeDataListener kdl;

    @ApiOperation(value = "添加知识库")
    @PostMapping(value = "/add")
    public CommonResult add(@RequestBody CcKnowledge knowledge){
        int rows = knowledgeService.add(knowledge);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "修改知识库")
    @PostMapping(value = "/update")
    public CommonResult update(@RequestBody CcKnowledge knowledge){
        int rows = knowledgeService.update(knowledge);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }


    @ApiOperation(value = "删除知识库")
    @PostMapping(value = "/delete/{id}")
    public CommonResult delete(@PathVariable Long id){
        int rows = knowledgeService.delete(id);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "获取某个知识库")
    @GetMapping(value = "/get/{id}")
    public CommonResult get(@PathVariable Long id){
        return CommonResult.success(knowledgeService.get(id));
    }
    
    @ApiOperation(value = "获取知识库")
    @GetMapping(value = "/list")
    public CommonResult list(@ApiParam("问题") @RequestParam(value = "question" , required = false) String question ,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        return CommonResult.success(CommonPage.restPage(knowledgeService.list(question , pageSize ,pageNum)));
    }


    
    @ApiOperation("批量导出")
	@PostMapping("/export")
    public CommonResult export(@RequestBody CcKnowledge param) {

		String templateFileName = "/uploads/sfcc/template.xlsx";
		
//		String templateFileName = FileUtil.getUploadResource(template);
		
//    	String templateFileName = this.getClass().getResource("").getPath()+"/template.xlsx";
    
		String fileName = "/uploads/sfcc/知识库.xlsx";
		ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build();
	    WriteSheet writeSheet = EasyExcel.writerSheet().build();
//        //获取表格内容
        List<CcKnowledge> kl=knowledgeService.list(param.getQuestion());
        
        excelWriter.fill(kl, writeSheet);
        // 千万别忘记关闭流
        excelWriter.finish();
        
        return CommonResult.success(fileName , "成功");

    }
    
    @ApiOperation("下载导入模板")
   	@PostMapping("/exportTemplate")
    public CommonResult exportTemplate() {
//        String templateFileName = FileUtil.getUploadResource(template);
        String templateFileName = "uploads/sfcc/知识库模板.xlsx";
		//String templateFileName = "uploads/sfcc/template.xlsx";
	    return CommonResult.success(templateFileName , "成功");
    }
    

    @ApiOperation("批量导入")
   	@PostMapping("/import")
    public CommonResult simpleRead(@RequestParam("fileName")String fileName) {
//    	 String fileName ="/Users/wuying/work/water/demo.xlsx";
    	
    	//解决老版本excel格式问题，复制一份新的xlsx文件
    	String fix = fileName.substring(fileName.lastIndexOf("."));
    	if(fix.equals(".xls")) {
    		File source = new File(fileName);
        	File dest = new File(fileName + "x");
        	try {
				FileUtils.copyFile(source, dest);
				fileName = fileName + "x";
			} catch (IOException e) {
				return CommonResult.failed("请根据模板上传数据");
			}
    	}  	
    	
    	if(fileName.lastIndexOf(".") == -1 || (!fix.equals(".xlsx") && !fix.equals(".xls"))){
           return CommonResult.failed("请根据模板上传数据");
        }
         // 一个文件一个reader
         ExcelReader excelReader = null;
         try{
             excelReader = EasyExcel.read(fileName, CcKnowledge.class, kdl).build();
             // 构建一个sheet 这里可以指定名字或者no
             ReadSheet readSheet = EasyExcel.readSheet(0).build();
             // 读取一个sheet
             excelReader.read(readSheet);
         }catch(Exception e){
        	 return CommonResult.failed("请根据模板上传数据");
         }finally {
        	 
             if (excelReader != null) {
                 // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                 excelReader.finish();
                 
             }
         }
         return CommonResult.success(null , "成功");
    }
    
    @ApiOperation(value = "获取知识库总量")
    @RequestMapping(value = "/total", method = RequestMethod.POST)
    public CommonResult list() {
    	return CommonResult.success(knowledgeService.total());
    }
    

}
