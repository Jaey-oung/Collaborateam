<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginInOut" value="${empty loginId ? 'Login' : 'Logout'}"/>
<c:set var="loginInOutLink" value="${empty loginId ? '/login/login' : '/login/logout'}"/>
<c:set var="optionASelected" value="${pagination.sc.option == 'A' || empty pagination.sc.option ? 'selected' : ''}"/>
<c:set var="optionTSelected" value="${pagination.sc.option == 'T' ? 'selected' : ''}"/>
<c:set var="optionWSelected" value="${pagination.sc.option == 'W' ? 'selected' : ''}"/>
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
        <li><a href="<c:url value='${loginInOutLink}'/>"><c:out value="${loginInOut}"/></a></li>
        ${loginId}
        <li><a href="">My Account</a></li>
    </ul>
</div>
<div>
    <form action="<c:url value="/board/list"/>" method="get">
        <select name="option">
            <option value="A" ${optionASelected}>Title+Content</option>
            <option value="T" ${optionTSelected}>Title</option>
            <option value="W" ${optionWSelected}>Writer</option>
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
            <a href="<c:url value='/board/list${pagination.sc.getQueryString(pagination.beginPage-1)}'/>">&lt;</a>
        </c:if>
        <c:forEach var="i" begin="${pagination.beginPage}" end="${pagination.endPage}">
            <a href="<c:url value='/board/list${pagination.sc.getQueryString(i)}'/>">${i}</a>
        </c:forEach>
        <c:if test="${pagination.showNext}">
            <a href="<c:url value='/board/list${pagination.sc.getQueryString(pagination.endPage+1)}'/>">&gt;</a>
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