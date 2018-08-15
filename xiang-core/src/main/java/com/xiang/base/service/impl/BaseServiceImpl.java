package com.xiang.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.xiang.cloud.common.util.database.BeanUtil;
import com.xiang.cloud.common.util.database.OrderByUtil;
import com.xiang.base.dao.BaseDao;
import com.xiang.base.model.BaseModel;
import com.xiang.base.model.Page;
import com.xiang.base.model.PagedResult;
import com.xiang.base.model.Params;
import com.xiang.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author zhaijianchao
 */
@Service
public class BaseServiceImpl<T extends BaseModel<Long>> implements BaseService<T> {
    @Autowired
    private BaseDao<T> baseDao;
    @Override
    public T queryByPK(Long pk) {
        return baseDao.selectByPK(pk);
    }

    @Override
    public List<T> queryByParams(Params params) {
        return baseDao.selectByParams(params);
    }

    @Override
    public List<T> likeQueryByParams(Params params) {
        return baseDao.likeByParams(params);
    }

    @Override
    public T querySingleByParams(T entity) {
        Params params = Params.create();
        params.add(entity);
        List<T> dataList = this.queryByParams(params);
        if(dataList != null && dataList.size()>0){
            return dataList.get(0);
        }
        return null;
    }

    @Override
    public PagedResult<T> page(Params params, Page page) {
        //条件分页查询
        String orderBy = OrderByUtil.getOrderBy(page.getOrderBys());
        //startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
        PageHelper.startPage(page.getPageNo(), page.getPageSize(),orderBy);
        return BeanUtil.toPagedResult(baseDao.selectByParams(params));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int modifyByPk(T entity) {
        entity.setModifyTime(new Timestamp(System.currentTimeMillis()));
        return baseDao.updateByPKSelective(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(T entity) {
        return baseDao.insertSelective(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchAdd(List<T> entityList) {
        return baseDao.batchInsert(entityList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int logicDelByPk(Long pk) {
        T t = this.queryByPK(pk);
        t.setDeleted(false);
        return this.modifyByPk(t);
    }
}
