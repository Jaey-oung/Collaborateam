$(document).ready(function() {
    displayMsg(msg)

    $("#writeBtn").on("click", function() {
        location.href = "/collaborateam/board/create";
    });
});

function displayMsg(msg) {
    if(msg === "BOARD_LIST_LOAD_ERR") alert("Failed to load the board list");
    if(msg === "BOARD_CRT_OK") alert("Successfully created the board");
    if(msg === "BOARD_READ_ERR") alert("Failed to read the board");
    if(msg === "BOARD_UPD_OK") alert("Successfully updated the board");
    if(msg === "BOARD_DEL_OK") alert("Successfully deleted the board");
}