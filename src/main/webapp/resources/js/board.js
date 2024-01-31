$(document).ready(function() {
    displayMsg(msg);
    displayCommentList();
    setUpBtnClickEvents();

    if (mode === "BOARD_CRT") boardCrtMode();
    if (mode === "BOARD_READ") boardReadMode();
});

function displayMsg(msg) {
    if(msg === "BOARD_CRT_ERR") alert("Failed to create the board");
    if(msg === "BOARD_UPD_ERR") alert("Failed to update the board");
    if(msg === "BOARD_DEL_ERR") alert("Failed to delete the board");
}

function boardCrtMode() {
    $("input[name=title]").attr("readonly", false);
    $("input[name=content]").attr("readonly", false);
    $("input[name=writer]").hide();
    $("input[name=bno]").hide();
    $("#invite-function").hide();
    $("#comment-function").hide();
}

function boardReadMode() {
    $("#field").attr("disabled", true);
    $("#spec").attr("disabled", true);
    $("#boardCrtBtn").hide();
    $("#commentUpdBtn").hide();
    $("#comment-reply").hide();
}

function setUpBtnClickEvents() {
    $("#boardCrtBtn").on("click", function() {
        if (!isBoardEmpty()) return;
        submitForm("/collaborateam/board/create");
    });

    $("#boardUpdBtn").on("click", function() {
        let title = $("input[name=title]");
        let content = $("input[name=content]");

        if(title.attr("readonly") && content.attr("readonly")) {
            $("#field").attr("disabled", false);
            $("#spec").attr("disabled", false);
            title.attr("readonly", false);
            content.attr("readonly", false);
            $("#boardUpdBtn").html("Write");
            $("#boardDelBtn").hide();
        } else {
            submitForm("/collaborateam/board/update");
        }
    });

    $("#boardDelBtn").on("click", function() {
        if(!confirm("Would you like to delete the board?")) return;
        submitForm("/collaborateam/board/delete");
    });

    $("#boardListBtn").on("click", function() {
        location.href = "/collaborateam/board/list" + queryString;
    });

    $("#teamInviteBtn").on("click", function() {
        $.ajax({
            type: "GET",
            url: "/collaborateam/team/teams",
            success: function(result){
                $("#team-list").html(formatTeam(result))
            },
            error: function(jqXHR) {
                alert(jqXHR.responseText);
            }
        });
    });

    $("#team-list").on("click", "li", function() {
        $(this).siblings().removeAttr("selected");
        $(this).attr("selected", true);
    });

    $("#team-list").on("click", "#teamSelectBtn", function () {
    let tnoValue = $("#team-list li[selected]").attr("data-tno");

        $.ajax({
            type: "POST",
            url: "/collaborateam/invites",
            contentType: "application/json",
            data: JSON.stringify({ tno: tnoValue, id: writer }),
            success: function(result){
                alert(result)
            },
            error: function(jqXHR) {
                alert(jqXHR.responseText);
            }
        });
    })

    $("#commentCrtBtn").on("click", function() {
        let bnoValue = $("input[name=bno]").val();
        let commentValue = $("input[name=comment]").val();

        if(commentValue.trim()==="") {
            alert("Write the comment");
            $("input[name=comment]").focus();
            return;
        }

        $.ajax({
            type: "POST",
            url: "/collaborateam/comments?bno="+bnoValue,
            headers: {"content-type": "application/json"},
            data: JSON.stringify({bno: bnoValue, comment: commentValue}),
            success: function(result){
                alert(result);
                displayCommentList();
            },
            error: function(jqXHR) {
                alert(jqXHR.responseText);
            }
        });

        $("input[name=comment]").val("");
    });

    $("#comment-list").on("click", "#dynCommentUpdBtn", function() {
        let cnoValue = $(this).parent().attr("data-cno");
        let commentValue = $("span.comment", $(this).parent()).text();

        $("#commentCrtBtn").hide();
        $("#commentUpdBtn").show();
        $("input[name=comment]").val(commentValue);
        $("#commentUpdBtn").attr("data-cno", cnoValue);
    });

    $("#commentUpdBtn").on("click", function() {
        let cnoValue = $(this).attr("data-cno");
        let commentValue = $("input[name=comment]").val();

        if(commentValue.trim() === "") {
            alert("Write the comment");
            $("input[name=comment]").focus();
            return;
        }

        $.ajax({
            type: "PATCH",
            url: "/collaborateam/comments/"+cnoValue,
            headers: {"content-type": "application/json"},
            data: JSON.stringify({cno: cnoValue, comment: commentValue}),
            success: function(result){
                alert(result);
                displayCommentList();
            },
            error: function(jqXHR) {
                alert(jqXHR.responseText);
            }
        });
    });

    $("#comment-list").on("click", "#dynCommentDelBtn", function() {
        let cnoValue = $(this).parent().attr("data-cno");
        let bnoValue = $(this).parent().attr("data-bno");

        if(!confirm("Would you like to delete the comment?")) return;

        $.ajax({
            type: "DELETE",
            url: "/collaborateam/comments/"+cnoValue+"?bno="+bnoValue,
            success: function(result){
                alert(result);
                displayCommentList();
            },
            error: function(jqXHR) {
                alert(jqXHR.responseText);
            }
        });
    });

    $("#comment-list").on("click", "#dynCommentRepBtn", function() {
        $("#comment-reply").appendTo($(this).parent());
        $("#comment-reply").show();
    });

    $("#commentRepCrtBtn").on("click", function() {
        let bnoValue = $("input[name=bno]").val();
        let pcnoValue = $("#comment-reply").parent().attr("data-pcno");
        let commentValue = $("input[name=commentRep]").val();

        if(commentValue.trim() === "") {
            alert("Write the comment");
            $("input[name=commentRep]").focus();
            return;
        }

        $.ajax({
            type: "POST",
            url: "/collaborateam/comments?bno="+bnoValue,
            headers: {"content-type": "application/json"},
            data: JSON.stringify({bno: bnoValue, pcno: pcnoValue, comment: commentValue}),
            success: function(result){
                alert(result);
                displayCommentList();
            },
            error: function(jqXHR) {
                alert(jqXHR.responseText);
            }
        });

        $("input[name=commentRep]").val("");
        $("#comment-reply").appendTo("body");
        $("#comment-reply").hide();
    });
}

function isBoardEmpty() {
    let titleValue = $("input[name=title]").val().trim();
    let contentValue = $("input[name=content]").val().trim();

    if (titleValue === "" || contentValue === "") {
        alert("Please fill in the empty field");
        return false;
    }
    return true;
}

function submitForm(action) {
    let form = $("#form");
    form.attr("action", action);
    form.attr("method", "post");
    form.submit();
}

function formatTeam(teams) {
    let tmp = "<ul>";

    teams.forEach(function(team) {
        tmp += "<li data-tno=" + team.tno + ">"
        tmp += " team : <span class='team'>" + team.name + "</span>"
        tmp += "</li>"
    })
    tmp += "</ul>"
    tmp += "<button type='button' id='teamSelectBtn' class='btn'>Confirm</button>"
    return tmp
}

function displayCommentList() {
    let bnoValue = $("input[name=bno]").val();
    $("#comment-function").show();

    $.ajax({
        type: "GET",
        url: "/collaborateam/comments?bno="+bnoValue,
        success : function(result){
            $("#comment-list").html(formatComment(result));
        },
        error : function(){
            alert("Failed to load the comment list");
        }
    });
}

function formatComment(comments) {
    let tmp = "<ul>";

    comments.forEach(function(comment) {
        tmp += "<li data-cno=" + comment.cno
        tmp += " data-bno=" + comment.bno
        tmp += " data-pcno=" + comment.pcno + ">"

        if(comment.cno !== comment.pcno)
            tmp += '->';

        tmp += " comment=<span class='comment'>" + comment.comment + "</span>"
        tmp += " commenter=<span class='commenter'>" + comment.commenter + "</span>"
        tmp += " up_date=" + comment.up_date

        if(user === comment.commenter) {
            tmp += " <button id='dynCommentDelBtn'>Remove</button>"
            tmp += " <button id='dynCommentUpdBtn'>Modify</button>"
        }

        tmp += " <button id='dynCommentRepBtn'>Reply</button>"
        tmp += "</li>"
    });
    tmp += "</ul>";
    return tmp;
}