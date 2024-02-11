서울시 공공 와이파이 위치 정보를 제공하여 공공 와이파이 정보를 검색하고, 상세 정보를 확인할 수 있는 서비스를 제공합니다.
또한, 사용자는 자신만의 북마크를 생성하고 관리할 수 있는 기능을 사용하여 편리하게 원하는 와이파이 위치를 저장할 수 있습니다.(구현 중)-미완성

주요 기능
와이파이 목록 조회: 사용자는 위도와 경도를 입력하여 현재 위치에서 가장 가까운 공공 와이파이 목록을 조회
와이파이 상세 정보 확인: 선택된 와이파이의 상세 정보를 확인
북마크 기능: 특정 와이파이를 북마크에 추가
북마크 그룹 관리: 사용자는 북마크를 그룹별로 관리하고, 그룹 이름을 수정하거나 삭제

사용 기술
이 프로젝트는 Java와 JSP를 기반으로 구축되었습니다.

Java Servlet: 서버 사이드 로직을 처리
JSP: 동적 웹 페이지를 생성
SQLite: 데이터베이스로 사용되며, 와이파이 정보와 북마크 데이터를 저장
OkHttp: 외부 API와의 통신을 위해 사용
JSON: API 응답을 파싱하는 데 사용

erd 이미지 

![Alt text](https://github.com/9taetae9/seoul-wifi-explorer/assets/89383263/bf9b746b-c601-47c3-b71e-4c20c2fb1ae4)

구현 기능 수행 영상

![Project Scenario](https://github.com/9taetae9/seoul-wifi-explorer/assets/89383263/043cc2e7-1a20-4f7e-a6d7-0ee46168a7a5.gif)