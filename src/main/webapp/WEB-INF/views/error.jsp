<%@ page contentType="text/html;charset=utf-8" isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>error.jsp</title>
</head>
<body>
<h1>Error Occured</h1>
Error : ${pageContext.exception}<br>
Message : ${pageContext.exception.message}<br>
<ol>
    <c:forEach items="${pageContext.exception.stackTrace}" var="i">
        <li>${i.toString()}</li>
    </c:forEach>
</ol>
</body>
</html>