<%--
  Created by IntelliJ IDEA.
  User: lvjiawei
  Date: 2017/4/6
  Time: 下午5:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logout</title>
</head>
<body>
<%
    session.invalidate();
    response.sendRedirect(request.getContextPath()+"/index.jsp");
%>

<script type="text/javascript">
    var msg="${requestScope.tipMessage}";
    if(msg!=""){
        alert(msg);
    }
</script>
</body>
</html>
