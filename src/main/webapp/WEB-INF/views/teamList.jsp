<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginInOut" value="${empty loginId ? 'Login' : 'Logout'}"/>
<c:set var="loginInOutLink" value="${empty loginId ? '/login/login' : '/login/logout'}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>TeamList</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
<div>
    <ul>
        <li><a href="<c:url value='/'/>">Logo</a></li>
        <li><a href="<c:url value='/'/>">Home</a></li>
        <li><a href="">About</a></li>
        <li><a href="<c:url value='/board/list'/>">Team Building</a></li>
        <li><a href="<c:url value='/team/list'/>">Team Management</a></li>
        <li><a href="">Contact Us</a></li>
        <li><a href="<c:url value='${loginInOutLink}'/>"><c:out value="${loginInOut}"/></a></li>
        ${loginId}
        <li><a href="">My Account</a></li>
    </ul>
</div>
<button type="button" id="crtBtn">New Team</button>
<script>
    $(document).ready(function() {
        let msg = "${msg}";

        if(msg === "TEAM_CRT_OK") alert("Successfully created the team");

        $("#crtBtn").on("click", function() {
            location.href = "<c:url value='/team/create'/>";
        });
    })
</script>