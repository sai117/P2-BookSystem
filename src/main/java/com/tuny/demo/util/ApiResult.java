package com.tuny.demo.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 接口返回类
 *
 * @author wangzhaofeng
 * @date 2019-03-16 14:10:38
 */
@ApiModel("接口返回结果对象")
@Data
public class ApiResult<T> {

    @ApiModelProperty("返回结果数据")
    private T data;

    @ApiModelProperty("备注/提醒")
    private String msg;

    @ApiModelProperty("分页信息")
    private Pagination page;

    @ApiModelProperty("结果状态[0:正常 1:错误]")
    private Integer code = StatusType.Normal.ordinal();

    @ApiModelProperty(value = "请求的服务器时间")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime serverDate = LocalDateTime.now();

    public ApiResult() {
    }

    public ApiResult(StatusType code, String msg) {
        this.code = code.ordinal();
        this.msg = msg;
    }

    public ApiResult(T data) {
        this.data = data;
    }

    public ApiResult(T data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    /**
     * 从PageInfo构建一个ApiResult
     *
     * @param pageInfo
     * @param <T>
     * @return
     */
    public static <T> ApiResult<List<T>> createFromPage(PageInfo<T> pageInfo) {
        ApiResult<List<T>> result = new ApiResult<>();
        result.data = pageInfo.getList();
        result.page = new Pagination();
        result.page.setPageIndex(pageInfo.getPageNum());
        result.page.setPageSize(pageInfo.getPageSize());
        result.page.setTotal((int) pageInfo.getTotal());
        result.page.setTotalPage(pageInfo.getPages());

        return result;
    }

    /**
     * 从PageVo构建一个ApiResult
     *
     * @param pageVo
     * @param <T>
     * @return
     * @author wangzhaofeng
     * @date 2019/4/18 下午6:13
     */
    public static <T> ApiResult<List<T>> createFromPage(PageVo<T> pageVo) {
        ApiResult<List<T>> result = new ApiResult<>();
        result.data = pageVo.getList();
        result.page = pageVo.getPagination();
        return result;
    }

    /**
     * 生成错误类型实例
     *
     * @param errorMsg
     * @return
     */
    public static <T> ApiResult<T> createError(String errorMsg) {
        return new ApiResult<>(StatusType.Warning, errorMsg);
    }

    /**
     * 生成正常类型实例
     *
     * @param msg
     * @return
     */
    public static <T> ApiResult<T> createNormal(String msg) {
        return new ApiResult<>(StatusType.Normal, msg);
    }

    /**
     * 生成需验证类型实例
     *
     * @param msg
     * @return
     */

    /**
     * 生成无权限类型实例
     *
     * @param msg
     * @return
     */

    /**
     * 设置类型
     *
     * @param type
     */

    /**
     * 创建默认带page实例化的返回对象
     *
     * @param hasPage
     */
    public ApiResult(Boolean hasPage) {
        if (hasPage) {
            page = new Pagination();
        }
    }

    /**
     * 设置错误信息并更新为Warning状态
     *
     * @param errorMsg
     */

}
