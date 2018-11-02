package com.module.product.common.search;

import com.module.product.common.bean.DataTablePageModel;



public class MockUrlResultVo extends DataTablePageModel {
    private String mockUrl;
    private String project;
    private String createTime;

    public String getMockUrl() {
        return mockUrl;
    }

    public void setMockUrl(String mockUrl) {
        this.mockUrl = mockUrl;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
