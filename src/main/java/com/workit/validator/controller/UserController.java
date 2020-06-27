package com.workit.validator.controller;

import com.workit.validator.comm.ResultViewModelUtil;
import com.workit.validator.model.ResultViewModel;
import com.workit.validator.model.User;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

@RestController
public class UserController {
    @PostMapping(value = "/save")
    @ResponseBody
    public ResultViewModel save(@RequestBody User user) {
        if(Objects.isNull(user)){
            throw new IllegalArgumentException("用户不能为空");
        }
        if(StringUtils.isEmpty(user.getUserName())){
            throw new IllegalArgumentException("用户名不能为空");
        }
        if(StringUtils.isEmpty(user.getUserName())){
            throw new IllegalArgumentException("用户名不能为空");
        }
        if(StringUtils.isEmpty(user.getSex())){
            throw new IllegalArgumentException("用户性别不能为空");
        }
        if(Objects.isNull(user.getUserDetail())){
            throw new IllegalArgumentException("用户详细信息不能为空");
        }
        if(Objects.isNull(user.getUserDetail().getAddress())){
            throw new IllegalArgumentException("用户地址不能为空");
        }
        if(!"M".equals(user.getSex()) && !"F".equals(user.getSex())){
            throw new IllegalArgumentException("用户性别不合法");
        }
        boolean saveUser = saveUser(user);
        if (saveUser) {
            return ResultViewModelUtil.success();
        }
        return ResultViewModelUtil.error();
    }

    @PostMapping(value = "/save2")
    @ResponseBody
    public ResultViewModel save2(@Valid @RequestBody User user) {
        boolean saveUser = saveUser(user);
        if (saveUser) {
            return ResultViewModelUtil.success();
        }
        return ResultViewModelUtil.error();
    }


    public boolean saveUser(User user){
        // db 入库操作省略
        return true;
    }
}
