package com.course.business.controller.admin;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.dto.SectionDto;
import com.course.server.service.SectionService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin/section")
public class SectionController {

public static final String BUSINESS_NAME = "";


    @Resource
    private SectionService sectionService;


    private static final Logger LOG = LoggerFactory.getLogger(SectionController.class);

    @RequestMapping("/list")
    @ResponseBody
     public ResponseDto list( PageDto pageDto){
        ResponseDto responseDto=new ResponseDto();
           sectionService.list(pageDto);
        responseDto.setContent(pageDto);
           return responseDto;
     }

    @RequestMapping("/save")
    public ResponseDto save(@RequestBody SectionDto sectionDto){
        LOG.info("sectionDto",sectionDto);
        ResponseDto responseDto=new ResponseDto();
        sectionService.save(sectionDto);
        responseDto.setContent(sectionDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        LOG.info("id",id);
        ResponseDto responseDto=new ResponseDto();
        sectionService.delete(id);
        return responseDto;
    }

}
