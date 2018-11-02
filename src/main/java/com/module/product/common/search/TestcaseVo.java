package com.module.product.common.search;

import com.module.product.common.bean.DataTablePageModel;

public class TestcaseVo extends DataTablePageModel {
    private String businessType;
    private String requirementName;
    private String testcaseTurn;
    private String testcaseLevel;
    private String testcaseActualResult;
    private String testcaseOwner;

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

    public String getTestcaseLevel() {
        return testcaseLevel;
    }

    public void setTestcaseLevel(String testcaseLevel) {
        this.testcaseLevel = testcaseLevel;
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
}
