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
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/boardList.css'/>">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="<c:url value='/js/menu.js'/>"></script>
    <script src="<c:url value='/js/boardList.js'/>"></script>
    <script src="<c:url value='/js/boardFilter.js'/>"></script>
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
            <div class="hamburger-nav"><i class="bx bx-menu"></i></div>
        </div>
        <div class="board-list">
            <div class="search-container">
                <form action="<c:url value="/board/list"/>" method="get">
                    <div class="search-box">
                        <div class="search-element">
                            <button type="button" class="new-board" id="boardCrtBtn">
                                <i class="bx bx-pencil"></i>New Board
                            </button>
                        </div>
                        <div class="search-element">
                            <h2>Field</h2>
                            <label for="field">
                                <select class="search-option" name="field" id="field"></select>
                            </label>
                        </div>
                        <div class="search-element">
                            <h2>Specialization</h2>
                            <label for="spec">
                                <select class="search-option" name="spec" id="spec"></select>
                            </label>
                        </div>
                        <div class="search-element">
                            <h2>Zone</h2>
                            <label>
                                <select class="search-option" name="zone">
                                    <c:forEach var="zone" items="${zones}">
                                        <option value="${zone.key}" ${zone.key == pagination.pc.zone ? "selected" : ""}>${zone.value}</option>
                                    </c:forEach>
                                </select>
                            </label>
                        </div>
                        <div class="search-element">
                            <h2>Criteria</h2>
                            <label>
                                <select class="search-option" name="option">
                                    <c:forEach var="option" items="${options}">
                                        <option value="${option.key}" ${option.key == pagination.pc.option ? "selected" : ""}>${option.value}</option>
                                    </c:forEach>
                                </select>
                            </label>
                        </div>
                        <div class="search-element">
                            <h2>Keyword</h2>
                            <label>
                                <input type="text" class="search-option" name="keyword" value="<c:out value='${pagination.pc.keyword}'/>" placeholder="Search">
                            </label>
                            <button type="submit" class="search-button">
                                <i class="bx bx-search"></i>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
            <table>
                <tr>
                    <th class="no">No</th>
                    <th class="title">Title</th>
                    <th class="writer">Name</th>
                    <th class="reg-date">Register Date</th>
                    <th class="view-cnt">View Count</th>
                </tr>
                <c:forEach var="boardDto" items="${list}">
                    <tr>
                        <td class="no"><c:out value="${boardDto.bno}"/></td>
                        <td class="title"><a href="<c:url value='/board/read${pagination.pc.queryString}&bno=${boardDto.bno}'/>"><c:out value="${boardDto.title}"/></a></td>
                        <td class="writer"><c:out value="${boardDto.writer}"/></td>
                        <c:choose>
                            <c:when test="${boardDto.reg_date.time >= today}">
                                <td class="reg-date"><fmt:formatDate value="${boardDto.reg_date}" pattern="HH:mm" type="time"/></td>
                            </c:when>
                            <c:otherwise>
                                <td class="reg-date"><fmt:formatDate value="${boardDto.reg_date}" pattern="yyyy-MM-dd" type="date"/></td>
                            </c:otherwise>
                        </c:choose>
                        <td class="view-cnt"><c:out value="${boardDto.view_cnt}"/></td>
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
                            <a class="page" href="<c:url value='/board/list${pagination.pc.getQueryString(pagination.beginPage-1, pagination.pc.field, pagination.pc.spec, pagination.pc.option, pagination.pc.keyword, pagination.pc.zone)}'/>">&lt;</a>
                        </c:if>
                        <c:forEach var="i" begin="${pagination.beginPage}" end="${pagination.endPage}">
                            <a class="page ${i==pagination.pc.page? 'pagination-active' : ""}" href="<c:url value='/board/list${pagination.pc.getQueryString(i, pagination.pc.field, pagination.pc.spec, pagination.pc.option, pagination.pc.keyword, pagination.pc.zone)}'/>">${i}</a>
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