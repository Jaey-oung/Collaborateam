<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginInOut" value="${empty loginId ? 'Login' : 'Logout'}"/>
<c:set var="loginInOutLink" value="${empty loginId ? '/login/login' : '/login/logout'}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>TeamList</title>
    <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/teamList.css'/>">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="<c:url value='/js/teamList.js'/>"></script>
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
        <div class="team-list">
            <div class="team-function">
                <button type="button" class="btn" id="teamCrtBtn"><i class="bx bx-group"></i>New Team</button>
                <button type="button" class="btn" id="teamInviteBtn"><i class="bx bx-envelope"></i>Invites</button>
                <div class="modal">
                    <div class="modal-content">
                        <span class="close">&times;</span>
                        <h1>Invite List</h1>
                        <div id="invite-list"></div>
                    </div>
                </div>
            </div>
            <div>
                <table>
                    <tr>
                        <th class="leader">Leader</th>
                        <th class="team">Team</th>
                        <th class="reg-date">Register Date</th>
                    </tr>
                    <c:forEach var="teamDto" items="${list}">
                        <tr>
                            <td class="leader"><c:out value="${teamDto.leader}"/></td>
                            <td class="team"><a href="<c:url value='/team/read${pagination.pc.queryString}&tno=${teamDto.tno}'/>"><c:out value="${teamDto.name}"/></a></td>
                            <c:choose>
                                <c:when test="${teamDto.reg_date.time >= today}">
                                    <td class="reg-date"><fmt:formatDate value="${teamDto.reg_date}" pattern="HH:mm" type="time"/></td>
                                </c:when>
                                <c:otherwise>
                                    <td class="reg-date"><fmt:formatDate value="${teamDto.reg_date}" pattern="yyyy-MM-dd" type="date"/></td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                </table>
                <div class="pagination-container">
                    <div class="pagination">
                        <c:if test="${totalCnt==null || totalCnt==0}">
                            <div>No results found</div>
                        </c:if>
                        <c:if test="${totalCnt!=null && totalCnt!=0}">
                            <c:if test="${pagination.showPrev}">
                                <a class="page" href="<c:url value='/team/list${pagination.pc.getQueryString(pagination.beginPage-1)}'/>">&lt;</a>
                            </c:if>
                            <c:forEach var="i" begin="${pagination.beginPage}" end="${pagination.endPage}">
                                <a class="page ${i==pagination.pc.page? 'pagination-active' : ""}" href="<c:url value='/team/list${pagination.pc.getQueryString(i)}'/>">${i}</a>
                            </c:forEach>
                            <c:if test="${pagination.showNext}">
                                <a class="page" href="<c:url value='/team/list${pagination.pc.getQueryString(pagination.endPage+1)}'/>">&gt;</a>
                            </c:if>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    const msg = "${msg}";
    const id = "${loginId}";
</script>