package com.module.product.common.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CreateHtml {
    /**
     * @Title: MakeHtml
     * @Description: 创建html
     * @param    templatePath 设定模板文件
     * @param    BusinessType 产品线
     * @param    AppType  app类型
     * @param    version 版本号
     * @param    appEnv app环境
     * @param    downloadUrl 下载地址
     * @param    fileName  生成html路径+名字
     * @return void    返回类型
     * @throws
     */
    public static void MakeHtml(String templatePath,String BusinessType,String AppType,String version,String appEnv,String downloadUrl, String fileName ){
        try {
            String templateContent = "";
            FileInputStream fileinputstream = new FileInputStream(templatePath);// 读取模板文件
            int lenght = fileinputstream.available();
            byte bytes[] = new byte[lenght];
            fileinputstream.read(bytes);
            fileinputstream.close();
            templateContent = new String(bytes);
            templateContent = templateContent.replaceAll("BusinessType", BusinessType);
            templateContent = templateContent.replaceAll("AppType", AppType);
            templateContent = templateContent.replaceAll("version", version);
            templateContent = templateContent.replaceAll("appEnv", appEnv);
            templateContent = templateContent.replaceAll("downloadUrl", downloadUrl);

            FileOutputStream fileoutputstream = new FileOutputStream(fileName);// 建立文件输出流

            byte tag_bytes[] = templateContent.getBytes();
            fileoutputstream.write(tag_bytes);
            fileoutputstream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
