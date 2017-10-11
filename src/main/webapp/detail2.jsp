
<%--
  Created by IntelliJ IDEA.
  User: lvjiawei
  Date: 2017/4/19
  Time: 下午5:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="navbar.jsp"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/custom.css">
    <script src="assets/js/jquery-3.1.1.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <title>Detail</title>
</head>
<body>

<div class="container">
    <div class="row">
        <hr>
        <div class="col-lg-6 col-md-6 col-sm-6">
            <s:iterator value="#request.list">
                <h1>书名: <s:property value="title"/>
                </h1>
                <s:form action="buy2" method="get" target="_blank">
                    <input hidden="hidden" value="<s:property value="id"/>" name="bookid">
                    <input hidden="hidden" name="action" value="add">
                    <br><br><br>
                    <p>ISBN: <s:property value="isbn"/>
                    </p>
                    <p>作者: <s:property value="author"/>
                    </p>
                    <p>出版社: <s:property value="press"/>
                    </p>
                    <p>简介: <s:property value="intro"/>
                    </p>
                    <p>价格: <s:property value="price"/>
                    </p>
                    <p>库存: <s:property value="amount"/>
                    </p>
                    <p>购买数量: <input type="number" id="buyamount" value="1" name="buyamount"></p>
                    <button type="submit" class="btn btn-success">加入购物车</button>
                </s:form>


        </div>

        <div class="col-lg-6 col-md-6 col-sm-6">
            <img src="imagebyId.action?filename=<s:property value="image_id"/>" class="pull-right" width="300" height="400">
        </div>
        </s:iterator>
    </div>
</div>

<script type="text/javascript">
    var msg="${requestScope.tipMessage}";
    if(msg!=""){
        alert(msg);
    }
</script>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
