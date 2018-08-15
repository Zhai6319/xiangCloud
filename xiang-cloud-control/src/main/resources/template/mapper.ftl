<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.dao.${className}Dao">
    <resultMap id="${className}ResultMap" type="${packageName}.model.${className}">
<#list attrs as attr>
    <#if attr.isPk>
        <id column="${attr.columnName}" jdbcType="${attr.jdbcType}" property="${attr.javaColumnName}" />
    <#else>
        <result column="${attr.columnName}" jdbcType="${attr.jdbcType}" property="${attr.javaColumnName}" />
    </#if>
</#list>
    </resultMap>
    <sql id="Column_List">
<#list attrs as attr>
    <#if attrs?size &gt; (attr_index+1)>    ${attr.columnName},<#else>  ${attr.columnName}</#if>
</#list>
    </sql>
    <sql id="ALL_SQL">
<#list attrs as attr>
    <#if (attr.width)?? && attr.width = 1>
        <if test="where.${attr.javaColumnName} != null">
            AND ${attr.columnName} = ${attr.mapperColumnName}
        </if>
    <#else>
        <if test="where.${attr.javaColumnName} != null and where.${attr.javaColumnName} != '' ">
            AND ${attr.columnName} = ${attr.mapperColumnName}
        </if>
    </#if>
</#list>
    </sql>
    <sql id="LIKE_SQL">
<#list attrs as attr>
    <#if (attr.width)?? && attr.width = 1>
        <if test="where.${attr.javaColumnName} != null">
            AND ${attr.columnName} = ${attr.mapperColumnName}
        </if>
    <#else>
        <if test="where.${attr.javaColumnName} != null and where.${attr.javaColumnName} != '' ">
            AND ${attr.columnName} like CONCAT('%',${attr.mapperColumnName},'%' )
        </if>
    </#if>
</#list>
    </sql>

    <select id="selectByParams" parameterType="map" resultMap="${className}ResultMap">
        <!-- 根据条件查询信息 -->
        select
        <include refid="Column_List"/>
        from ${tableName}
        <where>deleted = '0'
            <include refid="ALL_SQL"/>
        </where>
    </select>
    <select id="likeByParams" parameterType="map" resultMap="${className}ResultMap">
        <!-- 根据条件模糊匹配信息 -->
        select
        <include refid="Column_List"/>
        from ${tableName}
        <where>deleted = '0'
            <include refid="LIKE_SQL"/>
        </where>
    </select>
    <select id="selectByPK" parameterType="java.lang.Long" resultMap="${className}ResultMap">
        <!-- 根据主键ID查询信息 -->
        select
        <include refid="Column_List"/>
        from ${tableName}
        <where>
            DELETED = '0' AND ID = ${r'#{pk,jdbcType=INTEGER}'}
        </where>
    </select>
   <insert id="insert" parameterType="${packageName}.model.${className}" useGeneratedKeys="true" keyProperty="id">
       <!-- 添加数据 -->
       insert into ${tableName} (
        <#list attrs as attr>
            <#if attrs?size &gt; (attr_index+1)>    ${attr.columnName},<#else>  ${attr.columnName}</#if>
        </#list>
       ) values (
       <#list attrs as attr>
           <#if attrs?size &gt; (attr_index+1)>    ${attr.noWhereColumnName},<#else>  ${attr.noWhereColumnName}</#if>
       </#list>
       )
   </insert>
    <insert id="insertSelective" parameterType="${packageName}.model.${className}" useGeneratedKeys="true" keyProperty="id">
        <!-- 添加数据（判断是否为空） -->
        insert into ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list attrs as attr>
            <if test="${attr.javaColumnName} != null">
                ${attr.columnName},
            </if>
            </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            <#list attrs as attr>
            <if test="${attr.javaColumnName} != null">
                ${attr.noWhereColumnName},
            </if>
            </#list>
        </trim>
    </insert>
    <insert id="batchInsert" parameterType="java.util.List">
        <!-- 批量添加数据 -->
        INSERT INTO ${tableName} (
        <#list attrs as attr>
            <#if attrs?size &gt; (attr_index+1)>    ${attr.columnName},<#else>  ${attr.columnName}</#if>
        </#list>
        ) VALUES
        <foreach collection="list" item="item" index="index" separator=",">
            (
            <#list attrs as attr>
                <#if attrs?size &gt; (attr_index+1)>    ${r'#{item.'}${attr.javaColumnName}${r',jdbcType='}${attr.jdbcType}${r'}'},<#else>  ${r'#{item.'}${attr.javaColumnName}${r',jdbcType='}${attr.jdbcType}${r'}'}</#if>
            </#list>
            )
        </foreach>
    </insert>
    <update id="updateByPK" parameterType="${packageName}.model.${className}">
        update ${tableName} set
        <#list attrs as attr>
            <#if attrs?size &gt; (attr_index+1)>
                <#if !attr.isPk>
                ${attr.columnName} = ${r'#{'}${attr.javaColumnName}${r',jdbcType='}${attr.jdbcType}${r'}'},
                </#if>
            <#else>
                ${attr.columnName} = ${r'#{'}${attr.javaColumnName}${r',jdbcType='}${attr.jdbcType}${r'}'}
            </#if>
        </#list>
         <#list attrs as attr>
             <#if attr.isPk>
                 WHERE ${attr.columnName} = ${r'#{'}${attr.javaColumnName}${r',jdbcType='}${attr.jdbcType}${r'}'}
             </#if>
         </#list>
    </update>
    <update id="updateByPKSelective" parameterType="${packageName}.model.${className}">
        update ${tableName}
            <set>
        <#list attrs as attr>
            <#if attrs?size &gt; (attr_index+1)>
                <#if !attr.isPk>
                <if test="${attr.javaColumnName} != null">
                    ${attr.columnName} = ${r'#{'}${attr.javaColumnName}${r',jdbcType='}${attr.jdbcType}${r'}'},
                </if>
                </#if>
            <#else>
                <if test="${attr.javaColumnName} != null">
                    ${attr.columnName} = ${r'#{'}${attr.javaColumnName}${r',jdbcType='}${attr.jdbcType}${r'}'}
                </if>
            </#if>
        </#list>
            </set>
        <#list attrs as attr>
            <#if attr.isPk>
                 where ${attr.columnName} = ${r'#{'}${attr.javaColumnName}${r',jdbcType='}${attr.jdbcType}${r'}'}
            </#if>
        </#list>
    </update>
    <delete id="deleteByPK" parameterType="java.lang.Long">
        delete from ${tableName} where ID = ${r'#{pk,jdbcType=INTEGER}'}
    </delete>
</mapper>