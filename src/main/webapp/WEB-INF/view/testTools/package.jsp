<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2018/10/12
  Time: 下午5:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${ptitle}-安装包管理</title>
    <link href="${pageContext.request.contextPath}/resources/app/plugins/switch/css/bootstrap-switch.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/app/plugins/bootstrap-fileinput/css/fileinput.min.css" rel="stylesheet" />
</head>
<body>
<img id="showImage"
style="position: absolute; top:20%;left:91%;transform:translate(-50%,-50%);height:200px;width: 200px;z-index: 999;display:none">
<div>
    <div class="row">
        <div class="col-md-10" style="width:100%;">
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                    </div>
                    <h4 class="panel-title">安装包管理</h4>
                </div>
                <div class="panel-body">
                    <form class="form-inline">
                        <div class="form-group m-r-10">
                            <select id="query_businessType" class="form-control">
                                <option value="">--业务线--</option>
                                <option value="1">小凡</option>
                                <option value="2">个贷</option>
                                <option value="3">任买</option>
                                <option value="4">钱站</option>
                            </select>
                        </div>
                        <div class="form-group m-r-10">
                            <select id="query_appType" class="form-control">
                                <option value="">--App类型--</option>
                                <option value="1">安卓</option>
                                <option value="2">IOS</option>
                            </select>
                        </div>
                        <div class="form-group m-r-10">
                            <input type="text" class="form-control"  style="width:220px;" id="query_appRelease" placeholder="appRelease" />
                        </div>
                        <div class="form-group m-r-10">
                            <select id="query_appEnv" class="form-control">
                                <option value="">--环境--</option>
                                <option value="1">beta环境</option>
                                <option value="2">测试环境</option>
                                <option value="3">生产环境</option>
                                <option value="4">开发环境</option>
                            </select>
                        </div>
                        <div class="form-group  m-r-10" >
                            <div class="input-group">
                                <input  readonly="readonly" class="form-control" style="width:220px;" id="query_createTime" placeholder="创建日期">
                            </div>
                        </div>
                        <shiro:hasPermission name="package:find">
                            <button type="button" class="btn btn-sm btn-info m-r-5 glyphicon glyphicon-zoom-in" id="find">查询</button>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="package:save">
                            <button type="button" data-toggle="modal" href="#create-package-modal" onclick="page.uploadFileDialog('#id')" class="btn btn-sm btn-primary m-r-5 glyphicon glyphicon-upload">上传安装包</button>
                        </shiro:hasPermission>
                    </form>
                    <table id="package-table" class="table table-striped table-bordered" width="100%">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>id</th>
                            <th>业务线</th>
                            <th>App类型</th>
                            <th>App版本</th>
                            <th>环境</th>
                            <th>上传时间</th>
                            <th>备注</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/view/testTools/package_dialog.jsp" %>
<div class="row">
    <!-- 按钮-->
    <div id="button-download" style="display: none">
            <button type="button" class="btn btn-xs btn-success glyphicon glyphicon-download-alt" onclick="page.downloadFile('#id')">下载</button>
    </div>
    <div id="button-code" style="display: none">
            <button id="imgDetail" type="button" class="btn btn-xs btn-warning" data-toggle="modal" onMouseout="page.hideImg()" onmouseover="page.showImg('#id')">二维码</button>
    </div>
    <div id="button-del" style="display: none">
        <shiro:hasPermission name="mock:delete">
            <button type="button" class="btn btn-xs btn-danger glyphicon glyphicon-trash" onclick="page.deletePackage('#id')">删除</button>
        </shiro:hasPermission>
    </div>
</div>
<script src="${pageContext.request.contextPath}/resources/color-admin/plugins/DataTables/extensions/Select/js/dataTables.select.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/bootstrap-fileinput/js/fileinput.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/bootstrap-fileinput/js/locales/zh.js"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/switch/js/bootstrap-switch.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/app/js/tool.date.js"></script>
<script src="${pageContext.request.contextPath}/resources/app/js/package.js"></script>
<script>
    page.init();
</script>

</body>
</html>
