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

    <div class="panel-head"><strong class="icon-reorder"> 布置作业</strong></div>
    <div class="padding border-bottom">
        <button type="button" class="button border-yellow" onclick="window.location.href='#list'"><span
                class="icon-plus-square-o"></span> 往期作业
        </button>
    </div>
    <div class="panel admin-panel margin-top">
        <div class="body-content">
            <form method="post" enctype="multipart/form-data" class="form-x" action="<%=basePath%>person/hwupdate.do">
                <input type="hidden" name="classId" value="${hw.clazz.classId}">
                <input type="hidden" name="courseId" value="${hw.course.courseId}">
                <div class="form-group">
                    <div class="label">
                        <label>所属班级：</label>
                    </div>
                    <div class="field">
                        <input type="text" readonly="readonly" class="input w50" value="${hw.clazz.className}" data-validate="required:请输入账号"/>
                        <div class="tips"></div>
                    </div>
                </div><div class="form-group">
                <div class="label">
                    <label>所属课程：</label>
                </div>
                <div class="field">
                    <input type="text" readonly="readonly" class="input w50" value="${hw.course.courseName}" data-validate="required:请输入账号"/>
                    <div class="tips"></div>
                </div>
            </div>
                <div class="form-group">
                    <div class="label">
                        <label>作业日期：</label>
                    </div>
                    <div class="field">
                        <input type="date" class="input w50" readonly="readonly" name="createTime" value="${today}" data-validate="required:请输入作业日期"/>
                        <div class="tips"></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label>最大加分数：</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input w50" name="maxPointAdd" value="${hw.maxPointAdd}" data-validate="required:请输入最大加分数"/>
                        <div class="tips"></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label>作业描述：</label>
                    </div>
                    <div class="field">
                        <input type="text" class="input w50" name="homeworkDesc" value="${hw.homeworkDesc}" data-validate="required:请输入作业描述"/>
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
</div>
<p/>
<div id="list" class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 往期作业</strong></div>

    <table class="table table-hover text-center">
        <tr>
            <th width="5%">序号</th>
            <th>作业内容</th>
            <th width="15%">最大加分数</th>
            <th width="15%">作业日期</th>
        </tr>
        <c:forEach items="${hwlist}" var="hw" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${hw.homeworkDesc}</td>
                <td>${hw.maxPointAdd}</td>
                <td>${hw.createTime}</td>
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

</body>
</html>