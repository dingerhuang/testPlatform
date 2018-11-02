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
    <title>${ptitle}-用例管理</title>
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
                    <h4 class="panel-title">用例管理</h4>
                </div>
                <div class="panel-body">
                    <form class="form-inline">
                        <div class="form-group m-r-2">
                            <select id="query_businessType" class="form-control">
                                <option value="">--业务线--</option>
                                <option value="1">小凡</option>
                                <option value="2">个贷</option>
                                <option value="3">任买</option>
                                <option value="4">钱站</option>
                            </select>
                        </div>
                        <div class="form-group m-r-2">
                            <input type="text" class="form-control"  style="width:220px;" id="query_requirementName" placeholder="需求名称" />
                        </div>
                        <div class="form-group m-r-2">
                            <select id="query_testcaseTurn" class="form-control">
                                <option value="">--轮次--</option>
                                <option value="1">第一轮</option>
                                <option value="2">第二轮</option>
                                <option value="3">第三轮</option>
                                <option value="4">回归</option>
                            </select>
                        </div>
                        <div class="form-group m-r-2">
                            <select id="query_testcaseLevel" class="form-control">
                                <option value="">--用例等级--</option>
                                <option value="1">P0</option>
                                <option value="2">P1</option>
                                <option value="3">P2</option>
                                <option value="4">P3</option>
                            </select>
                        </div>
                        <div class="form-group m-r-2">
                            <select id="query_testcaseActualResult" class="form-control">
                                <option value="">--执行结果--</option>
                                <option value="1">通过</option>
                                <option value="2">不通过</option>
                                <option value="3">阻塞</option>
                                <option value="4">部分通过</option>
                            </select>
                        </div>
                        <div class="form-group m-r-2">
                            <input type="text" class="form-control"  style="width:220px;" id="query_testcaseOwner" placeholder="owner" />
                        </div>
                        <div class="form-group  m-r-2" >
                            <div class="input-group">
                                <input  readonly="readonly" class="form-control" style="width:220px;" id="query_createTime" placeholder="创建日期">
                            </div>
                        </div>
                        <shiro:hasPermission name="testCase:find">
                            <button type="button" class="btn btn-sm btn-info m-r-2 glyphicon glyphicon-search" id="find">查询</button>
                        </shiro:hasPermission>
                    </form>
                    <shiro:hasPermission name="testCase:upload">
                        <button type="button" class="btn btn-sm btn-success m-r-2 glyphicon glyphicon-upload" id="testcase_upload">上传用例</button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="testCase:export">
                        <button type="button" class="btn btn-sm btn-success m-r-2 glyphicon glyphicon-download" id="testcase_export">导出用例</button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="testCase:save">
                        <button type="button" data-toggle="modal" class="btn btn-sm btn-success m-r-2 glyphicon glyphicon-plus" id="testcase_add" href="#create_case_modal">新增用例</button>
                    </shiro:hasPermission>
                    <table id="package-table" class="table table-striped table-bordered" width="100%">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>id</th>
                            <th>业务线</th>
                            <th>需求名称</th>
                            <th>轮次</th>
                            <th>用例名称</th>
                            <th>用例等级</th>
                            <th>操作步骤</th>
                            <th>预期结果</th>
                            <th>实际结果</th>
                            <th>owner</th>
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
<%@include file="/WEB-INF/view/testTools/testcase_dialog.jsp" %>
<div class="row">
    <!-- 按钮-->
    <div id="button-download" style="display: none">
            <button type="button" class="btn btn-xs btn-success glyphicon glyphicon-cog" onclick="page.downloadFile('#id')">执行结果</button>
    </div>
    <div id="button-code" style="display: none">
            <button type="button" class="btn btn-xs btn-warning glyphicon glyphicon-pencil" data-toggle="modal" href="update_case_modal">修改</button>
    </div>
    <div id="button-del" style="display: none">
        <shiro:hasPermission name="testCase:delete">
            <button type="button" class="btn btn-xs btn-danger glyphicon glyphicon-trash" onclick="page.deleteTestcase('#id')">删除</button>
        </shiro:hasPermission>
    </div>
</div>
<script src="${pageContext.request.contextPath}/resources/color-admin/plugins/DataTables/extensions/Select/js/dataTables.select.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/bootstrap-fileinput/js/fileinput.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/bootstrap-fileinput/js/locales/zh.js"></script>
<script src="${pageContext.request.contextPath}/resources/app/plugins/switch/js/bootstrap-switch.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/app/js/tool.date.js"></script>
<script src="${pageContext.request.contextPath}/resources/app/js/testcase.js"></script>
<script>
    page.init();
</script>

</body>
</html>
