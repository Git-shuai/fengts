package tian.web.controller.upload;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tian.web.Result;
import tian.web.StringUtils;
import tian.web.enums.ResCode;
import tian.web.service.upload.BaseUploadImage;
import tian.web.service.upload.OSSImageService;

/**
 * @author tian
 * @date 2020/9/14
 */
@RestController
@RequestMapping("/upload")
public class ImageController {

    @Autowired
    private OSSImageService ossImageService;

    @Autowired
    private BaseUploadImage baseUploadImage;

    /**
     * Oss 简单上传
     * @param file
     * @return
     */
    @PostMapping("/uploadOss")
    public Result<Object> uploadImage(MultipartFile file){
        if (file.isEmpty()){
            return null;
        }
        String url = ossImageService.uploadImage(file);

        return new Result<Object>(ResCode.SUCCESS_CODE,url);
    }

    @PostMapping("/baseUploadImage")
    public Result<Object> baseUploadImage(MultipartFile file){
        if (StringUtils.isEmpty(file)){
            return null;
        }
//        throw new RuntimeException("服务端测试异常！");
        String url = baseUploadImage.uploadImage(file);
        return new Result<Object>(ResCode.SUCCESS_CODE,url);
    }

}
