package com.tuny.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;

/**
 * 缺省Service的抽象实现类
 *
 * @author wangzhaofeng
 * @date 2018/8/12 上午11:34
 */
@CacheConfig(cacheNames = "myCache")
public abstract class BaseService<T extends BaseDomain> {


    /**
     * 雪花ID生成器
     */
    @Autowired
    public IdWorker idWorker;


    /**
     * 日志Service
     */

    /**
     * 数据库类型
     */


    public abstract <D extends BaseDao> D getDAO();

    public T findByPrimaryKey(Object primaryKey) {
        return (T) this.getDAO().findById(primaryKey);
    }

    public int edit(T param) {
        // 主键或者ID不允许全部为空
        if (param.getId() == null && param.getPrimaryKey() == null) {
            throw new ParamCheckException("error: Table primaryKey is null ");
        }
        return this.getDAO().update(param);
    }

    public int add(T param) {
        // 主键为空
        if (param.getId() == null) {
            param.setId(idWorker.nextId());
        }
        return this.getDAO().insert(param);
    }



    /**
     * 删除
     *
     * @param primaryKey
     * @return
     */
    public int delete(Object primaryKey) {
        return this.getDAO().delete(primaryKey);
    }

}
