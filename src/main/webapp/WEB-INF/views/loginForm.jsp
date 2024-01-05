<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
<form action="<c:url value='/login/login'/>" method="post">
    <input type="text" id="id" name="id" value="${cookie.id.value}" placeholder="ID">
    <input type="password" id="pwd" name="pwd" placeholder="Password">
    <input type="URL" id="redirectUrl" name="redirectUrl" value="${param.redirectUrl}">
    <button>Login</button>
    <input type="checkbox" id="rememberId" name="rememberId" ${empty cookie.id.value ? "":"checked"}> Remember ID
</form>
</body>
<script>
    let msg = "${msg}";
    if(msg === "LOGIN_ERR") {
        alert("Login Failed");
    }
</script>
</html>