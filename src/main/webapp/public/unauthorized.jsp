<%@ page language="java" pageEncoding="utf-8"%>
<%
    String json = "{\"status\":401,\"msg\":\"unauthorized\"}";
    out.print(json);
    out.flush();
%>
