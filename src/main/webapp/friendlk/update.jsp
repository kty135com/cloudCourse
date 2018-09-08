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
        <form method="post" class="form-x" action="<%=basePath%>friendlk/update.do">
            <input type="hidden" name="linkId" value="${flk.linkId}">
            <div class="form-group">
                <div class="label">
                    <label>友链名称：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="linkName" value="${flk.linkName}" data-validate="required:请输入友链名称"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>友链地址：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="linkUrl" value="${flk.linkUrl}" data-validate="required:请输入密码"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>权重：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="priority" value="${flk.priority}"/>
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