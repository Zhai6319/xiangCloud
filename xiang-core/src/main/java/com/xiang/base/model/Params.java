package com.xiang.base.model;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 查询参数加工类
 * @author zhaijianchao
 */
public abstract class Params extends HashMap<String,Object>{

    private Map<String,String> orderBys;

    /**
     * 获取排序字段
     * */
    public Map<String,String> getOrderBys(){
        if (orderBys.keySet() == null || orderBys.values() == null || orderBys == null || orderBys.keySet().isEmpty() || orderBys.values().isEmpty()){
            this.orderBys = new HashMap<>();
            this.orderBys.put("createTime","desc");
        }
        return this.orderBys;
    }
    public void setOrderBys(Map<String,String> orderBys){
        this.orderBys = orderBys;
    }

    public PagedResult<Object> getPage(){
        Object page = this.get("page");
        return page == null ? null : (PagedResult)page;
    }
    public void setPage(PagedResult<Object> page){
        this.add("page",page);
    }



    public Params add(Object param){
        if(testParam(param)){
            this.put("where",param);
        }
        return this;
    }

    public Params add(String key,Object value){
        if(this.testParam(value)){
            this.put(key, value);
        }
        return this;
    }

    private boolean testParam(Object param){
        return param == null ? false : !(param instanceof String) || !StringUtils.isBlank((String) param);
    }

    public static Params create(){
        NewParams params = new NewParams();
        return params;
    }

    public static Params create(HttpServletRequest request){
        NewParams result = new NewParams();
        request.getParameterMap().forEach((k, v) -> {
            result.add(k, v[0]);
        });
        return result;
    }

    private static class NewParams extends Params{



    }

    public static void main(String[] args) {
        Params params = Params.create();
        BaseModel<Long> baseModel = new BaseModel<>();
        baseModel.setId(44l);
        params.add(baseModel);
        System.out.println(JSON.toJSONString(params));
    }

}

