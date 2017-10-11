
<%--
  Created by IntelliJ IDEA.
  User: lvjiawei
  Date: 2017/4/4
  Time: 下午8:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <title>Sign up</title>

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

<script>
    $(document).ready(function(){
        $("#register_username").focus();
        $("#register_username").keyup(function(){
            var username = $("#register_username").val();
            if(username.length==0){
                $("#available_status1").html("<span style='color:red'>用户名不能为空</span>");
            }
        });
        $("#register_username").blur(function(){
            var username = $("#register_username").val();
            if(username.length==0){
                $("#available_status1").html("<span style='color:red'>用户名不能为空</span>");
            }else {
                var param = $("#register_username").serialize();
                $.ajax({
                    url: "checkUsernameAvailable",
                    type: "get",
                    data: param,
                    dataType: "text",
                    success: function (data) {
                        var response = eval("(" + data + ")");
                        //alert(response.result);
                        if (response.result == true) {
                            $("#available_status1").html("<span style='color:red'>该用户名已存在</span>");
                        }
                        if (response.result == false) {
                            $("#available_status1").html("<span style='color:green'>该用户名可用</span>");
                        }
                    }
                });
            }
        });
        $("#register_password").focus();
        $("#register_password").keyup(function(){
            var password = $("#register_password").val();
            if(password.length < 6 || password.length > 12){
                $("#available_status2").html("<span style='color:red'>密码在6-12位</span>");
            }else{
                $("#available_status2").html("<span style='color:green'>密码格式正确</span>");
            }
        });
        $("#register_password").blur(function(){
            var password = $("#register_password").val();
            if(password.length < 6 || password.length > 12){
                $("#available_status2").html("<span style='color:red'>密码在6-12位</span>");
            }else{
                $("#available_status2").html("<span style='color:green'>密码格式正确</span>");
            }
        });
        $("#register_confirmpassword").focus();
        $("#register_confirmpassword").keyup(function(){
            var confirmpassword = $("#register_confirmpassword").val();
            var password = $("#register_password").val();
            if(confirmpassword != password){
                $("#available_status3").html("<span style='color:red'>两次密码不一致</span>");
            }else{
                $("#available_status3").html("<span></span>");
            }
        });

        $("#register_confirmpassword").blur(function(){
            var confirmpassword = $("#register_confirmpassword").val();
            var password = $("#register_password").val();
            if(confirmpassword != password){
                $("#available_status3").html("<span style='color:red'>两次密码不一致</span>");
            }else{
                $("#available_status3").html("<span></span>");
            }
        });

        $("#register_email").focus();
        $("#register_email").keyup(function(){
            var email = $("#register_email").val();
            if(email.indexOf("@") < 0){
                $("#available_status4").html("<span style='color:red'>请输入正确的邮件地址</span>");
            }else{
                $("#available_status4").html("<span></span>");
            }
        });

        $("#register").click(function(){
            var email = $("#register_email").val();
            var password = $("#register_password").val();
            var confirmpassword = $("#register_confirmpassword").val();
            var mobile = $("#mobile").val();
            if(email.indexOf("@")>0 && password.length>0 && confirmpassword == password){
                $("#register_form").submit();
            }else{
                alert("请重新输入注册信息");
            }
        });
    });
</script>

<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1><strong>Online Bookstore</strong> Register Form</h1>
                    <div class="description">
                        <p>
                            Register your account!
                        </p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-top">
                        <div class="form-top-left">
                            <h3>Sign up to our site</h3>
                            <p>Enter your username,password and email</p>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-key"></i>
                        </div>
                    </div>
                    <div class="form-bottom">
                        <s:form action="user_regist" id="register_form" method="post" class="login-form">
                            <div class="form-group">
                                <label class="sr-only" for="form-username">Username</label>
                                <input type="text" name="username" placeholder="Username..." class="form-username form-control" id="register_username">
                                <div id="available_status1"></div>
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">Password</label>
                                <input type="password"  name="password" placeholder="Password..." class="form-password form-control" id="register_password">
                                <div id="available_status2"></div>
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">Password</label>
                                <input type="password"  name="confirmpassword" placeholder="Password again..." class="form-password form-control" id="register_confirmpassword">
                                <div id="available_status3"></div>
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-username">Username</label>
                                <input type="text"  name="email" placeholder="Email..." class="form-username form-control" id="register_email"><div id="available_status4"></div>
                            </div>
                            <button type="submit" id="register" class="btn">Sign up!</button>
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

</body>
</html>
