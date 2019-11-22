package com.tuny.demo.util;

import java.util.List;

/**
 * 缺省Dao层接口类
 * Dao层的接口实现在MyBatis，所以不需要代码的impl实现层
 *
 * @author wangzhaofeng
 * @date 2018/8/12 上午10:16
 */
public interface BaseDao<T extends BaseDomain> {

    /**
     * 有条件查询 自带分页效果，返回List
     *
     * @param param
     * @return
     */
    List<T> find(T param);

    /**
     * 根据主键查询返回唯一
     *
     * @param primaryKey
     * @return
     */
    T findById(Object primaryKey);

    /**
     * 插入记录，主键非空不插入
     *
     * @param param
     * @return
     */
    int insert(T param);

    /**
     * 批量插入
     *
     * @param param
     * @return
     */
    int insertAll(List<T> param);

    /**
     * 批量插入Mysql
     *
     * @param param
     * @return
     */
    int insertAllMysql(List<T> param);

    /**
     * 根据主键非空更新
     *
     * @param param
     * @return
     */
    int update(T param);

    /**
     * 根据主键删除
     *
     * @param primaryKey
     * @return
     */
    int delete(Object primaryKey);

}
