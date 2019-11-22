package com.tuny.demo.vo.rent;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author hucs@tunynet.com
 * @version 0.5
 * @date Created in 2019-11-22 12:58
 * @description 描述
 * @modified By
 */
@Data
public class RentOutVo {

    private Long bookId;

    private Long userId;

    private String userName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date rentTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date backTime;
}
