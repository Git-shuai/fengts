package tian.web.service.upload;

import org.springframework.web.multipart.MultipartFile;

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
}
