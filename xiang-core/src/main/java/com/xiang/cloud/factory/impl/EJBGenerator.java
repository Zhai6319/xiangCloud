package com.xiang.cloud.factory.impl;

import com.xiang.cloud.factory.IGenerator;
import com.xiang.base.model.PagedResult;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhaijianchao
 */
@Component
@Lazy(true)
public class EJBGenerator implements IGenerator {

    @Autowired
    protected Mapper dozerMapper;

    @Override
    public <T, S> T convert(S s, Class<T> clz) {
        if (s == null) {
            return null;
        }
        return this.dozerMapper.map(s, clz);
    }

    @Override
    public <T, S> PagedResult<T> convert(PagedResult<S> s, Class<T> clz) {
        if (s == null) {
            return null;
        }
        PagedResult<T> resultSet = new PagedResult<T>();
        for (S vs : s.getDataList()) {
            resultSet.getDataList().add(this.dozerMapper.map(vs, clz));
        }
        resultSet.setTotal(s.getTotal());
        resultSet.setPageNo(s.getPageNo());
        resultSet.setPages(s.getPages());
        resultSet.setPageSize(s.getPageSize());
        return resultSet;
    }

    @Override
    public <T, S> List<T> convert(List<S> s, Class<T> clz) {
        if (s == null) {
            return null;
        }
        List<T> list = new ArrayList<T>();
        for (S vs : s) {
            list.add(this.dozerMapper.map(vs, clz));
        }
        return list;
    }

    @Override
    public <T, S> Set<T> convert(Set<S> s, Class<T> clz) {
        if (s == null) {
            return null;
        }
        Set<T> set = new HashSet<T>();
        for (S vs : s) {
            set.add(this.dozerMapper.map(vs, clz));
        }
        return set;
    }

    @Override
    public <T, S> T[] convert(S[] s, Class<T> clz) {
        if (s == null) {
            return null;
        }
        @SuppressWarnings("unchecked")
        T[] arr = (T[]) Array.newInstance(clz, s.length);
        for (int i = 0; i < s.length; i++) {
            arr[i] = this.dozerMapper.map(s[i], clz);
        }
        return arr;
    }
}
