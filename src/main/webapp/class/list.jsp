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
                class="icon-plus-square-o"></span> 新建班级
        </button>
    </div>
    <table class="table table-hover text-center">
        <tr>
            <th width="5%">序号</th>
            <th>班级名称</th>
            <th>所属学校</th>
            <th width="250">操作</th>
        </tr>
        <c:forEach items="${clalist}" var="cla" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${cla.className}</td>
                <td>${cla.school.realName}</td>
                <td>
                    <div class="button-group">
                        <a type="button" class="button border-main"
                           href="<%=basePath%>person/claupdt.do?classId=${cla.classId}"><span class="icon-edit"></span>修改</a>
                        <a class="button border-red"
                           href="<%=basePath%>class/rdClass.do?classId=${cla.classId}&actionType=3"
                           onclick="return del(17)"><span
                                class="icon-trash-o"></span> 删除</a>
                    </div>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="8">
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
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>新建班级</strong></div>
    <div class="body-content">
        <form method="post" enctype="multipart/form-data" class="form-x" action="<%=basePath%>class/cuClass.do">
            <input type="hidden" name="actionType" value="0"/>
            <input type="hidden" name="schoolId" value="${sessionScope.userId}"/>
            <div class="form-group">
                <div class="label">
                    <label>班级名称：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="className" value="" data-validate="required:请输入账号"/>
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