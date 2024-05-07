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
    <link rel="stylesheet" href="/resources/css/main/main.css">
    <link rel="stylesheet" href="/resources/css/style.css">
    <title>ProgrammerBlog</title><link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
<div>
    <header id="header">
        <div class="head_inner_wrap">
            <img id="logo" alt="로고" src="/resources/images/pblogo.png" onclick="location.href='/'"/>
        </div>
        <div id="head_right" class="head_inner_wrap">
            <div class="search_wrap">
                <i class="fa-solid fa-magnifying-glass"></i>
                <input placeholder="검색하기"/>
            </div>
<%--            <% if(session.getAttribute("user")==null){ %>--%>
<%--                <button onclick="location.href='login'">로그인</button>--%>
<%--            <% } else { %>--%>
<%--                <p id="header_user_name"><%= session.getAttribute("nickname") %>님</p>--%>
<%--&lt;%&ndash;                <button class="logout_btn" onclick="location.href='logout'">로그아웃</button>&ndash;%&gt;--%>
<%--                <i id="logout" onclick="location.href='logout'" class="fa-solid fa-arrow-right-from-bracket"></i>--%>
<%--            <% } %>--%>


            <sec:authorize access="isAuthenticated()">
                <!-- 사용자가 인증되었을 때 -->
                <sec:authentication property="principal.nickname" var="nickname"/>
                <p id="header_user_name">${nickname}님</p>
                <i id="logout" onclick="location.href='logout'" class="fa-solid fa-arrow-right-from-bracket"></i>
            </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
                <!-- 사용자가 비인증 상태일 때 -->
                <button onclick="location.href='login'">로그인</button>
            </sec:authorize>
        </div>
    </header>
    <div id="wrap">

    </div>
</div>
</body>
</html>