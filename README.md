# Project 이름 : 봄(Spring)이좋냐?
# 호텔 예약 사이트
## 이미지 
### 2021.05.14. ~ 2021-06-14 (31일 주말 및 공휴일포함)

***

## Project(봄(Spring)이좋냐?) 서비스 목적
- 사용자
    - 나이 드신 분도 쉽고 단순한 사이트 개발<br>    
    - 키워드와 일정을 통한 손쉬운 호텔예약 서비스 제공
***
### 사용버전(Version)

+ Java JDK 1.8
+ HTML5/CSS3/JavaScript
+ BootStrap 4
+ J-Query(ajax), Spring Rest API
+ Servlet/JSP Servlet 4.0
+ Spring- framework(MVC)  v5.2.13
   + Spring task/ Spring tx/ Spring WebSocket
+ Database
   + Oracle DatatBase 11g 기준
***

### ERD
![img](https://user-images.githubusercontent.com/81960642/125153051-37a28180-e18c-11eb-87b5-feefccbc6529.JPG)

***
### 목차
* [회원관리](https://github.com/Linux0232/Spring/blob/master/Hotel/src/main/java/web/project/spring/controller/UserController.java)
  - 회원관리 기능리스트
    + 회원가입 기능
    + 로그인 기능
    + 아이디 / 비밀번호찾기(메일) 기능
    + 회원정보 수정 / 삭제 기능

* [호텔목록 게시판](https://github.com/Linux0232/Spring/blob/master/Hotel/src/main/java/web/project/spring/controller/HotelController.java)
  - 호텔목록게시판 기능리스트
    + 호텔목록조회(키워드 / 일정)
    + 호텔정렬기능(평점순 / 리뷰순 / 최저가격순 / 최고가격순)
    + 호텔예약 / 결재 기능
    + 호텔 좋아요 기능

* [호텔예약관리](https://github.com/Linux0232/Spring/blob/master/Hotel/src/main/java/web/project/spring/controller/ReservationController.java)
  - 호텔예약관리 기능리스트
    + 예약된 호텔 조회
    + 예약된 호텔 결재취소

* [호텔리뷰관리](https://github.com/Linux0232/Spring/blob/master/Hotel/src/main/java/web/project/spring/controller/ReviewController.java)
  - 호텔리뷰 기능리스트
    + 리뷰작성
    + 나의 리뷰 조회 / 수정 / 삭제
***

<details>
  <summary>회원관리</summary>
  <div markdown="1">

- 회원가입기능(중복체크)

![회원가입기능](https://user-images.githubusercontent.com/81960642/125171144-9ea45280-e1ed-11eb-9ae7-ddd8f7ba8600.JPG)

- 로그인기능

![로그인기능](https://user-images.githubusercontent.com/81960642/125171165-b2e84f80-e1ed-11eb-9fc3-004ea869a870.JPG)

- 메일을 통한 아이디 / 비밀번호찾기

![아이디찾기(메일) 기능](https://user-images.githubusercontent.com/81960642/125171219-ea56fc00-e1ed-11eb-8c24-ddc129944182.JPG)

![비밀번호찾기(메일) 기능](https://user-images.githubusercontent.com/81960642/125171234-0490da00-e1ee-11eb-8157-66ef4f2ea05c.JPG)


- 회원정보 수정 / 삭제 기능

![회원정보 수정  탈퇴 기능](https://user-images.githubusercontent.com/81960642/125171251-24280280-e1ee-11eb-9ba6-3750cc05823d.JPG)


  </div>
</details>

  - ‘회원가입’ 기능
    + 정보입력시 ID는 Database의 PK값이므로 ajax 통해 중복체크 기능구현
    + 비밀번호와 비밀번호확인 위해 2개의 데이터가 동일한지 확인하는 기능구현
    + 메일을 통한 개인정보 확인위해 ajax 통해 중복체크 기능구현
  - ‘로그인’ 기능
    + ID, PW의 데이터를 입력받아 Database에 입력받은 값이 일치하는 VO가 존재한다면 로그인이 되도록하는 기능구현
  - ‘아이디 / 비밀번호찾기’ 기능
    + 아이디 찾기는 Name, E-mail 데이터를 입력받고, 보안을 위해서 입력받은 E-mail로 아이디를 발송하는 기능 구현
    + 비밀번호 찾기는 ID, E-mail 데이터를 입력받고, 임시비밀번호를 생성해서 Database의 입력받은 ID에 임시비밀번호를 저장 후 임시비밀번호를 E-maill로 전송  
  - ‘회원정보 수정 / 삭제’ 기능
    + 회원에 대한 개인정보가 변경되었을 경우 ID를 제외한 나머지 개인정보를 입력받아 Database의 해당 ID에 개인정보를 수정 / 회원탈퇴
***
<details>
  <summary>호텔목록 게시판</summary>
  <div markdown="1">

- 호텔목록조회(키워드 / 일정)

![호텔목록조회(키워드  일정)](https://user-images.githubusercontent.com/81960642/125171268-4883df00-e1ee-11eb-83a3-eec737dc77a0.JPG)


- 호텔정렬기능(평점순 / 리뷰순 / 최저가격순 / 최고가격순)

![호텔정렬기능](https://user-images.githubusercontent.com/81960642/125171289-694c3480-e1ee-11eb-8369-0700550c9b70.JPG)

- 호텔예약 / 결재 기능

![호텔룸예약  결재 기능](https://user-images.githubusercontent.com/81960642/125171308-897bf380-e1ee-11eb-9d92-5d3aa8becd0e.JPG)

- 호텔 좋아요 기능

![호텔 좋아요 기능](https://user-images.githubusercontent.com/81960642/125171329-a7495880-e1ee-11eb-9087-e5bbe969fa04.JPG)

  </div>
</details>

  - ‘호텔목록조회(키워드 / 일정)’ 기능
    + 키워드 단어검색과 checkin / checkout일정을 통한 숙박가능한 호텔 조회
  - ‘호텔정렬’ 기능
    + Database에 저장되어 있는 데이터를 바탕으로 평점순 / 리뷰순 / 최저가격순 / 최고가격순을 DB쿼리를 통해 호텔목록 조회 
  - 호텔룸예약 / 결재 기능
    + 호텔조회를 통해 예약가능한 호텔을 정했다면 해당 호텔의 룸선택을 통한 예약서비스를 제공하며, 해당 호텔의 룸일정에 대한 예약이 이미 있는 경우 해당 기간에 호텔룸을 중복 예약할 수 없도록 구현
    + 해당호텔의 룸이 비었을 경우 예약 및 결재가 가능하도록 구현 
  - 호텔 좋아요 기능
    + 호텔목록조회를 했을 경우 마음에 드는 호텔을 임시로 저장하고 싶은 경우 좋아요 기능을 통해 원하는 호텔을 하트표시를 통해 표시 할 수 있으면 좋아요 목록 또한 볼수 있도록 구현
***

<details>
  <summary>호텔예약관리</summary>
  <div markdown="1">

- 예약된 호텔 조회

![예약된 호텔 조회](https://user-images.githubusercontent.com/81960642/125171474-63a31e80-e1ef-11eb-9d7c-6fe602f44a9c.JPG)

- 완료된 호텔 조회

![완료된 예약](https://user-images.githubusercontent.com/81960642/125171480-6f8ee080-e1ef-11eb-818b-6122cac263b5.JPG)

- 예약된 호텔 결재취소

![예약된 호텔 결재취소](https://user-images.githubusercontent.com/81960642/125171517-9e0cbb80-e1ef-11eb-8e39-15a58d04b1af.JPG)


  </div>
</details>

  - ‘예약된 호텔’ 조회
    + 조회하는 날짜를 기준으로 REST Controller를 통해 해당 ID의 예약된 호텔목록 조회
  - ‘완료된 호텔’ 조회
    + 조회하는 날짜를 기준으로 REST Controller를 통해 해당 ID가 다녀온 호텔목록 조회
  - ‘예약된 호텔 결재취소’ 조회
    + 취소하는 해당날짜를 기준으로 해당호텔의 checkin 날짜가 지나지 않았을 경우 해당호텔의 결재취소가 가능하도록 구현
  
***

<details>
  <summary>호텔리뷰관리</summary>
  <div markdown="1">

- 리뷰작성

![리뷰작성](https://user-images.githubusercontent.com/81960642/125172102-97cc0e80-e1f2-11eb-8d59-c2a3223c07ca.JPG)

- 나의 리뷰 조회 / 수정 / 삭제

![나의 리뷰 조회  수정  삭제](https://user-images.githubusercontent.com/81960642/125172113-a5819400-e1f2-11eb-92eb-c637da504df2.JPG)


  </div>
</details>

  - ‘리뷰작성’ 기능
    + 리뷰를 작성하는 날짜가 해당호텔의 예약된 checkout 날짜가 이후 라면 해당호텔에 대한 사용자가 리뷰를 작성하도록 구현 (이미지 업로드 기능 구현)
  - ‘리뷰 수정 / 삭제 / 조회’ 기능
    + 리뷰를 작성한 ID사용자만 리뷰를 수정 / 삭제 / 조회 할 수 있도록 구현
    (이미지 변경가능)
  
***
