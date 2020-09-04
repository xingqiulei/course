package com.course.business.controller.admin;

import com.course.server.dto.CourseDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.CourseService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin/course")
public class CourseController {

public static final String BUSINESS_NAME = "课程表";


    @Resource
    private CourseService courseService;


    private static final Logger LOG = LoggerFactory.getLogger(CourseController.class);

    @RequestMapping("/list")
    @ResponseBody
     public ResponseDto list(@RequestBody PageDto pageDto){
        ResponseDto responseDto=new ResponseDto();
           courseService.list(pageDto);
        responseDto.setContent(pageDto);
           return responseDto;
     }

    @RequestMapping("/save")
    public ResponseDto save(@RequestBody CourseDto courseDto){
       // 保存校验
        ValidatorUtil.length(courseDto.getName(), "名称", 1, 255);
        ValidatorUtil.length(courseDto.getSummary(), "概述", 1, 2000);
        ValidatorUtil.length(courseDto.getImage(), "封面", 1, 255);
        LOG.info("courseDto",courseDto);
        ResponseDto responseDto=new ResponseDto();
        courseService.save(courseDto);
        responseDto.setContent(courseDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        LOG.info("id",id);
        ResponseDto responseDto=new ResponseDto();
        courseService.delete(id);
        return responseDto;
    }

}
