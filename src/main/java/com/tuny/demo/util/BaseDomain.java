package com.tuny.demo.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 缺省domain层类（domain指DomainObject层）
 * 含6个表必备字段字段
 *
 * @author wangzhaofeng
 * @date 2018/8/12 上午10:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("base data object")
public class BaseDomain implements Serializable {


    public static final String PRIMARY_KEY = "primaryKey";
    public static final String ID = "id";
    public static final String PAGINATION = "pagination";

    /**
     * 代指表数据库表主键
     */
    @ApiModelProperty("主键")
    private Object primaryKey;

    /**
     * id为表约定主键，但部分表不使用id
     */
    @ApiModelProperty("约定字段ID")
    private Long id;

    @ApiModelProperty("分页信息")
    private Pagination pagination = new Pagination();

}
