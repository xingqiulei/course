package com.course.file.controller.admin;

import com.course.server.dto.FileDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.FileService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
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

    @RequestMapping("/save")
    public ResponseDto save(@RequestBody FileDto fileDto){
      // 保存校验
                    ValidatorUtil.require(fileDto.getPath(), "相对路劲");
                    ValidatorUtil.length(fileDto.getPath(), "相对路劲", 1, 100);
                    ValidatorUtil.length(fileDto.getName(), "文件名", 1, 100);
                    ValidatorUtil.length(fileDto.getSuffix(), "后缀", 1, 10);
        LOG.info("fileDto",fileDto);
        ResponseDto responseDto=new ResponseDto();
        fileService.save(fileDto);
        responseDto.setContent(fileDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        LOG.info("id",id);
        ResponseDto responseDto=new ResponseDto();
        fileService.delete(id);
        return responseDto;
    }

}
