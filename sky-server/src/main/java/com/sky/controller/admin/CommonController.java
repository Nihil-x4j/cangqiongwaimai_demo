package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * ClassName: CommonController
 * Package: com.sky.controller.admin
 * Description:通用控制器
 *
 * @Author x4j
 * @Create 2024/4/12 16:05
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {

    @Value("${app.dir.upload}")
    private String uploadDir;

    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(@RequestBody MultipartFile file){
        if(!file.isEmpty()){
            String contentType = file.getContentType();
            if(contentType != null && contentType.startsWith("image")){
                String fileName = file.getOriginalFilename();
                Path filePath = Paths.get(uploadDir, "img", fileName);
                try(InputStream inputStream = file.getInputStream()){
                    Files.copy(inputStream, filePath, REPLACE_EXISTING);
                    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/uploads/img/")
                            .path(fileName)
                            .toUriString();
                    return Result.success(fileDownloadUri);
                }catch(IOException e){
                    e.getStackTrace();
                    log.info("{}",e.getStackTrace());
                }
            }
            return Result.error("文件不是图片格式");
        }else{
            return Result.error("文件为空");
        }
    }


}
