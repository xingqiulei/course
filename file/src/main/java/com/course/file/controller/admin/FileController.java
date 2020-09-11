package com.course.file.controller.admin;

import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin/file")
public class FileController {

public static final String BUSINESS_NAME = "文件";


    @Resource
    private FileService fileService;

    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);

    @RequestMapping("/list")
    @ResponseBody
     public ResponseDto list(@RequestBody PageDto pageDto){
        ResponseDto responseDto=new ResponseDto();
           fileService.list(pageDto);
        responseDto.setContent(pageDto);
           return responseDto;
     }

}
