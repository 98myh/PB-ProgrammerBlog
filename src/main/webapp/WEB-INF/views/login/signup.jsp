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
    <title>ProgrammerBlog-Sign Up</title>
</head>
<body>
<div id="wrap">
    <div>
        <div class="logo_div">
            <img id="login_logo" alt="로고" src="/resources/images/pblogo.png" onclick="location.href='/'"/>
        </div>
        <div id="login_wrap">
            <form class="form_wrap">
                <div class="signup_id_wrap">
                    <input class="form_input" type="text" placeholder="id" />
                    <button class="confirm_btn">중복 확인</button>
                </div>
                <div>
                    <input class="form_input" type="password" placeholder="password"/>
                </div>
                <div>
                    <input class="form_input" type="password" placeholder="re-password"/>
                </div>
                <div>
                    <input class="form_input" type="text" placeholder="Nickname"/>
                </div>
                <button>회원가입</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>