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
    <script src="<%=basePath%>js/ajax.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 信息管理</strong></div>
    <table class="table table-hover text-center">
        <tr>
            <th width="5%">序号</th>
            <th>班级名称</th>
            <th>课程类型</th>
            <th>课程名</th>
            <th>负责教师</th>
            <th width="250">操作</th>
        </tr>
        <c:forEach items="${cctlist}" var="cct" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${cct.clazz.className}</td>
                <td>${cct.course.courseCategory.categoryName}</td>
                <td>${cct.course.courseName}</td>
                <td>${cct.teacher.realName}</td>
                <td>
                    <div class="button-group">
                        <a class="button border-main"
                           href="<%=basePath%>cct/updt.do?classId=${cct.clazz.classId}&courseId=${cct.course.courseId}&teacherId=${cct.teacher.userId}"><span
                                class="icon-edit"></span> 分配</a>
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
</body>
</html>