package com.xiang.cloud.sys.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @author zhaijianchao
 */
public class SysMenuVo {
    /**
     * 标题
     * */
    @JSONField(ordinal = 1)
    private String title;
    @JSONField(ordinal = 2)
    /**
     * 样式
     * */
    private String icon;
    @JSONField(ordinal = 3)
    /**
     * 名称
     * */
    private String name;
    @JSONField(ordinal = 4)
    /**
     * 链接
     * */
    private String jump;
    private List<SysMenuVo> list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJump() {
        return jump;
    }

    public void setJump(String jump) {
        this.jump = jump;
    }

    public List<SysMenuVo> getList() {
        return list;
    }

    public void setList(List<SysMenuVo> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "SysMenuVo{" +
                "title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                ", jump='" + jump + '\'' +
                ", list=" + list +
                '}';
    }
}
