package com.xiang.base.service;


import com.xiang.base.model.BaseModel;
import com.xiang.base.model.Page;
import com.xiang.base.model.PagedResult;
import com.xiang.base.model.Params;

import java.util.List;

/**
 * service业务处理公共接口类
 * created by zhaijianchao on 2018-01-01
 * @author zhaijianchao
 */
public interface BaseService <T extends BaseModel<Long>>{
    /**
     * 根据主键查询数据
     * @param pk 主键
     * @return
     * */
    T queryByPK(Long pk);
    /**
     * 条件查询所有
     * @param params 查询条件
     * @return
     * */
    List<T> queryByParams(Params params);
    /**
     * 条件模糊查询所有
     * @param params 查询条件
     * @return
     * */
    List<T> likeQueryByParams(Params params);
    /**
     * 条件查询单条信息
     * @param entity 查询条件
     * @return
     * */
    T querySingleByParams(T entity);
    /**
     * 分页查询
     * @param params 查询条件
     * @param page 分页条件
     * @return
     * */
    PagedResult<T> page(Params params, Page page);
    /**
     * 根据ID修改信息
     * @param entity 修改实体类数据
     * @return
     * */
    int modifyByPk(T entity);
    /**
     * 添加数据
     * @param entity 实体类数据
     * @return
     * */
    int add(T entity);
    /**
     * 批量添加数据
     * @param entityList 实体数据集合
     * @return
     * */
    int batchAdd(List<T> entityList);
    /**
     * 根据主键逻辑删除数据
     * @param pk 主键-ID
     * @return
     * */
    int logicDelByPk(Long pk);
}
