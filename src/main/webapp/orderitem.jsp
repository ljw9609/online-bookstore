<%@ page import="com.dao.*"%>
<%@ page import="java.util.*" %>
<%--
  Created by IntelliJ IDEA.
  User: lvjiawei
  Date: 2017/5/6
  Time: 下午3:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="assets/bootstrap/css/bootstrap.css">
    <script src="assets/js/jquery-3.1.1.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <title>Item</title>
</head>
<body>

<div class="container">
    <div class="row">
        <h2>OrderItem</h2>
        <table class="table table-responsive table-hover">
            <tr>
                <td>订单号</td>
                <td>书名</td>
                <td>数量</td>
                <td>价格</td>
            </tr>
            <s:iterator value="#request.itemlist">
                <tr>
                    <td><s:property value="order_id"/></td>
                    <td><s:property value="title"/></td>
                    <td><s:property value="amount"/></td>
                    <td><s:property value="price"/> </td>
                </tr>
            </s:iterator>
        </table>
    </div>
</div>

</body>
</html>
