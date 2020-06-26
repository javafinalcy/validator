package com.workit.validator.comm;

import com.workit.validator.model.ResultViewModel;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultViewModel handleException(HttpServletRequest request, Exception ex) {
      return ResultViewModelUtil.error(ex.getMessage());
    }
}