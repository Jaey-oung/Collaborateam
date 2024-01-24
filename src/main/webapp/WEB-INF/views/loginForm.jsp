<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="<c:url value='/css/loginForm.css'/>">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="<c:url value='/js/loginForm.js'/>"></script>
</head>
<body>
    <div class="wrapper">
        <form action="<c:url value='/login/login'/>" method="post">
            <h1>Login</h1>
            <div class="input">
                <label>
                    <input type="text" name="id" value="<c:out value='${id}'/>" placeholder="ID">
                    <i class='bx bxs-user'></i>
                </label>
            </div>
            <div class="input">
                <label>
                    <input type="password" name="pwd" value="<c:out value='${pwd}'/>" placeholder="Password">
                    <i class='bx bxs-lock-alt'></i>
                </label>
            </div>
            <label>
                <input type="hidden" name="redirectUrl" value="<c:out value='${param.redirectUrl}'/>">
            </label>
            <div class="remember">
                <label>
                    <input type="checkbox" name="rememberId" ${empty cookie.id.value ? "": "checked"}> Remember ID
                </label>
            </div>
            <button type="submit" class="submit">Login</button>
            <div class="register">
                <p>Don't have an account? <a href="<c:url value='/register/add'/>">Register</a> </p>
            </div>
        </form>
    </div>
</body>
<script>
    const msg = "${msg}";
</script>
</html>