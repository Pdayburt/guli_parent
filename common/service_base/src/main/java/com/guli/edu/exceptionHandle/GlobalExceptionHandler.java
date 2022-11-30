package com.guli.edu.exceptionHandle;

import com.guli.edu.commonUtils.RT;
import com.guli.edu.exception.GuliException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RT exceptionHandler(Exception e){

        e.printStackTrace();
        log.error(e.getMessage());
        return RT.error().msg("925国际会所专用 全局异常处理已经执行了。。。。。。。");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public RT arithmeticException(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return RT.error().msg("ArithmeticException已被专用处理器捕获");
    }

    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public RT guliExceptionHandler(GuliException e) {
        e.printStackTrace();
        return RT.error().code(e.getCode()).data(e.getMsg());
    }

}
