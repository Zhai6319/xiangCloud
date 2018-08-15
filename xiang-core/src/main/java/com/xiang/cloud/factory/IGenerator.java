package com.xiang.cloud.factory;

import com.xiang.base.model.PagedResult;

import java.util.List;
import java.util.Set;

/**
 * DO与DTO/VO数据类转换工厂接口
 * @author zhaijianchao
 * @date 2018-08-06
 */
public interface IGenerator {

    /**
     * 单个对象的深度复制及类型转换，response/domain , po
     * @param s 数据对象
     * @param clz 复制目标类型
     * @return
     * @author zhaijianchao
     * @date  2018年8月6日
     */
    <T, S> T convert(S s, Class<T> clz);

    /**
     * 深度复制结果集(ResultSet为自定义的分页结果集)
     * @param s 数据对象
     * @param clz 复制目标类型
     * @return
     * @author zhaijianchao
     * @date  2018年8月6日
     */
    <T, S> PagedResult<T> convert(PagedResult<S> s, Class<T> clz);

    /**
     * list深度复制
     * @param s 数据对象
     * @param clz 复制目标类型
     * @return
     * @author zhaijianchao
     * @date  2018年8月6日
     */
    <T, S> List<T> convert(List<S> s, Class<T> clz);

    /**
     * set深度复制
     * @param s 数据对象
     * @param clz 复制目标类型
     * @return
     * @author zhaijianchao
     * @date  2018年8月6日
     */
    <T, S> Set<T> convert(Set<S> s, Class<T> clz);

    /**
     * 数组深度复制
     * @param s 数据对象
     * @param clz 复制目标类型
     * @return
     * @author zhaijianchao
     * @date  2018年8月6日
     */
    <T, S> T[] convert(S[] s, Class<T> clz);

}
