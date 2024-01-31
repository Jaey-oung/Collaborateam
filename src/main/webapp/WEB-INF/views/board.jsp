<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginInOut" value="${empty loginId ? 'Login' : 'Logout'}"/>
<c:set var="loginInOutLink" value="${empty loginId ? '/login/login' : '/login/logout'}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Board</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/water.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="<c:url value='/js/board.js'/>"></script>
    <script src="<c:url value='/js/boardFilter.js'/>"></script>
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
        <label>
            <input type="text" name="writer" value="<c:out value='${boardDto.writer}'/>" readonly>
        </label>
        <label for="field">
            <select name="field" id="field"></select>
        </label>
        <label for="spec">
            <select name="spec" id="spec"></select>
        </label>
        <input type="hidden" name="bno" value="<c:out value='${boardDto.bno}'/>">
        <label>
            <input type="text" name="title" value="<c:out value='${boardDto.title}'/>" placeholder="title" readonly>
        </label>
        <label>
            <input type="text" name="content" value="<c:out value='${boardDto.content}'/>" placeholder="content" readonly>
        </label>
        <button type="button" id="boardCrtBtn" class="btn">Write</button>
        <c:if test="${loginId eq boardDto.writer}">
            <button type="button" id="boardUpdBtn" class="btn">Modify</button>
            <button type="button" id="boardDelBtn" class="btn">Remove</button>
        </c:if>
        <button type="button" id="boardListBtn" class="btn">List</button>
    </form>
</div>
<c:if test="${loginId ne boardDto.writer}">
    <div id="invite-function">
        <button type="button" id="teamInviteBtn" class="btn">Invite</button>
        <div id="team-list"></div>
    </div>
</c:if>
<div id="comment-function">
    comment: <input type="text" name="comment">
    <button type="button" id="commentCrtBtn">Write</button>
    <button type="button" id="commentUpdBtn">Write</button>
    <div id="comment-list"></div>
    <div id="comment-reply">
        <input type="text" name="commentRep">
        <button type="button" id="commentRepCrtBtn">Write</button>
    </div>
</div>
</body>
<script>
    const user = "${loginId}";
    const writer = "${boardDto.writer}";
    const msg = "${msg}";
    const mode = "${mode}";
    const selectField = "${boardDto.field}";
    const selectSpec = "${boardDto.spec}";
    const queryString = "${boardListCondition.queryString}";
</script>
</html>