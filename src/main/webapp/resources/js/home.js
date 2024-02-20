$(document).ready(function() {
    displayMsg(msg);

    $("#joinBtn").on("click", function() {
        window.location.href = "/collaborateam/board/list";
    });
});

function displayMsg(msg) {
    if(msg === "USER_CRT_OK") alert("Successfully created the user");
}