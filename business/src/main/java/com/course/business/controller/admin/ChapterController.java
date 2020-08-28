package com.course.business.controller.admin;

import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.ChapterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin/chapter")
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    private static final Logger LOG = LoggerFactory.getLogger(ChapterController.class);

    @RequestMapping("/list")
    @ResponseBody
     public ResponseDto list( PageDto pageDto){
        ResponseDto responseDto=new ResponseDto();
           chapterService.list(pageDto);
        responseDto.setContent(pageDto);
           return responseDto;
     }

    @RequestMapping("/save")
    public ResponseDto save(@RequestBody ChapterDto  chapterDto){
        LOG.info("chapterDto",chapterDto);
        ResponseDto responseDto=new ResponseDto();
        chapterService.save(chapterDto);
        responseDto.setContent(chapterDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        LOG.info("id",id);
        ResponseDto responseDto=new ResponseDto();
        chapterService.delete(id);
        return responseDto;
    }

}
