package com.sifu.sfcc.controller;

import com.sifu.sfcc.common.api.CommonPage;
import com.sifu.sfcc.common.api.CommonResult;
import com.sifu.sfcc.model.CcNaire;
import com.sifu.sfcc.model.CcNaireAnswer;
import com.sifu.sfcc.model.CcNaireQuestion;
import com.sifu.sfcc.service.CcNaireService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "CcNaireController", description = "问卷系统")
@RequestMapping("/naire")
public class CcNaireController  {

    @Autowired
    CcNaireService naireService;
    
    @ApiOperation(value = "添加问卷")
    @PostMapping(value = "/addNaire")
    public CommonResult addNaire(@RequestBody CcNaire naire){
        int rows = naireService.addNaire(naire);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "修改问卷")
    @PostMapping(value = "/updateNaire")
    public CommonResult updateNaire(@RequestBody CcNaire naire){
        int rows = naireService.updateNaire(naire);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "删除问卷")
    @PostMapping(value = "/deleteNaire/{id}")
    public CommonResult deleteNaire(@PathVariable Long id){
        int rows = naireService.deleteNaire(id);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed("存在已关联的任务，请先删除任务");
        }
    }

    @ApiOperation(value = "获取某个问卷")
    @GetMapping(value = "/getNaire/{id}")
    public CommonResult getNaire(@PathVariable Long id){
        return CommonResult.success(naireService.getNaire(id));
    }
    
    @ApiOperation(value = "获取问卷")
    @GetMapping(value = "/listNaire")
    public CommonResult listNaire(@ApiParam("问题") @RequestParam(value = "question" , required = false) String question ,
    		@ApiParam("状态") @RequestParam(value = "status" , required = false) String status,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        return CommonResult.success(CommonPage.restPage(naireService.listNaire(question ,status, pageSize ,pageNum)));
    }
    
    
    @ApiOperation(value = "添加问卷问题")
    @PostMapping(value = "/addQuestion")
    public CommonResult addQuestion(@RequestBody CcNaireQuestion question){
        int rows = naireService.addQuestion(question);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "修改问卷问题")
    @PostMapping(value = "/updateQuestion")
    public CommonResult updateQuestion(@RequestBody CcNaireQuestion question){
        int rows = naireService.updateQuestion(question);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else if(rows == 0){
            return CommonResult.failed("当前问卷已经存在第一个问题");
        }else {
        	return CommonResult.failed("当前问题已经被其他问题选择为下一个");
        }
    }


    @ApiOperation(value = "删除问卷问题")
    @PostMapping(value = "/deleteQuestion/{id}")
    public CommonResult deleteQuestion(@PathVariable Long id){
        int rows = naireService.deleteQuestion(id);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed("当前问题已经被其他问题选择为下一个");
        }
    }

    @ApiOperation(value = "获取某个问题")
    @GetMapping(value = "/getQuestion/{id}")
    public CommonResult getQuestion(@PathVariable Long id){
        return CommonResult.success(naireService.getQuestion(id));
    }
    

    @ApiOperation(value = "获取未被选择的问题")
    @GetMapping(value = "/listNoQuestion")
    public CommonResult listNoQuestion(@ApiParam("naireId") @RequestParam(value = "naireId" , required = false) Long naireId,
    		@ApiParam("questionId") @RequestParam(value = "questionId" , required = false) Long questionId){
        return CommonResult.success(naireService.listNoQuestion(naireId,questionId));
    }
    
    @ApiOperation(value = "获取问题")
    @GetMapping(value = "/listQuestion")
    public CommonResult listQuestion(@ApiParam("naireId") @RequestParam(value = "naireId" , required = false) Long naireId ,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        return CommonResult.success(CommonPage.restPage(naireService.listQuestion(naireId , pageSize ,pageNum)));
    }


    @ApiOperation(value = "添加回答信息")
    @PostMapping(value = "/addAnswer")
    public CommonResult addAnswer(@RequestBody CcNaireAnswer answer){
        int rows = naireService.addAnswer(answer);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "修改回答信息")
    @PostMapping(value = "/updateAnswer")
    public CommonResult updateAnswer(@RequestBody CcNaireAnswer answer){
        int rows = naireService.updateAnswer(answer);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }


    @ApiOperation(value = "删除回答信息")
    @PostMapping(value = "/deleteAnswer/{id}")
    public CommonResult deleteAnswer(@PathVariable Long id){
        int rows = naireService.deleteAnswer(id);
        if(rows > 0){
            return CommonResult.success(null , "成功");
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "获取某个回答信息")
    @GetMapping(value = "/getAnswer/{id}")
    public CommonResult getAnswer(@PathVariable Long id){
        return CommonResult.success(naireService.getNaire(id));
    }
    
    
    @ApiOperation(value = "获取回答信息")
    @GetMapping(value = "/listAnswer")
    public CommonResult listAnswer(@ApiParam("questionId") @RequestParam(value = "questionId" , required = false) Long questionId ,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        return CommonResult.success(CommonPage.restPage(naireService.listAnswer(questionId , pageSize ,pageNum)));
    }
}
