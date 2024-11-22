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
    <meta name="_csrf" content="${_csrf.token}"/>
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
            <div class="mypage_top_right">
                <p>가입일 : ${fn:substring(userInfo.create_date, 0, 10)}</p>
                <sec:authorize access="isAuthenticated()">
                    <sec:authentication property="principal.uid" var="uid"/>
                    <sec:authentication property="principal.authorities" var="role"/>
                    <c:if test="${uid==userInfo.uid || role=='[ROLE_ADMIN]'}">
                        <div class="edit_user_btn_box">
                            <button onclick="goEditUser()">수정</button>
                            <button onclick="deleteUser()">탈퇴</button>
                        </div>
                    </c:if>
                </sec:authorize>
            </div>
        </div>
        <div class="list_wrap">
            <div class="list_header">
                <h3>글 목록</h3>
            </div>
            <div class="list_item_wrap">
                <c:choose>
                    <c:when test="${fn:length(boards)>0}">
                        <c:forEach items="${boards}" var="board">
                            <div class="list_item_inner_wrap" onclick="goBoard(${board.bid})">
                                <div class="list_item_header">
                                    <img src="${board.content}"/>
                                </div>
                                <div class="list_item_body">
                                    <p class="item_category">${board.category}</p>
                                    <p class="item_title">${board.title}</p>
                                    <p class="item_date">${fn:substring(board.update_date, 0, 10)}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <h5 class="not_h5">게시글이 없습니다...</h5>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <%--푸터--%>
    <jsp:include page="../common/footer.jsp"/>
</div>
<script>

    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');


    //수정 페이지로 이동
    function goEditUser(){
        window.location.href="/edit-user"
    }

    //게시글로 이동
    function goBoard(bid){
        window.location.href='/board/detail/'+bid
    }

    //회원탈퇴
    function deleteUser(){
        const password=prompt("회원탈퇴를 원하실 경우 비밀번호를 입력해주세요.")

        //비밀번호가 맞을 경우
            fetch('/delete-user',{
                method:'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                    [csrfHeader]: csrfToken
                },
                body: JSON.stringify({uid:${uid}, password:password})
            }).then(response=>{
                if (response.ok){
                    return response.json()
                }else{
                    return Promise.reject(alert('탈퇴 실패. 비밀번호를 확인해주세요.'))
                }
            }).then(response=>{
                Promise.resolve(alert('탈퇴 성공'))
                fetch('/logout',{
                    method:"POST",
                    headers: {
                        'Content-Type': 'application/json',
                        [csrfHeader]: csrfToken
                    }
                }).then(response=>{
                    window.location.href="/login"
                })
            })

    }
</script>
</body>
</html>