package com.xiang.cloud.zuul.model.vo;

import com.xiang.base.model.BaseModel;

import java.util.List;

public class Gen extends BaseModel<Object>{
    private static final long serialVersionUID = 3708639413062952693L;

    private String packPath;

    private List<CodeGenerator> codeGeneratorList;

    /**
     * 包路径
     * */
    public String getPackPath() {
        return packPath;
    }
    /**
     * 包路径
     * */
    public void setPackPath(String packPath) {
        this.packPath = packPath;
    }
    /**
     * 表字段
     * */
    public List<CodeGenerator> getCodeGeneratorList() {
        return codeGeneratorList;
    }
    /**
     * 表字段
     * */
    public void setCodeGeneratorList(List<CodeGenerator> codeGeneratorList) {
        this.codeGeneratorList = codeGeneratorList;
    }
}
