package com.tuny.demo.controller;

import com.tuny.demo.service.UserService;
import com.tuny.demo.util.BaseController;
import com.tuny.demo.util.ResultJson;
import com.tuny.demo.vo.LoginVo;
import io.swagger.annotations.*;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(value = "tn_user")
@RestController
@Validated
public class UserController extends BaseController {

    @Autowired
    public DozerBeanMapper mapper;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public ResultJson login(@Valid LoginVo param) {
        return userService.login(param);
    }

}

