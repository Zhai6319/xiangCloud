package com.xiang.base.controller;


import com.xiang.base.core.http.HttpHolder;
import com.xiang.base.model.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhaijianchao
 */
public abstract class BaseController {

    public HttpServletRequest getRequest(){
        return HttpHolder.getHttpRequest();
    }
    public HttpServletResponse getResponse(){
        return HttpHolder.getHttpResponse();
    }

    public Page getPage(){
        Integer pageNo = Integer.parseInt(this.getRequest().getParameter("page")==null?"1":this.getRequest().getParameter("page"));
        Integer pageSize = Integer.parseInt(this.getRequest().getParameter("limit")==null?"10":this.getRequest().getParameter("limit"));
        String orderSort = this.getRequest().getParameter("orderSort");
        String orderColumn = this.getRequest().getParameter("orderColumn");
        Page page = new Page(pageNo,pageSize,orderColumn,orderSort);
        return page;
    }
}
