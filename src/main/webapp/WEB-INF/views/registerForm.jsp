<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
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
        <select id="year" name="year"></select>
        <select id="month" name="month"></select>
        <select id="day" name="day"></select>
        <input type="text" id="birth" name="birth" placeholder="Please enter your birth">
        <button type="button" id="submitBtn">Sign Up</button>
    </form:form>

    <div>
        <h1>This is REGISTER</h1>
        <h1>This is REGISTER</h1>
    </div>
</body>
<script>
    $(document).ready(function() {
        updateYear();
        updateMonth();
        updateDay();

        $("#year, #month").on("change", function() {
            updateDay();
        });

        $("#submitBtn").on("click", function() {
            formatBirthAndSubmit();
        });
    })

    function updateYear() {
        let year = $("#year");

        const currentYear = new Date().getFullYear();

        for (let lastYear = currentYear; lastYear >= currentYear - 100; lastYear--) {
            year.append($("<option>", {
                value: lastYear,
                text: lastYear
            }));
        }
        year.val("");
    }

    function updateMonth() {
        let month = $("#month");

        for (let startMonth = 1; startMonth <= 12; startMonth++) {
            month.append($("<option>", {
                value: startMonth,
                text: startMonth
            }));
        }
        month.val("");
    }

    function updateDay() {
        let selectYear = $("#year").val();
        let selectMonth = $("#month").val();
        let day = $("#day");
        day.empty();

        const daysInMonth = new Date(selectYear, selectMonth, 0).getDate();

        for (let startDay = 1; startDay <= daysInMonth; startDay++) {
            day.append($("<option>", {
                value: startDay,
                text: startDay
            }));
        }
        day.val("");
    }

    function formatBirthAndSubmit() {
        let selectYear = $("#year").val();
        let selectMonth = $("#month").val();
        let selectDay = $("#day").val();

        let birth = selectYear + "-" + selectMonth + "-" + selectDay;
        $("#birth").val(birth);

        const currentDate = new Date();
        let selectBirth = new Date(birth);

        if (selectBirth > currentDate) {
            alert("Please verify your date of birth");
        } else {
            $("#registerForm").submit();
        }
    }
</script>
</html>