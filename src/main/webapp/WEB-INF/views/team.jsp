<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginInOut" value="${empty loginId ? 'Login' : 'Logout'}"/>
<c:set var="loginInOutLink" value="${empty loginId ? '/login/login' : '/login/logout'}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Team</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/water.css">
</head>
<body>
<div>
    <ul>
        <li><a href="<c:url value='/'/>">Logo</a></li>
        <li><a href="<c:url value='/'/>">Home</a></li>
        <li><a href="<c:url value='/board/list'/>">Team Building</a></li>
        <li><a href="<c:url value='/team/list'/>">Team Management</a></li>
        <li><a href="<c:url value='${loginInOutLink}'/>"><c:out value="${loginInOut}"/></a></li>
        ${loginId}
    </ul>
</div>
<div>
    <form id="form">
        <input type="text" name="tno" value="<c:out value='${teamDto.tno}'/>" readonly>
        <input type="text" name="leader" value="<c:out value='${teamDto.leader}'/>" readonly>
        <input type="text" name="name" value="<c:out value='${teamDto.name}'/>" readonly>
        <textarea name="description" cols="30" rows="10" readonly><c:out value="${teamDto.description}"/></textarea>
        <button type="button" id="teamCrtBtn" class="btn">Create</button>
        <c:if test="${loginId eq teamDto.leader}">
            <button type="button" id="teamModBtn" class="btn">Modify</button>
            <button type="button" id="teamRemBtn" class="btn">Remove</button>
        </c:if>
        <button type="button" id="teamListBtn" class="btn">List</button>
    </form>
</div>
<div id="member"></div>
<div id="taskFunction">
    <div id="tasks"></div>
    Task: <input type="text" name="task">
    <button type="button" id="taskWrtBtn">Write</button>
    <button type="button" id="taskUpdBtn">Modify</button>
</div>
<div id="goalFunction">
    Goal: <input type="text" name="goal">
    <button type="button" id="goalWrtBtn">Write</button>
    <button type="button" id="goalUpdBtn">Modify</button>
</div>
<div id="goals"></div>
</body>
<script>
    $(document).ready(function() {
        let msg = "${msg}";
        let mode = "${mode}";

        let tno = $("input[name=tno]").val();
        let name = $("input[name=name]");
        let description = $("textarea[name=description]");
        let teamCrtBtn = $("#teamCrtBtn");
        let teamModBtn = $("#teamModBtn");
        let teamRemBtn = $("#teamRemBtn");
        let teamListBtn = $("#teamListBtn");

        if(msg === "TEAM_CRT_ERR") alert("Failed to create the team");
        if(msg === "TEAM_MOD_ERR") alert("Failed to modify the team");
        if(msg === "TEAM_DEL_ERR") alert("Failed to delete the team");

        displayMemberList(tno)
        displayTaskList(tno)
        displayGoalList(tno)

        if(mode === "CRT_TEAM") {
            name.attr("readonly", false);
            description.attr("readonly", false);
        }

        if(mode === "READ_TEAM") {
            teamCrtBtn.hide();
        }

        teamCrtBtn.on("click", function () {
            if(!isTeamEmpty()) return;
            let form = $("#form");

            form.attr("action", "<c:url value='/team/create'/>");
            form.attr("method", "post");
            form.submit();
        });

        teamModBtn.on("click", function() {
            let form = $("#form");

            if(name.attr("readonly") && description.attr("readonly")) {
                name.attr("readonly", false);
                description.attr("readonly", false);
                teamModBtn.html("Write");
                teamRemBtn.hide();
                return;
            }

            form.attr("action", "<c:url value='/team/modify${teamListCondition.queryString}'/>");
            form.attr("method", "post");
            form.submit();
        });

        teamRemBtn.on("click", function() {
            if(!confirm("Would you like to delete the team?")) return;
            let form = $("#form");

            form.attr("action", "<c:url value='/team/remove${teamListCondition.queryString}'/>");
            form.attr("method", "post");
            form.submit();
        });

        teamListBtn.on("click", function() {
            location.href = "<c:url value='/team/list${teamListCondition.queryString}'/>";
        });

        $("#member").on("click", "#memberRemBtn", function () {
            let mno = $(this).parent().attr("data-mno");
            let id = $(this).parent().attr("data-id");

            $.ajax({
                type: "DELETE",
                url: "/collaborateam/members/"+mno+"?id="+id,
                success: function(result){
                    alert(result);
                    displayMemberList(mno)
                },
                error: function(jqXHR) {
                    alert(jqXHR.responseText);
                }
            });
        })

        $("#goals").on("click", "#goalModBtn", function() {
            let gno = $(this).parent().attr("data-gno");
            let goal = $("span.goal", $(this).parent()).text();

            $("input[name=goal]").val(goal);
            $("#goalUpdBtn").attr("data-gno", gno);
        });

        $("#goals").on("click", "#goalRemBtn", function() {
            let gno = $(this).parent().attr("data-gno");

            $.ajax({
                type: "DELETE",
                url: "/collaborateam/goals/"+gno,
                success: function(result){
                    alert(result);
                    displayGoalList(tno)
                },
                error: function(jqXHR) {
                    alert(jqXHR.responseText);
                }
            });
        });

        $("#goalWrtBtn").on("click", function() {
            let goal = $("input[name=goal]").val();

            if(goal.trim()==="") {
                alert("Write the goal");
                $("input[name=goal]").focus();
                return;
            }

            $.ajax({
                type: "POST",
                url: "/collaborateam/goals",
                headers: {"content-type": "application/json"},
                data: JSON.stringify({tno: tno, name: goal}),
                success: function(result){
                    alert(result)
                    displayGoalList(tno)
                },
                error: function(jqXHR) {
                    alert(jqXHR.responseText);
                }
            });

            $("input[name=goal]").val("");
        });

        $("#goalUpdBtn").on("click", function() {
            let gno = $(this).attr("data-gno");
            let goal = $("input[name=goal]").val();

            if(goal.trim()==="") {
                alert("Write the goal");
                $("input[name=goal]").focus();
                return;
            }

            $.ajax({
                type: "PATCH",
                url: "/collaborateam/goals/"+gno,
                headers: {"content-type": "application/json"},
                data: JSON.stringify({gno: gno, name: goal}),
                success: function(result){
                    alert(result)
                    displayGoalList(tno)
                },
                error: function(jqXHR) {
                    alert(jqXHR.responseText);
                }
            });
        });

        $("#member").on("click", "#memberTaskWrtBtn", function () {
            let id = $(this).parent().attr("data-id")
            $("input[name=task]").attr("data-id", id)
        })

        $("#taskWrtBtn").on("click", function() {
            let id = $("input[name=task]").attr("data-id");
            let task = $("input[name=task]").val();

            if(task.trim()==="") {
                alert("Write the task");
                $("input[name=task]").focus();
                return;
            }

            $.ajax({
                type: "POST",
                url: "/collaborateam/tasks",
                headers: {"content-type": "application/json"},
                data: JSON.stringify({tno: tno, member: id, name: task}),
                success: function(result){
                    alert(result)
                    displayTaskList(tno, id)
                },
                error: function(jqXHR) {
                    alert(jqXHR.responseText);
                }
            });

            $("input[name=task]").val("");
        });

        $("#tasks").on("click", "#taskModBtn", function () {
            let tano = $(this).parent().attr("data-tano")
            let task = $("span.task", $(this).parent()).text();
            let member = $(this).parent().attr("data-id")

            $("input[name=task]").attr("data-tano", tano);
            $("input[name=task]").attr("data-id", member);
            $("input[name=task]").val(task);
        })

        $("#taskUpdBtn").on("click", function () {
            let tano = $("input[name=task]").attr("data-tano");
            let task = $("input[name=task]").val();
            let member = $("input[name=task]").attr("data-id");

            console.log(tano)
            console.log(task)
            console.log(member)

            if(task.trim()==="") {
                alert("Write the task");
                $("input[name=task]").focus();
                return;
            }

            $.ajax({
                type: "PATCH",
                url: "/collaborateam/tasks/"+tano,
                headers: {"content-type": "application/json"},
                data: JSON.stringify({tano: tano, name: task}),
                success: function(result){
                    alert(result)
                    displayMemberList(tno)
                },
                error: function(jqXHR) {
                    alert(jqXHR.responseText);
                }
            });
        })

        $("#tasks").on("click", "#taskRemBtn", function () {
            let tano = $(this).parent().attr("data-tano")

            $.ajax({
                type: "DELETE",
                url: "/collaborateam/tasks/"+tano,
                success: function(result){
                    alert(result);
                    displayMemberList(tno)
                },
                error: function(jqXHR) {
                    alert(jqXHR.responseText);
                }
            });
        });
    });

    function displayMemberList(tno) {
        $.ajax({
            type: "GET",
            url: "/collaborateam/members?tno="+tno,
            success : function(result){
                $("#member").html(formatMember(result));
            },
            error : function(){ alert("Failed to load the member list")}
        });
    }

    function formatMember(members) {
        let loginId = "${loginId}";
        let tno = $("input[name=tno]").val();
        let leader = $("input[name=leader]").val();
        let tmp = "<ul>";

        members.forEach(function(member) {
            tmp += "<li data-mno=" + member.mno
            tmp += " data-id=" + member.id + ">"
            tmp += " member : <span class='member'>" + member.id + "</span>"
            if(leader === loginId && leader !== member.id)
                tmp += " <button type='button' id='memberRemBtn' class='btn'>Remove</button>"
            tmp += " <button type='button' id='memberTaskWrtBtn' class='btn'>New Task</button>"
            displayTaskList(tno, member.id)
            tmp += "</li>"
        })
        tmp += "</ul>"
        return tmp
    }

    function displayTaskList(tno, member) {
        $.ajax({
            type: "GET",
            url: "/collaborateam/tasks?tno="+tno+"&member="+member,
            success : function(result){
                $("#tasks").html(formatTask(result, member));
            },
            error : function(){ alert("Failed to load the task list")}
        });
    }

    function formatTask(tasks, id) {
        let tmp = "->";

        tasks.forEach(function(task) {
            tmp += "<ul>"
            tmp += "<li data-tano=" + task.tano
            tmp += " data-id=" + id + ">"

            tmp += " task : <span class='task'>" + task.name + "</span>"
            tmp += " <button type='button' id='taskModBtn' class='btn'>Modify</button>"
            tmp += " <button type='button' id='taskRemBtn' class='btn'>Remove</button>"
            tmp += "</li>"
            tmp += "</ul>"
        })
        return tmp
    }

    function displayGoalList(tno) {
        $.ajax({
            type: "GET",
            url: "/collaborateam/goals?tno="+tno,
            success : function(result){
                $("#goals").html(formatGoal(result));
            },
            error : function(){ alert("Failed to load the goal list")}
        });
    }

    function formatGoal(goals) {
        let tmp = "<ul>";

        goals.forEach(function(goal) {
            tmp += "<li data-gno=" + goal.gno + ">"
            tmp += " goal : <span class='goal'>" + goal.name + "</span>"
            tmp += " <button type='button' id='goalModBtn' class='btn'>Modify</button>"
            tmp += " <button type='button' id='goalRemBtn' class='btn'>Remove</button>"
            tmp += "</li>"
        })
        tmp += "</ul>"
        return tmp
    }

    function isTeamEmpty() {
        const name = $("input[name=name]").val().trim();

        if (name === "") {
            alert("Name is empty");
            return false;
        }
        return true;
    }
</script>