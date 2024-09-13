<%@ page  language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/resources/css/login/login.css">
    <link rel="stylesheet" href="/resources/css/login/signup.css">
    <link rel="stylesheet" href="/resources/css/style.css">
    <meta name="_csrf" content="${csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <title>ProgrammerBlog</title>
</head>

<body>
<div id="wrap">
    <div>
        <div class="logo_div">
            <img id="login_logo" alt="로고" src="/resources/images/pblogo.png" onclick="location.href='/'"/>
        </div>
        <div id="login_wrap">
            <form id="signupForm" class="form_wrap" action="/findId-check" method="get">
                <div>
                    <input class="form_input" id="name" type="text" name="name" placeholder="Name" required/>
                </div>
                <div>
                    <input class="form_input" id="email" type="email" name="email" placeholder="E-mail" required/>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="button" value="signup" onclick="findId()">아이디 찾기</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>