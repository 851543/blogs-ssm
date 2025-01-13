<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

<rapid:override name="title">
    - 评论列表
</rapid:override>
<rapid:override name="header-style">
    <style>
        /*覆盖 layui*/
        .layui-table {
            margin-top: 0;
        }
    </style>
</rapid:override>

<rapid:override name="content">
    <blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
              <a href="/after/manage">首页</a>
              <a><cite>评论列表</cite></a>
        </span>
    </blockquote>
    <div class="layui-tab layui-tab-card">
        <table class="layui-table" lay-even lay-skin="nob">
            <colgroup>
                <col width="50">
                <col width="100">
                <col width="300">
                <col width=200">
                <col width="150">
            </colgroup>
            <thead>
            <tr>
                <th>ID</th>
                <th>作者</th>
                <th>评论内容</th>
                <th>回复至</th>
                <th>提交于</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageInfo.list}" var="c">
                <tr>
                    <td>${c.commentId}</td>
                    <td>
                        <img src="${c.commentAuthorAvatar}" alt="" width="64px">
                        <strong>${c.commentAuthorName}</strong>
                        <br>
                            ${c.commentAuthorUrl} <br>
                            ${c.commentAuthorEmail} <br>
                            ${c.commentIp}
                    </td>
                    <td class="dashboard-comment-wrap">
                        <c:if test="${c.commentPid!=0}">
                            <span class="at">@ </span><a href="${c.commentAuthorUrl}">${c.commentPname}</a>
                        </c:if>
                            ${c.commentContent}
                        <div class="row-actions">
                                     <span class="">
                                        <a href="/comments/select/${c.commentId}">
                                            回复
                                        </a>
                                     </span>

                            <c:if test="${sessionScope.user.userRole == 'admin'}">
                                        <span class=""> |
                                            <a href="/comments/sel/${c.commentId}">编辑</a>
                                         </span>
                            </c:if>

                            <span class=""> |
                                        <a href="javascript:void(0)" onclick="deleteComment(${c.commentId})">删除</a>
                                     </span>
                        </div>
                    </td>
                    <td>
                        <a href="/article/${c.article.articleId}"
                           target="_blank">${c.article.articleTitle}</a>
                    </td>
                    <td>
                        <fmt:formatDate value="${c.commentCreateTime}" pattern="yyyy年MM月dd日 HH:dd:ss"/>
                    </td>


                </tr>
            </c:forEach>
            </tbody>

        </table>

        <div id="nav" style="">
            <%@ include file="../paging.jsp" %>
        </div>
    </div>


</rapid:override>

<%@ include file="../after.jsp" %>
