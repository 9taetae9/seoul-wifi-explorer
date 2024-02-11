<%@ page import="DTO.WifiSpot" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>와이파이 상세 정보</title>
  <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>

<div class="main-content">
  <h1>와이파이 상세 정보</h1>
  <%@ include file="header.jsp" %>
  <form action="/addBookmark" method = "post">
    <label for="bookmarkGroup" id = "bookmarkGroup">북마크 그룹 이름 선택</label>
    <select name = "bookmarkGroup" id = "bookmarkGroup">
      <!-- Dynamically populate this dropdown with groups from the database -->
        <option value="default">선택</option>
    </select>
    <input type="hidden" name="wifiSpotId" value="<%= wifiSpot.getWifiName() %>" />
    <input type="submit" value="북마크 추가하기" />
  </form>
  <%
    WifiSpot wifiSpot = (WifiSpot)request.getAttribute("wifiSpotDetails");
    if(wifiSpot != null) {
  %>
  <table class="wifi-details">
    <tr><td><strong>거리(Km)</strong></td><td><%= wifiSpot.getDistanceKm() %></td></tr>
    <tr><td><strong>관리번호</strong></td><td><%= wifiSpot.getManagementNumber() %></td></tr>
    <tr><td><strong>자치구</strong></td><td><%= wifiSpot.getWardOffice() %></td></tr>
    <tr><td><strong>와이파이명</strong></td><td><%= wifiSpot.getWifiName() %></td></tr>
    <tr><td><strong>도로명주소</strong></td><td><%= wifiSpot.getAddressStreet() %></td></tr>
    <tr><td><strong>상세주소</strong></td><td><%= wifiSpot.getAddressDetail() %></td></tr>
    <tr><td><strong>설치위치(층)</strong></td><td><%= wifiSpot.getInstallationFloor() %></td></tr>
    <tr><td><strong>설치유형</strong></td><td><%= wifiSpot.getInstallationType() %></td></tr>
    <tr><td><strong>설치기관</strong></td><td><%= wifiSpot.getInstallationAgency() %></td></tr>
    <tr><td><strong>서비스구분</strong></td><td><%= wifiSpot.getServiceType() %></td></tr>
    <tr><td><strong>망종류</strong></td><td><%= wifiSpot.getNetworkType() %></td></tr>
    <tr><td><strong>설치년도</strong></td><td><%= wifiSpot.getConstructionYear() %></td></tr>
    <tr><td><strong>실내외구분</strong></td><td><%= wifiSpot.getIndoorOutdoor() %></td></tr>
    <tr><td><strong>WIFI접속환경</strong></td><td><%= wifiSpot.getRemarks() %></td></tr>
    <tr><td><strong>X좌표</strong></td><td><%= wifiSpot.getLatitude() %></td></tr>
    <tr><td><strong>Y좌표</strong></td><td><%= wifiSpot.getLongitude() %></td></tr>
    <tr><td><strong>작업일자</strong></td><td><%= wifiSpot.getWorkDate() %></td></tr>
  </table>
  <%
    } else {
      //out.write("<p>와이파이 정보를 불러올 수 없습니다.</p>");
    }
  %>

  <div class="input-group">
    <button onclick="location.href='/'">목록으로 돌아가기</button>
  </div>
</div>

</body>
</html>
