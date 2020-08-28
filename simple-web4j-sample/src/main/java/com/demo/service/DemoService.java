package com.demo.service;

import org.hupeng.framework.context.annotation.Service;

import java.util.HashMap;

/**
 * @author : hupeng
 * @date : 2020/8/19
 */
@Service
public class DemoService {

    public Object hello(){
        HashMap<String,String> result = new HashMap<String, String>();
        result.put("status","success");
        result.put("msg","你好 !");
        return result;
    }
}
