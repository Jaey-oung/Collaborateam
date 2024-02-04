<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginInOut" value="${empty loginId ? 'Login' : 'Logout'}"/>
<c:set var="loginInOutLink" value="${empty loginId ? '/login/login' : '/login/logout'}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Board</title>
    <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/board.css'/>">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="<c:url value='/js/menu.js'/>"></script>
    <script src="<c:url value='/js/board.js'/>"></script>
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
        <div class="board">
            <div class="board-container">
                <div class="board-btn">
                    <div class="left-btn">
                        <button type="button" id="boardListBtn" class="btn"><i class="bx bx-left-arrow-alt"></i>List</button>
                    </div>
                    <div class="center-btn">
                        <c:if test="${loginId ne boardDto.writer}">
                            <div id="invite-function">
                                <button type="button" id="teamInviteBtn" class="btn"><i class="bx bx-envelope"></i>Invite</button>
                                <div class="modal">
                                    <div class="modal-content">
                                        <span class="close">&times;</span>
                                        <h2>Team List</h2>
                                        <div id="team-list"></div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                    <div class="right-btn">
                        <button type="button" id="boardCrtBtn" class="btn"><i class="bx bx-pencil"></i>Write</button>
                        <c:if test="${loginId eq boardDto.writer}">
                            <button type="button" id="boardUpdBtn" class="btn"><i class="bx bx-edit"></i>Modify</button>
                            <button type="button" id="boardDelBtn" class="btn"><i class="bx bx-trash"></i>Remove</button>
                        </c:if>
                    </div>
                </div>
                <div class="board-container">
                    <form id="form">
                        <label>
                            <h2>Writer</h2>
                            <input type="text" name="writer" value="<c:out value='${boardDto.writer}'/>" readonly>
                        </label>
                        <div class="board-option">
                            <label for="field">
                                <h2>Field</h2>
                                <select name="field" id="field"></select>
                            </label>
                            <label for="spec">
                                <h2>Specialization</h2>
                                <select name="spec" id="spec"></select>
                            </label>
                        </div>
                        <input type="hidden" name="bno" value="<c:out value='${boardDto.bno}'/>">
                        <label>
                            <h2>Title</h2>
                            <input type="text" name="title" value="<c:out value='${boardDto.title}'/>" placeholder="Title" readonly>
                        </label>
                        <label>
                            <h2>Content</h2>
                            <textarea name="content" placeholder="Content" readonly><c:out value="${boardDto.content}"/></textarea>
                        </label>
                    </form>
                    <div class="comment-container">
                        <div id="comment-function">
                            <label>
                                <h2>Comment</h2>
                                <textarea name="comment" placeholder="Write the comment"></textarea>
                            </label>
                            <button type="button" class="btn" id="commentCrtBtn"><i class="bx bx-pencil"></i>Write</button>
                            <button type="button" class="btn" id="commentUpdBtn"><i class="bx bx-pencil"></i>Write</button>
                        </div>
                        <div id="comment-list"></div>
                        <div id="comment-reply">
                            <label>
                                <textarea name="commentRep" placeholder="Write the comment"></textarea>
                            </label>
                            <button type="button" class="btn" id="commentRepCrtBtn"><i class="bx bx-pencil"></i>Write</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    const user = "${loginId}";
    const writer = "${boardDto.writer}";
    const msg = "${msg}";
    const mode = "${mode}";
    const selectField = "${boardDto.field}";
    const selectSpec = "${boardDto.spec}";
    const queryString = "${boardListCondition.queryString}";
</script>
</html>