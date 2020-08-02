package com.demo;

import org.hupeng.framework.ioc.Annotated.Action;
import org.hupeng.framework.ioc.Annotated.Controller;

@Controller
public class TestController {

    @Action( value = "/test")
    public String test(){
        return "success";
    }
}
