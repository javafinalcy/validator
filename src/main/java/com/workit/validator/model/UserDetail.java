package com.workit.validator.model;

import javax.validation.constraints.NotBlank;

public class UserDetail {

    @NotBlank(message = "用户地址不能为空")
    private String address;

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