package com.xiang.cloud.sys.dto;

import com.xiang.cloud.sys.model.SysMenu;

/**
 * @author zhaijianchao
 */
public class SysMenuDTO extends SysMenu {
    private static final long serialVersionUID = 8715174952471406915L;

    /**
     * 用户ID
     * */
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
