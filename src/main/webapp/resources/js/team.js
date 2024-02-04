$(document).ready(function() {
    displayMsg(msg);
    displayMemberList();
    displayGoalList();
    setUpBtnClickEvents();

    if(mode === "TEAM_CRT") teamCrtMode();
    if(mode === "TEAM_READ") teamReadMode();
});

function displayMsg(msg) {
    if(msg === "TEAM_CRT_ERR") alert("Failed to create the team");
    if(msg === "TEAM_UPD_ERR") alert("Failed to update the team");
    if(msg === "TEAM_DEL_ERR") alert("Failed to delete the team");
}

function teamCrtMode() {
    $("input[name=name]").attr("readonly", false);
    $("textarea[name=detail]").attr("readonly", false);
    $("input[name=tno]").hide();
    $(".leader").hide();
    $("#teamWithdrawBtn").hide();
    $(".goal-container").hide();
    $(".task-container").hide();
}

function teamReadMode() {
    $("input[name=tno]").hide();
    $("#teamCrtBtn").hide();
    $("#task-function").hide();
    $("#goalUpdBtn").hide();
}

function setUpBtnClickEvents() {
    $("#teamCrtBtn").on("click", function() {
        if(!isTeamEmpty()) return;
        submitForm("/collaborateam/team/create");
    });

    $("#teamUpdBtn").on("click", function() {
        let name = $("input[name=name]");
        let detail = $("textarea[name=detail]");

        if(name.attr("readonly") && detail.attr("readonly")) {
            name.attr("readonly", false);
            detail.attr("readonly", false);
            $("#teamUpdBtn").html("Write");
            $("#teamDelBtn").hide();
            $("#teamWithdrawBtn").hide();
            $(".task-container").hide();
            $(".goal-container").hide();
        } else {
            submitForm("/collaborateam/team/update");
        }
    });

    $("#teamDelBtn").on("click", function() {
        if(!confirm("Would you like to delete the team?")) return;
        submitForm("/collaborateam/team/delete");
    });

    $("#teamListBtn").on("click", function() {
        location.href = "/collaborateam/team/list" + queryString;
    });

    $("#teamWithdrawBtn").on("click", function() {
        let tnoValue = $("input[name=tno]").val();

        if(!confirm("Would you like to leave this team?")) return;

        $.ajax({
            type: "DELETE",
            url: "/collaborateam/members/withdraw/"+tnoValue,
            success: function(result){
                alert(result);
                location.href = "/collaborateam/team/list";
            },
            error: function(jqXHR) {
                alert(jqXHR.responseText);
            }
        });
    });

    $("#member-list").on("click", "#dynTaskCrtBtn", function() {
        let memberValue = $(this).closest("li").find("span.member").text()

        $("#task-function").hide();
        $("#taskUpdBtn").hide();
        $("#task-function").show();
        $("#taskCrtBtn").show();
        $("#taskCrtBtn").attr("data-member", memberValue);
    });

    $("#member-list").on("click", "#dynMemberDelBtn", function () {
        let mnoValue= $(this).parent().attr("data-mno");
        let memberValue = $(this).closest("li").find("span.member").text();

        if(!confirm("Would you like to delete this member?")) return;

        $.ajax({
            type: "DELETE",
            url: "/collaborateam/members/delete/"+mnoValue+"?id="+memberValue,
            success: function(result){
                alert(result);
                displayMemberList()
            },
            error: function(jqXHR) {
                alert(jqXHR.responseText);
            }
        });
    });

    $("#taskCrtBtn").on("click", function() {
        let tnoValue = $("input[name=tno]").val();
        let memberValue = $(this).attr("data-member");
        let taskValue = $("input[name=task]").val();

        if(taskValue.trim() === "") {
            alert("Write the task");
            $("input[name=task]").focus();
            return;
        }

        $.ajax({
            type: "POST",
            url: "/collaborateam/tasks",
            headers: {"content-type": "application/json"},
            data: JSON.stringify({tno: tnoValue, member: memberValue, name: taskValue}),
            success: function(result){
                alert(result);
                displayMemberList();
            },
            error: function(jqXHR) {
                alert(jqXHR.responseText);
            }
        });

        $("input[name=task]").val("");
        $("#task-function").insertBefore(".member-list");
        $("#task-function").hide();
    });

    $("#member-list").on("click", "#dynTaskListBtn", function () {
        let tnoValue = $("input[name=tno]").val();
        let memberValue = $(this).closest("li").find("span.member").text();
        let taskListDir = $(".task-list", $(this).parent())

        if (taskListDir.html().trim() !== "") {
            taskListDir.toggle();
            return;
        }

        $.ajax({
            type: "GET",
            url: "/collaborateam/tasks?tno="+tnoValue+"&member="+memberValue,
            success : function(result){
                taskListDir.html(formatTask(result));
            },
            error : function(){
                alert("Failed to load the task list");
            }
        });
    });

    $("#member-list").on("click", ".dynTaskUpdBtn", function () {
        let tanoValue = $(this).closest("li").attr("data-tano")
        let taskValue = $(this).closest("li").find("span.task").text();
        let memberValue = $(this).closest("ul").closest("li").find("span.member").text();

        $("#task-function").hide();
        $("#taskUpdBtn").attr("data-member", memberValue);
        $("input[name=task]").attr("data-tano", tanoValue);
        $("input[name=task]").val(taskValue);
        $("#task-function").show();
        $("#taskCrtBtn").hide();
        $("#taskUpdBtn").show();
    });

    $("#taskUpdBtn").on("click", function () {
        let tanoValue = $("input[name=task]").attr("data-tano");
        let taskValue = $("input[name=task]").val();
        let memberValue = $(this).attr("data-member");

        if(taskValue.trim()==="") {
            alert("Write the task");
            $("input[name=task]").focus();
            return;
        }

        $.ajax({
            type: "PATCH",
            url: "/collaborateam/tasks/"+tanoValue,
            headers: {"content-type": "application/json"},
            data: JSON.stringify({tano: tanoValue, member: memberValue, name: taskValue}),
            success: function(result){
                alert(result);
                displayMemberList();
            },
            error: function(jqXHR) {
                alert(jqXHR.responseText);
            }
        });

        $("input[name=task]").val("");
        $("#task-function").hide();
    })

    $("#member-list").on("click", ".dynTaskDelBtn", function () {
        let tanoValue = $(this).closest("li").attr("data-tano");
        let memberValue = $(this).closest("ul").closest("li").find("span.member").text();

        if(!confirm("Would you like to delete this task?")) return;

        $.ajax({
            type: "DELETE",
            url: "/collaborateam/tasks/"+tanoValue+"?member="+memberValue,
            success: function(result){
                alert(result);
                displayMemberList();
            },
            error: function(jqXHR) {
                alert(jqXHR.responseText);
            }
        });
    });

    $("#goalCrtBtn").on("click", function() {
        let tnoValue = $("input[name=tno]").val();
        let goalValue = $("input[name=goal]").val();

        if(goalValue.trim() === "") {
            alert("Write the goal");
            $("input[name=goal]").focus();
            return;
        }

        $.ajax({
            type: "POST",
            url: "/collaborateam/goals?tno="+tnoValue,
            headers: {"content-type": "application/json"},
            data: JSON.stringify({tno: tnoValue, name: goalValue}),
            success: function(result){
                alert(result);
                displayGoalList();
            },
            error: function(jqXHR) {
                alert(jqXHR.responseText);
            }
        });

        $("input[name=goal]").val("");
    });

    $("#goal-list").on("click", "#dynGoalUpdBtn", function() {
        let gnoValue = $(this).parent().attr("data-gno");
        let goalValue = $("span.goal", $(this).parent()).text();

        $("#goalCrtBtn").hide();
        $("#goalUpdBtn").show();
        $("#goalUpdBtn").html("Write");
        $("input[name=goal]").val(goalValue);
        $("#goalUpdBtn").attr("data-gno", gnoValue);
    });

    $("#goalUpdBtn").on("click", function() {
        let gnoValue = $(this).attr("data-gno");
        let goalValue = $("input[name=goal]").val();

        if(goalValue.trim() === "") {
            alert("Write the goal");
            $("input[name=goal]").focus();
            return;
        }

        $.ajax({
            type: "PATCH",
            url: "/collaborateam/goals/"+gnoValue,
            headers: {"content-type": "application/json"},
            data: JSON.stringify({name: goalValue}),
            success: function(result){
                alert(result);
                displayGoalList();
            },
            error: function(jqXHR) {
                alert(jqXHR.responseText);
            }
        });

        $("input[name=goal]").val("");
    });

    $("#goal-list").on("click", "#dynGoalDelBtn", function() {
        let gnoValue = $(this).parent().attr("data-gno");

        if(!confirm("Would you like to delete this goal?")) return;

        $.ajax({
            type: "DELETE",
            url: "/collaborateam/goals/"+gnoValue,
            success: function(result){
                alert(result);
                displayGoalList();
            },
            error: function(jqXHR) {
                alert(jqXHR.responseText);
            }
        });
    });
}

function displayMemberList() {
    let tnoValue = $("input[name=tno]").val();

    $.ajax({
        type: "GET",
        url: "/collaborateam/members?tno="+tnoValue,
        success : function(result){
            $("#member-list").html(formatMember(result));
        },
        error : function(){
            alert("Failed to load the member list");
        }
    });
}

function formatMember(members) {
    let leaderValue = $("input[name=leader]").val();
    let tmp = "<ul>";

    members.forEach(function(member) {
        tmp += "<li data-mno=" + member.mno + ">"
        tmp += " Member : <span class='member'>" + member.id + "</span>"
        tmp += "<button type='button' id='dynTaskListBtn' class='btn'><i class='bx bx-show'></i>Show Task List</button>"
        tmp += "<button type='button' id='dynTaskCrtBtn' class='btn'><i class='bx bx-pencil'></i>New Task</button>"
        if(leaderValue === user && leaderValue !== member.id)
            tmp += " <button type='button' id='dynMemberDelBtn' class='btn'><i class='bx bx-trash'></i>Remove</button>"
        tmp += "<div class='task-list'></div>"
        tmp += "</li>"
    })
    tmp += "</ul>"
    return tmp
}

function formatTask(tasks)   {
    let tmp = "";

    tasks.forEach(function(task) {
        tmp += "<ul>"
        tmp += "<li data-tano=" + task.tano + ">"
        tmp += "<i class='bx bx-subdirectory-right'></i> Task : <span class='task'>" + task.name + "</span>"
        tmp += "<div class='task-btn'>"
        tmp += " <button type='button' class='btn dynTaskUpdBtn'>Modify</button>"
        tmp += " <button type='button' class='btn dynTaskDelBtn'>Remove</button>"
        tmp += "</li>"
        tmp += "</div></ul>"
    })
    return tmp
}

function displayGoalList() {
    let tnoValue = $("input[name=tno]").val();

    $.ajax({
        type: "GET",
        url: "/collaborateam/goals?tno="+tnoValue,
        success : function(result){
            $("#goal-list").html(formatGoal(result));
        },
        error : function(){ alert("Failed to load the goal list")}
    });
}

function formatGoal(goals) {
    let tmp = "<ul>";

    goals.forEach(function(goal) {
        tmp += "<li data-gno=" + goal.gno + ">"
        tmp += " <i class='bx bx-subdirectory-right'></i>Goal : <span class='goal'>" + goal.name + "</span>"
        tmp += " <button type='button' id='dynGoalUpdBtn' class='btn'>Modify</button>"
        tmp += " <button type='button' id='dynGoalDelBtn' class='btn'>Remove</button>"
        tmp += "</li>"
    })
    tmp += "</ul>"
    return tmp
}

function isTeamEmpty() {
    let nameValue = $("input[name=name]").val().trim();

    if (nameValue === "") {
        alert("Please fill in the name field");
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