package com.module.product.orm.mapper;

import com.module.product.common.search.ManagePackageVo;
import com.module.product.orm.core.Mapper;
import com.module.product.orm.model.ManagePackage;

import java.util.List;

public interface ManagePackageMapper extends Mapper<ManagePackage> {
    List<ManagePackage> getManagePackageList(ManagePackageVo vo);
}
