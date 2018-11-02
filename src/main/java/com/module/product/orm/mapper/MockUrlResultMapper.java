package com.module.product.orm.mapper;

import com.module.product.common.search.MockUrlResultVo;
import com.module.product.orm.model.MockUrlResult;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MockUrlResultMapper extends Mapper<MockUrlResult> {

    List<MockUrlResult> getMockUrlListByCondition(MockUrlResultVo vo);

}
