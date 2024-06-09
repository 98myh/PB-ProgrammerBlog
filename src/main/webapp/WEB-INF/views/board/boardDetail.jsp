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
                    <div class="board_title_wrap">
                        <h2>${board_detail.board.title}</h2>
                        <!--로그인 한 경우 ...버튼이 나옴-->
                        <sec:authorize access="isAuthenticated()">
                            <sec:authentication property="principal.uid" var="uid"/>
                            <button id="change_btn" type="button" onclick="changeToggle()" >
                                <i class="fa-solid fa-ellipsis"></i>
                                <ul id="show_box" class="noShow">
                                    <c:choose>
                                        <c:when test="${uid==board_detail.board.uid}">
                                            <li onclick="goEdit(${board_detail.board.bid})">수정하기</li>
                                            <li>삭제하기</li>
                                        </c:when>
                                        <c:otherwise>
                                            <li>신고하기</li>
                                        </c:otherwise>
                                    </c:choose>
                                </ul>
                            </button>
                        </sec:authorize>

                    </div>
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
                    <div class="comment_small_wrap" id="top_${comment.topComment.cid}">
                        <!--부모 댓글 조회-->
                        <div class="comment_nickname">
                            <span>${comment.topComment.nickname}</span>
                        </div>
                        <div class="comment_content">
                            <span>${comment.topComment.comment}</span>
                        </div>
                        <div class="comment_end">
                            <div class="comment_date">
                                <span>${comment.topComment.create_date}</span>
                            </div>
                            <c:if test="${fn:length(comment.childComment)>0}">
                                <div class="child_comment_toggle">
                                    <span class="child_comment_toggle_btn" id="toggle_${comment.topComment.cid}" onclick="commentToggle(${comment.topComment.cid})">대댓글 보기</span>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <!--대댓글 조회-->
                    <div id="${comment.topComment.cid}" style="display: none">
                        <c:forEach var="childComment" items="${comment.childComment}">
                            <div class="comment_small_wrap child_comments">
                                <div class="comment_nickname">
                                    <span>${childComment.nickname}</span>
                                </div>
                                <div class="comment_content">
                                    <span>${childComment.comment}</span>
                                </div>
                                <div class="comment_date">
                                    <span>${childComment.create_date}</span>
                                </div>
                            </div>
                        </c:forEach>
                        <!--로그인 한 유저 대댓글 작성-->
                        <sec:authorize access="isAuthenticated()">
                            <form action="/comment/save" method="post">
                                <div class="comment_write_wrap">
                                    <input type="hidden" name="bid" value="${board_detail.board.bid}"/>
                                    <input type="hidden" name="parent_cid" value="${comment.topComment.cid}" />
                                    <textarea name="comment" class="comment_input" placeholder="댓글을 입력하세요"></textarea>
                                    <button type="submit">대댓글 작성</button>
                                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                                </div>
                            </form>
                        </sec:authorize>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

    <%--푸터--%>
    <jsp:include page="../common/footer.jsp"/>
</div>

<!--추후 js 파일로 분리-->
<script>
    //로그인 한 유저 수정/삭제 or 신고 하기 토글 버튼
    function changeToggle(){
        const show_box=document.getElementById('show_box')
        if(show_box.classList.contains('noShow')){
            show_box.classList.remove('noShow')
            show_box.classList.add('show')
        }else{
            show_box.classList.remove('show')
            show_box.classList.add('noShow')
        }
    }

    //수정하기 페이지로 이동
    function goEdit(bid){
        window.location.href='/board/edit/'+bid
    }


    //신고 모달창 띄우기 (보류)

    //대댓글 보기 토글
    function commentToggle(cid) {
        //대댓글 보기 클릭시 토글
        let childCommentsDiv = document.getElementById(cid)

        //대댓글 보기 <-> 줄이기 텍스트 토글
        let toggleText=document.getElementById("toggle_"+cid)

        if (childCommentsDiv.style.display === "none") {
            childCommentsDiv.style.display = "block";
            toggleText.textContent="줄이기"
        } else {
            childCommentsDiv.style.display = "none";
            toggleText.textContent="대댓글 보기"
        }
    }
</script>
</body>
</html>