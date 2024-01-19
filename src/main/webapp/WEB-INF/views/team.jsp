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
</head>
<body>
<div>
    <ul>
        <li><a href="<c:url value='/'/>">Logo</a></li>
        <li><a href="<c:url value='/'/>">Home</a></li>
        <li><a href="">About</a></li>
        <li><a href="<c:url value='/board/list'/>">Team Building</a></li>
        <li><a href="<c:url value='/team/list'/>">Team Management</a></li>
        <li><a href="">Contact Us</a></li>
        <li><a href="<c:url value='${loginInOutLink}'/>"><c:out value="${loginInOut}"/></a></li>
        ${loginId}
        <li><a href="">My Account</a></li>
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
</body>
<script>
    $(document).ready(function() {
        let msg = "${msg}";
        let mode = "${mode}";

        if(msg === "TEAM_CRT_ERR") alert("Failed to create the team");

        let name = $("input[name=name]");
        let description = $("textarea[name=description]");

        if(mode === "CRT_TEAM") {
            name.attr("readonly", false);
            description.attr("readonly", false);
        }

        $("#teamCrtBtn").on("click", function () {
            if(!isTeamEmpty()) return;
            let form = $("#form");

            form.attr("action", "<c:url value='/team/create'/>");
            form.attr("method", "post");
            form.submit();
        });
    });

    function isTeamEmpty() {
        const name = $("input[name=name]").val().trim();

        if (name === "") {
            alert("Name is empty");
            return false;
        }
        return true;
    }
</script>