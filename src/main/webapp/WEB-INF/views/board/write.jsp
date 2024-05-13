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
            <div class="write_title">
                <select class="category">
                    <option>카테고리</option>
                    <option>개발동향</option>
                    <option>개발스킬</option>
                    <option>알고리즘</option>
                    <option>etc</option>
                </select>
                <div>
                    <input id="title" placeholder="제목을 입력하세요" />
                </div>
            </div>
            <%--내용 입력--%>
            <div id="editable" contentEditable="true">
                내용을 입력해주세요.
            </div>
            <div>
                <button id="write_save">저장</button>
                <button>취소</button>
            </div>
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
                img.style.maxWidth = '100%'; // 이미지의 최대 너비 설정
                editableDiv.appendChild(img); // 이미지를 편집 영역에 삽입합니다.
            }
            reader.readAsDataURL(file);
        }
    });

    //글 저장
    document.getElementById('write_save').addEventListener('click', function() {
    const editableDiv = document.getElementById('editable')
    const images = editableDiv.getElementsByTagName('img')

    Array.from(images).forEach(img => {
        const file = img.src
        if (file.startsWith('data:')) {
            let formData = new FormData();
            formData.append('image', file);

            fetch('/board/img-upload', {
                method: 'POST',
                body: formData
            })
                .then(response => response.json())
                .then(data => {
                    console.log(data)
                    img.src = data; // 업데이트된 이미지 경로로 src 설정
                })
                .catch(error => console.error('Error:', error));
        }
    });

    setTimeout(() => {
        const updatedHTML = editableDiv.innerHTML;
        fetch('/board/save', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ content: updatedHTML })
        });
    }, 3000);
});


</script>
</body>
</html>