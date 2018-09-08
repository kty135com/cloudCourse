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
        <form method="post" class="form-x" action="<%=basePath%>course/couupdate.do">
            <input type="hidden" name="courseId" value="${course.courseId}">
            <div class="form-group">
                <div class="label">
                    <label>课程名称：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="courseName" value="${course.courseName}" data-validate="required:请输入课程名称"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>课程价格：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="coursePrice" value="${course.coursePrice}" data-validate="required:请输入课程价格"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>最大学生人数：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="maxStudentCount" value="${course.maxStudentCount}" data-validate="required:请输入最大人数"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>开始时间：</label>
                </div>
                <div class="field">
                    <input type="date" class="input w50" name="startTime" value="${course.startTime}" data-validate="required:请输入开始时间"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>结束时间：</label>
                </div>
                <div class="field">
                    <input type="date" class="input w50" name="endTime" value="${course.endTime}" data-validate="required:请输入结束时间"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>所属课程类型：</label>
                </div>
                <div class="field">
                    <select name="categoryId" class="input w50">
                        <c:forEach items="${catlist}" var="c">
                            <option value="${c.courseCategoryId}">${c.categoryName}</option>
                        </c:forEach>
                    </select>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>课程描述：</label>
                </div>
                <div class="field">
                    <textarea name="courseDesc" class="input" style="height:120px;">${course.courseDesc}</textarea>
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