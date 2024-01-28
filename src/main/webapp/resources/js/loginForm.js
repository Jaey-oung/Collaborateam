$(document).ready(function () {
    displayMsg(msg);
});

function displayMsg(msg) {
    if(msg === "EMPTY_FIELD") alert("Please fill in the empty field");
    if(msg === "LOGIN_ERR") alert("Please verify your id and password");
}