package com.tuny.demo.controller;

import com.tuny.demo.util.WebUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hucs@tunynet.com
 * @version 0.5
 * @date Created in 2019-11-20 21:29
 * @description 描述
 * @modified By
 */
@Controller
public class IndexController {
    /**
       * @description 描述:跳转首页
     */
    @RequestMapping("/")
    public String toIndex(){
        return "system/index/login";
    }

    @RequestMapping("/sys/toBook")
    public String toBook(){
        return "system/book/bookManager";
    }

    @RequestMapping("/sys/toRentList")
    public String toRentList(Long bookId){
        WebUtil.getSession().setAttribute("bookId",bookId);
        return "system/book/rentDetail";
    }
}
