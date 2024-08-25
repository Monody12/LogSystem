<%--
  Created by IntelliJ IDEA.
  User: monody
  Date: 2023/8/21
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.RequestDispatcher" %>
<%
    RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
    dispatcher.forward(request, response);
%>
<html>
<head>
    <title>Forwarding search.jsp</title>
</head>
<body>
</body>
</html>
