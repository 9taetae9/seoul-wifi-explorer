<%@ page import="java.util.*"%>
<%@ page contentType="text/htmll; charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
    <body>
        <h1>가까운 와이파이 정보 구하기</h1>
        <table>
            <thead>
                <tr>
                    <th>거리 (km)</th>
                    <th>관리번호</th>
                    <th>자치구</th>
                    <th>와이파이명</th>
                    <th>도로명주소</th>
                    <th>상세주소</th>
                    <th>설치위치(층)</th>
                    <th>설치유형</th>
                    <th>설치기관</th>
                    <th>서비스구분</th>
                    <th>망종류</th>
                    <th>설치년도</th>
                    <th>실내외구분</th>
                    <th>wifi접속환경</th>
                    <th>X좌표</th>
                    <th>Y좌표</th>
                    <th>작업일자</th>
                </tr>
            </thead>
            <tbody>
                <% List<WifiSpot> wifiSpotList = (List<WifiSpot>) request.getAttribute("wifiSpot");
                    for (WifiSpot wifiSpot : wifiSpotList) { %>
                <tr>
                    <td><%= spot.getDistance() %></td>
                    <td><%= spot.getManagementNumber() %></td>
                    <td><%= spot.getWardOffice()%></td>
                    <td><%= spot.getWifiName()%></td>
                    <td><%= spot.getAddressStreet()%></td>
                    <td><%= spot.getAddressDetail()%></td>
                    <td><%= spot.getInstallationFloor()%></td>
                    <td><%= spot.getInstallationType()%></td>
                    <td><%= spot.getServiceType()%></td>
                    <td><%= spot.getNetworkType()%></td>
                    <td><%= spot.getConstructionYear()%></td>
                    <td><%= spot.getIndoorOutdoor()%></td>
                    <td><%= spot.getRemarks()%></td>
                    <td><%= spot.getLatitude()%></td>
                    <td><%= spot.getLongitude()%></td>
                    <td><%= spot.getWorkDate()%></td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </body>
</html>
