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
    <title></title>
    <link rel="stylesheet" href="<%=basePath%>css/pintuer.css">
    <link rel="stylesheet" href="<%=basePath%>css/admin.css">
    <script src="<%=basePath%>js/jquery.js"></script>
    <script src="<%=basePath%>js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>后端用户修改</strong></div>
    <div class="body-content">
        <form method="post" class="form-x" action="<%=basePath%>category/update.do">
            <input type="hidden" name="courseCategoryId" value="${category.courseCategoryId}">
            <input type="hidden" name="lastEditTime" value="${category.lastEditTime}">
            <div class="form-group">
                <div class="label">
                    <label>类型名称：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="categoryName" value="${category.categoryName}" data-validate="required:请输入课程类型名称"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>权重：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="priority" value="${category.priority}" data-validate="required:请输入权重"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>类型描述：</label>
                </div>
                <div class="field">
                    <textarea name="categoryDesc" class="input" style="height:120px;">${category.categoryDesc}</textarea>
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