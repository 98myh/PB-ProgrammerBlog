<%@ page  language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/resources/css/board/write.css">
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
        <%--제목,카테고리 입력--%>
        <div id="write_wrap">
            <form id="boardForm" action="/board/save" method="post">
            <div class="write_title">
                <select class="category" name="category">
                    <option value="etc">카테고리</option>
                    <option value="trend">개발동향</option>
                    <option value="skill">개발스킬</option>
                    <option value="algorithm">알고리즘</option>
                    <option value="etc">etc</option>
                </select>
                <div>
                    <input id="title" name="title" placeholder="제목을 입력하세요" />
                </div>
            </div>
            <%--내용 입력--%>
            <div id="editable"  contentEditable="true">
            </div>
            <textarea name="content" style="display:none;"></textarea>
            <div>
                <button id="write_save" type="button">저장</button>
                <button type="reset">취소</button>
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
                img.style.maxWidth = '500px'; // 이미지의 최대 너비 설정
                img.style.maxHeight= "500px";
                editableDiv.appendChild(img); // 이미지를 편집 영역에 삽입합니다.
            }
            reader.readAsDataURL(file);
        }
    });

    //글 저장 로직
    const csrfToken = document.querySelector('meta[name="_csrf"]').content;
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;

    // console.log("CSRF Token:", csrfToken);
    // console.log("CSRF Header:", csrfHeader);
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

        fetch('/board/save', {
            method: 'POST',
            body: formData
        }).then(response => {
            console.log(response);
            if (response.ok) {
                window.location.href = '/board/recently';
            } else {
                alert('저장 실패');
            }
        });
    });
});


</script>
</body>
</html>