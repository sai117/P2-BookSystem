package com.tuny.demo.vo.book;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author hucs@tunynet.com
 * @version 0.5
 * @date Created in 2019-11-22 09:19
 * @description 描述
 * @modified By
 */
@Data
public class BookFindVo {

    private String bookName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    private Integer page = 1;

    private Integer limit = 10;
}
