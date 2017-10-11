
<%--
  Created by IntelliJ IDEA.
  User: lvjiawei
  Date: 2017/4/19
  Time: 下午9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ include file="navbar.jsp"%>
<html>
<head>
    <title>Search</title>
</head>
<body>


<div class="container">
    <div class="row">
        <h1 class="lead" id="searchResultTitle"><s:property value="getKeyword()"/> <strong>的搜索结果</strong>
        </h1>
    </div>

    <table class="table table-responsive table-hover">
        <tr>
            <td><strong>书名</strong></td>
            <td><strong>价格</strong></td>
            <td><strong>出版社</strong></td>
        </tr>
        <s:iterator value="#request.list">
        <tr>
            <td>
                <s:url action="show_detail" var="detaillink">
                    <s:param name="bookid"><s:property value="id"/></s:param>
                </s:url>
                <a href="${detaillink}"><s:property value="title"/>
            </a></td>
            <td><s:property value="price"/>
            </td>
            <td><s:property value="press"/>
            </td>
        </tr>
        </s:iterator>



</div>

</body>
</html>
