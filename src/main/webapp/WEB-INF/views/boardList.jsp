<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/water.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="<c:url value='/js/boardList.js'/>"></script>
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
    <form action="<c:url value="/board/list"/>" method="get">
        pc = ${pagination.pc}
        <label for="field">
            <select name="field" id="field"></select>
        </label>
        <label for="spec">
            <select name="spec" id="spec"></select>
        </label>
        <label>
            <select name="zone">
                <c:forEach var="zone" items="${zones}">
                    <option value="${zone.key}" ${zone.key == pagination.pc.zone ? "selected" : ""}>${zone.value}</option>
                </c:forEach>
            </select>
        </label>
        <label>
            <select name="option">
                <c:forEach var="option" items="${options}">
                    <option value="${option.key}" ${option.key == pagination.pc.option ? "selected" : ""}>${option.value}</option>
                </c:forEach>
            </select>
        </label>
        <input type="text" name="keyword" value="<c:out value='${pagination.pc.keyword}'/>" placeholder="Search">
        <input type="submit" value="Search">
    </form>
    <button type="button" id="writeBtn">New Board</button>
    <table border="1">
        <tr>
            <th>No</th>
            <th>Title</th>
            <th>Name</th>
            <th>Register Date</th>
            <th>View Count</th>
        </tr>
        <c:forEach var="boardDto" items="${list}">
        <tr>
            <td><c:out value="${boardDto.bno}"/></td>
            <td><a href="<c:url value='/board/read${pagination.pc.queryString}&bno=${boardDto.bno}'/>"><c:out value="${boardDto.title}"/></a></td>
            <td><c:out value="${boardDto.writer}"/></td>
            <td><c:out value="${boardDto.reg_date}"/></td>
            <td><c:out value="${boardDto.view_cnt}"/></td>
        </tr>
        </c:forEach>
    </table>
    <div>
        <c:if test="${pagination.showPrev}">
            <a href="<c:url value='/board/list${pagination.pc.getQueryString(pagination.beginPage-1, pagination.pc.field, pagination.pc.spec, pagination.pc.option, pagination.pc.keyword, pagination.pc.zone)}'/>">&lt;</a>
        </c:if>
        <c:forEach var="i" begin="${pagination.beginPage}" end="${pagination.endPage}">
            <a href="<c:url value='/board/list${pagination.pc.getQueryString(i, pagination.pc.field, pagination.pc.spec, pagination.pc.option, pagination.pc.keyword, pagination.pc.zone)}'/>">${i}</a>
        </c:forEach>
        <c:if test="${pagination.showNext}">
            <a href="<c:url value='/board/list${pagination.pc.getQueryString(pagination.endPage+1, pagination.pc.field, pagination.pc.spec, pagination.pc.option, pagination.pc.keyword, pagination.pc.zone)}'/>">&gt;</a>
        </c:if>
    </div>
</div>
</body>
<script>
    const msg = "${msg}";
    const selectField = "${pagination.pc.field}";
    const selectSpec = "${pagination.pc.spec}";
</script>
</html>