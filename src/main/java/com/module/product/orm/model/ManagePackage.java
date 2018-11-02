package com.module.product.orm.model;

import com.module.product.common.enums.BusinessType;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_manage_package")
public class ManagePackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "business_type")
    private String businessType;
//    private BusinessType businessType;
    @Column(name = "app_type")
    private String appType;

    @Column(name = "app_release")
    private String appRelease;

    @Column(name = "app_env")
    private String appEnv;

    @Column(name = "remark")
    private String remark;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "package_downloadurl")
    private String packageDownloadurl;

    public String getPackageDownloadurl() {
        return packageDownloadurl;
    }

    public void setPackageDownloadurl(String packageDownloadurl) {
        this.packageDownloadurl = packageDownloadurl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getAppRelease() {
        return appRelease;
    }

    public void setAppRelease(String appRelease) {
        this.appRelease = appRelease;
    }

    public String getAppEnv() {
        return appEnv;
    }

    public void setAppEnv(String appEnv) {
        this.appEnv = appEnv;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
