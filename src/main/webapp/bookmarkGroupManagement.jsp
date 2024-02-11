<%@ page import="java.util.List"%>
<%@ page import="DTO.BookmarkGroup"%>
<%@ page import="DAO.BookmarkGroupDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bookmark Group Management</title>
</head>
<body>
<h2>Bookmark Group Management</h2>
<form action="/addOrUpdateBookmarkGroup" method="post">
    <input type="hidden" name="id" value="" /> <!-- Leave empty for add -->
    Bookmark Group Name: <input type="text" name="bookmarkName" value="" /><br/>
    Order: <input type="number" name="order" value="" /><br/>
    <input type="submit" value="Submit" />
</form>
<table border="1">
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>순서</th>
        <th>등록일자</th>
        <th>수정일자</th>
        <th>비고</th>
    </tr>
    <% for(BookmarkGroup group : (List<BookmarkGroup>) request.getAttribute("bookmarkGroups")) { %>
    <tr>
        <td><%= group.getId() %></td>
        <td><%= group.getBookmarkName() %></td>
        <td><%= group.getOrder() %></td>
        <td><%= group.getRegistrationDate() %></td>
        <td><%= group.getUpdateDate() %></td>
        <td>
            <form action="/editBookmarkGroup" method="post">
                <input type="hidden" name="id" value="<%= group.getId() %>" />
                <input type="submit" value="Edit" />
            </form>
            <form action="/deleteBookmarkGroup" method="post">
                <input type="hidden" name="id" value="<%= group.getId() %>" />
                <input type="submit" value="Delete" />
            </form>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>
