$(document).ready(function() {
    displayMsg(msg)
    displayFieldList();

    $("#field").on("change", function() {
        displaySpecList();
    });

    $("#writeBtn").on("click", function() {
        location.href = "<c:url value='/board/write'/>";
    });
});

function displayMsg(msg) {
    if(msg === "BOARD_DEL_OK") alert("Successfully deleted the board");
    if(msg === "BOARD_CRT_OK") alert("Successfully created the board");
    if(msg === "BOARD_UPD_OK") alert("Successfully updated the board");
    if(msg === "BOARD_LIST_LOAD_ERR") alert("Failed to load the board list");
}

function displayFieldList() {
    $.ajax({
        type: "GET",
        url: "/collaborateam/fields",
        success : function(result) {
            $("#field").html(formatField(result));
            displaySpecList();
        },
        error : function() { alert("Failed to load the field list"); }
    });
}

function formatField(fields) {
    let tmp = "";

    fields.forEach(function(field) {
        tmp += "<option value=" + field.value

        if(field.value === selectField)
            tmp += " selected";

        tmp += ">" + field.name + "</option>"
    });
    return tmp;
}

function displaySpecList() {
    let selectField = $("#field").val();
    let spec = $("#spec");

    $.ajax({
        type: "GET",
        url: "/collaborateam/specs",
        data: { field: selectField },
        success : function(result) {
            spec.html(formatSpec(result));
        },
        error : function() { alert("Failed to load the spec list"); }
    });
}

function formatSpec(specs) {
    let tmp = "";

    specs.forEach(function(spec) {
        tmp += "<option value=" + spec.value

        if(spec.value === selectSpec)
            tmp += " selected";

        tmp += ">" + spec.name + "</option>"
    });
    return tmp;
}