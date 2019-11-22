package com.tuny.demo.vo.book;

import lombok.Data;


/**
 * @author hucs@tunynet.com
 * @version 0.5
 * @date Created in 2019-11-22 09:48
 * @description 描述
 * @modified By
 */
@Data
public class BookAddVo {

    private Long id;

    private String author;


    private String publisher;

    private Integer stock;

    private String description;


    private String bookName;
}
