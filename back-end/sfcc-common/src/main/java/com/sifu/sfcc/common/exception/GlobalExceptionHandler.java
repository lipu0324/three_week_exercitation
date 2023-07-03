package com.sifu.sfcc.common.exception;

import com.sifu.sfcc.common.api.CommonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 * Created by commerce on 2020/2/27.
 */
@RestControllerAdvice(basePackages = {"com.sifu.sfcc"})
public class GlobalExceptionHandler {
    GlobalExceptionHandler(){
        System.out.println("xxx");
    }


    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public CommonResult handle(ApiException e) {
        if (e.getErrorCode() != null) {
            return CommonResult.failed(e.getErrorCode());
        }
        return CommonResult.failed(e.getMessage());
    }
    @ExceptionHandler(value = Exception.class)
    public CommonResult  handleException(Exception e){
        return CommonResult.failed(e.getMessage());
    }
}
