package com.xiang.cloud.sys.model;

import com.xiang.base.model.BaseModel;

/**
 *  @author Zhai_
 */
public class SysMenu extends BaseModel<Long>{
    private static final long serialVersionUID = -783257831552036604L;
    private Long appId;
    private Long parentId;
    private String title;
    private String name;
    private String url;
    private String platform;
    private String type;
    private Integer sort;
    private String style;

    /**
     * 所属应用ID
     * */
    public void setAppId(Long appId){
        this.appId = appId;
    }
    /**
     * 所属应用ID
     * */
    public Long getAppId(){
        return this.appId;
    }

    /**
     * 上级菜单ID
     * */
    public void setParentId(Long parentId){
        this.parentId = parentId;
    }
    /**
     * 上级菜单ID
     * */
    public Long getParentId(){
        return this.parentId;
    }

    /**
     * 标题
     * */
    public void setTitle(String title){
        this.title = title;
    }
    /**
     * 标题
     * */
    public String getTitle(){
        return this.title;
    }

    /**
     * 菜单名称
     * */
    public void setName(String name){
        this.name = name;
    }
    /**
     * 菜单名称
     * */
    public String getName(){
        return this.name;
    }

    /**
     * 菜单链接
     * */
    public void setUrl(String url){
        this.url = url;
    }
    /**
     * 菜单链接
     * */
    public String getUrl(){
        return this.url;
    }

    /**
     * 所属平台
     * */
    public void setPlatform(String platform){
        this.platform = platform;
    }
    /**
     * 所属平台
     * */
    public String getPlatform(){
        return this.platform;
    }

    /**
     * 菜单类型
     * */
    public void setType(String type){
        this.type = type;
    }
    /**
     * 菜单类型
     * */
    public String getType(){
        return this.type;
    }

    /**
     * 排序
     * */
    public void setSort(Integer sort){
        this.sort = sort;
    }
    /**
     * 排序
     * */
    public Integer getSort(){
        return this.sort;
    }

    /**
     * 菜单样式
     * */
    public void setStyle(String style){
        this.style = style;
    }
    /**
     * 菜单样式
     * */
    public String getStyle(){
        return this.style;
    }

}