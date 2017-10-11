<%@ page import="com.dao.*"%>
<%@ page import="java.util.*" %>
<%@ page import="com.entity.BooksEntity" %>
<%@ page import="java.sql.*" %>
<%@ include file="navbar.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: lvjiawei
  Date: 2017/4/26
  Time: 上午9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/custom.css">
    <script src="assets/js/jquery-3.1.1.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <title>购物车</title>
</head>
<body>

<div class="container">
    <table class="table table-hovered ">
        <%

            if(session.getAttribute("cart") == null){
        %>

        <div class="container" id="emptyCartMsg">
            <hr>
            <hr>
            <h1>您的购物车是空的</h1>
            <p class="lead">可浏览商品并添加到购物车
            </p>
        </div>

        <%
        } else {
            Cart c = (Cart) session.getAttribute("cart");
            c.printItemList();
            HashMap<BooksEntity, Integer> itemlist = c.getCartItems();
            Set<BooksEntity> booklist = itemlist.keySet();
            Collection<Integer> amountlist = itemlist.values();
            Iterator<Integer> ait = amountlist.iterator();
            Iterator<BooksEntity> bit = booklist.iterator();
        %>
        <div class="container" id="emptyCartMsg">
            <hr>
            <hr>
            <h1>您的购物车</h1>
            <p class="lead">请确认并提交
            </p>
        </div>
        <tr>
            <td>书名</td>
            <td>出版社</td>
            <td>作者</td>
            <td>数量</td>
            <td></td>
            <td></td>
            <td></td>
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
            <td><a href="buy2.action?action=remove&bookid=<%=b.getId()%>&buyamount=<%=amt.toString()%>">删除 </a>
            </td>
            <td><a href="buy2.action?action=removeone&bookid=<%=b.getId()%>&buyamount=<%=amt.toString()%>">-1 </a>
            </td>
            <td><a href="buy2.action?action=addone&bookid=<%=b.getId()%>&buyamount=<%=amt.toString()%>">+1 </a>
            </td>
        </tr>
        <%
            }
        %>
        <tr>
            <td>总金额: <%=c.getTotalPrice()%>
            </td>
            <td><a href="buy2.action?action=empty">清空购物车 </a></td>
            <td><a href="buy2.action?action=pay">结算</a></td>
        </tr>
        <%
            }
        %>

    </table>
</div>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
