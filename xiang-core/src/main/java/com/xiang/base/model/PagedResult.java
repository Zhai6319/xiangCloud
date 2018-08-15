package com.xiang.base.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaijianchao
 * @date 2018.08.07
 * 分页查询结果传输类
 */
public class PagedResult<T> implements Serializable{

    private static final long serialVersionUID = -87683654499983094L;
    /**
     * 数据
     * */
    private List<T> dataList;
    /**
     * 当前页
     * */
    private long pageNo;
    /**
     * 条数
     * */
    private long pageSize;
    /**
     * 总条数
     * */
    private long total;
    /**
     * 总页面数目
     * */
    private long pages;

    public PagedResult(){}

    public PagedResult(long pageNo, long pageSize){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }


    public List<T> getDataList() {
        if (dataList == null) {
            dataList = new ArrayList<>();
        }
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public long getPageNo() {
        return pageNo;
    }

    public void setPageNo(long pageNo) {
        this.pageNo = pageNo;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }
}
