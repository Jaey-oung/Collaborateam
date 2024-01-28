$(document).ready(function() {
    displayFieldList();

    $("#field").on("change", function() {
        displaySpecList();
    });
});

function displayFieldList() {
    $.ajax({
        type: "GET",
        url: "/collaborateam/fields",
        success : function(result) {
            $("#field").html(formatField(result));
            displaySpecList();
        },
        error : function() {
            alert("Failed to load the field list");
        }
    });
}

function formatField(fields) {
    let tmp = "";

    fields.forEach(function(field) {
        tmp += "<option value=" + field.value;

        if(field.value === selectField)
            tmp += " selected";

        tmp += ">" + field.name + "</option>";
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
        error : function() {
            alert("Failed to load the spec list");
        }
    });
}

function formatSpec(specs) {
    let tmp = "";

    specs.forEach(function(spec) {
        tmp += "<option value=" + spec.value;

        if(spec.value === selectSpec)
            tmp += " selected";

        tmp += ">" + spec.name + "</option>";
    });
    return tmp;
}