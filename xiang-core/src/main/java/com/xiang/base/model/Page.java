package com.xiang.base.model;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaijianchao
 * 分页数据传输
 */
public class Page implements Serializable {
    private static final long serialVersionUID = -1135178566994101911L;

    private int pageNo;
    private int pageSize;
    private String orderColumn;
    private String orderSort;
    private Map<String,String> orderBys;

    public Page(){

    }
    public Page(int pageNo, int pageSize){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.orderBys = new HashMap<>();
        this.orderBys.put("createTime","desc");
    }

    public Page(int pageNo, int pageSize, String orderColumn, String orderSort){
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.orderColumn = orderColumn;
        this.orderSort = orderSort;
        if(StringUtils.isNotBlank(orderColumn) && StringUtils.isNotBlank(orderSort)){
            this.orderBys = new HashMap<>();
            this.orderBys.put(orderColumn,orderSort);
        }
    }

    public Page(int pageNo, int pageSize, String orderColumn) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.orderColumn = orderColumn;
        if(StringUtils.isNotBlank(orderColumn)){
            this.orderBys = new HashMap<>();
            this.orderBys.put(orderColumn,"DESC");
        }

    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderSort() {
        return orderSort;
    }

    public void setOrderSort(String orderSort) {
        this.orderSort = orderSort;
    }

    public Map<String, String> getOrderBys() {
        if (orderBys == null || orderBys.keySet() == null || orderBys.values() == null || orderBys.keySet().isEmpty() || orderBys.values().isEmpty()){
            this.orderBys = new HashMap<>();
            this.orderBys.put("createTime","desc");
        }
        return orderBys;
    }

    public void setOrderBys(String orderColumn,String orderSort) {
        orderBys = new HashMap<>();
        this.orderBys.put(orderColumn,orderSort);
    }
}
