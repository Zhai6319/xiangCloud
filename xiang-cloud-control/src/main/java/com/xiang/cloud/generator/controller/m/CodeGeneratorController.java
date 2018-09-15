package com.xiang.cloud.generator.controller.m;

import com.xiang.base.controller.BaseController;
import com.xiang.base.model.Page;
import com.xiang.base.model.PagedResult;
import com.xiang.base.model.Params;
import com.xiang.base.response.R;
import com.xiang.cloud.annotation.login.MLogin;
import com.xiang.cloud.generator.model.vo.CodeGenerator;
import com.xiang.cloud.generator.service.CodeGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhaijianchao
 */
@Controller
@RequestMapping("m/codeGenerator")
public class CodeGeneratorController extends BaseController{

    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @MLogin
    @ResponseBody
    @RequestMapping(value = "list.do")
    public R column(CodeGenerator codeGenerator){
        Page page = this.getPage();
        Params params = Params.create();
        params.add(codeGenerator);
        page.setOrderBys("id","asc");
        PagedResult<CodeGenerator> pagedResult = codeGeneratorService.page(params,page);
        return R.success(pagedResult);
    }
    @ResponseBody
    @RequestMapping(value = "createCode.do")
    public R createCode(String packageName,String outFile,CodeGenerator codeGenerator){
        String outPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println(outPath);
        outPath+=outFile;
        codeGeneratorService.createMapperXml(packageName,outPath,codeGenerator);
        codeGeneratorService.createModel(packageName,outPath,codeGenerator);
        codeGeneratorService.createDao(packageName,outPath,codeGenerator);
        codeGeneratorService.createService(packageName,outPath,codeGenerator);
        codeGeneratorService.createServiceImpl(packageName,outPath,codeGenerator);
        codeGeneratorService.createController(packageName,outPath,codeGenerator);
        return null;
    }
}
