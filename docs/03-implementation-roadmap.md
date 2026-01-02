# 구현 로드맵 (Implementation Roadmap)

이 문서는 **Simple Focus Timer** 앱 개발을 위한 단계별 진행 과정을 정의합니다. 각 단계는 이전 단계의 작업을 바탕으로 진행됩니다.

## 1단계: 프로젝트 초기 설정 (Project Setup)
개발 환경을 구성하고 필요한 라이브러리를 추가합니다.
- [ ] **Gradle 설정**: `build.gradle.kts` 파일에 필요한 의존성 추가
    - [ ] AndroidX Core, Lifecycle (ViewModel, Runtime)
    - [ ] Jetpack Compose Activity, UI, Material3, Tooling
    - [ ] **Koin** (Dependency Injection)
    - [ ] **Room** (Local Database)
    - [ ] **Coroutines** (Async tasks)
- [ ] **패키지 구조 생성**: Clean Architecture에 맞는 폴더 구조 (`data`, `domain`, `presentation`) 생성
- [ ] **Application 클래스**: Koin 초기화를 위한 Application 클래스 작성 및 `AndroidManifest.xml` 등록

## 2단계: 도메인 계층 구현 (Domain Layer)
비즈니스 로직의 핵심인 도메인 영역을 먼저 구현합니다. (외부 의존성 없음)
- [ ] **Data Model**: `FocusSession` 데이터 클래스 정의
- [ ] **Repository Interface**: `FocusRepository` 인터페이스 정의
- [ ] **Use Cases**:
    - [ ] `StartTimerUseCase`
    - [ ] `PauseTimerUseCase`
    - [ ] `StopTimerUseCase` (세션 저장 로직 포함 가능)
    - [ ] `GetFocusHistoryUseCase`

## 3단계: 데이터 계층 구현 (Data Layer)
도메인 계층의 인터페이스를 구현하고 데이터를 관리합니다.
- [ ] **Room Entity**: `FocusSessionEntity` 정의
- [ ] **DAO**: `FocusSessionDao` 인터페이스 정의 (Insert, Query)
- [ ] **Database**: `SimpleFocusDatabase` 추상 클래스 구현
- [ ] **Mapper**: Entity ↔ Domain Model 변환을 위한 매퍼 구현
- [ ] **Repository Implementation**: `FocusRepositoryImpl` 클래스 구현 (Data Source와 Domain 연결)

## 4단계: 의존성 주입 (Dependency Injection)
Koin 모듈을 작성하여 각 계층의 의존성을 연결합니다.
- [ ] **Database Module**: Room Database 및 DAO 제공
- [ ] **Repository Module**: Repository 구현체 바인딩
- [ ] **UseCase Module**: Use Case 제공
- [ ] **ViewModel Module**: ViewModel 제공

## 5단계: 프레젠테이션 계층 - 기본 (Presentation Base)
UI의 기본 틀을 잡습니다.
- [ ] **Theme**: `Theme.kt`, `Color.kt`, `Type.kt` 설정 (Material3 적용)
- [ ] **Navigation**: `NavHost` 설정 및 화면 간 이동 경로(`Screen` sealed class) 정의

## 6단계: 타이머 기능 구현 (Timer Feature)
핵심 기능인 타이머 화면을 구현합니다.
- [ ] **TimerViewModel**: 타이머 로직(카운트다운), 상태(`TimerState`) 관리 구현
- [ ] **TimerScreen UI**:
    - [ ] 원형 프로그레스 바 컴포넌트
    - [ ] 시간 표시 텍스트
    - [ ] 시작/일시정지/정지 버튼
    - [ ] ViewModel과 UI 연결

## 7단계: 기록 기능 구현 (History Feature)
완료된 집중 시간을 보여주는 화면을 구현합니다.
- [ ] **HistoryViewModel**: 완료된 세션 목록 로드 로직 구현
- [ ] **HistoryScreen UI**:
    - [ ] `LazyColumn`을 사용한 리스트 표시
    - [ ] 각 아이템(날짜, 집중 시간) 디자인
    - [ ] 빈 상태(Empty State) 처리

## 8단계: 테스트 및 마무리 (Testing & Polish)
앱의 완성도를 높입니다.
- [ ] **기능 테스트**: 전체 흐름(타이머 시작 -> 완료 -> 기록 저장 -> 목록 확인) 테스트
- [ ] **UI 다듬기**: 애니메이션 추가, 간격 및 색상 조정
- [ ] **코드 정리**: 불필요한 코드 제거 및 주석 보완
