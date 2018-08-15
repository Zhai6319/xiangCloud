package com.xiang.base.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 公共实体父类，所有实体类都有此类字段
 * @author zhaijianchao
 */
public class BaseModel<PK> implements Serializable{
    private static final long serialVersionUID = -3933923212009147207L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private PK id;
    private PK createById;
    private PK modifyById;
    private Timestamp createTime;
    private Timestamp modifyTime;
    private Boolean deleted;

    /**
     * 主键ID
     * */
    public PK getId() {
        return id;
    }

    /**
     * 主键ID
     * */
    public void setId(PK id) {
        this.id = id;
    }

    /**
     * 创建者用户ID
     * */
    public PK getCreateById() {
        return createById;
    }
    /**
     * 创建者用户ID
     * */
    public void setCreateById(PK createById) {
        this.createById = createById;
    }
    /**
     * 修改者用户ID
     * */
    public PK getModifyById() {
        return modifyById;
    }
    /**
     * 修改者用户ID
     * */
    public void setModifyUserId(PK modifyById) {
        this.modifyById = modifyById;
    }

    /**
     * 创建时间
     * */
    public Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间
     * */
    public Timestamp getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间
     * */
    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 删除标志
     * */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * 删除标志
     * */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

}
