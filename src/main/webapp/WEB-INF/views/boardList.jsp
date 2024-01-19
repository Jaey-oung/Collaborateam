<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginInOut" value="${empty loginId ? 'Login' : 'Logout'}"/>
<c:set var="loginInOutLink" value="${empty loginId ? '/login/login' : '/login/logout'}"/>
<c:set var="field" value="${{'A' : 'All', 'IT' : 'IT', 'FN' : 'Finance'}}"/>
<c:set var="selectedField" value="${pagination.sc.field}"/>
<c:set var="spec" value="${{'A' : 'All', 'WD' : 'Web Development', 'SD' : 'Software Development', 'FA': 'Financial Analysis', 'RM' : 'Risk Management'}}"/>
<c:set var="selectedSpec" value="${pagination.sc.specialization}"/>
<c:set var="option" value="${{'A' : 'All', 'TC' : 'Title+Content', 'T' : 'Title', 'W' : 'Writer'}}"/>
<c:set var="selectedOption" value="${pagination.sc.option}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>BoardList</title>
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
    <form action="<c:url value="/board/list"/>" method="get">
        <select name="field" id="field">
            <c:forEach var="i" items="${field}">
                <option value="${i.key}" ${i.key == selectedField ? 'selected' : ''}>${i.value}</option>
            </c:forEach>
        </select>
        <select name="specialization" id="specialization">
            <c:forEach var="i" items="${spec}">
                <option value="${i.key}" ${i.key == selectedSpec ? 'selected' : ''}>${i.value}</option>
            </c:forEach>
        </select>
        sc = ${pagination.sc}
        <select name="option">
            <c:forEach var="i" items="${option}">
                <option value="${i.key}" ${i.key == selectedOption ? 'selected' : ''}>${i.value}</option>
            </c:forEach>
        </select>
        <input type="text" name="keyword" value="<c:out value='${pagination.sc.keyword}'/>" placeholder="Search">
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
            <td><a href="<c:url value='/board/read${pagination.sc.queryString}&bno=${boardDto.bno}'/>"><c:out value="${boardDto.title}"/></a></td>
            <td><c:out value="${boardDto.writer}"/></td>
            <td><c:out value="${boardDto.reg_date}"/></td>
            <td><c:out value="${boardDto.view_cnt}"/></td>
        </tr>
        </c:forEach>
    </table>
    <div>
        <c:if test="${pagination.showPrev}">
            <a href="<c:url value='/board/list${pagination.sc.getQueryString(pagination.beginPage-1, pagination.sc.field, pagination.sc.specialization)}'/>">&lt;</a>
        </c:if>
        <c:forEach var="i" begin="${pagination.beginPage}" end="${pagination.endPage}">
            <a href="<c:url value='/board/list${pagination.sc.getQueryString(i, pagination.sc.field, pagination.sc.specialization)}'/>">${i}</a>
        </c:forEach>
        <c:if test="${pagination.showNext}">
            <a href="<c:url value='/board/list${pagination.sc.getQueryString(pagination.endPage+1, pagination.sc.field, pagination.sc.specialization)}'/>">&gt;</a>
        </c:if>
    </div>
</div>
</body>
<script>
    $(document).ready(function() {
        let msg = "${msg}";

        if(msg === "BOARD_DEL_OK") alert("Successfully deleted the board");
        if(msg === "BOARD_WRT_OK") alert("Successfully written the board");
        if(msg === "BOARD_MOD_OK") alert("Successfully modified the board");
        if(msg === "BOARD_LIST_LOAD_ERR") alert("Failed to load the board list");

        $("#writeBtn").on("click", function() {
            location.href = "<c:url value='/board/write'/>";
        });
    })
</script>
</html>