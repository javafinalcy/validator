package com.workit.validator.model;


import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * @author:
 * @Date: 2020/6/22
 * @Description:
 */
public class User {

    @NotBlank(message = "姓名不能为空")
    private String userName;

    @Range(min = 1,max = 200,message = "年龄不合法")
    private int age;

    @Email
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @Pattern(regexp = "F|M", message = "必须是F或M")
    private String sex;

    @NotNull
    @Valid
    private UserDetail userDetail;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }
}