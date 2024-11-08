<%@ page  language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %><!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="_csrf" content="${csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
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
        <input id="user_id" type="hidden" value="${id.id}">
        <div id="login_wrap">
            <form class="form_wrap" action="/loginProc" method="post">
                <div>
                    <input class="form_input" name="id" type="text" placeholder="id"/>
                </div>
                <div>
                    <input class="form_input" name="password" type="password" placeholder="password"/>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button class="login_btn" type="submit" value="login">Login</button>
            </form>
            <button class="login_btn" onclick="location.href='/signup'">회원가입</button>
            <div id="find_wrap">
                <p id="find_id" onclick="goFindId()">아이디 찾기</p>
                <p id="space_area">&nbsp; | &nbsp;</p>
                <p id="find_pw" onclick="goFindPwd()">비밀번호 찾기</p>
            </div>
        </div>
    </div>
</div>
<script>
    window.addEventListener('DOMContentLoaded',function(){
        const userId=document.getElementById('user_id')
        if(userId.value!=''){
            alert("아이디 : "+userId.value)
        }
    })


    //아이디 찾기
    function goFindId(){
        window.location.href='/find-id'
    }

    //비밀번호 찾기
    function goFindPwd(){
        window.location.href='/find-pwd'
    }
</script>
</body>
</html>