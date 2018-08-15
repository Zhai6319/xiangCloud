package com.xiang.cloud.zuul.model.vo;

import com.xiang.base.model.BaseModel;
import lombok.Data;

/**
 * @author zhaijianchao
 */
@Data
public class CodeGenerator extends BaseModel<Long>{
    /**
     * 是否主键
     * */
    private Boolean isPk;
    /**
     * 字段名称
     * */
    private String columnName;
    /**
     * 字段类型
     * */
    private String jdbcType;
    /**
     * java类型
     * */
    private String javaType;
    /**
     * java类型
     * */
    private String javaColumnName;
    /**
     * mapper文件映射java字段
     * */
    private String mapperColumnName;
    /**
     * mapper noWhere
     * */
    private String noWhereColumnName;
    /**
     * 字段注释
     * */
    private String notes;
    /**
     * 字段长度
     * */
    private Long width;
    /**
     * 小数点位数
     * */
    private Integer numericScale;
    /**
     * 默认值
     * */
    private String defaultValue;
    /**
     * 数据库
     * */
    private String tableSchema;
    /**
     * 表名称
     * */
    private String tableName;
    /**
     * 作者
     * */
    private String author;
    /**
     * className
     * */
    private String className;

}
