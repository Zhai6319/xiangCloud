package com.xiang.cloud.common.util.database;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * 功能概述：对象转换数据库字段
 * @author ZJC
 * @since 2017年01月14日
 * */
public class OrderByUtil {
    /**
     * 功能概述：对象转换数据库字段
     * @param directions 对象属性
     * */
    public static final String orderBy(String directions){
        StringBuffer sb = new StringBuffer();
        String[] ss = directions.split("(?=[A-Z])");
        for(int i = 0 ;i < ss.length; i ++){
            if(!ss[i].equals("")){
                if(i<ss.length-1){
                    sb.append(ss[i]+"_");
                }else{
                    sb.append(ss[i]);
                }
            }
        }
        return sb.toString();
    }

    public static String getOrderBy(Map<String,String> orderBy){
        if(orderBy == null || orderBy.isEmpty()){
            orderBy = new HashMap<String,String>();
            orderBy.put("createTime", "desc");
        }
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for (String key : orderBy.keySet()) {
            if(!StringUtils.isBlank(key)){
                String value = orderBy.get(key);
                sb.append(orderBy(key));
                sb.append(" ");
                sb.append(value);
                if((i+1)<orderBy.keySet().size()){
                    sb.append(",");
                }else{
                    sb.append(" ");
                }
            }
            i++;
        }
        return sb.toString();
    }
}

