package com.xiang.cloud.zuul.service;

import com.xiang.base.service.BaseService;
import com.xiang.cloud.zuul.model.vo.CodeGenerator;

/**
 * @author zhaijianchao
 */
public interface CodeGeneratorService extends BaseService<CodeGenerator>{

    /**
     * 创建model实体类
     * */
    String createModel(String packPath, String outPath, CodeGenerator codeGenerator);

    /**
     * 创建mybatis sqlmap 对应文件
     * */
    String createMapperXml(String packPath, String outPath, CodeGenerator codeGenerator);

    /**
     * 创建dao层
     * */
    String createDao(String packPath, String outPath, CodeGenerator codeGenerator);

    /**
     * 创建service层
     * */
    String createService(String packPath, String outPath, CodeGenerator codeGenerator);

    /**
     * 创建service实现类
     * */
    String createServiceImpl(String packPath, String outPath, CodeGenerator codeGenerator);

    /**
     * 创建Controller 层
     * */
    String createController(String packPath, String outPath, CodeGenerator codeGenerator);
    /**
     * 创建列表页面
     * */
    String createListPage(String packPath, String outPath, CodeGenerator codeGenerator);
    /**
     * 创建添加页面
     * */
    String createAddPage(String packPath, String outPath, CodeGenerator codeGenerator);
    /**
     * 创建修改页面
     * */
    String createUpdatePage(String packPath, String outPath, CodeGenerator codeGenerator);
    /**
     * 创建详情页
     * */
    String createDetailPage(String packPath, String outPath, CodeGenerator codeGenerator);
    /**
     * 创建列表页面js
     * */
    String createListPageJs(String packPath, String outPath, CodeGenerator codeGenerator);
    /**
     * 创建修改页面js
     * */
    String createUpdatePageJs(String packPath, String outPath, CodeGenerator codeGenerator);
    /**
     * 创建添加页面JS
     * */
    String createAddPageJs(String packPath, String outPath, CodeGenerator codeGenerator);
}
