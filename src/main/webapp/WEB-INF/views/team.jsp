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
    <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/team.css'/>">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="<c:url value='/js/team.js'/>"></script>
</head>
<body>
    <div class="wrapper">
        <div class="header">
            <h1><a href="<c:url value='/'/>">Collaborateam</a></h1>
            <div class="nav">
                <ul>
                    <li><a href="<c:url value='/'/>">Home</a></li>
                    <li><a href="<c:url value='/board/list'/>">Team Building</a></li>
                    <li><a href="<c:url value='/team/list'/>">Team Management</a></li>
                    <li><a href="<c:url value='${loginInOutLink}'/>"><c:out value="${loginInOut}"/></a></li>
                </ul>
            </div>
        </div>
        <div class="team">
            <div class="team-container">
                <form id="form">
                    <div class="team-btn">
                        <div class="left-btn">
                            <button type="button" id="teamListBtn" class="btn"><i class="bx bx-left-arrow-alt"></i>List</button>
                        </div>
                        <div class="right-btn">
                            <button type="button" id="teamCrtBtn" class="btn"><i class="bx bx-pencil"></i>Create</button>
                            <c:if test="${loginId eq teamDto.leader}">
                                <button type="button" id="teamUpdBtn" class="btn"><i class="bx bx-edit"></i>Modify</button>
                                <button type="button" id="teamDelBtn" class="btn"><i class="bx bx-trash"></i>Remove</button>
                            </c:if>
                            <button type="button" id="teamWithdrawBtn" class="btn"><i class='bx bx-door-open'></i>Leave</button>
                        </div>
                    </div>
                    <label>
                        <input type="hidden" name="tno" value="<c:out value='${teamDto.tno}'/>" readonly>
                    </label>
                    <div class="leader">
                        <label>
                            <h2>Leader</h2>
                            <input type="text" name="leader" value="<c:out value='${teamDto.leader}'/>" readonly>
                        </label>
                    </div>
                    <label>
                        <h2>Team</h2>
                        <input type="text" name="name" value="<c:out value='${teamDto.name}'/>" placeholder="name" readonly>
                    </label>
                    <label>
                        <h2>Detail</h2>
                        <textarea name="detail" readonly><c:out value="${teamDto.detail}"/></textarea>
                    </label>
                </form>
                <div class="task-container">
                    <div id="task-function">
                        <label>
                            <h2>Task</h2>
                            <input type="text" name="task">
                        </label>
                        <div class="task-btn">
                            <button type="button" id="taskCrtBtn" class="btn">Write</button>
                            <button type="button" id="taskUpdBtn" class="btn">Write</button>
                        </div>
                    </div>
                    <h2>Task Allocation</h2>
                    <div id="member-list">
                    </div>
                </div>
                <div class="goal-container">
                    <div id="goal-function">
                        <h2>Goal</h2>
                        <label>
                            <input type="text" name="goal">
                        </label>
                        <button type="button" id="goalCrtBtn" class="btn">Write</button>
                        <button type="button" id="goalUpdBtn" class="btn">Write</button>
                    </div>
                    <div id="goal-list"></div>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    const user = "${loginId}";
    const msg = "${msg}";
    const mode = "${mode}";
    const queryString = "${teamListCondition.queryString}";
</script>