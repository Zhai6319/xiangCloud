package com.xiang.base.dao;


import com.xiang.base.model.BaseModel;
import com.xiang.base.model.Params;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;
/**
 * @author zhaijianchao
 */
public interface BaseDao<T extends BaseModel<Long>> extends Mapper<T>,MySqlMapper<T> {
    /**
     * 根据主键查询信息
     * @param pk 主键-id
     * @return
     * */
    T selectByPK(Long pk);

    /**
     * 条件查询信息
     * @param params 查询条件
     * @return
     * */
    List<T> selectByParams(Params params);

    /**
     * 模糊查询信息
     * @param params 查询条件
     * @return
     * */
    List<T> likeByParams(Params params);

    /**
     * 通过ID修改信息
     * @param entity 实体类
     * @return
     * */
    int updateByPK(T entity);
    /**
     * 选择性通过ID修改信息
     * @return
     * */
    int updateByPKSelective(T entity);
    /**
     * 单条数据添加
     * @param entity 需要添加的数据
     * @return
     * */
    @Override
    int insert(T entity);
    /**
     * 选择性添加数据，避开为空数据
     * @param entity 需要添加的数据
     * @return
     * */
    @Override
    int insertSelective(T entity);
    /**
     * 批量添加数据
     * @param entityList 需要添加的数据集合
     * @return
     * */
    int batchInsert(List<T> entityList);
    /**
     * 物理删除数据
     * @param pk 主键-ID
     * @return
     * */
    int deleteByPK(Long pk);



}
