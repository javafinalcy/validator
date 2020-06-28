package com.workit.validator.comm;

import com.workit.validator.model.ResultViewModel;
import org.springframework.boot.context.properties.bind.BindException;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;


@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultViewModel handleException(HttpServletRequest request, Exception exception) {
        if (exception instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException argumentNotValidException = (MethodArgumentNotValidException) exception;
            BindingResult result = argumentNotValidException.getBindingResult();
            StringBuilder errorMsg = new StringBuilder();
            if (result.hasErrors()) {
                List<FieldError> fieldErrors = result.getFieldErrors();
                fieldErrors.forEach(error -> {
                    String msg = "field:" + error.getField() + ", msg:" + error.getDefaultMessage();
                    errorMsg.append(msg).append("!");
                });
                return ResultViewModelUtil.error(errorMsg.toString());
            }
        }else if(exception instanceof IllegalArgumentException){
            return ResultViewModelUtil.error(exception.getMessage());
        }
        return ResultViewModelUtil.error();
    }
}