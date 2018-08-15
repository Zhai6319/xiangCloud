package com.xiang.cloud.zuul.service.impl;

import com.alibaba.fastjson.JSON;
import com.xiang.cloud.common.util.PropertiesUtil;
import com.xiang.cloud.common.util.database.DatabaseUtil;
import com.xiang.base.model.Params;
import com.xiang.base.service.impl.BaseServiceImpl;
import com.xiang.cloud.zuul.model.vo.CodeGenerator;
import com.xiang.cloud.zuul.service.CodeGeneratorService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaijianchao
 */
@Service
public class CodeGeneratorServiceImpl extends BaseServiceImpl<CodeGenerator> implements CodeGeneratorService {
    @Override
    public String createModel(String packPath, String outPath,CodeGenerator codeGenerator) {
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource("template/").getPath();
            File templateDir = new File(path);
            System.out.println(templateDir.getCanonicalPath());
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
            cfg.setDirectoryForTemplateLoading(templateDir);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            Template template = cfg.getTemplate("model.ftl");
            System.out.println(JSON.toJSONString(template));

            Map<String, Object> root = new HashMap<String, Object>();

            root.put("packageName", packPath+".model");
            root.put("className", codeGenerator.getClassName());
            root.put("author",codeGenerator.getAuthor());
            Params params = Params.create();
            params.add(codeGenerator);
            List<CodeGenerator> attr_list = this.queryByParams(params);
            List<CodeGenerator> models = new ArrayList<>();
            attr_list.forEach(attr->{
                CodeGenerator model = attr;
                String javaColumn = DatabaseUtil.fieldToProperty(attr.getColumnName().toLowerCase());
                if(!javaColumn.equals("id") && !javaColumn.equals("createTime") && !javaColumn.equals("modifyTime") && !javaColumn.equals("deleted") && !javaColumn.equals("createById") && !javaColumn.equals("modifyById")){
                    model.setJavaColumnName(javaColumn);
                    String javaType = PropertiesUtil.getConfig("template/properties/jdbcType.properties",model.getJdbcType());
                    if(javaType.equals("Integer")){
                        if(model.getNumericScale()!=null && model.getNumericScale() > 0){
                            model.setJavaType("Double");
                        }else if(javaColumn.endsWith("Id")){
                            model.setJavaType("Long");
                        }else{
                            model.setJavaType(javaType);
                        }
                    }else {
                        model.setJavaType(javaType);
                    }
                    models.add(model);
                }
            });
            root.put("attrs", models);
            File dir = new File(outPath);
            if(!dir.exists()){
                dir.mkdirs();
            }
            OutputStream fos = new FileOutputStream( new File(dir, codeGenerator.getClassName()+".java")); //java文件的生成目录
            Writer out = new OutputStreamWriter(fos);
            template.process(root, out);


            fos.flush();
            fos.close();
            out.flush();
            out.close();

            System.out.println("gen code success!");
            return outPath;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String createMapperXml(String packPath, String outPath, CodeGenerator codeGenerator) {
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource("template/").getPath();
            File templateDir = new File(path);
            System.out.println(templateDir.getCanonicalPath());
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
            cfg.setDirectoryForTemplateLoading(templateDir);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            Template template = cfg.getTemplate("mapper.ftl");
            System.out.println(JSON.toJSONString(template));

            Map<String, Object> root = new HashMap<String, Object>();

            root.put("packageName", packPath);
            root.put("className", codeGenerator.getClassName());
            root.put("author",codeGenerator.getAuthor());
            root.put("tableName",codeGenerator.getTableName());
            Params params = Params.create();
            params.add(codeGenerator);
            List<CodeGenerator> attr_list = this.queryByParams(params);
            List<CodeGenerator> models = new ArrayList<>();
            attr_list.forEach(attr->{
                CodeGenerator model = attr;
                String javaType = PropertiesUtil.getConfig("template/properties/jdbcType.properties",model.getJdbcType());
                String jdbcType = PropertiesUtil.getConfig("template/properties/mybatisType.properties",model.getJdbcType());
                String javaColumn = DatabaseUtil.fieldToProperty(attr.getColumnName().toLowerCase());
                model.setJavaColumnName(javaColumn);
                if(javaType.equals("Integer")){
                    if(model.getNumericScale()!=null && model.getNumericScale() > 0){
                        model.setJdbcType("DOUBLE");
                        model.setMapperColumnName("#{where."+javaColumn+",jdbcType=DOUBLE}");
                        model.setNoWhereColumnName("#{"+javaColumn+",jdbcType=DOUBLE}");
                    }else{
                        model.setJdbcType("INTEGER");
                        model.setMapperColumnName("#{where."+javaColumn+",jdbcType=INTEGER}");
                        model.setNoWhereColumnName("#{"+javaColumn+",jdbcType=DOUBLE}");
                    }
                }else{
                    model.setJdbcType(jdbcType);
                    model.setMapperColumnName("#{where."+javaColumn+",jdbcType="+jdbcType+"}");
                    model.setNoWhereColumnName("#{"+javaColumn+",jdbcType="+jdbcType+"}");
                }
                models.add(model);
            });
            root.put("attrs", models);
            File dir = new File(outPath);
            if(!dir.exists()){
                dir.mkdirs();
            }
            OutputStream fos = new FileOutputStream( new File(dir, codeGenerator.getClassName()+"Mapper.xml")); //java文件的生成目录
            Writer out = new OutputStreamWriter(fos);
            template.process(root, out);


            fos.flush();
            fos.close();
            out.flush();
            out.close();

            System.out.println(dir.delete());
            System.out.println("gen code success!");
            return outPath;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String createDao(String packPath, String outPath, CodeGenerator codeGenerator) {
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource("template/").getPath();
            File templateDir = new File(path);
            System.out.println(templateDir.getCanonicalPath());
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
            cfg.setDirectoryForTemplateLoading(templateDir);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            Template template = cfg.getTemplate("dao.ftl");
            System.out.println(JSON.toJSONString(template));

            Map<String, Object> root = new HashMap<String, Object>();

            root.put("packageName", packPath);
            root.put("className", codeGenerator.getClassName());
            root.put("author",codeGenerator.getAuthor());
            root.put("tableName",codeGenerator.getTableName());
            File dir = new File(outPath);
            if(!dir.exists()){
                dir.mkdirs();
            }
            OutputStream fos = new FileOutputStream( new File(dir, codeGenerator.getClassName()+"Dao.java")); //java文件的生成目录
            Writer out = new OutputStreamWriter(fos);
            template.process(root, out);


            fos.flush();
            fos.close();
            out.flush();
            out.close();

            System.out.println(dir.delete());
            System.out.println("gen code success!");
            return outPath;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String createService(String packPath, String outPath, CodeGenerator codeGenerator) {
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource("template/").getPath();
            File templateDir = new File(path);
            System.out.println(templateDir.getCanonicalPath());
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
            cfg.setDirectoryForTemplateLoading(templateDir);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            Template template = cfg.getTemplate("service.ftl");
            System.out.println(JSON.toJSONString(template));

            Map<String, Object> root = new HashMap<String, Object>();

            root.put("packageName", packPath);
            root.put("className", codeGenerator.getClassName());
            root.put("author",codeGenerator.getAuthor());
            root.put("tableName",codeGenerator.getTableName());
            File dir = new File(outPath);
            if(!dir.exists()){
                dir.mkdirs();
            }
            OutputStream fos = new FileOutputStream( new File(dir, codeGenerator.getClassName()+"Service.java")); //java文件的生成目录
            Writer out = new OutputStreamWriter(fos);
            template.process(root, out);



            fos.flush();
            fos.close();
            out.flush();
            out.close();

            System.out.println(dir.delete());
            System.out.println("gen code success!");
            return outPath;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String createServiceImpl(String packPath, String outPath, CodeGenerator codeGenerator) {
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource("template/").getPath();
            File templateDir = new File(path);
            System.out.println(templateDir.getCanonicalPath());
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
            cfg.setDirectoryForTemplateLoading(templateDir);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            Template template = cfg.getTemplate("serviceImpl.ftl");
            System.out.println(JSON.toJSONString(template));

            Map<String, Object> root = new HashMap<String, Object>();

            root.put("packageName", packPath);
            root.put("className", codeGenerator.getClassName());
            root.put("author",codeGenerator.getAuthor());
            root.put("tableName",codeGenerator.getTableName());
            File dir = new File(outPath);
            if(!dir.exists()){
                dir.mkdirs();
            }
            OutputStream fos = new FileOutputStream( new File(dir, codeGenerator.getClassName()+"ServiceImpl.java")); //java文件的生成目录
            Writer out = new OutputStreamWriter(fos);
            template.process(root, out);


            fos.flush();
            fos.close();
            out.flush();
            out.close();

            System.out.println(dir.delete());
            System.out.println("gen code success!");
            return outPath;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String createController(String packPath, String outPath, CodeGenerator codeGenerator) {
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource("template/").getPath();
            File templateDir = new File(path);
            System.out.println(templateDir.getCanonicalPath());
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
            cfg.setDirectoryForTemplateLoading(templateDir);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            Template template = cfg.getTemplate("controller.ftl");
            System.out.println(JSON.toJSONString(template));

            Map<String, Object> root = new HashMap<String, Object>();

            root.put("packageName", packPath);
            root.put("className", codeGenerator.getClassName());
            root.put("author",codeGenerator.getAuthor());
            root.put("tableName",codeGenerator.getTableName());
            root.put("classAlias",DatabaseUtil.fieldToProperty(codeGenerator.getTableName()));
            File dir = new File(outPath);
            if(!dir.exists()){
                dir.mkdirs();
            }
            OutputStream fos = new FileOutputStream( new File(dir, codeGenerator.getClassName()+"Controller.java")); //java文件的生成目录
            Writer out = new OutputStreamWriter(fos);
            template.process(root, out);


            fos.flush();
            fos.close();
            out.flush();
            out.close();

            System.out.println(dir.delete());
            System.out.println("gen code success!");
            return outPath;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String createListPage(String packPath, String outPath, CodeGenerator codeGenerator) {
        return null;
    }

    @Override
    public String createAddPage(String packPath, String outPath, CodeGenerator codeGenerator) {
        return null;
    }

    @Override
    public String createUpdatePage(String packPath, String outPath, CodeGenerator codeGenerator) {
        return null;
    }

    @Override
    public String createDetailPage(String packPath, String outPath, CodeGenerator codeGenerator) {
        return null;
    }

    @Override
    public String createListPageJs(String packPath, String outPath, CodeGenerator codeGenerator) {
        return null;
    }

    @Override
    public String createUpdatePageJs(String packPath, String outPath, CodeGenerator codeGenerator) {
        return null;
    }

    @Override
    public String createAddPageJs(String packPath, String outPath, CodeGenerator codeGenerator) {
        return null;
    }

    public static void main(String[] args) {

    }
}
