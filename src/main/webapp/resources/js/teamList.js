$(document).ready(function() {
    displayMsg(msg);
    setUpBtnClickEvents();
});

function displayMsg(msg) {
    if(msg === "TEAM_CRT_OK") alert("Successfully created the team");
    if(msg === "TEAM_UPD_OK") alert("Successfully updated the team");
    if(msg === "TEAM_DEL_OK") alert("Successfully deleted the team");
    if(msg === "TEAM_LOAD_ERR") alert("Failed to load the team");
}

function setUpBtnClickEvents() {
    $("#teamCrtBtn").on("click", function() {
        location.href = "/collaborateam/team/create";
    });

    $("#teamInviteBtn").on("click", function () {
        displayInviteList();
    });

    $("#invite-function").on("click", "#dynAcceptBtn", function () {
        if(!confirm("Would you like to join the team?")) return;

        let inoValue = $(this).parent().attr("data-ino");
        let tnoValue = $(this).parent().attr("data-tno");

        $.ajax({
            type: "POST",
            url: "/collaborateam/members",
            contentType: "application/json",
            data: JSON.stringify({ tno: tnoValue, id: id}),
            success: function(result){
                alert(result);
                $.ajax({
                    type: "DELETE",
                    url: "/collaborateam/invites/"+inoValue,
                    success: function(result){
                        alert(result);
                        location.href = "/collaborateam/team/list";
                    },
                    error: function(jqXHR) {
                        alert(jqXHR.responseText);
                    }
                });
            },
            error: function(jqXHR) {
                alert(jqXHR.responseText);
            }
        });
    });

    $("#invite-function").on("click", "#dynDeclineBtn", function () {
        if(!confirm("Would you like to decline the team?")) return;

        let inoValue = $(this).parent().attr("data-ino");
        $.ajax({
            type: "DELETE",
            url: "/collaborateam/invites/"+inoValue,
            success: function(result){
                alert(result)
                displayInviteList()
            },
            error: function(jqXHR) {
                alert(jqXHR.responseText);
            }
        });
    });
}

function displayInviteList() {
    $.ajax({
        type: "GET",
        url: "/collaborateam/invites",
        success : function(result){
            $("#invite-function").html(formatInvite(result));
        },
        error : function(){
            alert("Failed to load the invite list");
        }
    });
}

function formatInvite(invites) {
    let tmp = "<ul>";

    invites.forEach(function(invite) {
        tmp += "<li data-ino=" + invite.ino
        tmp += " data-tno=" + invite.tno + ">"
        tmp += " Team: " + invite.name
        tmp += " <button id='dynAcceptBtn'>Accept</button>"
        tmp += " <button id='dynDeclineBtn'>Decline</button>"
        tmp += "</li>"
    })
    tmp += "</ul>";
    return tmp;
}