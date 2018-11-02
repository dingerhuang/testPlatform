package com.module.product.service;

import com.module.product.common.util.FtpUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileService {
    //获取ip地址
    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    //端口号
    @Value("${FTP_PORT}")
    private String FTP_PORT;
    //用户名
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    //密码
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    //基本路径
    @Value("${FTP_BASEPATH}")
    private String FTP_BASEPATH;

    public boolean upload(MultipartFile fileFile,String newName){
        boolean flag = false;
        try {
            String filePath = new DateTime().toString("/yyyy/MM/dd");
            int port = Integer.parseInt(FTP_PORT);

            //调用方法，上传文件
            boolean result = FtpUtils.uploadFile(FTP_ADDRESS, port,
                    FTP_USERNAME, FTP_PASSWORD, FTP_BASEPATH, filePath,
                    newName, fileFile.getInputStream());
            if(result){
                flag = true;
            }
        }catch (IOException e){
            e.printStackTrace();
        }



        return flag;
    }
    public boolean delete(String filePath,String fileName){
        boolean flag = false;
        try {
            int port = Integer.parseInt(FTP_PORT);
            //删除
            boolean result = FtpUtils.deleFile(FTP_ADDRESS, port, FTP_USERNAME, FTP_PASSWORD, FTP_BASEPATH, filePath, fileName);
            if (result) {
                flag = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

}
