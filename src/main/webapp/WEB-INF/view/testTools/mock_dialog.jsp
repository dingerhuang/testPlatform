<%@ page pageEncoding="utf-8"  language="java" %>
<%-- 新增mockUrl --%>
<div class="modal fade bs-example-modal-lg" id="create-mock-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >新增mockUrl</h4>
            </div>
            <div class="panel-body">
                <form id="mockUrlForm" class="form-horizontal form-bordered" data-parsley-validate="true">
                    <div class="form-group">
                        <label class="col-md-3 control-label">mockUrl*</label>
                        <div class="col-md-7">
                            <input id="add_mockUrl" name="mockUrl" type="text"  class="form-control"/>
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">mockPara</label>
                        <div class="col-md-7">
                            <input id="add_mockPara" name="mockPara" type="text" class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">mockResponse*</label>
                        <div class="col-md-7">
                            <input id="add_mockResponse" name="mockResponse" type="text" class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">timeOut</label>
                        <div class="col-md-7">
                            <input id="add_timeOut" name="timeOut" type="text" class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">项目*</label>
                        <div class="col-md-7">
                            <select id="add_project" class="form-control">
                                <option value="">--项目--</option>
                                <option value="1">小凡</option>
                                <option value="2">个贷</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">备注*</label>
                        <div class="col-md-7">
                            <input  id="add_remark" name="remark" class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a id="close" href="javascript:;" class="btn btn-sm btn-info" data-dismiss="modal">关闭</a>
                <a id="create-mockUrl-btn"  href="javascript:;" class="btn btn-sm btn-success">保存</a>
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