<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginInOut" value="${empty loginId ? 'Login' : 'Logout'}"/>
<c:set var="loginInOutLink" value="${empty loginId ? '/login/login' : '/login/logout'}"/>
<c:set var="zones" value="${{'A': 'All', 'N': 'Newbie'}}"/>
<c:set var="options" value="${{'A': 'All', 'T': 'Title', 'W': 'Writer', 'TC': 'Title+Content'}}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>BoardList</title>
    <link rel="stylesheet" href="<c:url value='/css/boardList.css'/>">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="<c:url value='/js/boardList.js'/>"></script>
</head>
<body>
<div class="menu">
    <ul>
        <li><a href="<c:url value='/'/>">Logo</a></li>
        <li><a href="<c:url value='/'/>">Home</a></li>
        <li><a href="<c:url value='/board/list'/>">Team Building</a></li>
        <li><a href="<c:url value='/team/list'/>">Team Management</a></li>
        <li><a href="<c:url value='${loginInOutLink}'/>"><c:out value="${loginInOut}"/></a></li>
        ${loginId}
    </ul>
</div>
<div class="wrapper">
    <div class="board-container">
        <div class="search-container">
            <form action="<c:url value="/board/list"/>" class="search-form" method="get">
                <label for="field">
                    <select class="search-option" name="field" id="field"></select>
                </label>
                <label for="spec">
                    <select class="search-option" name="spec" id="spec"></select>
                </label>
                <label>
                    <select class="search-option" name="zone">
                        <c:forEach var="zone" items="${zones}">
                            <option value="${zone.key}" ${zone.key == pagination.pc.zone ? "selected" : ""}>${zone.value}</option>
                        </c:forEach>
                    </select>
                </label>
                <label>
                    <select class="search-option" name="option">
                        <c:forEach var="option" items="${options}">
                            <option value="${option.key}" ${option.key == pagination.pc.option ? "selected" : ""}>${option.value}</option>
                        </c:forEach>
                    </select>
                </label>
                <input type="text" class="search-input" name="keyword" value="<c:out value='${pagination.pc.keyword}'/>" placeholder="Search">
                <input type="submit" class="search-button" value="Search">
            </form>
            <button type="button" class="btn-write" id="writeBtn">New Board</button>
        </div>
        <table border="1">
            <tr>
                <th class="no">No</th>
                <th class="title">Title</th>
                <th class="writer">Name</th>
                <th class="regDate">Register Date</th>
                <th class="viewcnt">View Count</th>
            </tr>
            <c:forEach var="boardDto" items="${list}">
                <tr>
                    <td class="no"><c:out value="${boardDto.bno}"/></td>
                    <td class="title"><a href="<c:url value='/board/read${pagination.pc.queryString}&bno=${boardDto.bno}'/>"><c:out value="${boardDto.title}"/></a></td>
                    <td class="writer"><c:out value="${boardDto.writer}"/></td>
                    <c:choose>
                        <c:when test="${boardDto.reg_date.time >= today}">
                            <td class="regdate"><fmt:formatDate value="${boardDto.reg_date}" pattern="HH:mm" type="time"/></td>
                        </c:when>
                        <c:otherwise>
                            <td class="regdate"><fmt:formatDate value="${boardDto.reg_date}" pattern="yyyy-MM-dd" type="date"/></td>
                        </c:otherwise>
                    </c:choose>
                    <td class="viewcnt"><c:out value="${boardDto.view_cnt}"/></td>
                </tr>
            </c:forEach>
        </table>
        <div class="pagination-container">
            <div class="pagination">
                <c:if test="${totalCnt==null || totalCnt==0}">
                    <div> 게시물이 없습니다. </div>
                </c:if>
                <c:if test="${totalCnt!=null && totalCnt!=0}">
                    <c:if test="${pagination.showPrev}">
                        <a class="page" href="<c:url value='/board/list${pagination.pc.getQueryString(pagination.beginPage-1, pagination.pc.field, pagination.pc.spec, pagination.pc.option, pagination.pc.keyword, pagination.pc.zone)}'/>">&lt;</a>
                    </c:if>
                    <c:forEach var="i" begin="${pagination.beginPage}" end="${pagination.endPage}">
                        <a class="page" href="<c:url value='/board/list${pagination.pc.getQueryString(i, pagination.pc.field, pagination.pc.spec, pagination.pc.option, pagination.pc.keyword, pagination.pc.zone)}'/>">${i}</a>
                    </c:forEach>
                    <c:if test="${pagination.showNext}">
                        <a class="page" href="<c:url value='/board/list${pagination.pc.getQueryString(pagination.endPage+1, pagination.pc.field, pagination.pc.spec, pagination.pc.option, pagination.pc.keyword, pagination.pc.zone)}'/>">&gt;</a>
                    </c:if>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    const msg = "${msg}";
    const selectField = "${pagination.pc.field}";
    const selectSpec = "${pagination.pc.spec}";
</script>
</html>