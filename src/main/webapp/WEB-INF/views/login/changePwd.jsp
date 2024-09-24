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
    <link rel="stylesheet" href="/resources/css/login/login.css">
    <link rel="stylesheet" href="/resources/css/login/signup.css">
    <link rel="stylesheet" href="/resources/css/style.css">
    <meta name="_csrf" content="${_csrf.token}"/>
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
        <input id="alert_text" type="hidden" value="${text}">
        <input id="user_id" type="hidden" value="${id}">
        <div id="login_wrap">
            <form id="changePwdForm" class="form_wrap">
                <div>
                    <input class="form_input" id="password" type="password" name="password" placeholder="Password" required/>
                </div>
                <div>
                    <input class="form_input" id="repassword" type="password" name="repassword" placeholder="Re-Password" required/>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="button" onclick="commentEdit()">비밀번호 변경</button>
            </form>
        </div>
    </div>
</div>
<script>
    window.addEventListener('DOMContentLoaded',function(){
        const text=document.getElementById('alert_text')
        alert(text.value)
    })

    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');


    //비밀번호 변경
    function commentEdit(){
        const userId=document.getElementById('user_id').value
        const password=document.getElementById('password').value
        const repassword=document.getElementById('repassword').value

        if(password!=repassword){
            alert('비밀번호가 일치하지 않습니다.')
            return
        }

        let check=confirm('비밀번호를 변경하시겠습니까?')
        if(check){
            fetch('/change-pwd-req',{
                method:'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    [csrfHeader]: csrfToken
                },
                body: JSON.stringify({id:userId, password, repassword})
            }).then(response=>{
                if (response.ok){
                    return response.json()
                }else{
                    return Promise.reject(alert('변경 실패'))
                }
            }).then(response=>{
                Promise.resolve(alert('변경 성공'))
                window.location.href="/login"
            })
        }
    }
</script>
</body>
</html>