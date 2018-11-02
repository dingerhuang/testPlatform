package com.module.product.orm.mapper;

import com.module.product.common.search.TestcaseVo;
import com.module.product.orm.core.Mapper;
import com.module.product.orm.model.TestCases;

import java.util.List;

public interface TestcasesMapper extends Mapper<TestCases> {
    List<TestCases> getTestcases(TestcaseVo vo);
}
