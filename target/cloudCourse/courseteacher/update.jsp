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
        <form method="post" class="form-x" action="<%=basePath%>cct/ucct.do">
            <div class="form-group">
                <div class="label">
                    <label>班级名称：</label>
                </div>
                <div class="field">
                    <input type="hidden" name="classId" value="${clazz.classId}">
                    <input type="hidden" name="courseId" value="${course.courseId}">
                    <input type="text" readonly="readonly" class="input w50" value="${clazz.className}"
                           data-validate="required:请输入班级名称"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>课程名称：</label>
                </div>
                <div class="field">
                    <input type="text" readonly="readonly" class="input w50" value="${course.courseName}"
                           data-validate="required:请输入班级名称"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>任课教师：</label>
                </div>
                <div class="field">
                    <select id="select" name="teacherId" class="input w50">
                        <c:choose>
                            <c:when test="${teacher!=null}">
                                <option value="${teacher.userId}">${teacher.realName}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="">请选择任课教师</option>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach items="${teacherList}" var="u">
                            <option value="${u.userId}">${u.realName}</option>
                        </c:forEach>
                    </select>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button onclick="return checkTeacher()" class="button bg-main icon-check-square-o" type="submit"> 提交</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    function checkTeacher() {
        var value = document.getElementById("select").value;
        if (value==null||value==""){
            return false;
        } else{
            return true;
        }
    }
</script>
</body>
</html>