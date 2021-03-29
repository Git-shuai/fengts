package tian.web.service.upload;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author tian
 * @date 2020/9/14
 */
public interface BaseUploadImage {

    String uploadImage(MultipartFile file);
}
