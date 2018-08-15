package com.xiang.cloud.common.util.database;


import com.github.pagehelper.Page;
import com.xiang.base.model.PagedResult;

import java.util.List;

/**
 * 功能概要：分页
 * @author zjc
 * @since  2017年01月03日
 */
public class BeanUtil {

    public static <T> PagedResult<T> toPagedResult(List<T> datas) {
        PagedResult<T> result = new PagedResult<T>();
        if (datas instanceof Page) {
            Page<T> page = (Page<T>) datas;
            result.setPageNo(page.getPageNum());
            result.setPageSize(page.getPageSize());
            result.setDataList(page.getResult());
            result.setTotal(page.getTotal());
            result.setPages(page.getPages());
        }
        else {
            result.setPageNo(1);
            result.setPageSize(datas.size());
            result.setDataList(datas);
            result.setTotal(datas.size());
        }

        return result;
    }

}

