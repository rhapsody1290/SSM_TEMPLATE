package com.qianmingxs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Asus on 2016/10/7.
 */

@Controller
@RequestMapping(value = "/")
public class TemplateController {

    //页面跳转合并
    @RequestMapping(value = "/page/{pageName}")
    public String toPage(@PathVariable("pageName") String pageName){
        return pageName;
    }


    @ResponseBody
    @RequestMapping(value = "/main")
    public Map<String,Object> main(@RequestParam(value = "token",defaultValue = "") String token) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status","200");
        return result;
    }

}
