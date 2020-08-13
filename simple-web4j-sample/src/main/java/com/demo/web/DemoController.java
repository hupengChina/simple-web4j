package com.demo.web;

import org.hupeng.framework.web.annotated.RequestMapping;
import org.hupeng.framework.web.annotated.Controller;

import java.util.HashMap;

@Controller
public class DemoController {

    @RequestMapping( value = "/hello")
    public Object hello(){
        HashMap<String,String> result = new HashMap<String, String>();
        result.put("status","success");
        result.put("msg","你好 !");
        return result;
    }
}
