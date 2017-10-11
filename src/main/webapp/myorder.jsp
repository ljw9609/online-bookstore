<%@page import="com.dao.*" %>
<%@page import="com.entity.*"%>
<%@ include file="navbar.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: lvjiawei
  Date: 2017/4/27
  Time: 下午4:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Orderlist</title>
    <link rel="stylesheet" type="text/css" href="assets/bootstrap/css/bootstrap.css">
    <script src="assets/js/jquery-3.1.1.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>


<script>
    function show_detail(orderid) {
        var id = String.valueOf(orderid);
        var url = "show_item.action?orderid="+orderid+"";
        window.showModalDialog(url, window, 'help:no;dialogWidth:650px;dialogHeight:500px');
    }
</script>

<div class="container">
    <div class="row">
        <hr>
        <hr>
        <hr>
        <h2>Orderlist</h2>
        <table class="table table-responsive table-hover">
            <tr>
                <td><strong>id</strong></td>
                <td><strong>username</strong></td>
                <td><strong>receiver</strong></td>
                <td><strong>address</strong></td>
                <td><strong>mobile</strong></td>
                <td><strong>total price</strong></td>
                <td><strong>date</strong></td>
            </tr>

            <s:iterator value="#request.list">
                <tr>
                    <td><a href="javascript:void(0);" onclick="show_detail(<s:property value="id"/>)"><s:property value="id"/></a></td>
                    <td><s:property value="username"/></td>
                    <td><s:property value="receiver"/></td>
                    <td><s:property value="address"/></td>
                    <td><s:property value="mobile"/></td>
                    <td><s:property value="total_price"/></td>
                    <td><s:property value="orderdate"/></td>
                </tr>
            </s:iterator>

        </table>

    </div>
</div>
</body>
</html>
