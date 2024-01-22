<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/water.css">
</head>
<body>
<form action="<c:url value='/login/login'/>" method="post">
    <input type="text" id="id" name="id" value="<c:out value='${id}'/>" placeholder="ID">
    <input type="password" id="pwd" name="pwd" value="<c:out value='${pwd}'/>" placeholder="Password">
    <input type="URL" id="redirectUrl" name="redirectUrl" value="<c:out value='${param.redirectUrl}'/>">
    <button>Login</button>
    <input type="checkbox" id="rememberId" name="rememberId" ${empty cookie.id.value ? "": "checked"}> Remember ID
</form>
</body>
<script>
    let msg = "${msg}";
    if(msg === "LOGIN_ERR") {
        alert("Please verify your id and password");
    } else if(msg === "EMPTY_FIELD") {
        alert("Please fill in the empty field");
    }
</script>
</html>