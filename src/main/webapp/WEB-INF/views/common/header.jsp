<%@ page  language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="ko">
<script type="text/javascript">
</script>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="_csrf" content="${csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <link rel="stylesheet" href="/resources/css/style.css">
    <title>ProgrammerBlog</title><link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<header id="header">
    <div class="head_inner_wrap">
        <img id="logo" alt="로고" src="/resources/images/pblogo.png" onclick="location.href='/'"/>
    </div>
    <div id="head_right" class="head_inner_wrap">
        <div class="search_wrap">
            <i class="fa-solid fa-magnifying-glass"></i>
            <input placeholder="검색하기"/>
        </div>

        <%-- 사용자 정보 부분 로그인 or 사용자 명 --%>
        <sec:authorize access="isAuthenticated()">
            <!-- 사용자가 인증되었을 때 -->
            <sec:authentication property="principal.nickname" var="nickname"/>
            <button type="button" onclick="location.href='board/write'">글 작성</button>
            <p id="header_user_name">${nickname}님</p>
            <form action="/logout" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="fa-solid fa-arrow-right-from-bracket" />
            </form>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
            <!-- 사용자가 비인증 상태일 때 -->
            <button onclick="location.href='login'">로그인</button>
        </sec:authorize>
    </div>
</header>
</body>
</html>