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
    <script src="<%=basePath%>js/ajax.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong>
    </div>
    <div class="padding border-bottom">
        <ul class="search" style="padding-left:10px;">
            <li>按 培训机构--班级 查找：</li>
            <form action="<%=basePath%>student/queryStuByCla.do" method="post">
                <if condition="$iscid eq 1">
                    <li>
                        <select id="querySch" class="input" style="width:200px; line-height:17px;"
                                onchange="queryTeacherBySchool(this.value)">
                            <option value="">所有培训机构</option>
                            <c:forEach items="${schList}" var="school">
                                <option value="${school.userId}">${school.realName}</option>
                            </c:forEach>
                        </select>
                    </li>
                    <li>
                        <select id="queryCla" name="classId" class="input" style="width:200px; line-height:17px;">
                            <option value="-1">所有班级</option>
                        </select>
                    </li>
                    <input type="hidden" name="url" value="student/list">
                    <li>
                        <button class="button border-main icon-search" type="submit"> 搜索</button>
                    </li>
                </if>
            </form>
        </ul>
    </div>
    <table class="table table-hover text-center">
        <tr>
            <th>序号</th>
            <th>姓名</th>
            <th>用户名</th>
            <th>密码</th>
            <th width="10%">所属学校</th>
            <th width="10%">所属班级</th>
            <th>照片</th>
            <th>联系电话</th>
            <th>账号状态</th>
            <th width="310">操作</th>
        </tr>
        <volist name="list" id="vo">
            <c:forEach items="${stuList}" var="stu" varStatus="status">
                <tr>
                    <td style="text-align:left; padding-left:20px;">
                            ${status.index+1}
                    </td>
                    <td>${stu.realName}</td>
                    <td>${stu.userName}</td>
                    <td>******</td>
                    <td>${stu.school.realName}</td>
                    <td>${stu.clazz.className}</td>
                    <td width="10%"><img src="<%=basePath%>person/img.do?url=${stu.userHeadImg}" alt="${stu.realName}"
                                         width="70" height="50"/></td>
                    <td>${stu.tel}</td>
                    <td><c:choose><c:when
                            test="${stu.enableStatus==0}">已启用</c:when><c:otherwise>已禁用</c:otherwise></c:choose></td>
                    <td>
                        <div class="button-group"><a class="button border-main"
                                                     href="<%=basePath%>student/updt.do?stuId=${stu.studentId}"><span
                                class="icon-edit"></span> 修改</a><a class="button border-red"
                                                                   href="<%=basePath%>person/studel.do?stuId=${stu.studentId}&actionType=3"
                                                                   onclick="return del(1,1,1)"><span
                                class="icon-trash-o"></span> 删除</a></div>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="10">
                    <div class="pagelist"><a href="">上一页</a> <span class="current">1</span><a href="">2</a><a
                            href="">3</a><a href="">下一页</a><a href="">尾页</a></div>
                </td>
            </tr>
        </volist>
    </table>
</div>
<script type="text/javascript">

    //单个删除
    function del(id, mid, iscid) {
        if (confirm("您确定要删除吗?")) {

        } else {
            return false;
        }
    }

    //全选
    $("#checkall").click(function () {
        $("input[name='id[]']").each(function () {
            if (this.checked) {
                this.checked = false;
            }
            else {
                this.checked = true;
            }
        });
    })

    //批量删除
    function DelSelect() {
        var Checkbox = false;
        $("input[name='id[]']").each(function () {
            if (this.checked == true) {
                Checkbox = true;
            }
        });
        if (Checkbox) {
            var t = confirm("您确认要删除选中的内容吗？");
            if (t == false) return false;
            $("#listform").submit();
        }
        else {
            alert("请选择您要删除的内容!");
            return false;
        }
    }

    //批量排序
    function sorts() {
        var Checkbox = false;
        $("input[name='id[]']").each(function () {
            if (this.checked == true) {
                Checkbox = true;
            }
        });
        if (Checkbox) {

            $("#listform").submit();
        }
        else {
            alert("请选择要操作的内容!");
            return false;
        }
    }


    //批量首页显示
    function changeishome(o) {
        var Checkbox = false;
        $("input[name='id[]']").each(function () {
            if (this.checked == true) {
                Checkbox = true;
            }
        });
        if (Checkbox) {

            $("#listform").submit();
        }
        else {
            alert("请选择要操作的内容!");

            return false;
        }
    }

    //批量推荐
    function changeisvouch(o) {
        var Checkbox = false;
        $("input[name='id[]']").each(function () {
            if (this.checked == true) {
                Checkbox = true;
            }
        });
        if (Checkbox) {


            $("#listform").submit();
        }
        else {
            alert("请选择要操作的内容!");

            return false;
        }
    }

    //批量置顶
    function changeistop(o) {
        var Checkbox = false;
        $("input[name='id[]']").each(function () {
            if (this.checked == true) {
                Checkbox = true;
            }
        });
        if (Checkbox) {

            $("#listform").submit();
        }
        else {
            alert("请选择要操作的内容!");

            return false;
        }
    }


    //批量移动
    function changecate(o) {
        var Checkbox = false;
        $("input[name='id[]']").each(function () {
            if (this.checked == true) {
                Checkbox = true;
            }
        });
        if (Checkbox) {

            $("#listform").submit();
        }
        else {
            alert("请选择要操作的内容!");

            return false;
        }
    }

    //批量复制
    function changecopy(o) {
        var Checkbox = false;
        $("input[name='id[]']").each(function () {
            if (this.checked == true) {
                Checkbox = true;
            }
        });
        if (Checkbox) {
            var i = 0;
            $("input[name='id[]']").each(function () {
                if (this.checked == true) {
                    i++;
                }
            });
            if (i > 1) {
                alert("只能选择一条信息!");
                $(o).find("option:first").prop("selected", "selected");
            } else {

                $("#listform").submit();
            }
        }
        else {
            alert("请选择要复制的内容!");
            $(o).find("option:first").prop("selected", "selected");
            return false;
        }
    }

</script>
</body>
</html>