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
    <div class="padding border-bottom">
        <button type="button" class="button border-yellow" onclick="window.location.href='#add'"><span
                class="icon-plus-square-o"></span> 班级课程分配
        </button>
    </div>
    <table class="table table-hover text-center">
        <tr>
            <th width="5%">序号</th>
            <th>班级名称</th>
            <th>课程类型</th>
            <th>课程名</th>
            <th>创建时间</th>
            <th width="250">操作</th>
        </tr>
        <c:forEach items="${classCourseList}" var="cc" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${cc.clazz.className}</td>
                <td>${cc.course.courseCategory.categoryName}</td>
                <td>${cc.course.courseName}</td>
                <td>${cc.createTime}</td>
                <td>
                    <div class="button-group">
                        <a class="button border-red"
                           href="<%=basePath%>cct/cdcct.do?classId=${cc.clazz.classId}&courseId=${cc.course.courseId}&actionType=3"
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
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>班级课程分配</strong></div>
    <div class="body-content">
        <form method="post" class="form-x" action="<%=basePath%>cct/cdcct.do">
            <input type="hidden" name="actionType" value="0"/>
            <div class="form-group">
                <div class="label">
                    <label>班级名称：</label>
                </div>
                <div class="field">
                    <select name="classId" class="input w50">
                        <option>请选择班级</option>
                        <c:forEach items="${clalist}" var="c">
                            <option value="${c.classId}">${c.className}</option>
                        </c:forEach>
                    </select>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>课程类型：</label>
                </div>
                <div class="field">
                    <select class="input w50" onchange="queryCourseByCategory(this.value)">
                        <option value="">请选择课程类型</option>
                        <c:forEach items="${catlist}" var="c">
                            <option value="${c.courseCategoryId}">${c.categoryName}</option>
                        </c:forEach>
                    </select>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>课程名称：</label>
                </div>
                <div class="field">
                    <select id="courseName" name="courseId" class="input w50">
                        <option value="">请选择课程名称</option>
                    </select>
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