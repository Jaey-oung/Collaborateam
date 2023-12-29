<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>
<body>
<form action="<c:url value='/register/add'/>" method="post">
    <input type="text" name="id" placeholder="Please enter the ID">
    <input type="text" name="pwd" placeholder="Please enter the password">
    <input type="text" name="email" placeholder="example@example.com">
    <input type="text" name="name" placeholder="Please enter your name">
    <input type="text" name="birth" placeholder="DD-MM-YYYY">
    <button type="submit">Sign Up</button>
</form>
<div>
    <h1>This is REGISTER</h1>
    <h1>This is REGISTER</h1>
</div>
</body>
</html>
