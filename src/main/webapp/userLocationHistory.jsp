<%@ page import="DTO.UserHistory" %>
<%@ page import="java.util.List" %>
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
    <h2>위치 히스토리 목록</h2>
    <%@ include file="header.jsp" %>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Latitude</th>
            <th>Longitude</th>
            <th>Query Date</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
            <%
                List<UserHistory> userHistories = (List<UserHistory>) request.getAttribute("userHistories");
                if (userHistories != null && !userHistories.isEmpty()) {
                    for (UserHistory userHistory : userHistories) {
            %>
                <tr>
                    <td><%= userHistory.getId() %></td>
                    <td><%= userHistory.getLatitude() %></td>
                    <td><%= userHistory.getLongitude() %></td>
                    <td><%= userHistory.getQueryDate() %></td>
                    <td>
                        <form action="DeleteUserHistoryServlet" method="post">
                            <input type="hidden" name="id" value="<%= userHistory.getId() %>">
                            <input type="submit" value="Delete">
                        </form>
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="4">히스토리 정보가 없습니다.</td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>

</body>
</html>
