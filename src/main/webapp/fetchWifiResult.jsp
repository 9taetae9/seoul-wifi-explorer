<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        .center-content {
            text-align: center;
            margin-top: 20px;
        }

        .input-group button {
            margin-top: 10px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
        }

        .input-group button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<div class="main-content">
    <div class="center-content">
        <p><%= request.getAttribute("message") %></p>
        <div class="input-group">
            <button onclick="location.href='/'">홈으로 가기</button>
        </div>
    </div>
</div>

</body>
</html>
