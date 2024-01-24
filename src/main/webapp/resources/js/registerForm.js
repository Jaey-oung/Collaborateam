$(document).ready(function() {
    displayMsg(msg);
    displayErr(err)

    updateYear();
    updateMonth();
    updateDay();

    $("#year, #month").on("change", function() {
        updateDay();
    });

    $("#submitBtn").on("click", function() {
        formatBirthAndSubmit();
    });
});

function displayMsg(msg) {
    if(msg === "SIGN_UP_ERR") alert("Failed to create the user");
}

function displayErr(err) {
    if(err) {
        $("#error").before("<i class=\"bx bx-error-circle\"></i>");
    }
}

function updateYear() {
    let year = $("#year");
    let currentYear = new Date().getFullYear();

    year.append('<option value="">--Year--</option>');

    for (let lastYear = currentYear; lastYear >= currentYear - 100; lastYear--) {
        year.append($("<option>", {
            value: lastYear,
            text: lastYear
        }));
    }
}

function updateMonth() {
    let month = $("#month");

    month.append('<option value="">--Month--</option>');

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
        day.append('<option value="">--Day--</option>');
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
        let currentDate = new Date();
        let selectBirth = new Date(birth);

        $("#birth").val(birth);

        if (selectBirth > currentDate) {
            alert("Please verify your date of birth");
        } else {
            $("#registerForm").submit();
        }
    }
}