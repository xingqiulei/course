package com.course.system.controller;

import com.course.server.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/system")
public class TestController {

    @Resource
    private TestService testService;

    @RequestMapping("/test")
    @ResponseBody
     public String test(){
         return  "success";
     }
}
