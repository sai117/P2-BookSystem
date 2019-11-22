package com.tuny.demo.enums;

public enum ResultCode {

    /**
     * 500 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),

    /**
     * 0000 接口返回成功
     */
    SUCCESS(0, "接口返回成功"),

    /**
     * 0001 接口返回失败
     */
    FAIL(1, "接口返回失败"),


    /**
     * 0004 接口不存在
     */
    NOT_FOUND(404, "接口不存在");


    public Integer code;
    public String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
