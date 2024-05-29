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
    <link rel="stylesheet" href="/resources/css/style.css">
    <link rel="stylesheet" href="/resources/css/board/boardDetail.css">
    <meta name="_csrf" content="${csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>ProgrammerBlog</title>
</head>
<body>
<div>
    <%--헤더--%>
    <jsp:include page="../common/header.jsp"/>

    <div id="wrap">
        <div class="baord_detail_wrap">
            <!--게시글 내용들-->
            <div class="board_content_wrap">
                <div class="detail_inner_wrap">
                    <h2>${board_detail.board.title}</h2>
                    <div class="detail_title">
                        <p>${board_detail.board.nickname}</p>
                        <p>${fn:substring(board_detail.board.create_date, 0, 10)}</p>
                    </div>
                    <div class="detail_content">
                        ${board_detail.board.content}
                    </div>
                </div>
            </div>
            <!--댓글들-->
            <div>
                <div class="comment_top">
                    <h2>댓글</h2>
                </div>
                <!--댓글 입력-->
                <sec:authorize access="isAuthenticated()">
                    <form action="/comment/save" method="post">
                        <div class="comment_write_wrap">
                            <input type="hidden" name="bid" value="${board_detail.board.bid}"/>
                            <input type="hidden" name="parent_cid" value='' />
                            <textarea name="comment" class="comment_input" placeholder="댓글을 입력하세요"></textarea>
                            <button type="submit">댓글 작성</button>
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                        </div>
                    </form>
                </sec:authorize>
                <!--댓글 조회-->
                <c:forEach var="comment" items="${board_detail.comments}">
                    <div class="comment_small_wrap">
                        <div class="comment_nickname">
                            <span>${comment.nickname}</span>
                        </div>
                        <div class="comment_content">
                            <span>${comment.comment}</span>
                        </div>
                        <div class="comment_date">
                            <span>${comment.create_date}</span>
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