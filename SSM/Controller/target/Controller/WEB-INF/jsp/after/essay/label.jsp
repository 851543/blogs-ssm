<%--全部标签--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

<rapid:override name="title">
    - 标签列表
</rapid:override>
<rapid:override name="header-style">
    <style>
        /*覆盖 layui*/
        .layui-input-block {
            margin:0px 10px;
        }
        .layui-table {
            margin-top: 0;
        }
        .layui-col-md4 {
            padding:10px;
        }
        .layui-col-md8 {
            padding:10px;
        }
        .layui-btn {
            margin: 2px 0!important;
        }
    </style>
</rapid:override>

<rapid:override name="content">

    <blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
              <a href="/admin">首页</a>
              <a href="/admin/category">标签列表</a>
              <a><cite>添加分类</cite></a>
        </span>
    </blockquote>
    <div class="layui-row">
        <div class="layui-col-md4">
                                                                        <%--  表单提交--%>
            <form class="layui-form"  method="post" id="myForm" action="/essay/addSubmit">
                <div class="layui-form-item">
                        <%-- 标题--%>
                    <div class="layui-input-block">
                        <strong>添加标签</strong>
                    </div>
                        <%--开始输入增加的标签名称 --%>
                    <div class="layui-input-block">
                        名称 <span style="color: #FF5722; ">*</span>
                        <%--name对应的为实体类，否则无法识别，placeholder为输入框的提示内容 --%>
                        <input type="text" name="tagName" placeholder="请输入标签名称" autocomplete="off" class="layui-input" required>
                    </div>
                    <br>
                    <div class="layui-input-block">
                        标签描述
                        <input type="text" name="tagDescription" placeholder="请输入标签描述" autocomplete="off" class="layui-input" >
                    </div>
                    <br>
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-filter="formDemo" type="submit">添加</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="layui-col-md8" >
            <table class="layui-table" >
                <colgroup>
                    <col width="300">
                    <col width="100">
                    <col width="100">
                    <col width="100">
                    <col width="50">
                    <col width="50">
                </colgroup>
                <thead>
                <tr>
                    <th>名称</th>
                    <th>文章数</th>
                    <th>操作</th>
                    <th>ID</th>

                </tr>
                </thead>
                <tbody>
                <c:forEach var="d" items="${list}" varStatus="lst">
                <tr>
                    <td>
                        <a href="#" target="_blank">${d.tagName}</a>
                    </td>
                    <td>
                        <a href="#" target="_blank">${d.tagDescription}</a>
                    </td>
                    <td>
                        <a href="/essay/tag/${d.tagId}" class="layui-btn layui-btn-mini">编辑</a>
<%--                        <c:if test="${c.articleCount==0}">--%>
                            <a href="/essay/del/${d.tagId}" class="layui-btn layui-btn-danger layui-btn-mini" onclick="return confirmDelete()">删除</a>
<%--                        </c:if>--%>
                    </td>
                    <td>${d.tagId}</td>
                </tr>
                </c:forEach>

                </tbody>
            </table>
            <blockquote class="layui-elem-quote layui-quote-nm">
                温馨提示：
                <ul>
                    <li>分类最多只有两级，一级分类pid=0，二级分类pid=其父节点id</li>
                    <li>如果该分类包含文章，将不可删除</li>
                </ul>
            </blockquote>
        </div>
    </div>
</rapid:override>
<rapid:override name="footer-script">

</rapid:override>
<%@include file="../after.jsp" %>
<%--<%@ include file="../Public/framework.jsp"%>--%>



