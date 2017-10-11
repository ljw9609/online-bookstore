<%--
  Created by IntelliJ IDEA.
  User: lvjiawei
  Date: 2017/5/6
  Time: 上午11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <title>Person information</title>

    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/form-elements.css">
    <link rel="stylesheet" href="assets/css/style.css">

    <link rel="shortcut icon" href="assets/ico/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">

    <script src="assets/js/jquery-1.11.1.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/jquery.backstretch.min.js"></script>
    <script src="assets/js/scripts.js"></script>
</head>
<body>

<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1><strong>Personal Information</strong></h1>
                    <div class="description">
                        <p>
                            You can revise your information!
                        </p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-top">
                        <div class="form-top-left">
                            <h3>Glad to see you!</h3>
                            <p>Your username,password and email</p>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-key"></i>
                        </div>
                    </div>
                    <div class="form-bottom">
                        <s:iterator value="#request.list1">
                        <s:form action="change_info" method="post" class="login-form">
                            <div class="form-group">
                                <label class="sr-only" for="form-username">Username</label>
                                <input type="text" name="username" value="<s:property value="username"/>" readonly="readonly" class="form-username form-control" id="username">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">Password</label>
                                <input type="password" name="oldpassword" placeholder="Old password..." class="form-password form-control" id="oldpassword">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">Password</label>
                                <input type="password" name="newpassword" placeholder="New password..." class="form-password form-control" id="newpassword">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-username">Username</label>
                                <input type="text" name="email" value="<s:property value="email"/>" class="form-username form-control" id="email">
                            </div>
                            <button type="submit" class="btn">Change!</button>
                        </s:form>
                        </s:iterator>
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

</body>
</html>
