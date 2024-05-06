<%@ page  language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/resources/css/login/login.css">
    <link rel="stylesheet" href="/resources/css/style.css">
    <title>ProgrammerBlog-Login</title>
</head>
<body>
<div id="wrap">
    <div>
        <div class="logo_div">
            <img id="login_logo" alt="로고" src="/resources/images/pblogo.png" onclick="location.href='/'"/>
        </div>
        <div id="login_wrap">
            <form class="form_wrap" action="/loginProc" method="post">
                <div>
                    <input class="form_input" name="username" type="text" placeholder="id"/>
                </div>
                <div>
                    <input class="form_input" name="password" type="password" placeholder="password"/>
                </div>
                <button type="submit" value="login">Login</button>
            </form>
            <button onclick="location.href='/signup'">회원가입</button>
            <div id="find_wrap">
                <p id="find_idpw">아이디/비밀번호 찾기</p>
            </div>
        </div>
    </div>
</div>

<% if (request.getAttribute("login false") != null) { %>
<script>
    alert('<%= request.getAttribute("login false") %>');
</script>
<% } %>
</body>
</html>