<%@ page import="com.dao.*" %>
<%@ page import="com.entity.*"%>
<%@ page import="java.util.*"%>
<%@ include file="navbar.jsp"%>

<%--
  Created by IntelliJ IDEA.
  User: lvjiawei
  Date: 2017/4/27
  Time: 下午4:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/custom.css">
    <script src="assets/js/jquery-3.1.1.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <title>订单提交</title>
</head>
<body>

<div class="container">
    <hr>
    <hr>
    <h1>请确认您的购物车</h1>
    <table class="table table-hovered">
        <%
            if(session.getAttribute("cart") == null){
        %>

        <h3>购物车为空</h3>
        <%
        }else{
            Cart c = (Cart) session.getAttribute("cart");
            c.printItemList();
            HashMap<BooksEntity, Integer> itemlist = c.getCartItems();
            Set<BooksEntity> booklist = itemlist.keySet();
            Collection<Integer> amountlist = itemlist.values();
            Iterator<Integer> ait = amountlist.iterator();
            Iterator<BooksEntity> bit = booklist.iterator();
        %>
        <tr>
            <td>书名</td>
            <td>出版社</td>
            <td>作者</td>
            <td>数量</td>
            <td>价格</td>
        </tr>
        <%
            while (ait.hasNext() && bit.hasNext()) {
                BooksEntity b = bit.next();
                Integer amt = ait.next();
        %>
        <tr>
            <td><%=b.getTitle()%>
            </td>
            <td><%=b.getPress()%>
            </td>
            <td><%=b.getTitle()%>
            </td>
            <td><%=amt%>
            </td>
            <td><%=b.getPrice() * amt%>
            </td>
        </tr>
        <%
            }
        %>
        <tr>
            <td>总金额: <%=c.getTotalPrice()%>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>
<div class="container">
    <hr>
    <hr>
    <h1>请确认订单信息</h1>
    <form class="form-inline" action="submit_order2" method="post">
        <input hidden="hidden" name="username" value="<%=session.getAttribute("username")%>">
        <div class="form-group">
            <label for="exampleInputEmail1">收件人</label>
            <input class="form-control" id="exampleInputEmail1" placeholder="" name="receiver">
            <span></span>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">地址</label>
            <input class="form-control" id="exampleInputPassword1" placeholder="" name="address">
            <span></span>
        </div>
        <div class="form-group">
            <label for="exampleInputFile">联系电话</label>
            <input class="form-control" id="exampleInputFile" placeholder="" name="mobile">
            <span></span>
        </div>
        <button type="submit" class="btn btn-default">提交</button>
    </form>


</div>

</body>
</html>
