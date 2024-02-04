<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"%>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginInOut" value="${empty loginId ? 'Login' : 'Logout'}"/>
<c:set var="loginInOutLink" value="${empty loginId ? '/login/login' : '/login/logout'}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/loginForm.css'/>">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="<c:url value='/js/menu.js'/>"></script>
    <script src="<c:url value='/js/loginForm.js'/>"></script>
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
    </div>
    <div class="login">
        <form action="<c:url value='/login/login'/>" method="post">
            <h1>Login</h1>
            <div class="input">
                <label>
                    <input type="text" name="id" value="<c:out value='${not empty id ? id : not empty cookie.id.value ? cookie.id.value : ""}'/>" placeholder="ID">
                    <i class="bx bxs-user"></i>
                </label>
            </div>
            <div class="input">
                <label>
                    <input type="password" name="pwd" value="<c:out value='${pwd}'/>" placeholder="Password">
                    <i class="bx bxs-lock-alt"></i>
                </label>
            </div>
            <label>
                <input type="hidden" name="redirectUrl" value="<c:out value='${param.redirectUrl}'/>">
            </label>
            <div class="remember">
                <label>
                    <input type="checkbox" name="rememberId" ${empty cookie.id.value ? "" : "checked"}> Remember ID
                </label>
            </div>
            <button type="submit" id="loginBtn">Login</button>
            <div class="register">
                <p>Don't have an account? <a href="<c:url value='/register/create'/>">Register</a></p>
            </div>
        </form>
    </div>
</body>
<script>
    const msg = "${msg}";
</script>
</html>