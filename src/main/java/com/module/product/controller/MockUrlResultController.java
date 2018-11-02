package com.module.product.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.module.product.common.annotation.AdminLogAnn;
import com.module.product.common.annotation.PermissionAnn;
import com.module.product.common.bean.DataTablePageModel;
import com.module.product.common.bean.ResponseJsonModel;
import com.module.product.common.enums.OperLevel;
import com.module.product.common.enums.OperType;
import com.module.product.common.search.MockUrlResultVo;
import com.module.product.orm.mapper.MockUrlResultMapper;
import com.module.product.orm.model.MockUrlResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/mock")
public class MockUrlResultController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private MockUrlResultMapper mockUrlResultMapper;

    @PermissionAnn(menuCode = "mock", msg = "你没有进入mock管理页面的权限!")
    @RequestMapping(value = "manage")
    public String forwardMockManager() {
        return "testTools/mock";
    }

    @PermissionAnn(menuCode = "mock", operCode = "find", msg = "你没有mock管理页面的查询权限!")
    @ResponseBody
    @RequestMapping(value = "/search")
    public DataTablePageModel getMockUrlList(MockUrlResultVo pagevo){
        PageHelper.offsetPage(pagevo.getStart(),pagevo.getLength());
        List<MockUrlResult> list = mockUrlResultMapper.getMockUrlListByCondition(pagevo);
        pagevo.setData(list);
        long total = ((Page) (list)).getTotal();
        pagevo.setRecordsTotal(total);
        pagevo.setRecordsFiltered(total);
        return pagevo;
    }

    @ResponseBody
    @RequestMapping(value = "/entity")
    public ResponseJsonModel getMockUrl(Integer id){
        ResponseJsonModel responseJsonModel = new ResponseJsonModel();
        MockUrlResult mockUrlResult = mockUrlResultMapper.selectByPrimaryKey(id);
        responseJsonModel.setResult(mockUrlResult);

        return responseJsonModel;
    }

    @PermissionAnn(menuCode = "mock", operCode = "update", msg = "你没有修改的权限!")
    @ResponseBody
    @RequestMapping(value = "/update")
    public ResponseJsonModel updateMockUrlResult(MockUrlResult mockUrlResult){
        Date now = new Date();
        mockUrlResult.setUpdateTime(now);
        mockUrlResultMapper.updateByPrimaryKeySelective(mockUrlResult);

        return new ResponseJsonModel();
    }

    @PermissionAnn(menuCode = "mock", operCode = "delete", msg = "你没有删除的权限!")
    @ResponseBody
    @RequestMapping(value = "/del")
    public ResponseJsonModel delMockUrlResult(Integer id){
        mockUrlResultMapper.deleteByPrimaryKey(id);

        return new ResponseJsonModel();
    }

    @PermissionAnn(menuCode = "mock", operCode = "save", msg = "你没有添加的权限!")
    @ResponseBody
    @RequestMapping(value = "/save")
    public ResponseJsonModel saveMockUrlResult(MockUrlResult mockUrlResult){
        Date now = new Date();
        mockUrlResult.setCreateTime(now);
        mockUrlResult.setUpdateTime(now);
        mockUrlResultMapper.insertSelective(mockUrlResult);

        return new ResponseJsonModel();
    }

    @RequestMapping(value="/**",produces={"application/json;charset=UTF-8"})
    @ResponseBody
    public String matchUrl(HttpServletRequest httpServletRequest) throws InterruptedException {
        String url = httpServletRequest.getRequestURI();
        for(MockUrlResult mockData : mockUrlResultMapper.selectAll()){
            if(url.endsWith(mockData.getMockUrl())){
                if(mockData.getTimeOut() == null || mockData.getTimeOut().equals("")){
                }else {
                    Thread.sleep(Integer.parseInt(mockData.getTimeOut()));
                }
                //JSONObject JO = JSONObject.parseObject(mockData.getMockResponse());
                return mockData.getMockResponse();
            }
        }

        return "error";
    }
}
