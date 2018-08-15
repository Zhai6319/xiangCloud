package com.xiang.base.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhaijianchao
 */
@Data
public class AuthMenu implements Serializable {

    private static final long serialVersionUID = 6489308103969672346L;

    private Long userId;
    private Long appId;
    private Long parentId;
    private String title;
    private String name;
    private String url;
    private String platform;
    private String type;
    private Integer sort;
    private String style;
}
