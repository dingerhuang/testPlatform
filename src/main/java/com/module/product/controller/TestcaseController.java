package com.module.product.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.module.product.common.annotation.PermissionAnn;
import com.module.product.common.bean.DataTablePageModel;
import com.module.product.common.bean.ResponseJsonModel;
import com.module.product.common.search.TestcaseVo;
import com.module.product.orm.mapper.TestcasesMapper;
import com.module.product.orm.model.ManagePackage;
import com.module.product.orm.model.TestCases;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/testCase")
public class TestcaseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private TestcasesMapper testcasesMapper;

    @PermissionAnn(menuCode = "testCase", msg = "你没有进入testCase管理页面的权限!")
    @RequestMapping(value = "/manage")
    public String forwardTestcaseManager() {
        return "testTools/testcase";
    }

    @PermissionAnn(menuCode = "testCase", operCode = "find", msg = "你没有testCase管理页面的查询权限!")
    @ResponseBody
    @RequestMapping(value = "/search")
    public DataTablePageModel getAllCases(TestcaseVo pagevo){

        PageHelper.offsetPage(pagevo.getStart(),pagevo.getLength());
        List<TestCases> list = testcasesMapper.getTestcases(pagevo);

        pagevo.setData(list);
        long total = ((Page) (list)).getTotal();
        pagevo.setRecordsTotal(total);
        pagevo.setRecordsFiltered(total);
        return pagevo;
    }
    @PermissionAnn(menuCode = "testCase", operCode = "save", msg = "你没有testCase管理页面的新增权限!")
    @ResponseBody
    @RequestMapping(value = "/save")
    public ResponseJsonModel saveCase(TestCases testCases){
        try{
            if(Objects.equals(testcasesMapper.insert(testCases),1)){
                return new ResponseJsonModel();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        ResponseJsonModel responseJsonModel = new ResponseJsonModel();
        responseJsonModel.setCode("500");
        return responseJsonModel;
    }

    @PermissionAnn(menuCode = "testCase", operCode = "delete", msg = "你没有testCase管理页面的新增权限!")
    @ResponseBody
    @RequestMapping(value = "/del")
    public ResponseJsonModel delCase(Integer id){
        try{
            if(Objects.equals(testcasesMapper.deleteByPrimaryKey(id),1)){
                return new ResponseJsonModel();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        ResponseJsonModel responseJsonModel = new ResponseJsonModel();
        responseJsonModel.setCode("500");
        return responseJsonModel;
    }

}
