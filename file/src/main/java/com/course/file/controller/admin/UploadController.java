package com.course.file.controller.admin;

import com.course.server.dto.FileDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.FileService;
import com.course.server.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/admin/file")
public class UploadController {

    private static final Logger LOG = LoggerFactory.getLogger(UploadController.class);
    public static final String BUSINESS_NAME = "文件";

    @Resource
    private FileService fileService;

    @Value("${file.domain}")
    private String FILE_DOMAIN;
    @Value("${file.path}")
    private String FILE_PATH;


@RequestMapping("/upload")
 public ResponseDto upload(@RequestParam MultipartFile file ) throws IOException {
     String fileName =file.getOriginalFilename();
     String key= UuidUtil.getShortUuid();
     String suffix=fileName.substring(fileName.lastIndexOf(".") +1).toLowerCase();
     String path="teacher/" +key + "." +suffix;
     String fullPath =FILE_PATH + path;
     File dest =new File(fullPath);
     file.transferTo(dest);
     LOG.info("文件保存记录开始");
    FileDto fileDto=new FileDto();
    fileDto.setPath(path);
    fileDto.setName(fileName);
    fileDto.setSize(Math.toIntExact(file.getSize()));
    fileDto.setSuffix(suffix);
    fileDto.setUse("");
    fileService.save(fileDto);
     LOG.info(dest.getAbsolutePath());
     ResponseDto responseDto=new ResponseDto();
     responseDto.setContent(FILE_DOMAIN + path);
     return responseDto;
 }

}
