<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginInOut" value="${empty loginId ? 'Login' : 'Logout'}"/>
<c:set var="loginInOutLink" value="${empty loginId ? '/login/login' : '/login/logout'}"/>
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
        <li><a href="<c:url value='${loginInOutLink}'/>"><c:out value="${loginInOut}"/></a></li>
        ${loginId}
        <li><a href="">My Account</a></li>
    </ul>
</div>
<div>
    <h1></h1>
    <form action="" id="form">
        <input type="text" name="bno" value="<c:out value='${boardDto.bno}'/>" readonly>
        <input type="text" name="title" value="<c:out value='${boardDto.title}'/>" readonly>
        <textarea name="content" cols="30" rows="10" readonly><c:out value="${boardDto.content}"/></textarea>
        <button type="button" id="writeBtn" class="btn">Write</button>
        <c:if test="${loginId eq boardDto.writer}">
        <button type="button" id="modifyBtn" class="btn">Modify</button>
        <button type="button" id="removeBtn" class="btn">Remove</button>
        </c:if>
        <button type="button" id="listBtn" class="btn">List</button>
    </form>
</div>
</body>
<script>
    $(document).ready(function() {
        let msg = "${msg}";
        let mode = "${mode}";

        let title = $("input[name=title]");
        let content = $("textarea[name=content]");
        let writeBtn = $("#writeBtn");
        let modifyBtn = $("#modifyBtn");
        let removeBtn = $("#removeBtn");
        let listBtn = $("#listBtn");

        if(msg === "BOARD_WRT_ERR") alert("Failed to create the board");
        if(msg === "BOARD_DEL_ERR") alert("Failed to delete the board");
        if(msg === "BOARD_MOD_ERR") alert("Failed to modify the board");

        if(mode === "WRT_BOARD") {
            $("h1").html("Write Board");
            title.attr("readonly", false);
            content.attr("readonly", false);
        }

        if(mode === "READ_BOARD") {
            $("h1").html("Read Board");
            writeBtn.hide();
        }

        writeBtn.on("click", function() {
            let form = $("#form");

            form.attr("action", "<c:url value='/board/write'/>");
            form.attr("method", "post");
            form.submit();
        });

        modifyBtn.on("click", function() {
            let form = $("#form");

            if(title.attr("readonly") && content.attr("readonly")) {
                $("h1").html("Modify Board");
                title.attr("readonly", false);
                content.attr("readonly", false);
                modifyBtn.html("Write");
                removeBtn.hide();
                return;
            }

            form.attr("action", "<c:url value='/board/modify${searchCondition.queryString}'/>");
            form.attr("method", "post");
            form.submit();
        });

        removeBtn.on("click", function() {
            if(!confirm("Would you like to delete the board?")) return;
            let form = $("#form");

            form.attr("action", "<c:url value='/board/remove${searchCondition.queryString}'/>");
            form.attr("method", "post");
            form.submit();
        });

        listBtn.on("click", function() {
            location.href = "<c:url value='/board/list${searchCondition.queryString}'/>";
        });
    });
</script>
</html>