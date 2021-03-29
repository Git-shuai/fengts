package tian.web.service.upload.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tian.web.service.upload.BaseUploadImage;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author tian
 * @date 2020/9/14
 */
@Service("baseUploadImage")
public class BaseUploadImageImpl implements BaseUploadImage {

    @Value("${spring.application.name}")
    private String application;

    @Override
    public String uploadImage(MultipartFile file) {
        //使用UUID给图片重命名，并去掉四个“-”
        String name = UUID.randomUUID().toString().replaceAll("-", "")+file.getOriginalFilename();
        //设置图片上传路径
        //获取项目路径
        String path = System.getProperty("user.dir");
        //上传文件保存路径
        String url = "upload\\";
        File file1 = new File(path+"\\"+url);
        if (!file1.exists()){
            file1.mkdir();
        }
        String basePath=path+"\\"+url+name;
        //以绝对路径保存重名命后的图片
        try {
            file.transferTo(new File(basePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return basePath;
    }
}
