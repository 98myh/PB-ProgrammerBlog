<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/resources/css/board/write.css">
    <link rel="stylesheet" href="/resources/css/style.css">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>ProgrammerBlog</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<div>
    <%--헤더--%>
    <jsp:include page="../common/header.jsp"/>
    <div id="wrap">
        <%--제목,카테고리 입력--%>
        <div id="write_wrap">
            <form id="boardForm" action="/board/save" method="post">

                <sec:authentication property="principal.uid" var="uid"/>
                <input type="hidden" value="${uid}" name="uid">

                <!--수정글일 경우 bid 넘기기-->
                <c:if test="${board!=null}">
                    <input type="hidden" value="${board.board.bid}" name="bid">
                </c:if>

                <div class="write_title">
                    <select class="category" name="category" >
                        <option value="etc">카테고리</option>
                        <option ${board.board.category=='trend'?"selected":""} value="trend">개발동향</option>
                        <option ${board.board.category=='skill'?"selected":""} value="skill">개발스킬</option>
                        <option ${board.board.category=='algorithm'?"selected":""} value="algorithm">알고리즘</option>
                        <option ${board.board.category=='etc'?"selected":""} value="etc">etc</option>
                    </select>
                    <div>
                        <input id="title" name="title" placeholder="제목을 입력하세요" value="${board!=null?board.board.title:''}" />
                    </div>
                </div>
                <%--내용 입력--%>
                <div id="editable" contentEditable="true" >
                    <c:if test="${board !=null}">
                        ${board.board.content}
                    </c:if>
                </div>
                <textarea name="content" style="display:none;">

                </textarea>
                <div>
                    <!--수정과 글 저장 나눔-->
                    <c:choose>
                        <c:when test="${board!=null}">
                            <button id="write_save" type="button">수정</button>
                        </c:when>
                        <c:otherwise>
                            <button id="write_save" type="button">저장</button>
                        </c:otherwise>
                    </c:choose>
                    <button type="reset" onclick="window.history.back()">취소</button>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
            </form>
        </div>

    </div>
    <%--푸터--%>
    <jsp:include page="../common/footer.jsp"/>
</div>

<script>
<%--    드래그하여 이미지 올리도록 함--%>
    const editableDiv = document.getElementById('editable');
    editableDiv.addEventListener('dragover', function(event) {
        event.preventDefault(); // 드래그 중인 요소가 드롭 가능한 영역임을 표시
    });
    editableDiv.addEventListener('drop', function(event) {
        event.preventDefault(); // 기본 드롭 이벤트 방지
        const file = event.dataTransfer.files[0]; // 드롭된 파일을 가져옵니다.
        if (file && file.type.startsWith('image/')) { // 파일이 이미지인지 확인합니다.
            const reader = new FileReader();
            reader.onload = function(e) {
                const img = document.createElement('img');
                img.src = e.target.result;
                img.style.maxWidth = '500px !import'; // 이미지의 최대 너비 설정
                img.style.maxHeight= "500px !import";
                editableDiv.appendChild(img); // 이미지를 편집 영역에 삽입합니다.
            }
            reader.readAsDataURL(file);
        }
    });

    //글 저장 로직
    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    document.getElementById('write_save').addEventListener('click', function() {
        const editableDiv = document.getElementById('editable');
        const images = editableDiv.getElementsByTagName('img');

        //먼저 이미지 저장
        let imagePromises = Array.from(images).map(img => {
            if (img.src.startsWith('data:')) {
                return fetch(img.src)
                    .then(res => res.blob())
                    .then(blob => {
                        let formData = new FormData();
                        formData.append('image', blob, 'upload.jpg');

                        return fetch('/board/img-upload', {
                            method: 'POST',
                            headers: {
                                [csrfHeader]: csrfToken
                            },
                            body: formData
                        }).then(response => {
                            if (response.ok){
                                return response.text()
                            }
                        })
                            .then(data => {
                                img.src = data;
                            });
                    });
            } else {
                return Promise.resolve();  // 이미 `data:`로 시작하지 않는 이미지에 대한 처리
            }
        });

        //글저장
        Promise.all(imagePromises).then(() => {
            const form = document.getElementById('boardForm');
            form.content.value = editableDiv.innerHTML;  // 이미지 업로드가 완료된 후 내용 업데이트
            const formData = new FormData(form);
            const btn=document.getElementById('write_save').innerText


            const url= btn=='저장'?'/board/save':'/board/edit'
            const formMethod= btn=='저장'?'POST':'PUT'

            let jsonObject = {};
            new FormData(form).forEach((value, key) => {
                jsonObject[key] = value;
            });

            fetch(url, {
                method: formMethod,
                headers: {
                    'Content-Type': 'application/json',
                    [csrfHeader]: csrfToken
                },
                body: btn=='저장'?formData:JSON.stringify(jsonObject)
            }).then(response=> {
                if(response.ok) {
                    return response.json()
                }else{
                    return Promise.reject(alert("저장 실패"));
                }
            }).then(response=>{
                alert("저장성공")
                window.location.href='/board/detail/'+response
            });
        });


    });


</script>
</body>
</html>