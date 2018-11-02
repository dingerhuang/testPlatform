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
    <title>${ptitle}-mock管理</title>
    <link href="${pageContext.request.contextPath}/resources/app/plugins/switch/css/bootstrap-switch.min.css" rel="stylesheet" />
</head>
<body>
<div>
    <div class="row">
        <div class="col-md-10" style="width:100%;">
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                    </div>
                    <h4 class="panel-title">mockUrl列表</h4>
                </div>
                <div class="panel-body">
                    <form class="form-inline">
                        <div class="form-group m-r-10">
                            <input type="text" class="form-control"  style="width:220px;" id="mockUrl" placeholder="mockUrl" />
                        </div>
                        <div class="form-group  m-r-10" >
                            <div class="input-group">
                                <input  readonly="readonly" class="form-control" style="width:220px;" id="createTime" placeholder="创建日期">
                            </div>
                        </div>
                        <div class="form-group m-r-10">
                            <select id="project" class="form-control">
                                <option value="">--项目--</option>
                                <option value="1">小凡</option>
                                <option value="2">个贷</option>
                            </select>
                        </div>
                        <shiro:hasPermission name="mock:find">
                            <button type="button" class="btn btn-sm btn-info m-r-5" id="find">查询</button>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="mock:save">
                            <button type="button" data-toggle="modal" href="#create-mock-modal"  class="btn btn-sm btn-primary m-r-5">新增</button>
                        </shiro:hasPermission>
                    </form>
                    <table id="mock-table" class="table table-striped table-bordered" width="100%">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>id</th>
                            <th>mockUrl</th>
                            <th>mockPara</th>
                            <th>mockResponse</th>
                            <th>timeOut</th>
                            <th>project</th>
                            <th>remark</th>
                            <th>修改人</th>
                            <th>修改时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/view/testTools/mock_dialog.jsp" %>
<div class="row">
    <!-- 按钮-->
    <div id="button-1" style="display: none">
        <shiro:hasPermission name="mock:update">
            <button type="button" class="btn btn-sm btn-primary" data-toggle="modal" href="#update-mock-modal" onclick="page.updateMockDialog('#id')">修改</button>
        </shiro:hasPermission>
    </div>
    <div id="button-2" style="display: none">
        <shiro:hasPermission name="mock:delete">
            <button type="button" class="btn btn-sm btn-danger" onclick="page.deleteMockUrl('#id')">删除</button>
        </shiro:hasPermission>
    </div>
</div>
<script src="${pageContext.request.contextPath}/resources/color-admin/plugins/DataTables/extensions/Select/js/dataTables.select.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/switch/js/bootstrap-switch.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/app/js/tool.date.js"></script>
<script src="${pageContext.request.contextPath}/resources/app/js/mock.js"></script>
<script>
    page.init();
</script>
</body>
</html>
