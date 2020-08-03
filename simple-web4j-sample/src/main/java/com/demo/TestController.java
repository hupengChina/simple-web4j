package com.demo;

import org.hupeng.framework.ioc.Annotated.RequestMapping;
import org.hupeng.framework.ioc.Annotated.Controller;

@Controller
public class TestController {

    @RequestMapping( value = "/test")
    public String test(){
        return "success";
    }
}
