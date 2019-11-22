package com.tuny.demo.controller;

import com.tuny.demo.service.BookService;
import com.tuny.demo.util.BaseController;
import com.tuny.demo.util.ResultJson;
import com.tuny.demo.vo.book.BookAddVo;
import com.tuny.demo.vo.book.BookFindVo;
import io.swagger.annotations.*;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Api(value = "tn_book")
@RestController
@Validated
@RequestMapping("/sys")
public class BookController extends BaseController {

    @Autowired
    public DozerBeanMapper mapper;

    @Autowired
    private BookService bookService;

    @GetMapping("/book")
    public ResultJson bookList(BookFindVo param) {
        return ResultJson.createFromPage(bookService.bookList(param));
    }

    @PostMapping("/book")
    public ResultJson insertOrUpdateBook(BookAddVo param) {
        //根据参数ID来判断新增或修改
        if (null == param.getId()) {
            return bookService.insertBook(param);
        }
        if (null != param.getId() && param.getId() > 0) {
            return bookService.updateBook(param);
        }
        return ResultJson.error("提交时失败");
    }

    @DeleteMapping("/book")
    public ResultJson deleteBook(String[] ids) {
        List<Long> list = new ArrayList<>();
        for (String s : ids) {
            list.add(Long.valueOf(s));
        }
        return bookService.deleteBook(list);
    }
}

