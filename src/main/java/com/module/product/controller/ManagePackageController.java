package com.module.product.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.module.product.common.annotation.PermissionAnn;
import com.module.product.common.bean.DataTablePageModel;
import com.module.product.common.search.ManagePackageVo;
import com.module.product.orm.mapper.ManagePackageMapper;
import com.module.product.orm.model.ManagePackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/package")
public class ManagePackageController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ManagePackageMapper managePackageMapper;
    @PermissionAnn(menuCode = "package", msg = "你没有进入package管理页面的权限!")
    @RequestMapping(value = "manage")
    public String forwardPackageManager() {
        return "testTools/package";
    }

    @PermissionAnn(menuCode = "package", operCode = "find", msg = "你没有mock管理页面的查询权限!")
    @ResponseBody
    @RequestMapping(value = "/search")
    public DataTablePageModel getPackages(ManagePackageVo pagevo){
        PageHelper.offsetPage(pagevo.getStart(),pagevo.getLength());
        DataTablePageModel dataTablePageModel = new DataTablePageModel();
        List<ManagePackage> list = managePackageMapper.getManagePackageList(pagevo);

        pagevo.setData(list);
        long total = ((Page) (list)).getTotal();
        pagevo.setRecordsTotal(total);
        pagevo.setRecordsFiltered(total);
        return pagevo;
    }
}
