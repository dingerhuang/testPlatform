<%@ page pageEncoding="utf-8"  language="java" %>
<%-- 上传安装包 --%>
<div class="modal fade bs-example-modal-lg" id="create-package-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >上传安装包</h4>
            </div>
            <div class="panel-body">
                <form id="uploadForm" class="form-horizontal form-bordered" data-parsley-validate="true" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="col-md-3 control-label">安装包*</label>
                        <div class="col-md-9">
                            <input id="upload_package" name="uploadFile" type="file" multiple  class="file-loading" data-show-preview="false">
                            <span style="color: red;">只允许上传.apk;.ipa文件</span>
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">业务线*</label>
                        <div class="col-md-7">
                            <select id="upload_businessType" class="form-control">
                                <option value="">--业务线--</option>
                                <option value="1">小凡</option>
                                <option value="2">个贷</option>
                                <option value="3">钱站</option>
                                <option value="4">任买</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">App类型*</label>
                        <div class="col-md-7">
                            <select id="upload_appType" class="form-control">
                                <option value="">--App类型--</option>
                                <option value="android">安卓</option>
                                <option value="IOS">IOS</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">App版本*</label>
                        <div class="col-md-7">
                            <input id="upload_appRelease" name="appRelease" type="text" class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">环境*</label>
                        <div class="col-md-7">
                            <select id="upload_appEnv" class="form-control">
                                <option value="">--环境--</option>
                                <option value="1">Beta环境</option>
                                <option value="2">测试环境</option>
                                <option value="3">生产环境</option>
                                <option value="4">开发环境</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">备注*</label>
                        <div class="col-md-7">
                            <input  id="upload_remark" name="remark" class="form-control" />
                            <ul  class="parsley-errors-list filled">
                                <li name="ErrorTip" class="parsley-required"></li>
                            </ul>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a id="upload_btn_close" href="javascript:;" class="btn btn-sm btn-info" data-dismiss="modal">关闭</a>
                <a id="package-save-btn"  href="javascript:;" class="btn btn-sm btn-success">保存</a>
            </div>
        </div>
    </div>
</div>

</div>