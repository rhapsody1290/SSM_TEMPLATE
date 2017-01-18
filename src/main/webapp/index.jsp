<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 2016/10/6
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
    <%--首页——默认访问跳转页面--%>
    <%--<jsp page="/public/login.html"/>--%>
    <%
        response.sendRedirect("/ssm/public/login.html");
    %>
</body>
</html>
