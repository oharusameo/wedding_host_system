package com.quxue.wedding_host.util;

import io.minio.*;
import io.minio.errors.*;
import lombok.Cleanup;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
public class MinIOUtils {


    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Resource
    private MinioClient minioClient;


    public String uploadFile(MultipartFile multipartFile) {

        //根据旧文件名称生成一个新的文件名称
        String fileName = generateName(multipartFile.getOriginalFilename());

        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
            //创建PutObject对象（封装对象信息）
            PutObjectArgs putObjectArgs = PutObjectArgs.builder().object(fileName)//设置对象的名称
                    .contentType(multipartFile.getContentType())//设置文件类型
                    .stream(inputStream, multipartFile.getSize(), -1)//设置上传的文件
                    .bucket(bucketName)//设置桶
                    .build();

            //提交对象到minio服务器
            minioClient.putObject(putObjectArgs);

            //拼接一个minio服务器的地址返回给前端
            //http://192.168.126.11:9000/miniotest/37362e29131541ddb35082da168f0680.jpg
            StringBuilder filePath = new StringBuilder();
            filePath.append(this.endpoint).append("/").append(bucketName).append("/").append(fileName);
            return filePath.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 下载文件方法
     *
     * @param fileName
     * @return
     */
    public FilterInputStream getInputStream(String fileName) {
        GetObjectResponse objectResponse;
        try {
            objectResponse = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileName).build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return objectResponse;

    }

    /**
     * 获取文件的MIME类型  如  jpg : image/jpeg
     *
     * @param fileName
     * @return
     */
    public String getFileMimeType(String fileName) {
        try {
            StatObjectResponse statObjectResponse = minioClient.statObject(StatObjectArgs.builder().bucket(bucketName).object(fileName).build());
            return statObjectResponse.contentType();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private String generateName(String oldName) {
        //获取文件后缀
        String extension = FilenameUtils.getExtension(oldName);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //生成新的文件名称
        return uuid + "." + extension;
    }
}
