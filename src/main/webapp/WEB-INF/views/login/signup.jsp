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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <title>ProgrammerBlog-Sign Up</title>
    <script>
<%--        중복 체크 js --%>
        function checkId() {
            const id = document.getElementById('id').value;
            if (id) {
                fetch(`/check-id`,{
                    method:'POST',
                    headers:{
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: new URLSearchParams({id:id})
                })
                    .then(response =>response.json())  // 서버로부터 텍스트로 응답 받기
                    .then(data => {
                        if (data===0) {
                            alert('사용 가능한 아이디입니다.');
                        } else {
                            alert('이미 사용중인 아이디입니다.');
                        }
                    })
                    .catch(error => console.error('Error:', error));
            } else {
                alert('아이디를 입력하세요.');
            }
        }
    </script>
</head>

<body>
<div id="wrap">
    <div>
        <div class="logo_div">
            <img id="login_logo" alt="로고" src="/resources/images/pblogo.png" onclick="location.href='/'"/>
        </div>
        <div id="login_wrap">
            <form class="form_wrap" action="/signupProc" method="post">
                <div class="signup_id_wrap">
                    <input class="form_input" id="id" type="text" placeholder="id" />
                    <button type="button" class="confirm_btn" onclick="checkId()">중복 확인</button>
                </div>
                <div>
                    <input class="form_input" id="password" type="password" placeholder="password"/>
                </div>
                <div>
                    <input class="form_input" id="re-password" type="password" placeholder="re-password"/>
                </div>
                <div>
                    <input class="form_input" id="nickname" type="text" placeholder="Nickname"/>
                </div>
                <button type="submit" value="signup">회원가입</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>