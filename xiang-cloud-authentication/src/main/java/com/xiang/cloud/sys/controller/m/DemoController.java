package com.xiang.cloud.sys.controller.m;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {

    @RequestMapping("demo")
    public String demo(){

        System.out.println("哈哈哈");
        return "/views/demo";
    }
}
