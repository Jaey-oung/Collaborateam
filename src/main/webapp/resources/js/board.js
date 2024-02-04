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
    $("textarea[name=content]").attr("readonly", false);
    $("input[name=writer]").closest("label").hide();
    $("input[name=writer]").hide();
    $("input[name=bno]").hide();
    $("#invite-function").hide();
    $(".comment-container").hide();
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
        let content = $("textarea[name=content]");

        if(title.attr("readonly") && content.attr("readonly")) {
            $("#field").attr("disabled", false);
            $("#spec").attr("disabled", false);
            title.attr("readonly", false);
            content.attr("readonly", false);
            $("#boardUpdBtn").html('<i class="bx bx-pencil"></i>Write');
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
        $(".modal").show();
        $(".close").show();

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

    $(".close").on("click", function () {
        $(".modal").hide();
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

        $(".modal").hide();
    })

    $("#commentCrtBtn").on("click", function() {
        let bnoValue = $("input[name=bno]").val();
        let commentValue = $("textarea[name=comment]").val();

        if(commentValue.trim()==="") {
            alert("Write the comment");
            $("textarea[name=comment]").focus();
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

        $("textarea[name=comment]").val("");
    });

    $("#comment-list").on("click", "#dynCommentUpdBtn", function() {
        let cnoValue = $(this).parent().parent().attr("data-cno");
        let commentValue = $(this).closest(".comment-item").find(".comment").text();

        $("#commentCrtBtn").hide();
        $("#commentUpdBtn").show();
        $("textarea[name=comment]").val(commentValue);
        $("#commentUpdBtn").attr("data-cno", cnoValue);

    });

    $("#commentUpdBtn").on("click", function() {
        let cnoValue = $(this).attr("data-cno");
        let commentValue = $("textarea[name=comment]").val();

        if(commentValue.trim() === "") {
            alert("Write the comment");
            $("textarea[name=comment]").focus();
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

        $("textarea[name=comment]").val("");
    });

    $("#comment-list").on("click", "#dynCommentDelBtn", function() {
        let cnoValue = $(this).closest(".comment-item").attr("data-cno");
        let bnoValue = $(this).closest(".comment-item").attr("data-bno");

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
        $("#comment-reply").show();
        $("#comment-reply").appendTo($(this).parent());
    });

    $("#commentRepCrtBtn").on("click", function() {
        let bnoValue = $("input[name=bno]").val();
        let pcnoValue = $(this).closest(".comment-item").attr("data-pcno");
        let commentValue = $("textarea[name=commentRep]").val();

        if(commentValue.trim() === "") {
            alert("Write the comment");
            $("textarea[name=commentRep]").focus();
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

        $("textarea[name=commentRep]").val("");
        $("#comment-reply").appendTo("body");
        $("#comment-reply").hide();
    });
}

function isBoardEmpty() {
    let titleValue = $("input[name=title]").val().trim();
    let contentValue = $("textarea[name=content]").val().trim();

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
        tmp += " <div class='team'>" + team.name + "</div>"
        tmp += "</li>"
    })
    tmp += "</ul>"
    tmp += "<button type='button' id='teamSelectBtn'>Confirm</button>"
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
        tmp += "<li class='comment-item' data-cno=" + comment.cno
        tmp += " data-bno=" + comment.bno
        tmp += " data-pcno=" + comment.pcno + ">"
        tmp += " <div class='commenter'>"
        if(comment.cno !== comment.pcno)
            tmp += "<i class='bx bx-subdirectory-right'></i>";
        tmp += comment.commenter + "</div>"
        tmp += " <div class='comment'>" + comment.comment + "</div>"
        tmp += " <div class='comment-detail'>"
        tmp += " <span class='reg-date'>" + formatDate(comment.up_date) + "</span>"
        tmp += " <button class='btn' id='dynCommentRepBtn'><i class='bx bx-chat'></i>Reply</button>"

        if(user === comment.commenter) {
            tmp += " <button class='btn' id='dynCommentUpdBtn'><i class='bx bx-edit'></i>Modify</button>"
            tmp += " <button class='btn' id='dynCommentDelBtn'><i class='bx bx-trash'></i>Remove</button>"
        }

        tmp += "</div></li>"
    });
    tmp += "</ul>";
    return tmp;
}

function addZero(value) {
    return value > 9 ? value : "0" + value;
}

function formatDate(ms) {
    let date = new Date(ms);
    let yyyy = date.getFullYear();
    let mm = addZero(date.getMonth() + 1);
    let dd = addZero(date.getDate());
    let HH = addZero(date.getHours());
    let MM = addZero(date.getMinutes());
    let ss = addZero(date.getSeconds());
    return yyyy + "." + mm + "." + dd + " " + HH + ":" + MM + ":" + ss;
}