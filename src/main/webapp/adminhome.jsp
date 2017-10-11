<%--
  Created by IntelliJ IDEA.
  User: lvjiawei
  Date: 2017/5/9
  Time: 下午4:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <meta charset="utf-8">
    <title>adminuser</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" type="text/css" href="assets/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="assets/lib/bootstrap/css/bootstrap-responsive.css">
    <link rel="stylesheet" type="text/css" href="assets/stylesheets/theme.css">
    <link rel="stylesheet" type="text/css" href="assets/lib/font-awesome/css/font-awesome.css">
    <script type="text/javascript" src="assets/lib/jquery-1.8.1.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>

    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
    </style>
</head>
<body>

<div class="navbar">
    <div class="navbar-inner">
        <div class="container-fluid">
            <ul class="nav pull-right">

                <li id="fat-menu" class="dropdown">
                    <a href="#" id="drop3" role="button" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="icon-user"></i> admin
                        <i class="icon-caret-down"></i>
                    </a>

                    <ul class="dropdown-menu">
                        <li class="divider"></li>
                        <li><a tabindex="-1" href="logout.jsp">Logout</a></li>
                    </ul>
                </li>

            </ul>
            <a class="brand" href="index.action"><span class="first">Online</span> <span class="second">Bookstore</span></a>
        </div>
    </div>
</div>


<div class="container-fluid">

    <div class="row-fluid">
        <div class="span3">
            <div class="sidebar-nav">
                <div class="nav-header" data-toggle="collapse" data-target="#dashboard-menu"><i class="icon-dashboard"></i>Dashboard</div>
                <ul id="dashboard-menu" class="nav nav-list collapse in">
                    <li class="active"><a href="<s:url action="admin"/>">Home</a></li>
                    <li ><a href="<s:url action="adminuser"/>">Users</a></li>
                    <li ><a href="<s:url action="adminbook"/>">Books</a></li>
                    <li ><a href="<s:url action="adminorder"/>">Orders</a></li>
                </ul>
            </div>
        </div>
        <div class="span9">
            <script type="text/javascript" src="assets/lib/jqplot/jquery.jqplot.min.js"></script>
            <div class="stats">
                <p class="stat"><span class="number"><s:property value="useramount"/> </span>users</p>
                <p class="stat"><span class="number"><s:property value="bookamount"/></span>books</p>
                <p class="stat"><span class="number"><s:property value="orderamount"/></span>orders</p>
            </div>
            <h1 class="page-title">Dashboard</h1>

            <div class="row-fluid">
                <div class="block span6">
                    <div class="block-heading" data-toggle="collapse" data-target="#tablewidget">Users</div>
                    <div id="tablewidget" class="block-body collapse in">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Username</th>
                                <th>Email</th>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#request.userlist">
                                <tr>
                                    <td><s:property value="id"/></td>
                                    <td><s:property value="username"/></td>
                                    <td><s:property value="email"/></td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                        <p><a href="adminuser.action">More...</a></p>
                    </div>
                </div>
                <div class="block span6">
                    <div class="block-heading" data-toggle="collapse" data-target="#tablewidget2">Books</div>
                    <div id="tablewidget2" class="block-body collapse in">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>title</th>
                                <th>author</th>
                                <th>price</th>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#request.booklist">
                                <tr>
                                    <td><s:property value="title"/></td>
                                    <td><s:property value="author"/></td>
                                    <td><s:property value="price"/></td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                        <p><a href="adminbook.action">More...</a></p>
                    </div>
                </div>
            </div>

            <div class="row-fluid">

                <div class="block span6">
                    <div class="block-heading" data-toggle="collapse" data-target="#tablewidget3">Orders</div>
                    <div id="tablewidget3" class="block-body collapse in">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>username</th>
                                <th>orderdate</th>
                                <th>Total Price</th>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#request.orderlist">
                                <tr>
                                    <td><s:property value="id"/></td>
                                    <td><s:property value="username"/></td>
                                    <td><s:property value="orderdate"/></td>
                                    <td><s:property value="total_price"/></td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                        <p><a href="adminorder.action">More...</a></p>
                    </div>
                </div>
            </div>




        </div>
    </div>



    <footer>
        <hr>

        <p>&copy; SE228 </p>
    </footer>




    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="assets/lib/bootstrap/js/bootstrap.js"></script>


</body>
</html>
