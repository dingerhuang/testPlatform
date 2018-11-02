package com.module.product.orm.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_mock_url_result")
public class MockUrlResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "mock_url")
    private String mockUrl;

    @Column(name = "mock_para")
    private String mockPara;

    @Column(name = "mock_response")
    private String mockResponse;

    @Column(name = "timeOut")
    private String timeOut;

    @Column(name = "project")
    private String project;

    @Column(name = "remark")
    private String remark;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "last_modify_user")
    private String lastModifyUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMockUrl() {
        return mockUrl;
    }

    public void setMockUrl(String mockUrl) {
        this.mockUrl = mockUrl;
    }

    public String getMockPara() {
        return mockPara;
    }

    public void setMockPara(String mockPara) {
        this.mockPara = mockPara;
    }

    public String getMockResponse() {
        return mockResponse;
    }

    public void setMockResponse(String mockResponse) {
        this.mockResponse = mockResponse;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
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

    public String getLastModifyUser() {
        return lastModifyUser;
    }

    public void setLastModifyUser(String lastModifyUser) {
        this.lastModifyUser = lastModifyUser;
    }
}
