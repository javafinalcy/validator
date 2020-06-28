package com.workit.validator.model;

import com.workit.validator.annotation.MyNotBlank;

import javax.validation.constraints.NotBlank;

public class UserDetail {

    @NotBlank(message = "用户地址不能为空")
    private String address;

    @MyNotBlank(message = "自定义注解UserDetail.education不能为空")
    private String education;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
}