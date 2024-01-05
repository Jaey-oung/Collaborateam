<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="loginInOut" value="${sessionScope.id==null ? 'Login' : 'Logout'}"/>
<c:set var="loginInOutLink" value="${sessionScope.id==null ? '/login/login' : '/login/logout'}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Board</title>
</head>
<body>
<div>
    <ul>
        <li><a href="<c:url value='/'/>">Logo</a></li>
        <li><a href="<c:url value='/'/>">Home</a></li>
        <li><a href="">About</a></li>
        <li><a href="<c:url value='/board/list'/>">Team Building</a></li>
        <li><a href="">Team Management</a></li>
        <li><a href="">Contact Us</a></li>
        <li><a href="<c:url value='${loginInOutLink}'/>">${loginInOut}</a></li>
        ${sessionScope.id}
        <li><a href="">My Account</a></li>
    </ul>
</div>
<div>
    <h1>This is BOARD</h1>
    <h1>This is BOARD</h1>
    <h1>This is BOARD</h1>
</div>
</body>
</html>