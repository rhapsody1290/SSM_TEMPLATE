<%--<%@ page contentType="application/json;charset=UTF-8" language="java" %>--%>
<%
    String json = "{\"status\":405,\"msg\":\"Method Not Allowed\"}";
    out.print(json);
    out.flush();
%>
