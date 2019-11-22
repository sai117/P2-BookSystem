package com.tuny.demo.vo.rent;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author hucs@tunynet.com
 * @version 0.5
 * @date Created in 2019-11-22 11:43
 * @description 描述
 * @modified By
 */
@Data
public class RentVo {

    private Long bookId;

    private String rentTime;

    private String backTime;
}
