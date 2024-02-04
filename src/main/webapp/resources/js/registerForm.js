$(document).ready(function() {
    displayMsg(msg);
    displayErr(err);

    formatDate();
});

function displayMsg(msg) {
    if(msg === "SIGN_UP_ERR") alert("Failed to create the user");
}

function displayErr(err) {
    if(err) $("#error").before("<i class=\"bx bx-error-circle\"></i>");
}

function formatDate() {
    let today = new Date();
    let year = today.getFullYear();
    let month = ("0" + (today.getMonth() + 1)).slice(-2);
    let day = ("0" + today.getDate()).slice(-2);
    let maxDate = year + "-" + month + "-" + day
    let minDate = (year-100) + "-" + month + "-" + day

    $("input[type='date']").attr("min", minDate);
    $("input[type='date']").attr("max", maxDate);
}