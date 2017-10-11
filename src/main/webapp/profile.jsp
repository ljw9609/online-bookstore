<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lvjiawei
  Date: 2017/6/6
  Time: 下午3:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>profile</title>
    <meta charset="utf-8">
    <link href="assets/css/landing-page.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="assets/bootstrap/css/bootstrap.css">
    <script src="assets/js/jquery-3.1.1.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container index-content me-page">
    <p class="path-nav">
        <a href="index.action">首页</a>
       <%=session.getAttribute("path_nav")%>
    </p>
    <div class="me-main">
        <div class="userinfo-edit" style="">
            <h2 class="title" style="background-image: url(/img/dog.png)"></h2>
            <div class="edit-box" style="">
                <div class="user-image-info">
                    <h2 class="sub-title">
                        会员头像
                    </h2>
                    <div class="user-image">
                        <img class="img-show" src="imagebyId.action?filename=${sessionScope.user.image_id}" alt="hello">
                    </div>
                    <form class="form-horizontal" method="post" action="uploadUserImage.action" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="myFile" class="col-sm-2 control-label">File input</label>
                            <div class="col-sm-4">
                                <input type="file" name="myFile"/>
                                <p class="help-block">上传您的新头像</p>
                            </div>
                        </div>
                        <div class="col-sm-offset-2">
                            <button type="submit" id="" class="btn btn-primary">
                                <span>修改</span>
                            </button>
                        </div>
                    </form>
                </div>

            </div>
        </div>

    </div>
</div>

</body>
</html>
