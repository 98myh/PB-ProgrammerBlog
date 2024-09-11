<%@ page  language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/resources/css/user/mypage.css">
    <link rel="stylesheet" href="/resources/css/style.css">
    <meta name="_csrf" content="${csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>ProgrammerBlog</title>
</head>
<body>
<div>
    <%--헤더--%>
    <jsp:include page="../common/header.jsp"/>
    <div id="wrap">
        <div class="mypage_top">
            <h3>닉네임 님</h3>
            <p>가입일자</p>
        </div>
        <div class="list_wrap">
            <div class="list_header">
                <h3>글 목록</h3>
            </div>
            <div>
                
            </div>
        </div>
    </div>
    <%--푸터--%>
    <jsp:include page="../common/footer.jsp"/>
</div>
</body>
</html>