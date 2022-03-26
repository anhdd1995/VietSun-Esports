<%-- 
    Document   : Update
    Created on : Feb 28, 2022, 11:46:17 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% String update = (String)request.getAttribute("update");%>
        <% String z = (String)request.getAttribute("z");%>
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%=update %></h1><br>
        <h2><%=z %></h2>
    </body>
</html>
