package com.xiang.cloud.sys.model;

import com.xiang.base.model.BaseModel;

/**
 *  @author Zhai_
 */
public class SysRole extends BaseModel<Long>{
    private static final long serialVersionUID = -5287464470113009933L;
    private Long corpId;
    private String roleName;
    private String remark;

    /**
     * 所属企业id
     * */
    public void setCorpId(Long corpId){
        this.corpId = corpId;
    }
    /**
     * 所属企业id
     * */
    public Long getCorpId(){
        return this.corpId;
    }

    /**
     * 角色名称
     * */
    public void setRoleName(String roleName){
        this.roleName = roleName;
    }
    /**
     * 角色名称
     * */
    public String getRoleName(){
        return this.roleName;
    }

    /**
     * 备注
     * */
    public void setRemark(String remark){
        this.remark = remark;
    }
    /**
     * 备注
     * */
    public String getRemark(){
        return this.remark;
    }

}