package com.tuny.demo.vo.book;

import com.tuny.demo.domain.BookDomain;
import lombok.Data;


/**
 * @author hucs@tunynet.com
 * @version 0.5
 * @date Created in 2019-11-22 09:24
 * @description 描述
 * @modified By
 */
@Data
public class BookOutVo extends BookDomain {

    private String author;

    private String image;

    private String publisher;

    private String bookName;

    private Integer stock;

    private String description;
}
