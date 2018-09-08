<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title>培训机构</title>
    <link rel="stylesheet" href="<%=basePath%>css/pintuer.css">
    <link rel="stylesheet" href="<%=basePath%>css/admin.css">
    <script src="<%=basePath%>js/jquery.js"></script>
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
    <div class="logo margin-big-left fadein-top">
        <h1><img src="<%=basePath%>person/img.do?url=${person.userHeadImg}" class="radius-circle rotate-hover"
                 height="50" alt=""/>${person.realName}</h1>
    </div>
    <div class="head-l"><a class="button button-little bg-green" href="<%=basePath%>person/shouye.do"
                           target="_blank"><span
            class="icon-home"></span> 首页</a> &nbsp;&nbsp;<a class="button button-little bg-red"
                                                            href="<%=basePath%>person/exit.do"><span
            class="icon-power-off"></span> 退出</a></div>
</div>
<div class="leftnav">
    <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
    <h2><span class="icon-user"></span>基本设置</h2>
    <ul style="display:block">
        <li><a href="../password.jsp" target="right"><span class="icon-caret-right"></span>修改密码</a></li>
    </ul>
    <h2><span class="icon-pencil-square-o"></span>各项管理</h2>
    <ul>
        <li><a href="<%=basePath%>person/clalist.do?schoolId=${person.userId}" target="right"><span
                class="icon-caret-right"></span>班级管理</a></li>
        <li><a href="<%=basePath%>person/tealist.do?url=teacher/tealist" target="right"><span
                class="icon-caret-right"></span>教师管理</a></li>
        <li><a href="<%=basePath%>person/coulist.do" target="right"><span class="icon-caret-right"></span>课程管理</a></li>
        <li><a href="<%=basePath%>column/list.do?url=column/list" target="right"><span
                class="icon-caret-right"></span>栏目管理</a></li>
        <li><a href="<%=basePath%>category/list.do" target="right"><span
                class="icon-caret-right"></span>课程类型管理</a></li>
        <li><a href="<%=basePath%>cct/cctlist.do" target="right"><span class="icon-caret-right"></span>班级课程安排</a></li>
        <li><a href="<%=basePath%>cct/ctlist.do" target="right"><span class="icon-caret-right"></span>课程教师安排</a></li>
    </ul>
</div>
<script type="text/javascript">
    $(function () {
        $(".leftnav h2").click(function () {
            $(this).next().slideToggle(200);
            $(this).toggleClass("on");
        })
        $(".leftnav ul li a").click(function () {
            $("#a_leader_txt").text($(this).text());
            $(".leftnav ul li a").removeClass("on");
            $(this).addClass("on");
        })
    });
</script>
<ul class="bread">
    <li><a href="<%=basePath%>welcome.jsp" target="right" class="icon-home"> 首页</a></li>
    <li><a href="#" id="a_leader_txt">网站信息</a></li>
    <li><b>当前语言：</b><span style="color:red;">中文</span>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;切换语言：<a href="#">中文</a> &nbsp;&nbsp;<a href="#">英文</a></li>
</ul>
<div class="admin">
    <iframe scrolling="auto" rameborder="0" src="<%=basePath%>welcome.jsp" name="right" width="100%"
            height="100%"></iframe>
</div>
</body>
</html>