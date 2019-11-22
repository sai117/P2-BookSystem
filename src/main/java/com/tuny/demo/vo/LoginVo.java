package com.tuny.demo.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author hucs@tunynet.com
 * @version 0.5
 * @date Created in 2019-11-22 08:49
 * @description 描述
 * @modified By
 */
@Data
public class LoginVo {

    @NotNull(message = "用户名不能为空")
    private String userName;

    @NotNull(message = "密码不能为空")
    private String passWord;
}
