<%@ page  language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/resources/css/board/board.css">
    <link rel="stylesheet" href="/resources/css/style.css">
    <link rel="stylesheet" href="/resources/css/main/main.css">
    <meta name="_csrf" content="${csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>ProgrammerBlog</title>
</head>
<body>
<div>
    <%--헤더--%>
    <jsp:include page="../common/header.jsp"/>
    <div id="wrap">
        <div class="board_list_wrap">
            <div class="line_inner_wrap">
                <c:forEach var="board" items="${board}">
                    <div class="card" style="width: 18rem;">
                        <div class="image-wrap">
                            <img src="${board.content}" class="card-img-top" alt="...">
                        </div>
                        <div class="card-body">
                            <!--제목-->
                            <h4 class="card-text">${board.title}</h4>
                            <div class="card_bottom">
                                <!--닉네임-->
                                <p class="card-writer">${board.nickname}</p>
                                <!--날짜 10자로 잘라서 출력-->
                                <p class="card-date">${fn:substring(board.create_date, 0, 10)}</p>
                            </div>
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