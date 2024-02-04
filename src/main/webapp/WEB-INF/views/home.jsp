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
    <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/home.css'/>">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="<c:url value='/js/menu.js'/>"></script>
    <script src="<c:url value='/js/home.js'/>"></script>
</head>
<body>
    <div class="wrapper">
        <div class="header">
            <h1><a href="<c:url value='/'/>">Collaborateam</a></h1>
            <div class="nav">
                <ul>
                    <li><a href="<c:url value='/'/>">Home</a></li>
                    <li><a href="<c:url value='/board/list'/>">Team Building</a></li>
                    <li><a href="<c:url value='/team/list'/>">Team Management</a></li>
                    <li><a href="<c:url value='${loginInOutLink}'/>"><c:out value="${loginInOut}"/></a></li>
                </ul>
            </div>
            <div class="hamburger-nav"><i class="bx bx-menu"></i></div>
        </div>
        <div class="container">
            <h2>Together, We Achieve More</h2>
            <p>Become a Member and Enhance Your Collaborative Skills</p>
            <button id="joinBtn">Join Us</button>
        </div>
    </div>
</body>
<script>
    const msg = "${msg}";
</script>
</html>