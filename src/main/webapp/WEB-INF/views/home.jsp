<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginInOut" value="${loginId==null ? 'Login' : 'Logout'}"/>
<c:set var="loginInOutLink" value="${loginId==null ? '/login/login' : '/login/logout'}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>Home</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/water.css">
</head>
<body>
<div>
    <ul>
        <li><a href="<c:url value='/'/>">Logo</a></li>
        <li><a href="<c:url value='/'/>">Home</a></li>
        <li><a href="<c:url value='/board/list'/>">Team Building</a></li>
        <li><a href="<c:url value='/team/list'/>">Team Management</a></li>
        <li><a href="<c:url value='${loginInOutLink}'/>"><c:out value="${loginInOut}"/></a></li>
        ${loginId}
    </ul>
</div>
<div>
    <h1>This is HOME</h1>
    <h1>This is HOME</h1>
    <h1>This is HOME</h1>
</div>
</body>
<script>
    $(document).ready(function() {
        let msg = "${msg}";

        if(msg === "SIGN_UP_OK") {
            alert("Successfully created the user")
        }
    })
</script>
</html>