package com.tuny.demo.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 分页参数类-传入参数专用
 *
 * @author wangzhaofeng
 * @date 2018/8/13 下午8:11
 */
@ApiModel("分页参数")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaginationVo implements java.io.Serializable {

    @ApiModelProperty("页码")
    private int pageIndex = 1;

    @ApiModelProperty("每页行数")
    private int pageSize = 15;
}
