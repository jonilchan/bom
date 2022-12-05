package com.cvte.bom.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Jonil
 * @Date: 2022/12/5
 * @Description: 返回的封装信息类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R {
    private Integer code;
    private String msg;
    private Object data;

    public static R success() {
        return new R(200, "success", null);
    }

    public static R success(Object data) {
        return new R(200, "success", data);
    }

    public static R success(String message, Object data) {
        return new R(200, message, data);
    }


    public static R fail() {
        return new R(500, "fail", null);
    }

    public static R fail(Object data) {
        return new R(500, "fail", data);
    }

    public static R fail(String message, Object data) {
        return new R(500, message, data);
    }

}