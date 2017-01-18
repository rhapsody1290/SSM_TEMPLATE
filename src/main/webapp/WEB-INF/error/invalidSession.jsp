<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String json = "{\"status\":400,\"msg\":\"会话过期，请重新登录！\"}";
    out.print(json);
    out.flush();
%>

