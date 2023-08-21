<%--
  Created by IntelliJ IDEA.
  User: monody
  Date: 2023/8/21
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Log Search</title>
</head>
<body>
<form method="get" action="${pageContext.request.contextPath}/search">
  Start Date: <input type="datetime-local" name="startDate" value="${startDate}"><br>
  End Date: <input type="datetime-local" name="endDate" value="${endDate}"><br>
  Level: <input type="text" name="level" value="${level}"><br>
  Trace ID: <input type="text" name="traceId" value="${traceId}"><br>
  Class Name: <input type="text" name="className" value="${className}"><br>
  Message: <input type="text" name="message" value="${message}"><br>
  Page Number: <input type="number" name="pageNumber" value="${empty pageNumber ? 0 : pageNumber}" min="0"><br>
  Page Size: <input type="number" name="pageSize" value="${empty pageSize ? 50 : pageSize}" min="1"><br>
  <input type="submit" value="Search">
</form>

<h2>Search Results</h2>
<c:if test="${not empty results}">
  <table>
    <tr>
      <th>Timestamp</th>
      <th>Level</th>
      <th>Trace ID</th>
      <th>Class Name</th>
      <th>Message</th>
    </tr>
    <c:forEach items="${results.content}" var="result">
      <tr>
        <td>${result.timestamp}</td>
        <td>${result.logLevel}</td>
        <td>${result.traceId}</td>
        <td>${result.class_}</td>
        <td>${result.message}</td>
      </tr>
    </c:forEach>
  </table>
  <p>Total pages: ${results.totalPages}</p>
  <p>Total elements: ${results.totalElements}</p>
</c:if>
</body>
</html>