package com.tuny.demo.util;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangzhaofeng
 * @date 2018/8/12 上午10:58
 */
@Data
@AllArgsConstructor
@ApiModel("分页返回对象")
@Deprecated
public class PageVo<T> implements Serializable {

    @ApiModelProperty("rows total")
    private Integer total;

    @ApiModelProperty("data source")
    private List<T> list;

    @ApiModelProperty("分页信息")
    private Pagination pagination = new Pagination();

    public PageVo() {
        this.setTotal(0);
        this.setList(null);
    }

    public PageVo(long total, List<T> list) {
        this.setTotal((int) total);
        this.setList(list);
    }

    public PageVo(PageInfo<T> pi) {
        this.setTotal((int) pi.getTotal());
        this.setList(pi.getList());
        this.getPagination().setPageIndex(pi.getPageNum());
        this.getPagination().setPageSize(pi.getPageSize());
        this.getPagination().setTotal((int) pi.getTotal());
        this.getPagination().setTotalPage(pi.getPages());
    }

    public PageVo(PageInfo pi, List<T> list) {
        this.setTotal((int) pi.getTotal());
        this.setList(list);
        this.getPagination().setPageIndex(pi.getPageNum());
        this.getPagination().setPageSize(pi.getPageSize());
        this.getPagination().setTotal((int) pi.getTotal());
        this.getPagination().setTotalPage(pi.getPages());
    }
}
