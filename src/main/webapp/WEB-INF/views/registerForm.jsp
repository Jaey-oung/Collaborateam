<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>
<body>
    <form:form id="registerForm" modelAttribute="userDto">
        <div id="msg" class="msg">
            <form:errors path="id"/>    <%--span id="id.errors"></span--%>
            <form:errors path="pwd"/>
            <form:errors path="name"/>
        </div>
        <input type="text" id="id" name="id" placeholder="Please enter the ID">
        <input type="text" id="pwd" name="pwd" placeholder="Please enter the password">
        <input type="text" id="email" name="email" placeholder="example@example.com">
        <input type="text" id="name" name="name" placeholder="Please enter your name">
        <h1>Birth</h1>
        <select id="year" name="year">
            <option value="2024">2024</option>
            <option value="2023">2023</option>
            <option value="2022">2022</option>
        </select>
        <select id="month" name="month">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
        </select>
        <select id="day" name="day">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
        </select>
        <input type="text" id="birth" name="birth" placeholder="Please enter your birth">
        <button type="button" onclick="formatBirth()">Sign Up</button>
    </form:form>

    <div>
        <h1>This is REGISTER</h1>
        <h1>This is REGISTER</h1>
    </div>
</body>
<script>
    function formatBirth() {
        let year = document.getElementById("year").value;
        let month = document.getElementById("month").value;
        let day = document.getElementById("day").value;
        document.getElementById("birth").value = year + "-" + month + "-" + day;

        document.getElementById("registerForm").submit();
    }
</script>
</html>
