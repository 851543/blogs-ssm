<%--
  Created by IntelliJ IDEA.
  User: 611
  Date: 2023/5/22
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.security.MessageDigest" %>
<%@ page import="java.security.NoSuchAlgorithmException" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

<rapid:override name="title">
<%--    回复评论--%>
</rapid:override>
<rapid:override name="header-style">
    <style>
        /*覆盖 layui*/

        .layui-btn {
            margin: 2px 0 !important;
        }
    </style>
</rapid:override>

<rapid:override name="content">

    <blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
              <a href="/after/manage">首页</a>
              <a href="/comments/all">评论列表</a>
              <a><cite>回复评论</cite></a>
        </span>
    </blockquote>
    <form class="layui-form"  method="post" id="myForm" action="/comments/insert">
<%--        <c:forEach var="d" items="${comment}" varStatus="=lst">--%>
        <input type="hidden" name="commentPid" value="${comment.commentId}">
        <input type="hidden" name="commentPname" value="${comment.commentAuthorName}">
        <input type="hidden" name="commentArticleId" value="${comment.commentArticleId}">
<%--</c:forEach>--%>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">原内容</label>
            <div class="layui-input-block">
                <textarea  class="layui-textarea" disabled>${comment.commentContent}</textarea>
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">我的回复</label>
            <div class="layui-input-block">
                <textarea name="commentContent"  class="layui-textarea"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="demo1">回复</button>
                <button type="reset" class="layui-btn layui-btn-primary" >重置</button>
            </div>
        </div>

    </form>
</rapid:override>
<rapid:override name="footer-script">
    <script>

    </script>
</rapid:override>
<%--这里是重写左边菜单框架--%>
<%@include file="../after.jsp" %>

