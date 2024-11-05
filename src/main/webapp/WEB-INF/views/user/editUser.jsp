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
    <link rel="stylesheet" href="/resources/css/user/editUser.css">
    <link rel="stylesheet" href="/resources/css/style.css">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <title>ProgrammerBlog</title>
</head>
<body>
<div>
    <%--헤더--%>
    <jsp:include page="../common/header.jsp"/>
    <div id="wrap">
        <div class="edit_wrap">
            <h2>정보 수정</h2>
            <sec:authentication property="principal.uid" var="uid"/>
            <form id="editUserForm" class="form_wrap">
                <div>
                    <input class="form_input" id="oldPassword" type="password" name="oldPassword" placeholder="기존 비밀번호(필수 입력)" required/>
                </div>
                <div>
                    <input class="form_input" id="newPassword" type="password" name="newPassword" placeholder="새비밀번호" />
                </div>
                <div>
                    <input class="form_input" id="rePassword" type="password" name="rePassword" placeholder="새비밀번호 다시 입력" />
                </div>
                <div>
                    <input class="form_input" id="nickname" name="nickname" placeholder="닉네임" />
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button class="editBtn" type="button" onclick="editUser()">정보 수정</button>
            </form>
        </div>
    </div>
    <%--푸터--%>
    <jsp:include page="../common/footer.jsp"/>
</div>
<script>

    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    //비밀번호 변경
    function editUser(){
        const oldPassword=document.getElementById('oldPassword').value
        const newPassword=document.getElementById('newPassword').value
        const rePassword=document.getElementById('rePassword').value
        const nickname=document.getElementById('nickname').value

        if(newPassword!=rePassword){
            alert('비밀번호가 일치하지 않습니다.')
            return
        }

        let check=confirm('수정 하시겠습니까?')
        if(check){
            fetch('/edit-user-req',{
                method:'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    [csrfHeader]: csrfToken
                },
                body: JSON.stringify({uid:${uid}, oldPassword: oldPassword, newPassword: newPassword, nickname: nickname})
            }).then(response=>{
                if (response.ok){
                    return response.json()
                }else{
                    return Promise.reject(alert('수정 실패. 비밀번호를 확인해주세요.'))
                }
            }).then(response=>{
                Promise.resolve(alert('수정 성공'))
                window.location.href="/mypage/${uid}"
            })
        }
    }
</script>
</body>
</html>