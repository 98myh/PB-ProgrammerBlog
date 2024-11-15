<%@ page  language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="_csrf" content="${csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <link rel="stylesheet" href="/resources/css/main/main.css">
    <link rel="stylesheet" href="/resources/css/style.css">
    <title>ProgrammerBlog</title><link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<div>
    <%-- 헤더  --%>
    <jsp:include page="../common/header.jsp"/>
    <%--메인 컨텐츠--%>
    <div id="wrap">
        <div class="main_wrap">
            <div class="main_title_wrap">
                <div class="main_title">
                    <h1 class="">Programmer Blog</h1>
                    <p>다양한 개발자들의 지식 나눔터</p>
                </div>
            </div>
            <%--    최근 게시글        --%>
            <c:if test="${fn:length(main.listRecently)>0}">
                <div class="oneline_wrap">
                    <div class="sub_title_wrap">
                        <h2 class="sub_title">최근 게시글</h2>
                        <button class="more_btn" onclick="location.href='/board/category/recently?title='"><i class="fa-solid fa-arrow-right"></i></button>
                    </div>
                    <div class="line_inner_wrap">
                        <%--나중에 for문 사용해서 출력되도록 수정해야함--%>
                        <c:forEach items="${main.listRecently}" var="recently">
                            <div class="card" style="width: 18rem;"  onclick="location.href='/board/detail/${recently.bid}'">
                                <img src="${recently.content}" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h3>${recently.title}</h3>
                                    <p class="card-text">${recently.nickname}</p>
                                    <p class="card-text">${fn:substring(recently.create_date, 0, 10)}</p>

                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </c:if>

            <%--개발동향 게시글--%>
            <c:if test="${fn:length(main.listTrend)>0}">
                <div class="oneline_wrap">
                    <div class="sub_title_wrap">
                        <h2 class="sub_title">개발동향</h2>
                        <button class="more_btn" onclick="location.href='/board/category/trend?title='"><i class="fa-solid fa-arrow-right"></i></button>
                    </div>
                    <div class="line_inner_wrap">
                        <c:forEach items="${main.listTrend}" var="trend">
                            <div class="card" style="width: 18rem;"  onclick="location.href='/board/detail/${trend.bid}'">
                                <img src="${trend.content}" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h3>${trend.title}</h3>
                                    <p class="card-text">${trend.nickname}</p>
                                    <p class="card-text">${fn:substring(trend.create_date, 0, 10)}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </c:if>

            <!--개발스킬-->
            <c:if test="${fn:length(main.listSkill)>0}">
                <div class="oneline_wrap">
                    <div class="sub_title_wrap">
                        <h2 class="sub_title">개발스킬</h2>
                        <button class="more_btn" onclick="location.href='/board/skill?title='"><i class="fa-solid fa-arrow-right"></i></button>
                    </div>
                    <div class="line_inner_wrap">
                        <c:forEach items="${main.listSkill}" var="skill">
                            <div class="card" style="width: 18rem;"  onclick="location.href='/board/category/detail/${skill.bid}'">
                                <img src="${skill.content}" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h3>${skill.title}</h3>
                                    <p class="card-text">${skill.nickname}</p>
                                    <p class="card-text">${fn:substring(skill.create_date, 0, 10)}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </c:if>

            <!--알고리즘-->
            <c:if test="${fn:length(main.listAlgorithm)>0}">
                <div class="oneline_wrap">
                    <div class="sub_title_wrap">
                        <h2 class="sub_title">알고리즘</h2>
                        <button class="more_btn" onclick="location.href='/board/category/algorithm?title='"><i class="fa-solid fa-arrow-right"></i></button>
                    </div>
                    <div class="line_inner_wrap">
                        <c:forEach items="${main.listAlgorithm}" var="algorithm">
                            <div class="card" style="width: 18rem;" onclick="location.href='/board/detail/${algorithm.bid}'">
                                <img src="${algorithm.content}" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h3>${algorithm.title}</h3>
                                    <p class="card-text">${algorithm.nickname}</p>
                                    <p class="card-text">${fn:substring(algorithm.create_date, 0, 10)}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </c:if>

        </div>
    </div>

    <%--footer--%>
    <jsp:include page="../common/footer.jsp"/>
</div>
</body>
</html>