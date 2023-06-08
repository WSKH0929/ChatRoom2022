package com.wskh.chatroom.handler;


import com.wskh.chatroom.common.R;
import com.wskh.chatroom.exception.RRException;
import io.swagger.annotations.ApiModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ApiModel(value = "全局异常处理(监控)类")
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 老大异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody // 为了返回数据
    public R error(Exception e){
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().message("系统异常!");
    }

    // 特殊特定异常处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody // 为了返回数据
    public R error(ArithmeticException arithmeticException){
        arithmeticException.printStackTrace();
        log.error(arithmeticException.getMessage());
        return R.error().message("特殊异常!");
    }

    // 空指针异常处理
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody // 为了返回数据
    public R error(NullPointerException nullPointerException){
        log.error(nullPointerException.getMessage());
        nullPointerException.printStackTrace();
        return R.error().message("空指针异常!");
    }

    // 自定义异常处理
    @ExceptionHandler(RRException.class)
    @ResponseBody // 为了返回数据
    public R error(RRException rrException){
        log.error(rrException.getMessage());
        rrException.printStackTrace();
        return R.error().message("全局异常!");
    }

}
