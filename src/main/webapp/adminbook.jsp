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
    <title>adminbook</title>
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
                    <li><a href="<s:url action="admin"/>">Home</a></li>
                    <li ><a href="<s:url action="adminuser"/>">Users</a></li>
                    <li class="active"><a href="<s:url action="adminbook"/>">Books</a></li>
                    <li ><a href="<s:url action="adminorder"/>">Orders</a></li>
                </ul>
            </div>
        </div>
        <div class="span9">
            <h1 class="page-title">Books</h1>
            <div class="btn-toolbar">
                <a href="addbook.jsp" role="button" class="btn"><i class="icon-plus"></i> New Book</a>
                <div class="btn-group">
                </div>
            </div>
            <div class="well">
                <table class="table">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>isbn</th>
                        <th>title</th>
                        <th>author</th>
                        <th>press</th>
                        <th>price</th>
                        <th>amount</th>
                        <th style="width: 26px;"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="#request.booklist">
                        <tr>
                            <td><s:property value="id"/></td>
                            <td><s:property value="isbn"/></td>
                            <td><s:property value="title"/></td>
                            <td><s:property value="author"/></td>
                            <td><s:property value="press"/></td>
                            <td><s:property value="price"/></td>
                            <td><s:property value="amount"/></td>
                            <td><s:property value="categoryid"/></td>
                            <td>
                                <s:url action="editbook_show" var="editbookshowlink">
                                    <s:param name="bookid"><s:property value="id" /></s:param>
                                </s:url>
                                <a href="${editbookshowlink}" role="button"><i class="icon-pencil"></i></a>
                            </td>
                            <td>
                                <s:url action="deletebook" var="deletebooklink">
                                    <s:param name="bookid"><s:property value="id"/></s:param>
                                </s:url>
                                <a href="${deletebooklink}" role="button"><i class="icon-remove"></i></a>
                            </td>
                        </tr>
                    </s:iterator>

                    </tbody>
                </table>
            </div>
            <div class="pagination">
                <ul>
                    <li><a href="#">Prev</a></li>
                    <li><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">Next</a></li>
                </ul>
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
