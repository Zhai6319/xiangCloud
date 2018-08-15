package com.xiang.base.response;

import com.xiang.base.model.PagedResult;

import java.util.HashMap;

/**
 * @author zhai_
 * @Date 2018-03-25
 * @功能 返回结果类
 * */
public class R extends HashMap<String,Object>{
    private static final long serialVersionUID = -4187205174083008835L;

    public R(){
        put("code","0");
        put("result",true);
    }
    /**
     * 返回分页数据
     * */
    public static <T> R page(PagedResult<T> pagedResult){
        R r = new R();
        r.put("code","0");
        r.put("result",true);
        r.put("data",pagedResult.getDataList());
        r.put("count",pagedResult.getTotal());
        return r;
    }
    /**
     * 返回成功
     * */
    public static R success(){
        return new R();
    }
    /**
     * 带信息返回成功
     * */
    public static R success(String msg){
        R r = new R();
        r.put("msg",msg);
        return r;
    }
    /**
     * 带数据返回成功
     * */
    public static R success(Object data){
        R r = new R();
        r.put("data",data);
        return r;
    }
    /**
     * 返回失败
     * */
    public static R error(){
        R r = new R();
        r.put("code",500);
        r.put("result",false);
        r.put("msg","未知错误，请系统联系管理员");
        return r;
    }
    /**
     * 退出登陆
     * */
    public static R logout(String msg){
        R r = new R();
        r.put("code","1001");
        r.put("result",false);
        r.put("msg",msg);
        return r;
    }
    /**
     * 带信息返回失败
     * */
    public static R error(String msg){
        R r = R.error();
        r.put("code",500);
        r.put("result",false);
        r.put("msg",msg);
        return r;
    }
    /**
     * 重写错误编码和信息返回失败
     * */
    public static R error(int code,String msg){
        R r = new R();
        r.put("code",code);
        r.put("result",false);
        r.put("msg",msg);
        return r;
    }

     @Override
     public R put(String key, Object value){
        super.put(key, value);
        return this;
     }


}
