<%@ page  language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/resources/css/main/main.css">
    <link rel="stylesheet" href="/resources/css/style.css">
    <title>PBlog</title><link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
            <button onclick="location.href='login'">로그인</button>
        </div>
    </header>
    <div id="wrap">

    </div>
</div>
</body>
</html>