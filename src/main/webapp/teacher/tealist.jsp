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
    <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong>
    </div>
    <div class="padding border-bottom">
        <button type="button" class="button border-yellow" onclick="window.location.href='#add'"><span
                class="icon-plus-square-o"></span> 新建教师
        </button>
    </div>
    <table class="table table-hover text-center">
        <tr>
            <th>序号</th>
            <th>姓名</th>
            <th>用户名</th>
            <th>密码</th>
            <th width="10%">所属学校</th>
            <th>照片</th>
            <th>状态</th>
            <th>用户类型</th>
            <th width="310">操作</th>
        </tr>
        <volist name="list" id="vo">
            <c:forEach items="${teaList}" var="tea" varStatus="status">
            <tr>
                <td style="text-align:left; padding-left:20px;">
                        ${status.index+1}
                </td>
                <td>${tea.realName}</td>
                <td>${tea.userName}</td>
                <td>******</td>
                <td>${tea.parentInfo.realName}</td>
                <td width="10%"><img src="<%=basePath%>person/img.do?url=${tea.userHeadImg}" alt="${tea.realName}"
                                     width="70" height="50"/></td>
                <td><c:choose><c:when
                        test="${tea.enableStatus==0}">已启用</c:when><c:otherwise>已禁用</c:otherwise></c:choose></td>
                <td><font color="#00CC99"><c:if test="${tea.userType==2}">教师</c:if></font></td>
                <td>
                    <div class="button-group"><a class="button border-main"
                                                 href="<%=basePath%>person/updt.do?personId=${tea.userId}"><span
                            class="icon-edit"></span> 修改</a> <a class="button border-red"
                                                                href="<%=basePath%>person/rdPerson.do?personId=${tea.userId}&actionType=3"
                                                                onclick="return del(1,1,1)"><span
                            class="icon-trash-o"></span> 删除</a></div>
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
<div class="panel admin-panel margin-top">
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>新建教师</strong></div>
    <div class="body-content">
        <form method="post" enctype="multipart/form-data" class="form-x" action="<%=basePath%>person/cuPerson.do">
            <input type="hidden" name="actionType" value="0"/>
            <input type="hidden" name="userType" value="2"/>
            <div class="form-group">
                <div class="label">
                    <label>教师账号：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="userName" value="" data-validate="required:请输入账号"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>教师密码：</label>
                </div>
                <div class="field">
                    <input type="password" class="input w50" name="password" value="1234"
                           data-validate="required:请输入密码"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>头像上传：</label>
                </div>
                <div class="field">
                    <input type="file" id="url1" name="headImgFile" style="width:25%; float:left;"
                           value="" data-toggle="hover" data-place="right" data-image=""/>
                    <div class="tipss">图片尺寸：1920*200</div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>教师名称：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="realName"/>
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