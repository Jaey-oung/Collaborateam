<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/water.css">
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
        <input type="text" id="birth" name="birth">
        <button type="button" id="submitBtn">Sign Up</button>
    </form:form>

    <div>
        <h1>This is REGISTER</h1>
        <h1>This is REGISTER</h1>
    </div>
</body>
<script>
    $(document).ready(function() {
        let msg = "${msg}";
        if(msg === "SIGN_UP_ERR") {
            alert("Failed to register");
        }

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

        year.append('<option value="">--Select Year--</option>');

        const currentYear = new Date().getFullYear();

        for (let lastYear = currentYear; lastYear >= currentYear - 100; lastYear--) {
            year.append($("<option>", {
                value: lastYear,
                text: lastYear
            }));
        }
    }

    function updateMonth() {
        let month = $("#month");

        month.append('<option value="">--Select Month--</option>');

        for (let startMonth = 1; startMonth <= 12; startMonth++) {
            month.append($("<option>", {
                value: startMonth,
                text: startMonth
            }));
        }
    }

    function updateDay() {
        let selectYear = $("#year").val();
        let selectMonth = $("#month").val();
        let day = $("#day");
        day.empty();

        if (selectYear !== "" && selectMonth !== "") {
            let daysInMonth = new Date(selectYear, selectMonth, 0).getDate();

            for (let startDay = 1; startDay <= daysInMonth; startDay++) {
                day.append($("<option>", {
                    value: startDay,
                    text: startDay
                }));
            }
        } else {
            day.append('<option value="">--Select Day--</option>');
        }
    }

    function formatBirthAndSubmit() {
        let selectYear = $("#year").val();
        let selectMonth = $("#month").val();
        let selectDay = $("#day").val();

        if (selectYear === "" || selectMonth === "" || selectDay === "") {
            alert("Please select your date of birth");
        } else {
            let birth = selectYear + "-" + selectMonth + "-" + selectDay;
            $("#birth").val(birth);

            const currentDate = new Date();
            let selectBirth = new Date(birth);

            if (selectBirth > currentDate) {
                alert("Please verify your date of birth.");
            } else {
                $("#registerForm").submit();
            }
        }
    }
</script>
</html>