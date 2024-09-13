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
    <title>ProgrammerBlog-Sign Up</title>
    <script type="text/javascript">
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

<%--        회원가입 js --%>
        document.getElementById('signupForm').addEventListener('submit', function(event) {
            const id = document.getElementById('id').value;
            const password = document.getElementById('password').value;
            const rePassword = document.getElementById('re-password').value;
            const nickname = document.getElementById('nickname').value;
            if(!(id&&password&&rePassword&&nickname)){
                alert("모두 입력해주세요.")
                event.preventDefault();
            }
            else if (password !== rePassword) {
                alert("비밀번호가 같지 않습니다.");
                event.preventDefault();
            }
        })

        function signUp(){
            const signupForm=document.getElementById('signupForm')

            const id = document.getElementById('id').value;
            const password = document.getElementById('password').value;
            const repassword = document.getElementById('repassword').value;
            const nickname = document.getElementById('nickname').value;

            if(!(id && password && repassword && nickname)){
                alert("모두 입력해주세요.")
            }
            else if (password !== repassword) {
                alert("비밀번호가 같지 않습니다.")
            }else{
                alert("회원가입 완료")
                signupForm.submit()
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
            <form id="signupForm" class="form_wrap" action="/signupProc" method="post">
                <div class="signup_id_wrap">
                    <input class="form_input" id="id" type="text" name="id" placeholder="ID" required />
                    <button type="button" class="confirm_btn" onclick="checkId()">중복 확인</button>
                </div>
                <div>
                    <input class="form_input" id="password" type="password" name="password" placeholder="Password" required/>
                </div>
                <div>
                    <input class="form_input" id="repassword" type="password" name="repassword" placeholder="Re-Password" required/>
                </div>
                <div>
                    <input class="form_input" id="name" type="text" name="name" placeholder="Name" required/>
                </div>
                <div>
                    <input class="form_input" id="email" type="email" name="email" placeholder="E-mail" required/>
                </div>
                <div>
                    <input class="form_input" id="nickname" type="text" name="nickname" placeholder="Nickname" required/>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="button" value="signup" onclick="signUp()">회원가입</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>