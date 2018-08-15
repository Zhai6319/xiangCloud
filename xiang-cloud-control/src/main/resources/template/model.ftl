package ${packageName}.model;

import com.xiang.base.model.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

/**
 *  @author ${author}
 */
public class ${className} extends BaseModel<Long>{
<#list attrs as attr>
    private ${attr.javaType} ${attr.javaColumnName};
</#list>

<#list attrs as attr>
    /**
     * ${attr.notes}
     * */
    public void set${attr.javaColumnName?cap_first}(${attr.javaType} ${attr.javaColumnName}){
        this.${attr.javaColumnName} = ${attr.javaColumnName};
    }
    /**
     * ${attr.notes}
     * */
    public ${attr.javaType} get${attr.javaColumnName?cap_first}(){
        return this.${attr.javaColumnName};
    }

</#list>
}