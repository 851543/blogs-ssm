<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<!DOCTYPE html>
<!-- "en"代表英语，"zh-CN"代表中文 -->
<html lang="zh-CN">
<head>
    <!-- 编码格式为utf-8-->
    <meta charset="utf-8">
    <meta http-equiv="Content-Security-Policy">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <link rel="shortcut icon" href="/img/other/login.png">
    <!-- 父jsp的title模块-->
    <title>
        ${options.optionSiteTitle}后台
        <rapid:block name="title"></rapid:block>
    </title>
    <!-- 看不懂自己去对样式 -->
    <link rel="stylesheet" href="/plugin/layui/css/layui.css">
    <link rel="stylesheet" href="/css/back.css">
    <link rel="stylesheet" href="/plugin/font-awesome/css/font-awesome.min.css">
    <!-- 父jsp的header样式模块-->
    <rapid:block name="header-style"></rapid:block>
    <!-- 父jsp的header-script模块-->
    <rapid:block name="header-script"></rapid:block>
</head>
<body>
<div class="layui-layout layui-layout-admin">

    <!----------------------------------------------------------->
    <!-- 头顶导航-->
    <div class="layui-header">
        <div class="layui-logo">
            <!-- 后台首页 -->
            <a href="/after/manage" style="color:#009688;">
                ${options.optionSiteTitle}后台
            </a>
        </div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <!-- 前台-->
            <li class="layui-nav-item"><a href="/" target="_blank">前台</a></li>
            <!-- 新建快捷键-->
            <li class="layui-nav-item">
        <%--    这里全部都是新建，跳到相对于的新建添加页面即可--%>
                <a href="javascript:;">新建</a>
                <dl class="layui-nav-child">
            <c:if test="${sessionScope.user != null && sessionScope.user.userRole == 'admin'}">
                    <dd><a href="/essay/add">文章</a></dd>
                    <dd><a href="/page/add">页面</a></dd>
                    <dd><a href="/essay/categorized">分类</a></dd>
                    <dd><a href="/announcement/add">公告</a></dd>
                    <dd><a href="/link/add">链接</a></dd>
            </c:if>
            <c:if test="${sessionScope.user != null && sessionScope.user.userRole == 'user'}">
                    <dd><a href="/essay/add">文章</a></dd>
            </c:if>
                </dl>
            </li>
        </ul>

        <ul class="layui-nav layui-layout-right">
            <!-- 头像+名字 -->
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="${sessionScope.user.userAvatar}" class="layui-nav-img">
                    ${sessionScope.user.userName}
                </a>
            <%--     展示当前登录的个人信息  --%>
                <dl class="layui-nav-child">
                    <dd><a href="/options/after/profile">基本资料</a></dd>
                </dl>
            <%--   直接退出到登录页面--%>
            </li>
            <!-- 退出登录-->
            <li class="layui-nav-item">
                <a href="/other/logout">退出登录</a>
            </li>
        </ul>
    </div>

    <!----------------------------------------------------------->

    <!-- 左侧导航 -->
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->

            <c:if test="${sessionScope.user != null && sessionScope.user.userRole == 'admin'}">
                <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                    <!-- 文章 -->
                    <li class="layui-nav-item layui-nav-itemed">
                        <a class="" href="javascript:;">文章</a>
                        <dl class="layui-nav-child">
                            <dd><a href="/essay/all">全部文章</a></dd>
                            <dd><a href="/essay/add">写文章</a></dd>
                            <dd><a href="/essay/categorized">全部分类</a></dd>
                            <dd><a href="/essay/label">全部标签</a></dd>
                        </dl>
                    </li>
                    <!-- 页面 -->
                    <li class="layui-nav-item">
                        <a href="javascript:;">页面</a>
                        <dl class="layui-nav-child">
                            <dd><a href="/page/all">全部页面</a></dd>
                            <dd><a href="/page/add">添加页面</a></dd>
                        </dl>
                    </li>
                    <!-- 链接 -->
                    <li class="layui-nav-item">
                        <a class="" href="javascript:;">
                            链接
                        </a>
                        <dl class="layui-nav-child">
                            <dd><a href="/link/all">全部链接</a></dd>
                            <dd><a href="/link/add">添加链接</a></dd>
                        </dl>
                    </li>
                    <!-- 公告 -->
                    <li class="layui-nav-item">
                        <a href="javascript:;">公告</a>
                        <dl class="layui-nav-child">
                            <dd><a href="/announcement/all">全部公告</a></dd>
                            <dd><a href="/announcement/add">添加公告</a></dd>
                        </dl>
                    </li>
                    <!-- 评论 -->
                    <li class="layui-nav-item">
                        <a href="/comments/all">评论</a>
                    </li>
                    <!-- 用户 -->
                    <li class="layui-nav-item">
                        <a href="javascript:;">用户</a>
                        <dl class="layui-nav-child">
                            <dd><a href="/user/all">全部用户</a></dd>
                            <dd><a href="/user/add">添加用户</a></dd>
                        </dl>
                    </li>
                    <!-- 设置 -->
                    <li class="layui-nav-item">
                        <a href="javascript:;">设置</a>
                        <dl class="layui-nav-child">
                            <dd><a href="/setup/all">菜单</a></dd>
                            <dd><a href="/options/all">主要选项</a></dd>
                        </dl>
                    </li>
                </ul>
            </c:if>

            <c:if test="${sessionScope.user != null && sessionScope.user.userRole == 'user'}">
                <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                    <li class="layui-nav-item layui-nav-itemed">
                        <a class="" href="javascript:;">文章</a>
                        <dl class="layui-nav-child">
                            <dd><a href="/essay/all">我的文章</a></dd>
                            <dd><a href="/essay/add">写文章</a></dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item layui-nav-itemed">
                        <a class="" href="javascript:;">评论</a>
                        <dl class="layui-nav-child">
                            <dd><a href="/comments/all">我的评论</a></dd>
                            <dd><a href="/comments/receive">评论我的</a></dd>
                        </dl>
                    </li>
                </ul>
            </c:if>

        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <rapid:block name="content">

            </rapid:block>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © <a href="https://www.baidu.com">百度</a> 不会就百度呀 无语-------_-------- <a href="https://www.csdn.net" target="_blank">CSDN</a>
    </div>

</div>

<!-- 我只知道jquery -->
<script src="/js/jquery.min.js"></script>
<script src="/plugin/layui/layui.all.js"></script>
<script src="/js/back.js"></script>

<rapid:block name="footer-script">

</rapid:block>

<script>

    //给文本编辑器的iframe引入代码高亮的css
    $("iframe").contents().find("head").append("<link rel=\"stylesheet\" href=\"/css/highlight.css\">\n");

</script>

</body>
</html>
