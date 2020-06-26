package com.workit.validator.comm;

import com.workit.validator.model.ResultViewModel;

/**
 * 响应数据封装类
 */
public class ResultViewModelUtil {
    /**
     * 请求成功方法01
     * @param object 响应数据
     * @return 视图模型实例
     */
    public static ResultViewModel success(Object object) {
        ResultViewModel resultViewModel = new ResultViewModel();
        resultViewModel.setCode(0);
        resultViewModel.setMessage("请求成功");
        resultViewModel.setData(object);
        return resultViewModel;
    }

    /**
     * 请求成功方法02
     * @return 视图模型实例
     */
    public static ResultViewModel success() {
        return success(null);
    }

    /**
     * 请求失败方法01（捕获到的已知异常）
     * @param code 异常编号
     * @param message 异常信息
     * @return 视图模型实例
     */
    public static ResultViewModel error(Integer code, String message) {
        ResultViewModel resultViewModel = new ResultViewModel();
        resultViewModel.setCode(code);
        resultViewModel.setMessage(message);
        resultViewModel.setData(null);
        return resultViewModel;
    }

    /**
     * 请求失败方法02（系统异常）
     * @return 视图模型实例
     */
    public static ResultViewModel error() {
        ResultViewModel resultViewModel = new ResultViewModel();
        resultViewModel.setCode(-1);
        resultViewModel.setMessage("系统异常");
        resultViewModel.setData("系统维护中...");
        return resultViewModel;
    }

    public static ResultViewModel error(String message) {
        ResultViewModel resultViewModel = new ResultViewModel();
        resultViewModel.setCode(-1);
        resultViewModel.setMessage(message);
        return resultViewModel;
    }


}