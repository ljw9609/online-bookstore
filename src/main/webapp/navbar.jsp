<%--
  Created by IntelliJ IDEA.
  User: lvjiawei
  Date: 2017/4/5
  Time: 下午5:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>Bookstore</title>
    <link rel="stylesheet" type="text/css" href="assets/bootstrap/css/bootstrap.css" >
    <script src="assets/js/jquery-3.1.1.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>


</head>
<body>

<!-- Navigation -->
<nav class="navbar navbar-default navbar-fixed-top topnav" role="navigation">
    <div class="container topnav">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand topnav" href="index.action">Online Bookstore</a>
        </div>

        <s:form class="navbar-form navbar-left pull-right" method="get" action="search">
            <span class="hidden-xs">
                <%
                    String name = (String)session.getAttribute("username");
                    if (session.getAttribute("username")!=null){
                        String is_admin = (String)session.getAttribute("is_admin");
                        if("admin".equals(is_admin)){
                %>
                <s:url action="personinfo" var="infolink">
                    <s:param name="username"><%=session.getAttribute("username")%></s:param>
                </s:url>
                <a href="#"><%=session.getAttribute("username")%>&nbsp;&nbsp;</a>
                <s:url action="admin" var="adminuserlink"></s:url>
                <a href="${adminuserlink}">management</a>&nbsp;&nbsp;
                <a href="logout.jsp">Log out</a>&nbsp;&nbsp;
                <%
                        }else{
                %>
                <s:url action="personinfo" var="infolink">
                    <s:param name="username"><%=session.getAttribute("username")%></s:param>
                </s:url>
                <s:url action="base_info" var="profilelink">
                </s:url>
                        <a href="${infolink}"><%=session.getAttribute("username")%>&nbsp;&nbsp;</a>
                        <a href="${profilelink}">Profile&nbsp;&nbsp;</a>
                        <a href="logout.jsp">Sign out</a>&nbsp;&nbsp;

                <s:url action="get_order" var="orderlink">
                    <s:param name="username"><%=session.getAttribute("username")%></s:param>
                </s:url>

                <a href="${orderlink}">Order</a>&nbsp;&nbsp;
                <%
                        }
                    }
                    else{
                %>
                    <a href="signin.jsp">Sign in</a>&nbsp;&nbsp;
                    <a href="signup.jsp">Sign up</a>&nbsp;&nbsp;
                <%
                    }
                %>
                <a href="cart2.jsp">Cart</a>&nbsp;&nbsp;

            </span>

            <div class="form-group hidden-xs">
                <input name="keyword" type="text" class="form-control" placeholder="搜索">
                <button type="submit" class="btn btn-default  hidden-xs">Search</button>

            </div>

        </s:form>

        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

</body>
</html>
