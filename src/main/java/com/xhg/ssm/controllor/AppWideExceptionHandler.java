package com.xhg.ssm.controllor;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author 16033
 */
@ControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(){
        return "error/errorPage";
    }
}
