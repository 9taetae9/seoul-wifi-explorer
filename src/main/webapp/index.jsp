<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>와이파이 정보구하기</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>

<div class="main-content">
    <div class="title-with-buttons">
        <h1>와이파이 정보 구하기</h1>
        <%@ include file="header.jsp" %>
    </div>

    <div class="input-group">
        <label for="latitude">LAT:</label>
        <input id="latitude" name="latitude" type="text" placeholder="위도 입력">
        <label for="longitude">LNT:</label>
        <input id="longitude" name="longitude" type="text" placeholder="경도 입력">
        <button id="save-location">내 위치 가져오기</button>
        <button id="view-nearby-wifi">근처 WIFI 정보 보기</button>
    </div>

    <table>
        <thead>
        <tr>
            <th>거리(Km)</th>
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
            <th>WIFI접속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
    <p id="placeholder-text">위치 정보를 입력한 후에 조회해 주세요.</p>
</div>

<script>
    document.getElementById('view-nearby-wifi').addEventListener('click', function(event) {
        event.preventDefault(); // 클릭 이벤트의 기본 동작 중단

        var lat = document.getElementById('latitude').value;
        var lng = document.getElementById('longitude').value;

        if(lat && lng) {
            fetch('/wifiSpots?lat=' + encodeURIComponent(lat) + '&lng=' + encodeURIComponent(lng))
                .then(response => response.json()) // JSON 형식으로 응답 파싱
                .then(data => {
                    if(data && data.length > 0) {
                        updateTable(data);
                    } else {
                        alert('근처에 WIFI 정보가 없습니다.');
                    }
                })
                .catch(error => {
                    console.error('Error :', error);
                    alert('Error : ' + error.message);
                });
        } else {
            alert('위도와 경도를 입력해 주세요.');
        }
    });

    function updateTable(wifiSpots) {
        var tableBody = document.querySelector('table tbody');
        tableBody.innerHTML = '';

        wifiSpots.forEach(spot => {
            var row = tableBody.insertRow();
            row.insertCell(0).innerText = spot.distanceKm.toFixed(2);
            row.insertCell(1).innerText = spot.managementNumber;
            row.insertCell(2).innerText = spot.wardOffice;
            row.insertCell(3).innerText = spot.wifiName;
            row.insertCell(4).innerText = spot.addressStreet;
            row.insertCell(5).innerText = spot.addressDetail;
            row.insertCell(6).innerText = spot.installationFloor;
            row.insertCell(7).innerText = spot.installationType;
            row.insertCell(8).innerText = spot.installationAgency;
            row.insertCell(9).innerText = spot.serviceType;
            row.insertCell(10).innerText = spot.networkType;
            row.insertCell(11).innerText = spot.constructionYear;
            row.insertCell(12).innerText = spot.indoorOutdoor;
            row.insertCell(13).innerText = spot.remarks;
            row.insertCell(14).innerText = spot.latitude;
            row.insertCell(15).innerText = spot.longitude;
            row.insertCell(16).innerText = spot.workDate;
        });

        document.getElementById('placeholder-text').style.display = 'none';
    }

    document.getElementById('save-location').addEventListener('click', function(event) {
        event.preventDefault();

        var lat = document.getElementById('latitude').value;
        var lng = document.getElementById('longitude').value;

        if(lat && lng) {
            // 서버에 위치 정보 저장 요청
            fetch('/saveUserLocation', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: 'lat=' + encodeURIComponent(lat) + '&lng=' + encodeURIComponent(lng)
            })
                .then(response => {
                    if(response.ok) {
                        return response.text(); // 응답 성공시 응답 텍스트 반환
                    } else {
                        throw new Error("Error : " + response.status + " " + response.statusText);
                    }
                })
                .then(text => {
                    alert('업데이트 완료: ' + text); // 서버 응답 텍스트를 알림으로 표시
                })
                .catch(err => {
                    console.error('Error :', err);
                    alert('Error : ' + err.message);
                });
        } else {
            alert('위도와 경도를 입력해 주세요.');
        }
    });

</script>

</body>
</html>
