<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginInOut" value="${empty loginId ? 'Login' : 'Logout'}"/>
<c:set var="loginInOutLink" value="${empty loginId ? '/login/login' : '/login/logout'}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/registerForm.css'/>">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="<c:url value='/js/menu.js'/>"></script>
    <script src="<c:url value='/js/registerForm.js'/>"></script>
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
    <div class="signup">
        <form:form id="registerForm" modelAttribute="userDto">
            <h1>Sign Up</h1>
            <div id="error-container">
                <div id="error">
                    <form:errors path="id"/>
                    <form:errors path="pwd"/>
                    <form:errors path="birth"/>
                    <form:errors path="name"/>
                </div>
            </div>
            <div class="input">
                <label>
                    <input type="text" name="id" value="<c:out value='${userDto.id}'/>" placeholder="ID" required>
                    <i class="bx bxs-user"></i>
                    <button type="button" id="checkIdBtn">Check ID</button>
                </label>
            </div>
            <div class="input">
                <label>
                    <input type="password" name="pwd" value="<c:out value='${userDto.pwd}'/>" placeholder="Password" required>
                    <i class="bx bxs-lock-alt"></i>
                </label>
            </div>
            <div class="input">
                <label>
                    <i class="bx bx-cake"></i>
                    <input type="date" name="birth" value="<fmt:formatDate value="${userDto.birth}" pattern="yyyy-MM-dd"/>" required>
                </label>
            </div>
            <div class="input">
                <label>
                    <input type="email" name="email" value="<c:out value='${userDto.email}'/>" placeholder="Email is optional">
                    <i class="bx bx-envelope"></i>
                </label>
            </div>
            <div class="input">
                <label>
                    <input type="text" name="name" value="<c:out value='${userDto.name}'/>" placeholder="Name" required>
                    <i class="bx bx-id-card"></i>
                </label>
            </div>
            <button type="submit" id="submitBtn">Sign Up</button>
        </form:form>
    </div>
</body>
<script>
    const msg = "${msg}";
    const err = "${err}"
</script>
</html>