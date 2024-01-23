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
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/water.css">
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
<button type="button" id="crtBtn">New Team</button>
<button type="button" id="inviteBtn">Invites</button>
<div id="inviteFunction"></div>
<div>
    <table border="1">
        <tr>
            <th>Leader</th>
            <th>Team</th>
            <th>Name</th>
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
        <c:if test="${pagination.showPrev}">
            <a href="<c:url value='/team/list${pagination.pc.getQueryString(pagination.beginPage-1)}'/>">&lt;</a>
        </c:if>
        <c:forEach var="i" begin="${pagination.beginPage}" end="${pagination.endPage}">
            <a href="<c:url value='/team/list${pagination.pc.getQueryString(i)}'/>">${i}</a>
        </c:forEach>
        <c:if test="${pagination.showNext}">
            <a href="<c:url value='/team/list${pagination.pc.getQueryString(pagination.endPage+1)}'/>">&gt;</a>
        </c:if>
    </div>
</div>
<script>
    $(document).ready(function() {
        let msg = "${msg}";
        let id = "${loginId}";

        if(msg === "TEAM_CRT_OK") alert("Successfully created the team");
        if(msg === "TEAM_MOD_OK") alert("Successfully modified the team");
        if(msg === "TEAM_DEL_OK") alert("Successfully deleted the team");
        if(msg === "TEAM_LOAD_ERR") alert("Failed to load the team");
        if(msg === "TEAM_LIST_LOAD_ERR") alert("Failed to load the team list");

        $("#crtBtn").on("click", function() {
            location.href = "<c:url value='/team/create'/>";
        });

        $("#inviteBtn").on("click", function () {
            displayInviteList(id);
        });

        $("#inviteFunction").on("click", "#acceptBtn", function () {
            let ino = $(this).parent().attr("data-ino");
            let tno = $(this).parent().attr("data-tno");

            $.ajax({
                type: "POST",
                url: "/collaborateam/members",
                contentType: "application/json",
                data: JSON.stringify({ tno: tno, id: id, role: "M" }),
                success: function(result){
                    alert(result)
                    $.ajax({
                        type: "DELETE",
                        url: "/collaborateam/invites/"+ino,
                        success: function(result){
                            alert(result)
                            displayInviteList(id)
                        },
                        error: function(jqXHR) {
                            alert(jqXHR.responseText);
                        }
                    });
                },
                error: function(jqXHR) {
                    alert(jqXHR.responseText);
                }
            });
        });

        $("#inviteFunction").on("click", "#declineBtn", function () {
            let ino = $(this).parent().attr("data-ino");
            alert(ino)
            $.ajax({
                type: "DELETE",
                url: "/collaborateam/invites/"+ino,
                success: function(result){
                    alert(result)
                    displayInviteList(id)
                },
                error: function(jqXHR) {
                    alert(jqXHR.responseText);
                }
            });
        });
    });

    function displayInviteList(id) {
        $.ajax({
            type: "GET",
            url: "/collaborateam/invites?id="+id,
            success : function(result){
                $("#inviteFunction").html(formatInvite(result));
            },
            error : function(){ alert("Failed to load the invite list")}
        });
    }

    function formatInvite(invites) {
        let tmp = "<ul>";

        invites.forEach(function(invite) {
            tmp += "<li data-ino=" + invite.ino
            tmp += " data-tno=" + invite.tno + ">"
            tmp += " Team: " + invite.team_name
            tmp += " <button id='acceptBtn'>Accept</button>"
            tmp += " <button id='declineBtn'>Decline</button>"
            tmp += "</li>"
        })
        return tmp + "</ul>"
    }
</script>