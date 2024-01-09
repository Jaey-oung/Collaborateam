<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginInOut" value="${loginId==null ? 'Login' : 'Logout'}"/>
<c:set var="loginInOutLink" value="${loginId==null ? '/login/login' : '/login/logout'}"/>
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
        <li><a href="">Team Management</a></li>
        <li><a href="">Contact Us</a></li>
        <li><a href="<c:url value='${loginInOutLink}'/>">${loginInOut}</a></li>
        ${loginId}
        <li><a href="">My Account</a></li>
    </ul>
</div>
<div>
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
            <td><a href="<c:url value='/board/read'/>?bno=${boardDto.bno}&page=${page}&pageSize=${pageSize}"><c:out value="${boardDto.title}"/></a></td>
            <td><c:out value="${boardDto.writer}"/></td>
            <td><c:out value="${boardDto.reg_date}"/></td>
            <td><c:out value="${boardDto.view_cnt}"/></td>
        </tr>
        </c:forEach>
    </table>
    <div>
        <c:if test="${pagination.showPrev}">
            <a href="<c:url value='/board/list'/>?page=${pagination.beginPage-1}&pageSize=${pagination.pageSize}">&lt;</a>
        </c:if>
        <c:forEach var="i" begin="${pagination.beginPage}" end="${pagination.endPage}">
            <a href="<c:url value='/board/list'/>?page=${i}&pageSize=${pagination.pageSize}">${i}</a>
        </c:forEach>
        <c:if test="${pagination.showNext}">
            <a href="<c:url value='/board/list'/>?page=${pagination.endPage+1}&pageSize=${pagination.pageSize}">&gt;</a>
        </c:if>
    </div>
</div>
</body>
<script>
    $(document).ready(function() {
        let msg = "${msg}";
        if(msg === "LIST_LOAD_ERR") {
            alert("List load has failed")
        }
        if(msg === "BOARD_LOAD_ERR") {
            alert("Board load has failed")
        }

    })
</script>
</html>