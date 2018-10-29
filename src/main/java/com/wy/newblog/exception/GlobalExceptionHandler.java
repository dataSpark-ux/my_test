package com.wy.newblog.exception;

import com.wy.newblog.core.Result;
import com.wy.newblog.entity.enums.ResultCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author
 * @Date 2018/10/24 13:57
 * @Description 全局异常处理器
 * @Version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest request, Exception exception) throws Exception {
        exception.printStackTrace();
        return new Result(ResultCode.INTERNAL_SERVER_ERROR);
    }

}
