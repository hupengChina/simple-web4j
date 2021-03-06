package com.demo.controller;

import com.demo.service.DemoService;
import org.hupeng.framework.context.annotation.Autowired;
import org.hupeng.framework.context.annotation.Controller;
import org.hupeng.framework.web.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class DemoController {

    private static final Logger log = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private DemoService demoService;

    @RequestMapping( value = "/hello")
    public Object hello(){
        return demoService.hello();
    }

    @RequestMapping( value = "/log")
    public void log(){
        log.info("info");
        log.warn("warn");
        log.error("error");
    }
}
