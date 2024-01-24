<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="<c:url value='/css/registerForm.css'/>">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="<c:url value='/js/registerForm.js'/>"></script>
</head>
<body>
    <div class="wrapper">
        <form:form id="registerForm" modelAttribute="userDto">
            <h1>Sign Up</h1>
            <div id="error-container">
                <div id="error">
                    <form:errors path="id"/>
                    <form:errors path="pwd"/>
                    <form:errors path="name"/>
                </div>
            </div>
            <div class="input">
                <label>
                    <input type="text" name="id" value="<c:out value='${userDto.id}'/>" placeholder="Minimum 8 characters" required>
                    <i class="bx bxs-user"></i>
                </label>
            </div>
            <div class="input">
                <label>
                    <input type="password" name="pwd" value="<c:out value='${userDto.pwd}'/>" placeholder="Minimum 8 characters with a number and a letter" required>
                    <i class="bx bxs-lock-alt"></i>
                </label>
            </div>
            <div class="input">
                <div class="birth">
                    <i class="bx bx-cake"></i>
                    <label for="year">
                        <select id="year" name="year"></select>
                    </label>
                    <label for="month">
                        <select id="month" name="month"></select>
                    </label>
                    <label for="day">
                        <select id="day" name="day"></select>
                    </label>
                </div>
            </div>
            <div class="input">
                <label>
                    <input type="email" name="email" value="<c:out value='${userDto.email}'/>" placeholder="example@example.com">
                    <i class="bx bx-envelope"></i>
                </label>
            </div>
            <div class="input">
                <label>
                    <input type="text" name="name" value="<c:out value='${userDto.name}'/>" placeholder="Please enter your name" required>
                    <i class="bx bx-id-card"></i>
                </label>
            </div>
            <label>
                <input type="hidden" id="birth" name="birth">
            </label>
            <button type="button" id="submitBtn">Sign Up</button>
        </form:form>
    </div>
</body>
<script>
    const msg = "${msg}";
    const err = "${err}"
</script>
</html>