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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/water.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="<c:url value='/js/team.js'/>"></script>
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
        <input type="text" name="name" value="<c:out value='${teamDto.name}'/>" placeholder="name" readonly>
        <input type="text" name="detail" value="<c:out value='${teamDto.detail}'/>" placeholder="detail" readonly>
        <button type="button" id="teamCrtBtn" class="btn">Create</button>
        <c:if test="${loginId eq teamDto.leader}">
            <button type="button" id="teamUpdBtn" class="btn">Modify</button>
            <button type="button" id="teamDelBtn" class="btn">Remove</button>
        </c:if>
        <button type="button" id="teamListBtn" class="btn">List</button>
    </form>
</div>
<button type="button" id="teamWithdrawBtn">Leave</button>
<div id="member-list"></div>
<div id="task-function">
    Task: <input type="text" name="task">
    <button type="button" id="taskCrtBtn">Write</button>
    <button type="button" id="taskUpdBtn">Modify</button>
</div>
<div id="goal-function">
    Goal: <input type="text" name="goal">
    <button type="button" id="goalCrtBtn">Write</button>
    <button type="button" id="goalUpdBtn">Modify</button>
</div>
<div id="goal-list"></div>
</body>
<script>
    const user = "${loginId}";
    const msg = "${msg}";
    const mode = "${mode}";
    const queryString = "${teamListCondition.queryString}";
</script>