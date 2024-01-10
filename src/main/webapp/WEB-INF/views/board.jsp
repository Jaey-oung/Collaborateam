<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginInOut" value="${loginId==null ? 'Login' : 'Logout'}"/>
<c:set var="loginInOutLink" value="${loginId==null ? '/login/login' : '/login/logout'}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Board</title>
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
    <h1>${mode == "NEW_BOARD" ? "Write" : "Read"} Board</h1>
    <form action="" id="form">
        <input type="text" name="bno" value="<c:out value='${boardDto.bno}'/>" readonly>
        <input type="text" id="title" name="title" value="<c:out value='${boardDto.title}'/>" readonly>
        <textarea name="content" id="content" cols="30" rows="10" value="<c:out value='${boardDto.content}'/>" readonly></textarea>
        <button type="button" id="writeBtn" class="btn">Write</button>
        <button type="button" id="modifyBtn" class="btn">Modify</button>
        <button type="button" id="removeBtn" class="btn">Remove</button>
        <button type="button" id="listBtn" class="btn">List</button>
    </form>
</div>
</body>
<script>
    $(document).ready(function() {
        let msg = "${msg}";
        let mode = "${mode}";

        if(msg === "BOARD_WRT_ERR") alert("Failed to create the board");

        let title = $("#title");
        let content = $("#content");
        let writeBtn = $("#writeBtn");
        let modifyBtn = $("#modifyBtn");
        let removeBtn = $("#removeBtn");
        let listBtn = $("#listBtn");

        if (mode === "WRITE_BOARD") {
            title.attr("readonly", false);
            content.attr("readonly", false);
            modifyBtn.hide();
            removeBtn.hide();
            listBtn.hide();
        } else {
            writeBtn.hide();
        }

        writeBtn.on("click", function() {
            let form = $("#form");
            form.attr("action", "<c:url value='/board/write'/>");
            form.attr("method", "post");
            form.submit();
        });

        removeBtn.on("click", function() {
            if(!confirm("Would you like to delete the board?")) return;
            let form = $("#form");
            form.attr("action", "<c:url value='/board/remove'/>?page=${page}&pageSize=${pageSize}");
            form.attr("method", "post");
            form.submit();
        });

        listBtn.on("click", function() {
            location.href = "<c:url value='/board/list'/>?page=${page}&pageSize=${pageSize}";
        });
    });
</script>
</html>