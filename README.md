# SweetMe-Back

> EFUB 3rd toy-project Sweetie SweetMe

<img src="https://github.com/SweetMe-Sweetie/SweetMe-Back/assets/89291223/0d793cf9-2afa-4840-99d5-83c6f8f8de24" width=250 />

<br>

## 🍭 프로젝트 설명
![메인페이지](https://github.com/SweetMe-Sweetie/SweetMe-Back/assets/89291223/8ccd639b-42f1-499e-a26a-31a2d1cb3638)
![모집글페이지](https://github.com/SweetMe-Sweetie/SweetMe-Back/assets/89291223/fe43f472-c29d-4152-9498-348785ff21dc)

<details>
<summary>추가 페이지</summary>
    
![로그인페이지](https://github.com/SweetMe-Sweetie/SweetMe-Back/assets/89291223/41c1ab5b-b301-48c6-84fd-0283c4ea4465)
![마이페이지](https://github.com/SweetMe-Sweetie/SweetMe-Back/assets/89291223/b58576a7-ef4d-4b4b-98bf-491796767391)
    
</details>

### 📚 스터디 모집 서비스 SweetMe

> SweetMe는 동일한 목표를 지닌 사람들이 교류하며 함께 성장해나갈 수 있도록 연결고리를 만들어주는 스터디 모집 서비스입니다. SweetMe에서는 간단한 소셜 로그인 이후 스터디 모집 글을 작성할 수 있습니다. 메인 페이지에서 사용자는 모집 여부와 카테고리를 선택하여 원하는 스터디를 찾고 모집 글 작성자에게 연락하여 스터디 활동을 하게 됩니다. 작성자는 자신이 작성한 글을 홍보하고 싶을 경우 일정 금액을 결제하여 메인 페이지 상단의 광고 배너에 게시할 수 있습니다. SweetMe는 같이 공부하자는 의미의 스터디윗미(Study With Me)를 줄인 말인 스윗미를 SweetMe라고 표현한 것으로, 달콤함이 주는 긍정적인 에너지처럼 이 서비스에서 모집되는 스터디에서 만들어질 시너지를 기대합니다. 

### 📆 개발 기간

- 기획: 2023.09.05. - 2023.09.16.
- 프로젝트 세팅: 2023.09.17. - 2023.09.26.
- API 개발: 2023.09.27. - 2023.10.31.
- 배포 및 API 연결: 2023.11.01 - 2023.11.24.

<br>

## 👩‍💻 팀원 소개

|                                            이한나                                         |                                조민서                           |
|:-----------------------------------------------------------------------------------------:|:------------------------------------------------------------------:|
|             <img src="https://avatars.githubusercontent.com/u/89291223?s=400&u=64dcff931bf6efee8bb8cc371573472faa9b373f&v=4"/>             | <img src="https://avatars.githubusercontent.com/u/97473239?v=4"/> |
|                     [@hannah0226](https://github.com/hannah0226)                      |            [@rovemin](https://github.com/orgs/SweetMe-Sweetie/people/rovemin)            |
| 프로젝트 세팅 및 엔티티 생성<br>카카오 OAuth 로그인 개발<br>게시글 작성/수정/삭제 API 개발<br>좋아요 API 개발<br>배포 & CI/CD 환경 구축 |   카카오 결제 API 개발<br>게시글 조회 & 필터링 API 개발<br>게시글 정렬 API 개발<br>GitHub Wiki API 명세서 작성   |      

<br>

## 📌 Commit Convention

### TAG: 메시지

|  태그 이름   |             설명              |
|:--------:|:---------------------------:|
| ✨ feat   |          새로운 기능 추가          |
|   🔧 fix    |          버그, 오류 수정          |
|  🎨 style   | 코드 포맷팅, 오타 수정, 주석 수정 및 삭제 등 |
|   📝 docs   |            문서 수정            |
|  💚 chore   |      빌드 및 패키지 수정 및 삭제       |
| ♻️ refactor |           코드 리팩토링           |

### 🪵 Branch Strategy

1. issue 생성
2. local - feature/~ 에서 각자 기능 작업
3. remote - feature/~ 에 Push
4. remote - develop 으로 Pull Request
5. 코드 리뷰 후 remote - develop Merge
6. remote - develop 에 Merge 될 때 local - develop pull 받아 최신 상태 유지

<br>

## ⚙️ 기술 아키텍쳐

### 사용 스택

| 통합 개발 환경         | IntelliJ                        |
|------------------|---------------------------------|
| Spring 버전        | 2.7.11                          |
| 데이터베이스           | AWS RDS(MySQL)                  |
| 배포               | AWS EC2(Ubuntu), S3, CodeDepoly |
| Project 빌드 관리 도구 | Gradle                          |
| CI/CD 툴          | Github Actions                  |
| ERD 다이어그램 툴      | ERD Cloud                       |
| Java version     | Java 17                         |

<br>

### 아키텍쳐 구조

![아키텍처 구조](https://github.com/SweetMe-Sweetie/SweetMe-Back/assets/97473239/15b30a99-afff-4c71-87de-e3327ce6c2a4)

<br>

## ☁️ ERD

![SweetMe](https://github.com/SweetMe-Sweetie/SweetMe-Back/assets/89291223/4fccce3d-b5c6-45d3-8130-c8915a9c9678)

<br>

## 📜 API 명세서
https://github.com/SweetMe-Sweetie/SweetMe-Back/wiki
<br>
<br>

## 📁 프로젝트 폴더 구조

```
📂
└─src
    └─main
       ├─generated
       ├─java
       │  └─efub
       │      └─SweetMeback
       │          ├─domain
       │          │  ├─global
       │          │  │  └─exception
       │          │  ├─heart
       │          │  │  ├─entity
       │          │  │  ├─repository
       │          │  │  └─service
       │          │  ├─member
       │          │  │  ├─controller
       │          │  │  ├─dto
       │          │  │  ├─entity
       │          │  │  ├─repository
       │          │  │  └─service
       │          │  ├─oauth
       │          │  │  ├─controller
       │          │  │  ├─dto
       │          │  │  └─service
       │          │  ├─payment
       │          │  │  ├─controller
       │          │  │  ├─dto
       │          │  │  ├─entity
       │          │  │  ├─repository
       │          │  │  └─Service
       │          │  └─post
       │          │      ├─controller
       │          │      ├─dto
       │          │      ├─entity
       │          │      ├─repository
       │          │      └─service
       │          └─global
       │              ├─config
       │              └─jwt
       └─resources
           ├─static
           └─templates
```

