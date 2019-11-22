package com.tuny.demo.controller;

import com.tuny.demo.service.RentService;
import com.tuny.demo.util.BaseController;
import com.tuny.demo.util.ResultJson;
import com.tuny.demo.vo.rent.RentFindVo;
import com.tuny.demo.vo.rent.RentVo;
import io.swagger.annotations.*;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;


@Api(value = "tn_rent")
@RestController
@Validated
@RequestMapping("/sys")
public class RentController extends BaseController {

    @Autowired
    public DozerBeanMapper mapper;

    @Autowired
    private RentService rentService;

    @PostMapping("/rent")
    public ResultJson rentBook(RentVo param) throws ParseException {
        if (null != param.getRentTime() && null != param.getBackTime()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (sdf.parse(param.getRentTime()).after((sdf.parse(param.getBackTime())))) {
                return ResultJson.error("开始日期不能大于结束日期");
            }

        }
        return rentService.rentBook(param);
    }

    @GetMapping("/rent-list")
    public ResultJson rentList(RentFindVo param) {
        return ResultJson.createFromPage(rentService.rentList(param));
    }


}

