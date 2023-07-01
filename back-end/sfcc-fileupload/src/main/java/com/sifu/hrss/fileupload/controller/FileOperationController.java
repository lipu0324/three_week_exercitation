package com.sifu.hrss.fileupload.controller;

import com.sifu.sfcc.common.api.CommonResult;
import com.sifu.sfcc.common.utils.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @date 2020.06.07
 */
@Api(tags = "FileOperationController", description = "文件上传")
@RestController
@RequestMapping("/file")
@Slf4j
public class FileOperationController {

    @ApiOperation("单文件上传")
    @PostMapping("/single")
    public CommonResult<Object> singleFileUpload(MultipartFile file,@RequestParam("dir") String directory) {
        try {
            long size = file.getSize() / 1024;
            System.out.println("single size = " + size);
            if(size > 5000){
                return CommonResult.failed("图片不允许超过5M");
            }
            String url = FileUtil.upload(file, directory);
            return CommonResult.success(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CommonResult.failed();
    }

    @ApiOperation("多文件上传")
    @PostMapping("/multi")
    public CommonResult<Object> multiFileUpload(@RequestParam(value = "image0" , required = false) MultipartFile image0 ,
                                                @RequestParam(value = "image1", required = false)MultipartFile image1 ,
                                                @RequestParam(value = "image2", required = false) MultipartFile image2 ,
                                                @RequestParam(value = "image3", required = false) MultipartFile image3,
                                                @RequestParam(value = "image4", required = false) MultipartFile image4 ,
                                                @RequestParam(value = "image5", required = false) MultipartFile image5
            ,@RequestParam("dir") String directory) {
        List<String> urls = new ArrayList<>();
        try {
            List<MultipartFile> files = new ArrayList<>();
            files.add(image0);
            files.add(image1);
            files.add(image2);
            files.add(image3);
            files.add(image4);
            files.add(image5);
            for (MultipartFile file : files) {
                if(null != file){
                    long size = file.getSize() / 1024;
                    if(size > 5 * 1024){
                        return CommonResult.failed("图片不允许超过5M");
                    }
                    String url = FileUtil.upload(file, directory);
                    urls.add(url);
                }
            }
            return CommonResult.success(urls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CommonResult.failed();
    }

    @ApiOperation("文件删除")
    @PostMapping("/remove")
    public CommonResult<Object> removeFile(String relativePath) {
        return CommonResult.success(FileUtil.delete(relativePath));
    }


}

