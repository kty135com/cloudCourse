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
                class="icon-plus-square-o"></span> 新建课程
        </button>
    </div>
    <table class="table table-hover text-center">
        <tr>
            <th width="5%">序号</th>
            <th>课程名称</th>
            <th>课程价格</th>
            <th>最大学生总数</th>
            <th>课程图片</th>
            <th>开始时间</th>
            <th>结束时间</th>
            <th>所属课程类型</th>
            <th width="250">操作</th>
        </tr>
        <c:forEach items="${coulist}" var="course" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${course.courseName}</td>
                <td>${course.coursePrice}</td>
                <td>${course.maxStudentCount}</td>
                <td><img src="<%=basePath%>person/img.do?url=${course.imgPath}" width="70" height="50"></td>
                <td>${course.startTime}</td>
                <td>${course.endTime}</td>
                <td>${course.courseCategory.categoryName}</td>
                <td>
                    <div class="button-group">
                        <a type="button" class="button border-main"
                           href="<%=basePath%>person/couupdt.do?courseId=${course.courseId}"><span
                                class="icon-edit"></span>修改</a>
                        <a class="button border-red"
                           href="<%=basePath%>course/rdCourse.do?courseId=${course.courseId}&actionType=3"
                           onclick="return del(17)"><span
                                class="icon-trash-o"></span> 删除</a>
                    </div>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="9">
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
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>新建课程</strong></div>
    <div class="body-content">
        <form method="post" enctype="multipart/form-data" class="form-x" action="<%=basePath%>course/cuCourse.do">
            <input type="hidden" name="actionType" value="0"/>
            <div class="form-group">
                <div class="label">
                    <label>课程名称：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="courseName" data-validate="required:请输入课程"/>
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
                    <label>课程图片：</label>
                </div>
                <div class="field">
                    <input type="file" id="url1" name="file" style="width:25%; float:left;"
                           value="" data-toggle="hover" data-place="right" data-image=""/>
                    <div class="tipss">图片尺寸：自适应</div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>最大学生人数：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="maxStudentCount" value=""
                           data-validate="required:请输入最大学生人数"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>课程价格：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="coursePrice" value="" data-validate="required:请输入课程价格"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>课程开始时间：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="startTime" value="" data-validate="required:请输入课程开始时间"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>课程结束时间：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="endTime" value="" data-validate="required:请输入课程结束时间"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>课程描述：</label>
                </div>
                <div class="field">
                    <textarea name="courseDesc" class="input" style="height:120px;"></textarea>
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