<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title>信息管理</title>
    <link rel="stylesheet" href="<%=basePath%>css/pintuer.css">
    <link rel="stylesheet" href="<%=basePath%>css/admin.css">
    <script src="<%=basePath%>js/jquery.js"></script>
    <script src="<%=basePath%>js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 信息管理</strong></div>
    <div class="padding border-bottom">
        <button type="button" class="button border-yellow" onclick="window.location.href='#add'"><span
                class="icon-plus-square-o"></span> 新建友链
        </button>
    </div>
    <table class="table table-hover text-center">
        <tr>
            <th width="5%">序号</th>
            <th>友链名称</th>
            <th>友链地址</th>
            <th>权重</th>
            <th width="10%">点击总数</th>
            <th width="250">操作</th>
        </tr>
        <c:forEach items="${flklist}" var="flk" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${flk.linkName}</td>
                <td>${flk.linkUrl}</td>
                <td>${flk.priority}</td>
                <td>${flk.clickCount}</td>
                <td>
                    <div class="button-group">
                        <a type="button" class="button border-main"
                           href="<%=basePath%>friendlk/updt.do?linkId=${flk.linkId}"><span
                                class="icon-edit"></span>修改</a>
                        <a class="button border-red"
                           href="<%=basePath%>friendlk/del.do?linkId=${flk.linkId}"
                           onclick="return del(17)"><span
                                class="icon-trash-o"></span> 删除</a>
                    </div>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="6">
                <div class="pagelist"><a href="">上一页</a> <span class="current">1</span><a href="">2</a><a
                        href="">3</a><a href="">下一页</a><a href="">尾页</a></div>
            </td>
        </tr>
    </table>
</div>
<script>
    function del(id) {
        if (confirm("您确定要删除吗?")) {

        } else {
            return false;
        }
    }
</script>
<div class="panel admin-panel margin-top">
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>新建机构</strong></div>
    <div class="body-content">
        <form method="post" enctype="multipart/form-data" class="form-x" action="<%=basePath%>friendlk/add.do">
            <input type="hidden" name="actionType" value="0"/>
            <input type="hidden" name="userType" value="1"/>
            <div class="form-group">
                <div class="label">
                    <label>友链名称：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="linkName" data-validate="required:请输入友链名称"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>友链地址：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="linkUrl" data-validate="required:请输入友链地址"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>权重：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="priority"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>