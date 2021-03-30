package tian.web.service.upload;

import org.springframework.web.multipart.MultipartFile;
import tian.web.Result;

/**
 * @author tian
 * @date 2020/9/14
 */
public interface OSSImageService {


    /**
     * 简单上传图片
     * @return
     */
    String uploadImage(MultipartFile file);

    /**
     * 简单上传图片
     * @return
     */
    Result<Object> uploadImage(MultipartFile file, String id);
}
