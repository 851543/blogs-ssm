<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<rapid:override name="title">
    - 页面列表
</rapid:override>
<rapid:override name="header-style">
    <style>
    </style>
</rapid:override>

<rapid:override name="content">
    <!-- 小页面导航 -->
    <blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
          <a href="/after/manage">首页</a>
          <a><cite>页面列表</cite></a>
        </span>
    </blockquote>
    <!-- 全部页面数据 -->
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>页面列表</legend>
    </fieldset>
    <!-- 全部页面数据表单 -->
    <form id="pageForm" method="post">
        <table class="layui-table">
            <colgroup>
                <col width="50">
                <col width="50">
                <col width="100">
                <col width="200">
                <col width="50">
                <col width="100">
            </colgroup>
            <thead>
            <tr>
                <th>id</th>
                <th>key</th>
                <th>标题</th>
                <th>内容</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageList}" var="p">
                <c:if test="${p.pageStatus!=2}">
                    <tr>
                        <td>${p.pageId}</td>
                        <td>${p.pageKey}</td>
                        <td>
                                ${p.pageTitle}
                        </td>
                        <td>
                            <a href="/${p.pageKey}"
                               target="_blank">
                                    ${fn:substring(p.pageContent, 0,20 )}

                            </a>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${p.pageStatus==1}">
                                    显示
                                </c:when>
                                <c:otherwise>
                                    <span style="color: #FF5722;">
                                        隐藏
                                    </span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a href="/page/edit/${p.pageId}"
                               class="layui-btn layui-btn-mini">编辑</a>
                            <a href="/page/delete/${p.pageId}"
                               class="layui-btn layui-btn-danger layui-btn-mini" onclick="return confirmDelete()">删除</a>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </form>

    <!-- 写死了 -->
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>自定义页面</legend>
    </fieldset>
    <div class="layui-form">
        <table class="layui-table" style="width: 40%;">
            <colgroup>
                <col width="150">
                <col width="150">
                <col width="200">
                <col>
            </colgroup>
            <thead>
            <tr>
                <th>别名</th>
                <th>标题</th>
                <th>内容</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageList}" var="p">
                <c:if test="${p.pageStatus==2}">
                    <tr>
                        <td>${p.pageKey}</td>
                        <td>${p.pageTitle}</td>
                        <td><a href="/${p.pageKey}" target="_blank">点击查看</a></td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>


    <blockquote class="layui-elem-quote layui-quote-nm">
        温馨提示： <br>
        数据是这个时代最重要的东西,删除之前请你考虑一下,他的来之不易.
    </blockquote>

</rapid:override>
<rapid:override name="footer-script">
    <script>


    </script>
</rapid:override>
<!-- 继承after.jsp -->
<%@ include file="../after.jsp" %>
