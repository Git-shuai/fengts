package tian.web.service.upload.impl;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tian.web.service.upload.OSSImageService;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

/**
 * @author tian
 * @date 2020/9/14
 */
@Service("imageService")
public class ImageServiceImpl implements OSSImageService {

    @Value("${alibaba.oss.endpoint}")
    private String endpoint;

    @Value("${alibaba.accessKeyId}")
    private String accessKeyId;

    @Value("${alibaba.secret}")
    private String accessKeySecret;

    @Value("${alibaba.oss.bucketName}")
    private String bucketName;

    @Value("${alibaba.oss.filename}")
    private String application;

    /**
     * 简单上传图片
     * @return String
     */
    @Override
    public String uploadImage(MultipartFile file) {
        String filename = file.getOriginalFilename();
        //获取OSS 客户端对象
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        filename = UUID.randomUUID().toString() + "." + filename;

        try {
            ossClient.putObject(bucketName, application+"/" + filename, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = getUrl(application+"/"+filename);
        // 关闭OSSClient。
        ossClient.shutdown();
        return url;
    }

    /**
     * 获得url链接（OSS 用到）
     *
     * @param key
     * @return
     */
    public String getUrl(String key) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 设置URL过期时间为10年  3600l* 1000*24*365*10
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
        ossClient.shutdown();
        if (url != null) {
            return url.toString();
        }
        return null;
    }

}
