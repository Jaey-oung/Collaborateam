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
        <input type="text" id="id" name="id" value="<c:out value='${userDto.id}'/>" placeholder="Please enter the ID">
        <input type="text" id="pwd" name="pwd" value="<c:out value='${userDto.pwd}'/>" placeholder="Please enter the password">
        <input type="text" id="email" name="email" value="<c:out value='${userDto.email}'/>" placeholder="example@example.com">
        <input type="text" id="name" name="name" value="<c:out value='${userDto.name}'/>" placeholder="Please enter your name">
        <h1>Birth</h1>
        <select id="year" name="year" onchange="updateDay()"></select>
        <select id="month" name="month" onchange="updateDay()"></select>
        <select id="day" name="day"></select>
        <input type="text" id="birth" name="birth" placeholder="Please enter your birth">
        <button type="button" onclick="formatBirthAndSubmit()">Sign Up</button>
    </form:form>

    <div>
        <h1>This is REGISTER</h1>
        <h1>This is REGISTER</h1>
    </div>
</body>
<script>
    window.onload = function() {
        updateYear();
        updateMonth();
        updateDay();
    }

    const currentDate = new Date();
    const currentYear = currentDate.getFullYear();

    function updateYear() {
        let selectYear = document.getElementById("year");

        for (let year = currentYear; year >= currentYear - 100; year--) {
            const yearOption = document.createElement("option");
            yearOption.value = year;
            yearOption.text = year;
            selectYear.appendChild(yearOption);
        }
    }

    function updateMonth() {
        let selectMonth = document.getElementById("month");

        for (let month = 1; month <= 12; month++) {
            const monthOption = document.createElement("option");
            monthOption.value = month;
            monthOption.text = month;
            selectMonth.appendChild(monthOption);
        }
    }

    function updateDay() {
        let year = document.getElementById("year").value;
        let month = document.getElementById("month").value;
        let selectDay = document.getElementById("day");

        selectDay.innerHTML = "";

        const daysInMonth = new Date(year, month, 0).getDate();

        for (let day = 1; day <= daysInMonth; day++) {
            const dayOption = document.createElement("option");
            dayOption.value = day;
            dayOption.text = day;
            selectDay.appendChild(dayOption);
        }
    }

    function formatBirthAndSubmit() {
        let year = document.getElementById("year").value;
        let month = document.getElementById("month").value;
        let day = document.getElementById("day").value;

        document.getElementById("birth").value = year + "-" + month + "-" + day;

        let selectBirth = new Date(document.getElementById('birth').value);

        if (selectBirth > currentDate) {
            alert("Please verify your date of birth");
        } else {
            document.getElementById("registerForm").submit();
        }
    }
</script>
</html>