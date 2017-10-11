<%--
  Created by IntelliJ IDEA.
  User: lvjiawei
  Date: 2017/5/24
  Time: 下午1:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="navbar.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="assets/bootstrap/css/bootstrap.css">
    <script src="assets/js/jquery-3.1.1.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <title>分类</title>

    <style type="text/css">
        .mydiv{
            background-color: #fbfbfb;
            border: 1px solid #fbf4f4;
            text-align: left;
            line-height: 20px;
            font-size: 16px;
            font-weight: bold;
            z-index: 999;
            width: 700px;
            height: 450px;
            left: 10%; /*FF IE7*/
            top: 10%; /*FF IE7*/
            margin-left: 200px !important; /*FF IE7 该值为本身宽的一半 */
        }
    </style>

    <script type="text/javascript">
        function showDiv(title) {
            $('#popDiv').removeClass().addClass("mydiv").css("display","block").css("background","ff9");

            var xmlhttp;
            if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
                xmlhttp = new XMLHttpRequest();
            } else {// code for IE6, IE5
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
            xmlhttp.onreadystatechange = function() {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    //document.getElementById("popDiv").innerHTML = xmlhttp.responseText;
                    var response = xmlhttp.responseText;
                    var jsonobj = eval("("+response+")");

                    $("#popDiv").empty();
                    var content = "";
                    $.each(jsonobj,function(n,item){
                        var str1 = "<p>ISBN: " + item.isbn + "</p>";
                        var str2 = "<p>书名: <a href='show_detail.action?bookid="+item.id+"'>"+item.title+"</a></p>";
                        var str3 = "<p>作者: " + item.author + "</p>";
                        var str4 = "<p>出版社: " + item.press + "</p>";
                        var str5 = "<p>价格: " + item.price + "</p>";
                        var str6 = "<p>简介: " + item.intro + "</p>";
                        content += str2 + str1 + str3 + str4 + str5 + str6;
                    });

                    $("#popDiv").append(content);
                    var str7 = "<a href='javascript:closeDiv()'>返回</a>";
                    $("#popDiv").append(str7);

                }
            }
            xmlhttp.open("GET", "showbookinfo.action?title=" + title, true);
            xmlhttp.send();


        }


        function closeDiv() {
            $('#popDiv').css("display","none");

        }
    </script>
</head>
<body>


<div class="container">
    <div class="row">
        <hr>
        <hr>
        <hr>
        <h1><s:property value="getCatelog()"/>
        </h1>
        <table class="table table-responsive table-hover" >
            <tr>
                <td><strong>书名</strong></td>
                <td><strong>作者</strong></td>
                <td><strong>出版社</strong></td>
                <td><strong>价格</strong></td>
            </tr>
            <s:iterator value="#request.list">
            <tr>
                <td>
                    <a href="javascript:showDiv('<s:property value="title"/>')"><s:property value="title"/>
                    </a></td>
                <td><span style="vertical-align: middle"><s:property value="author"/></span>
                </td>
                <td><s:property value="press"/>
                </td>
                <td><s:property value="price"/>
                </td>
            </tr>
            </s:iterator>
        </table>
    </div>
</div>

<div id="popDiv" class="mydiv" style="display: none;">
    <a href="javascript:closeDiv()">关闭窗口</a>
</div>

</body>
</html>
