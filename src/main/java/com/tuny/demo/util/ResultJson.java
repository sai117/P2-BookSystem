package com.tuny.demo.util;

import com.github.pagehelper.PageInfo;
import com.tuny.demo.enums.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author hucs
 * @date 2019/10/20 12:57
 * @since JDK 1.8
 */
@ApiModel("接口返回结果对象")
@Data
public class ResultJson<T> {
    @ApiModelProperty("返回结果数据")
    private T data;

    @ApiModelProperty("分页信息")
    private Pagination page;

    @ApiModelProperty("code")
    private Integer code = ResultCode.SUCCESS.getCode();

    @ApiModelProperty("msg")
    private String msg;

    public static ResultJson success(Integer count, Object data) {
        ResultJson r = new ResultJson();
        r.code = ResultCode.SUCCESS.getCode();
        r.data = data;
        return r;
    }

    public static ResultJson success(Object data) {
        ResultJson r = new ResultJson();
        r.code = ResultCode.SUCCESS.getCode();
        r.data = data;
        return r;
    }

    public static ResultJson error(String msg) {
        ResultJson r = new ResultJson();
        r.code = ResultCode.FAIL.getCode();
        r.msg = msg;
        return r;
    }

    public static <T> ResultJson<List<T>> createFromPage(PageInfo<T> pageInfo) {
        ResultJson<List<T>> result = new ResultJson();
        result.data = pageInfo.getList();
        result.page = new Pagination();
        result.page.setPageIndex(pageInfo.getPageNum());
        result.page.setPageSize(pageInfo.getPageSize());
        result.page.setTotal((int) pageInfo.getTotal());
        result.page.setTotalPage(pageInfo.getPages());
        return result;
    }
}