<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>

<div class="main-content">
    <h2>서비스 이용 불가</h2>
    <p><%= request.getAttribute("error") %></p>
    <div class="input-group">
        <button onclick="location.href='/'">홈으로 돌아가기</button>
    </div>
</div>

</body>
</html>