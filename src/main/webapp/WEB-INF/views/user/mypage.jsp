<%@ page  language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/resources/css/user/mypage.css">
    <link rel="stylesheet" href="/resources/css/style.css">
    <meta name="_csrf" content="${csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>ProgrammerBlog</title>
</head>
<body>
<div>
    <%--헤더--%>
    <jsp:include page="../common/header.jsp"/>
    <div id="wrap">
        <div class="mypage_top">
            <h3>${userInfo.nickname} 님</h3>
            <div>
                <p>가입일 : ${userInfo.create_date}</p>
                <sec:authorize access="isAuthenticated()">
                    <sec:authentication property="principal.uid" var="uid"/>
                    <c:if test="${uid==userInfo.uid}">
                        <button>수정</button>
                        <button>탈퇴</button>
                    </c:if>
                </sec:authorize>
            </div>
        </div>
        <div class="list_wrap">
            <div class="list_header">
                <h3>글 목록</h3>
            </div>
            <div class="list_item_wrap">
                <c:forEach items="${boards}" var="board">
                <div class="list_item_inner_wrap">
                    <input type="hidden" value="${board.bid}">
                    <div class="list_item_header">
                        <img src="${board.content}"/>
                    </div>
                    <div class="list_item_body">
                        <p class="item_category">${board.category}</p>
                        <p class="item_title">${board.title}</p>
                        <p class="item_date">${board.update_date}</p>
                    </div>
                </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <%--푸터--%>
    <jsp:include page="../common/footer.jsp"/>
</div>
</body>
</html>