package com.course.business.controller;

import com.course.server.domain.Test;
import com.course.server.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
/*@RequestMapping("/system")*/
public class TestController {

    @Resource
    private TestService testService;

    @RequestMapping("/test")
    @ResponseBody
     public List<Test> test(){
         return  testService.list();
     }
}
