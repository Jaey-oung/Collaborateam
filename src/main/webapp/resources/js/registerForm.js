$(document).ready(function() {
    let isCheckIdClicked = false;

    displayMsg(msg);
    displayErr(err);

    $("#checkIdBtn").on("click", function() {
        isCheckIdClicked = true;
        isIdDuplicate();
    });

    $("#submitBtn").on("click", function(e) {
        if(!isCheckIdClicked) {
            e.preventDefault();
            alert("Please check the ID");
            return false;
        }
    });

    formatDate();
});

function displayMsg(msg) {
    if(msg === "SIGN_UP_ERR") alert("Failed to create the user");
}

function displayErr(err) {
    if(err) $("#error").before("<i class=\"bx bx-error-circle\"></i>");
}

function isIdDuplicate() {
    let idValue = $("input[name=id]").val();

    if(idValue.trim() === "") {
        alert("Please fill in the ID");
        $("input[name=id]").focus();
        return;
    }

    $.ajax({
        type: "GET",
        url: "/collaborateam/register/isIdDuplicate?id="+idValue,
        success: function(result){
            alert(result);
            $("#submitBtn").removeAttr("disabled").removeClass("button-disabled").addClass("button-enabled");
        },
        error: function(jqXHR) {
            alert(jqXHR.responseText);
            $("#submitBtn").attr("disabled", "disabled").removeClass("button-enabled").addClass("button-disabled");
        }
    });
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