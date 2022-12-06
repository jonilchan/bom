package com.cvte.bom.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Jonil
 * @Date: 2022/12/5
 * @Description: 自定义的参数异常类
 */
@Getter
@Setter
@AllArgsConstructor
public class ParamsException extends RuntimeException {
    private Integer code = 300;
    private String msg = "参数异常!";

    public ParamsException() {
        super("参数异常!");
    }

    public ParamsException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ParamsException(Integer code) {
        super("参数异常!");
        this.code = code;
    }
}