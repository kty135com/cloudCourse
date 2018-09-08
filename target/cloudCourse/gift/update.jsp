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
        <form method="post" class="form-x" action="<%=basePath%>person/giftupdate.do">
            <input type="hidden" name="giftId" value="${gift.giftId}">
            <div class="form-group">
                <div class="label">
                    <label>礼品名称：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="giftName" value="${gift.giftName}"
                           data-validate="required:请输入礼品名称"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>礼品描述：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="giftDesc" value="${gift.giftDesc}"
                           data-validate="required:请输入礼品描述"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>礼物剩余数：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="giftNum" value="${gift.giftNum}"
                           data-validate="required:请输入礼物剩余数"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>礼物总数：</label>
                </div>
                <div class="field">
                    <input type="date" class="input w50" name="giftCount" value="${gift.giftCount}"
                           data-validate="required:请输入礼物总数"/>
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