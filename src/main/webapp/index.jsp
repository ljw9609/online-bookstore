<%@ page import="java.util.Random" %>
<%@ page import="java.sql.*" %>

<%--
  Created by IntelliJ IDEA.
  User: lvjiawei
  Date: 2017/4/4
  Time: 下午4:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="navbar.jsp"%>
<html>
<head>
  <meta charset="utf-8">
  <title>Online Bookstore</title>

  <link href="assets/css/landing-page.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="assets/bootstrap/css/bootstrap.css">
  <script src="assets/js/jquery-3.1.1.min.js"></script>
  <script src="assets/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
<script type="text/javascript">
    var msg="${requestScope.tipMessage}";
    if(msg!=""){
        alert(msg);
    }
</script>
<!-- Header -->
<a name="about"></a>
<div class="intro-header">
  <div class="container">

    <div class="row">
      <div class="col-lg-12">
        <div class="intro-message">
          <h1>Online Bookstore</h1>
          <h3>Web project for SE228</h3>
          <hr class="intro-divider">
          <ul class="list-inline intro-social-buttons">
            <li>
              <s:url action="show_catelog" var="cateloglink">
                <s:param name="type">1</s:param>
              </s:url>
              <a href="${cateloglink}" class="btn btn-default btn-lg"><span class="network-name">计算机图书</span></a>
            </li>
            <li>
              <s:url action="show_catelog" var="cateloglink2">
                <s:param name="type">2</s:param>
              </s:url>
              <a href="${cateloglink2}" class="btn btn-default btn-lg"><span class="network-name">文学类图书</span></a>
            </li>
            <li>
              <s:url action="show_catelog" var="cateloglink3">
                <s:param name="type">3</s:param>
              </s:url>
              <a href="${cateloglink3}" class="btn btn-default btn-lg"><span class="network-name">教辅类图书</span></a>
            </li>
          </ul>
        </div>
      </div>
    </div>

  </div>
  <!-- /.container -->

</div>
<!-- /.intro-header -->

<!-- Page Content -->
<a  name="service"></a>
<s:iterator value="#booklist">
  <div class="content-section-a">
    <div class="container">
      <div class="row">
        <div class="col-lg-5 col-sm-6">
          <hr class="section-heading-spacer">
          <div class="clearfix"></div>
          <h2 class="section-heading">
            <s:url action="show_detail" var="detaillink">
              <s:param name="bookid"><s:property value="id"/></s:param>
            </s:url>
            <a href="${detaillink}"><s:property value="title"/></a>
          </h2>
          <p class="lead"><s:property value="intro"/></p>
        </div>
        <div class="col-lg-5 col-lg-offset-2 col-sm-6">
          <s:url action="show_detail" var="detaillink">
            <s:param name="bookid"><s:property value="id"/></s:param>
          </s:url>
          <a href="${detaillink}"><img class="img-responsive" src="imagebyId.action?filename=<s:property value="image_id"/>" width="300" height="450"></a>
        </div>
      </div>
    </div>
  </div>
</s:iterator>

<a  name="contact"></a>
<div class="banner">

  <div class="container">

    <div class="row">
      <div class="col-lg-6">
        <h2>Online Bookstore</h2>
      </div>
      <div class="col-lg-6">
        <ul class="list-inline banner-social-buttons">
          <li>
            <s:url action="show_catelog" var="cateloglink">
              <s:param name="type">1</s:param>
            </s:url>
            <a href="${cateloglink}" class="btn btn-default btn-lg"><span class="network-name">计算机图书</span></a>
          </li>
          <li>
            <s:url action="show_catelog" var="cateloglink2">
              <s:param name="type">2</s:param>
            </s:url>
            <a href="${cateloglink2}" class="btn btn-default btn-lg"><span class="network-name">文学类图书</span></a>
          </li>
          <li>
            <s:url action="show_catelog" var="cateloglink3">
              <s:param name="type">3</s:param>
            </s:url>
            <a href="${cateloglink3}" class="btn btn-default btn-lg"><span class="network-name">教辅类图书</span></a>
          </li>
        </ul>
      </div>
    </div>

  </div>
  <!-- /.container -->

</div>
<!-- /.banner -->

<!-- Footer -->
<jsp:include page="footer.jsp"></jsp:include>



</body>
</html>
