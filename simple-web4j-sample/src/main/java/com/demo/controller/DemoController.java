package com.demo.controller;

import com.demo.service.DemoService;
import org.hupeng.framework.context.Annotated.Autowired;
import org.hupeng.framework.context.Annotated.Controller;
import org.hupeng.framework.web.annotated.RequestMapping;

@Controller
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping( value = "/hello")
    public Object hello(){
        return demoService.hello();
    }
}
