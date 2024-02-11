<%@ page import="java.util.List"%>
<%@ page import="DTO.Bookmark"%>
<%@ page import="DAO.BookmarkDAO"%>
<%@ page import="DTO.Bookmark" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bookmark List</title>
</head>
<body>
<h2>Bookmark List</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>와이파이명</th>
        <th>등록일자</th>
        <th>비고</th>
    </tr>
    <% for(Bookmark bookmark : (List<Bookmark>) request.getAttribute("bookmarks")) { %>
    <tr>
        <td><%= bookmark.getId() %></td>
        <td><%= bookmark.getBookmarkName() %></td>
        <td><%= bookmark.getWifiName() %></td>
        <td><%= bookmark.getRegistrationDate() %></td>
        <td>
            <form action="/deleteBookmark" method="post">
                <input type="hidden" name="id" value="<%= bookmark.getId() %>" />
                <input type="submit" value="Delete" />
            </form>
        </td>
    </tr>
    <% } %>
</table>
<a href="/addBookmark">Add New Bookmark</a>
</body>
</html>
