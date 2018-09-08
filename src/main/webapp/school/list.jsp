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
        <button type="button" class="button border-yellow" onclick="window.location.href='#add'"><span class="icon-plus-square-o"></span> 新建机构</button>
    </div>
    <table class="table table-hover text-center">
        <tr>
            <th width="5%">序号</th>
            <th>培训机构名称</th>
            <th>培训机构账号</th>
            <th>机构商标</th>
            <th width="10%">是否禁用</th>
            <th>账号类型</th>
            <th width="250">操作</th>
        </tr>
        <c:forEach items="${schoolList}" var="school" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${school.realName}</td>
                <td>${school.userName}</td>
                <td><img src="<%=basePath%>person/img.do?url=${school.userHeadImg}" width="70" height="50"/></td>
                <td><c:choose><c:when
                        test="${school.enableStatus==0}">已启用</c:when><c:otherwise>已禁用</c:otherwise></c:choose></td>
                <td><c:if test="${school.userType==1}">培训机构</c:if></td>
                <td>
                    <div class="button-group">
                        <a type="button" class="button border-main" href="<%=basePath%>person/updt.do?personId=${school.userId}"><span class="icon-edit"></span>修改</a>
                        <a class="button border-red"
                           href="<%=basePath%>person/rdPerson.do?personId=${school.userId}&actionType=3"
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

        }else{
            return false;
        }
    }
</script>
<div class="panel admin-panel margin-top">
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>新建机构</strong></div>
    <div class="body-content">
        <form method="post" enctype="multipart/form-data" class="form-x" action="<%=basePath%>person/cuPerson.do">
            <input type="hidden" name="actionType" value="0"/>
            <input type="hidden" name="userType" value="1"/>
            <div class="form-group">
                <div class="label">
                    <label>机构账号：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="userName" value="" data-validate="required:请输入账号"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>机构密码：</label>
                </div>
                <div class="field">
                    <input type="password" class="input w50" name="password" value="1234" data-validate="required:请输入密码"/>
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
                    <label>机构名称：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="realName"/>
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