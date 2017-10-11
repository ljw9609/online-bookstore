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
    <title>editbook</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" type="text/css" href="assets/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="assets/lib/bootstrap/css/bootstrap-responsive.css">
    <link rel="stylesheet" type="text/css" href="assets/stylesheets/theme.css">
    <link rel="stylesheet" type="text/css" href="assets/lib/font-awesome/css/font-awesome.css">
    <script type="text/javascript" src="assets/lib/jquery-1.8.1.min.js"></script>
    <!-- Demo page code -->

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




<div class="container-fluid">
    <div class="row-fluid">
        <div class="span9">
                <h1 class="page-title">Edit Book</h1>
                <div class="well">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#home" data-toggle="tab">Profile</a></li>
                    </ul>
                    <div class="user-image">
                        <img class="img-show" src="imagebyId.action?filename=<s:property value="#bookprofile.image_id"/>" alt="hello">
                    </div>
                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane active in" id="home">
                            <s:form action="editbook" method="post" enctype="multipart/form-data" >
                                <label>isbn</label>
                                <input type="text" name="isbn" value="<s:property value="#bookprofile.isbn"/>" class="input-xlarge">
                                <label>title</label>
                                <input type="text" name="title" value="<s:property value="#bookprofile.title"/>" class="input-xlarge">
                                <label>press</label>
                                <input type="text" name="press" value="<s:property value="#bookprofile.press"/>" class="input-xlarge">
                                <label>author</label>
                                <input type="text" name="author" value="<s:property value="#bookprofile.author"/>" class="input-xlarge">
                                <label>amount</label>
                                <input type="text" name="amount" value="<s:property value="#bookprofile.amount"/>" class="input-xlarge">
                                <label>price</label>
                                <input type="text" name="price" value="<s:property value="#bookprofile.price"/>" class="input-xlarge">
                                <label>category</label>
                                <input type="text" name="categoryid" value="<s:property value="#bookprofile.categoryid"/>" class="input-xlarge">
                                <label>introduction</label>
                                <textarea name="intro" rows="3" class="input-xlarge"><s:property value="#bookprofile.intro"/></textarea>
                                <label for="myFile" class="col-sm-2 control-label">File input</label>
                                <div class="col-sm-4">
                                    <input type="file" name="myFile"/>
                                    <p class="help-block">上传图书封面</p>
                                </div>
                                <div class="btn-toolbar">
                                    <button type="submit" class="btn">Save</button>
                                    <s:url action="deletebook" var="deletebooklink">
                                        <s:param name="bookid"><s:property value="#bookprofile.id"/></s:param>
                                    </s:url>
                                    <a href="${deletebooklink}" class="btn">Delete</a>
                                </div>
                            </s:form>
                        </div>
                    </div>

                </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var msg="${requestScope.tipMessage}";
    if(msg!=""){
        alert(msg);
    }
</script>

<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="assets/lib/bootstrap/js/bootstrap.js"></script>


</body>
</html>
