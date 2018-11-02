package com.module.product.orm.model;

import javax.persistence.*;

@Table(name = "t_manage_testcase")
public class TestCases {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "business_type")
    private String businessType;

    @Column(name = "requirement_name")
    private String requirementName;

    @Column(name = "testcase_turn")
    private String testcaseTurn;

    @Column(name = "testcase_name")
    private String testcaseName;

    @Column(name = "testcase_level")
    private String testcaseLevel;

    @Column(name = "testcase_step")
    private String testcaseStep;

    @Column(name = "testcase_expected_result")
    private String testcaseExpectedResult;

    @Column(name = "testcase_actual_result")
    private String testcaseActualResult;

    @Column(name = "testcase_owner")
    private String testcaseOwner;

    @Column(name = "remark")
    private String remark;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "update_time")
    private String updateTime;

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

    public String getRequirementName() {
        return requirementName;
    }

    public void setRequirementName(String requirementName) {
        this.requirementName = requirementName;
    }

    public String getTestcaseTurn() {
        return testcaseTurn;
    }

    public void setTestcaseTurn(String testcaseTurn) {
        this.testcaseTurn = testcaseTurn;
    }

    public String getTestcaseName() {
        return testcaseName;
    }

    public void setTestcaseName(String testcaseName) {
        this.testcaseName = testcaseName;
    }

    public String getTestcaseLevel() {
        return testcaseLevel;
    }

    public void setTestcaseLevel(String testcaseLevel) {
        this.testcaseLevel = testcaseLevel;
    }

    public String getTestcaseStep() {
        return testcaseStep;
    }

    public void setTestcaseStep(String testcaseStep) {
        this.testcaseStep = testcaseStep;
    }

    public String getTestcaseExpectedResult() {
        return testcaseExpectedResult;
    }

    public void setTestcaseExpectedResult(String testcaseExpectedResult) {
        this.testcaseExpectedResult = testcaseExpectedResult;
    }

    public String getTestcaseActualResult() {
        return testcaseActualResult;
    }

    public void setTestcaseActualResult(String testcaseActualResult) {
        this.testcaseActualResult = testcaseActualResult;
    }

    public String getTestcaseOwner() {
        return testcaseOwner;
    }

    public void setTestcaseOwner(String testcaseOwner) {
        this.testcaseOwner = testcaseOwner;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}
