package com.tuny.demo.enums;

/**
 * @author hucs
 * @date 2019/10/30 23:06
 * @since JDK 1.8
 */
public enum Login {
    Login_Success("登录成功", 0),
    Login_Error("登录失败", 1);

    private String msg;

    private Integer code;

    Login(String msg, int code) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return msg;
    }

}
