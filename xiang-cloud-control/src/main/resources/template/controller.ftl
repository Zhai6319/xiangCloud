package ${packageName}.controller.m;

import com.xiang.base.controller.BaseController;
import com.xiang.base.model.Page;
import com.xiang.base.model.PagedResult;
import com.xiang.base.model.Params;
import com.xiang.base.response.R;
import ${packageName}.model.${className};
import ${packageName}.service.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统后台用户Controller
 * */
@RequestMapping("m/${classAlias}")
public class ${className}Controller extends BaseController{
    @Autowired
    private ${className}Service ${classAlias}Service;

    @ResponseBody
    @RequestMapping("page.do")
    public R page(${className} ${classAlias}){
        Params params = Params.create();
        params.add(${classAlias});
        Page page = this.getPage();
        PagedResult<${className}> pages = ${classAlias}Service.page(params,page);
        return R.success().put("pageData",pages);
    }

    @ResponseBody
    @RequestMapping("detail.do")
        public R detail(Long id){
        ${className} ${classAlias} = ${classAlias}Service.queryByPK(id);
        return R.success().put("${classAlias}",${classAlias});
    }

    @ResponseBody
    @RequestMapping("save.do")
    public R save(${className} ${classAlias}){
        if(${classAlias}Service.add(${classAlias})>0){
            return R.success("添加保存信息成功");
        }
        return R.error("添加保存信息失败");
    }

    @ResponseBody
    @RequestMapping("edit.do")
    public R edit(Long id){
        ${className} ${classAlias} = ${classAlias}Service.queryByPK(id);
        return R.success().put("${classAlias}",${classAlias});
    }

    @ResponseBody
    @RequestMapping("update.do")
    public R update(${className} ${classAlias}){
        if(${classAlias}Service.modifyByPk(${classAlias}) > 0){
            return R.success("修改更新数据成功");
        }
        return R.error("修改更新数据失败");
    }

    @ResponseBody
    @RequestMapping("del.do")
    public R del(Long id){
        if(${classAlias}Service.logicDelByPk(id) > 0){
            return R.success("删除数据成功");
        }
        return R.error("删除数据失败");
    }

}
