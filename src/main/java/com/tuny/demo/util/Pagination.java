package com.tuny.demo.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 适配pagehelper的分页类-后端使用
 *
 * @author wangzhaofeng
 * @date 2018/8/13 下午8:11
 */
@ApiModel("分页对象")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pagination implements java.io.Serializable {

    public static final String PAGE_INDEX = "pageIndex";

    public static final String PAGE_SIZE = "pageSize";

    public static final String TOTAL = "total";

    public static final String TOTAL_PAGE = "totalPage";

    public static final String DESCENDING = "descending";

    public static final String SORT_BY = "sortBy";

    @ApiModelProperty("页码")
    private Integer pageIndex = 1;

    @ApiModelProperty("每页行数")
    private Integer pageSize = 15;

    @ApiModelProperty("总记录数")
    private Integer total;

    @ApiModelProperty("总页数")
    private Integer totalPage;

    @ApiModelProperty("排序顺序 true|false")
    private Boolean descending;

    @ApiModelProperty("排序字段")
    private String sortBy;
}
