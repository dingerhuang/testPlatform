<%@ page pageEncoding="utf-8"  language="java" %>
<%-- 新增用例 --%>
<div class="modal fade bs-example-modal-lg" id="create_case_modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >新增用例</h4>
            </div>
            <div class="panel-body">
                <form id="testcaseForm" class="form-horizontal form-bordered" data-parsley-validate="true">
                    <div class="form-group">
                        <label class="col-md-3 control-label">业务线*</label>
                        <div class="col-md-7">
                            <select id="add_businessType" class="form-control">
                                <option value="">--项目--</option>
                                <option value="1">小凡</option>
                                <option value="2">个贷</option>
                                <option value="3">任买</option>
                                <option value="4">一账通</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">需求名称*</label>
                        <div class="col-md-7">
                            <input id="add_requirementName" placeholder="需求名称" type="text"  class="form-control"/>
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">轮次*</label>
                        <div class="col-md-7">
                            <select id="add_testcaseTurn" class="form-control">
                                <option value="">--轮次--</option>
                                <option value="1">第一轮</option>
                                <option value="2">第二轮</option>
                                <option value="3">第三轮</option>
                                <option value="4">回归</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">用例名称*</label>
                        <div class="col-md-7">
                            <input id="add_testcaseName" placeholder="用例名称" type="text" class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">用例等级*</label>
                        <div class="col-md-7">
                            <select id="add_testcaseLevel" class="form-control">
                                <option value="">--用例等级--</option>
                                <option value="1">第一轮</option>
                                <option value="2">第二轮</option>
                                <option value="3">第三轮</option>
                                <option value="4">回归</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">操作步骤</label>
                        <div class="col-md-7">
                            <input id="add_testcaseStep" placeholder="操作步骤" type="text" class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">预期结果</label>
                        <div class="col-md-7">
                            <input id="add_testcaseExpectedResult" placeholder="预期结果" type="text" class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">owner*</label>
                        <div class="col-md-7">
                            <input  id="add_testcaseOwner" placeholder="owner" class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a id="close" href="javascript:;" class="btn btn-sm btn-info" data-dismiss="modal">关闭</a>
                <a id="create_case_btn"  href="javascript:;" class="btn btn-sm btn-success">保存</a>
            </div>
        </div>
    </div>
</div>

<%-- 修改 --%>
<div class="modal fade" id="update_case_modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >用例修改</h4>
            </div>
            <div class="panel-body">
                <form id="updateCaseForm" class="form-horizontal form-bordered" data-parsley-validate="true">
                    <input type="hidden" id="caseId">
                    <div class="form-group">
                        <label class="col-md-3 control-label">业务线*</label>
                        <div class="col-md-7">
                            <%--readonly设置为只读--%>
                            <%--<input id="update_mockUrl" name="mockUrl" type="text" readonly  class="form-control"/>--%>
                            <input id="update_businessType" type="text" readonly  class="form-control"/>
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">需求名称*</label>
                        <div class="col-md-7">
                            <input id="update_requirementName" type="text" readonly class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">轮次*</label>
                        <div class="col-md-7">
                            <input id="update_testcaseTurn" type="text" readonly class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">用例名称*</label>
                        <div class="col-md-7">
                            <input id="update_testcaseName" type="text" readonly class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">用例等级*</label>
                        <div class="col-md-7">
                            <select id="update_testcaseLevel" class="form-control">
                                <option value="">--用例等级--</option>
                                <option value="1">第一轮</option>
                                <option value="2">第二轮</option>
                                <option value="3">第三轮</option>
                                <option value="4">回归</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">操作步骤</label>
                        <div class="col-md-7">
                            <input id="update_testcaseStep" type="text" class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">预期结果</label>
                        <div class="col-md-7">
                            <input id="update_testcaseExpectedResult" type="text" class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">owner*</label>
                        <div class="col-md-7">
                            <input  id="update_testcaseOwner" class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <a id="update_case_close" href="javascript:;" class="btn btn-sm btn-info" data-dismiss="modal">关闭</a>
                <a id="update-case-btn"  href="javascript:;" class="btn btn-sm btn-success">保存</a>
            </div>
        </div>
    </div>
</div>

<%-- 修改 --%>
<div class="modal fade" id="update-mock-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >mock修改</h4>
            </div>
            <div class="panel-body">
                <form id="updateMockForm" class="form-horizontal form-bordered" data-parsley-validate="true">
                    <input type="hidden" id="mockId">
                    <div class="form-group">
                        <label class="col-md-3 control-label">mockUrl*</label>
                        <div class="col-md-7">
                            <%--readonly设置为只读--%>
                            <%--<input id="update_mockUrl" name="mockUrl" type="text" readonly  class="form-control"/>--%>
                            <input id="update_mockUrl" name="mockUrl" type="text" readonly  class="form-control"/>
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">mock参数*</label>
                        <div class="col-md-7">
                            <input id="update_mockPara" name="mockPara" type="text" class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">mockResponse*</label>
                        <div class="col-md-7">
                            <input id="update_mockResponse" name="mockResponse" type="text" class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">超时时间(毫秒)</label>
                        <div class="col-md-7">
                            <input id="update_timeOut"  name="timeOut" type="text" class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">备注</label>
                        <div class="col-md-7">
                            <input  id="update_remark" name="remark" type="text"   class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <a id="update_close" href="javascript:;" class="btn btn-sm btn-info" data-dismiss="modal">关闭</a>
                <a id="update-mock-btn"  href="javascript:;" class="btn btn-sm btn-success">保存</a>
            </div>
        </div>
    </div>
</div>