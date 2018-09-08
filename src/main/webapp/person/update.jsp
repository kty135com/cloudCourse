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
        <form method="post" class="form-x" action="<%=basePath%>person/updtPerson.do">
            <input type="hidden" name="userId" value="${person.userId}">
            <div class="form-group">
                <div class="label">
                    <label>用户名：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="userName" value="${person.userName}" data-validate="required:请输入账号"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>密码：</label>
                </div>
                <div class="field">
                    <input type="password" class="input w50" name="password" readonly="readonly" value="${person.password}" data-validate="required:请输入密码"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label><c:if test="${person.userType==1}">机构名称</c:if><c:if test="${person.userType==2}">真实姓名</c:if>：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="realName" value="${person.realName}"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>所属上级用户：</label>
                </div>
                <div class="field">
                    <select name="parent_id" class="input w50">
                        <c:forEach items="${list}" var="u">
                            <option value="${u.userId}">${u.realName}</option>
                        </c:forEach>
                    </select>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>是否禁用：</label>
                </div>
                <div class="field">
                    <div class="button-group radio">
                        <label class="button active">
                            <span class="icon icon-check"></span>
                            <input name="enableStatus" value="1" type="radio" checked="checked">是
                        </label>
                        <label class="button active"><span class="icon icon-times"></span>
                            <input name="enableStatus" value="0" type="radio" checked="checked">否
                        </label>
                    </div>
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