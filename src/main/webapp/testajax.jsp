<%--
  Created by IntelliJ IDEA.
  User: lvjiawei
  Date: 2017/5/23
  Time: 下午4:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta charset="utf-8">
    <title>adminorder</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" type="text/css" href="assets/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="assets/lib/bootstrap/css/bootstrap-responsive.css">
    <link rel="stylesheet" type="text/css" href="assets/stylesheets/theme.css">
    <link rel="stylesheet" type="text/css" href="assets/lib/font-awesome/css/font-awesome.css">
    <script type="text/javascript" src="assets/lib/jquery-1.8.1.min.js" charset="utf-8"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js" charset="UTF-8"></script>

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
    <script type="text/javascript">

        function showorder(str) {
            var xmlhttp;
            if (str == "") {
                document.getElementById("txtHint").innerHTML = "";
                return;
            }
            if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
                xmlhttp = new XMLHttpRequest();
            } else {// code for IE6, IE5
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
            xmlhttp.onreadystatechange = function() {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    document.getElementById("txtHint").innerHTML = xmlhttp.responseText;
                }
            }
            //xmlhttp.open("GET", "statbyuser.action?user=" + str, true);
            xmlhttp.open("GET","saleby.action?user=" + str,true);
            xmlhttp.send();
        }

        function showsecond(str){
            var xmlhttp;
            if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
                xmlhttp = new XMLHttpRequest();
            } else {// code for IE6, IE5
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
            xmlhttp.onreadystatechange = function() {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    var response = xmlhttp.responseText;

                    var jsonobj = eval("("+response+")");

                    $("#second").find("option").remove();


                    $.each(jsonobj,function(i,item){jQuery('#second').append("<option value="+item+">"+item+"</option>");});

                }
            }
            xmlhttp.open("GET", "showsecond.action?method=" + str, true);
            xmlhttp.send();
        }

        $(function(){
            $('#tableId').on('dblclick','td',function(){
                //console.info($(this).text());
                var oldVal = $(this).text();
                var input = "<input type='text' id='tmpId' value='" + oldVal + "' >";
                $(this).text('');
                $(this).append(input);
                $('#tmpId').focus();
                $('#tmpId').blur(function(){
                    if($(this).val() != ''){
                        oldVal = $(this).val();
                    }

                    $(this).closest('td').text(oldVal);
                });
            });
        });

    </script>
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
                    <li ><a href="<s:url action="adminbook"/>">Books</a></li>
                    <li  class="active"><a href="<s:url action="adminorder"/>">Orders</a></li>
                </ul>
            </div>
        </div>
        <div class="span9">
            <h1 class="page-title">分类统计</h1>

<div class="btn-toolbar">
<label>统计方式:
    <select name="statistic" onchange="showsecond(this.value)" class="font-family: Verdana, Arial, Helvetica, sans-serif;">
        <option value="---">---</option>
        <option value="year">year</option>
        <option value="user">user</option>
        <option value="category">category</option>
        <option value="bookname">bookname</option>
    </select>
</label>

<label>分类:
    <select id="second" name="second" onchange="showorder(this.value)" class="font-family: Verdana, Arial, Helvetica, sans-serif;">
        <option id="opt" value="---">---</option>
    </select>
</label>
</div>

    <div class="well" id="txtHint">Here's the information:</div>
        </div>
    </div>
</div>







</body>
</html>
