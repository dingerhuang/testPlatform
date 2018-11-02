package com.module.product.controller;

import com.google.zxing.WriterException;
import com.module.product.common.bean.DataTablePageModel;
import com.module.product.common.bean.ResponseJsonModel;
import com.module.product.common.util.ZXingUtil;
import com.module.product.orm.mapper.ManagePackageMapper;
import com.module.product.orm.model.ManagePackage;
import com.module.product.service.FileService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

import static com.module.product.common.util.CallShell.callShell;
import static com.module.product.common.util.CreateHtml.MakeHtml;
import static com.module.product.common.util.ZXingUtil.createQrCode;

@RestController
@RequestMapping("/package")
public class FileController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Value("${FTP_ADDRESS}")
    private String ftpIp;
    @Value("${FTP_PORT}")
    private String ftpPort;
    @Value("${FTP_USERNAME}")
    private String ftpUser;
    @Value("${FTP_PASSWORD}")
    private String ftpPassword;
    @Value("${FTP_BASEPATH}")
    private String ftpBasePath;

    @Autowired
    private FileService fileService;
    @Autowired
    private ManagePackageMapper managePackageMapper;


    @RequestMapping(value = "/upload")
    @ResponseBody
    @Transactional
    public ResponseJsonModel uploadFile(ManagePackage uploadPackage, @RequestParam MultipartFile uploadFile) throws IOException {
        ResponseJsonModel responseJsonModel = new ResponseJsonModel();
        String oldName = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString();
        newName =oldName.substring(0,oldName.lastIndexOf("."))+ "_" + newName + oldName.substring(oldName.lastIndexOf("."));
        boolean flag = fileService.upload(uploadFile,newName);
        if(flag){
            Date date = new Date();
            uploadPackage.setCreateTime(date);
            uploadPackage.setUpdateTime(date);
            String filePath = new DateTime().toString("/yyyy/MM/dd");
            filePath = filePath + "/" + newName;
            uploadPackage.setPackageDownloadurl(filePath);
            managePackageMapper.insertSelective(uploadPackage);
            //生成二维码
            String downloandUrl="http://"+ftpIp+":8081"+ftpBasePath+uploadPackage.getPackageDownloadurl();
            String fileName ="";
            if(uploadPackage.getPackageDownloadurl().contains(".apk")){
                fileName=uploadPackage.getPackageDownloadurl().substring(uploadPackage.getPackageDownloadurl().lastIndexOf("/")).replace(".apk",".jpg");
                if(createQrCode(downloandUrl,"/data/ftp/images",fileName)){
                    logger.info("生成二维码成功！");
                }else {
                    logger.error("生成二维码失败！");
                }
            }else {
                fileName=uploadPackage.getPackageDownloadurl().substring(uploadPackage.getPackageDownloadurl().lastIndexOf("/")).replace(".ipa",".jpg");
                ZXingUtil zXingUtil = new ZXingUtil();
                String pName = uploadPackage.getBusinessType();
                String version = uploadPackage.getAppRelease();
                String oldUrl=uploadPackage.getPackageDownloadurl();
                //创建plist
                zXingUtil.createPlist(pName,version,downloandUrl,"/data/ftp/plists"+
                        oldUrl.substring(oldUrl.lastIndexOf("/")).replace(".ipa",".plist"));
                //将plist上传到https服务器 github
                callShell("/data/ftp/plists/upload.sh");
                //生成下载页面,并放到nginx
                String templatePath = "/data/ftp/html/template.html";
                String giteeUrl="itms-services://?action=download-manifest&url=https://raw.githubusercontent.com/dingerhuang/plist/master"+
                        oldUrl.substring(oldUrl.lastIndexOf("/")).replace(".ipa",".plist");
                String htmlFileName="/data/ftp/html/"+oldUrl.substring(oldUrl.lastIndexOf("/")).replace(".ipa",".html");
                MakeHtml(templatePath,uploadPackage.getBusinessType(),"IOS",uploadPackage.getAppRelease(),uploadPackage.getAppEnv(),giteeUrl,htmlFileName);
                String iosDownloadUrl="http://"+ftpIp+":8081/html"+oldUrl.substring(oldUrl.lastIndexOf("/")).replace(".ipa",".html");
                if(createQrCode(iosDownloadUrl,"/data/ftp/images",fileName)){
                    logger.info("生成二维码成功！");
                }else {
                    logger.error("生成二维码失败！");
                }
            }


            return responseJsonModel;
        }
        responseJsonModel.setCode("500");
        responseJsonModel.setMsg("上传失败");

        return responseJsonModel;
    }

    @RequestMapping(value = "/getDownloadUrl")
    public String getDownloadUrl(@RequestParam("id") Integer id){
        ManagePackage managePackage = managePackageMapper.selectByPrimaryKey(id);
        String downloadUrl = "ftp://"+ftpUser+":"+ftpPassword+"@"+ftpIp+":"+ftpPort+ftpBasePath+managePackage.getPackageDownloadurl();

        return downloadUrl;
    }

    @RequestMapping("/getImageUrl")
    public String getCodeUrl(Integer id) {

        ManagePackage managePackage = managePackageMapper.selectByPrimaryKey(id);
        String imageUrl = managePackage.getPackageDownloadurl();

        imageUrl="http://"+ftpIp+":8081/images"+imageUrl.substring(imageUrl.lastIndexOf("/")).replace(".apk",".jpg").replace(".ipa",".jpg");

        return imageUrl;
    }

    @RequestMapping("/del")
    @ResponseBody
    public ResponseJsonModel delPackage(Integer id) {

        ManagePackage managePackage = managePackageMapper.selectByPrimaryKey(id);
        String oldPath = managePackage.getPackageDownloadurl();
        String filePath = oldPath.substring(0,oldPath.lastIndexOf("/"));
        String fileName = oldPath.split("/")[oldPath.split("/").length-1];
        if(fileService.delete(filePath,fileName)){
            managePackageMapper.deleteByPrimaryKey(id);
        }

        return new ResponseJsonModel();
    }

}
