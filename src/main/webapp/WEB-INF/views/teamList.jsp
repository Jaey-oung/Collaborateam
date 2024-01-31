<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginInOut" value="${empty loginId ? 'Login' : 'Logout'}"/>
<c:set var="loginInOutLink" value="${empty loginId ? '/login/login' : '/login/logout'}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>TeamList</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/water.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="<c:url value='/js/teamList.js'/>"></script>
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
<button type="button" id="teamCrtBtn">New Team</button>
<button type="button" id="teamInviteBtn">Invites</button>
<div id="invite-function"></div>
<div>
    <table border="1">
        <tr>
            <th>Leader</th>
            <th>Team</th>
            <th>Register Date</th>
        </tr>
        <c:forEach var="teamDto" items="${list}">
            <tr>
                <td><c:out value="${teamDto.leader}"/></td>
                <td><a href="<c:url value='/team/read${pagination.pc.queryString}&tno=${teamDto.tno}'/>"><c:out value="${teamDto.name}"/></a></td>
                <td><c:out value="${teamDto.reg_date}"/></td>
            </tr>
        </c:forEach>
    </table>
    <div>
        <c:if test="${totalCnt==null || totalCnt==0}">
            <div>No results found</div>
        </c:if>
        <c:if test="${totalCnt!=null && totalCnt!=0}">
            <c:if test="${pagination.showPrev}">
                <a href="<c:url value='/team/list${pagination.pc.getQueryString(pagination.beginPage-1)}'/>">&lt;</a>
            </c:if>
            <c:forEach var="i" begin="${pagination.beginPage}" end="${pagination.endPage}">
                <a href="<c:url value='/team/list${pagination.pc.getQueryString(i)}'/>">${i}</a>
            </c:forEach>
            <c:if test="${pagination.showNext}">
                <a href="<c:url value='/team/list${pagination.pc.getQueryString(pagination.endPage+1)}'/>">&gt;</a>
            </c:if>
        </c:if>
    </div>
</div>
<script>
    const msg = "${msg}";
    const id = "${loginId}";
</script>