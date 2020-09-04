package com.course.${module}.controller.admin;
import com.course.server.dto.ResponseDto;
import com.course.server.service.${Domain}Service;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin/${domain}")
public class ${Domain}Controller {

public static final String BUSINESS_NAME = "${tableNameCn}";


    @Resource
    private ${Domain}Service ${domain}Service;


    private static final Logger LOG = LoggerFactory.getLogger(${Domain}Controller.class);

    @RequestMapping("/list")
    @ResponseBody
     public ResponseDto list(@RequestBody PageDto pageDto){
        ResponseDto responseDto=new ResponseDto();
           ${domain}Service.list(pageDto);
        responseDto.setContent(pageDto);
           return responseDto;
     }

    @RequestMapping("/save")
    public ResponseDto save(@RequestBody ${Domain}Dto  ${domain}Dto){
      // 保存校验
        <#list fieldList as field>
            <#if field.name!="id" && field.nameHump!="createdAt" && field.nameHump!="updatedAt" && field.nameHump!="sort">
                <#if !field.nullAble>
                    ValidatorUtil.require(${domain}Dto.get${field.nameBigHump}(), "${field.nameCn}");
                </#if>
                <#if (field.length > 0)>
                    ValidatorUtil.length(${domain}Dto.get${field.nameBigHump}(), "${field.nameCn}", 1, ${field.length?c});
                </#if>
            </#if>
        </#list>
        LOG.info("${domain}Dto",${domain}Dto);
        ResponseDto responseDto=new ResponseDto();
        ${domain}Service.save(${domain}Dto);
        responseDto.setContent(${domain}Dto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){
        LOG.info("id",id);
        ResponseDto responseDto=new ResponseDto();
        ${domain}Service.delete(id);
        return responseDto;
    }

}
