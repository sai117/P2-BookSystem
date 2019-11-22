package com.tuny.demo.vo.rent;

import lombok.Data;

/**
 * @author hucs@tunynet.com
 * @version 0.5
 * @date Created in 2019-11-22 12:55
 * @description 描述
 * @modified By
 */
@Data
public class RentFindVo {
    private Integer page = 1;

    private Integer limit = 10;

    private Long bookId;
}
