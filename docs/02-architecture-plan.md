# 아키텍처 계획 및 폴더 구조

## 아키텍처 개요
이 프로젝트는 관심사의 분리, 테스트 용이성, 프레임워크로부터의 독립성을 보장하기 위해 **Clean Architecture** 원칙을 따릅니다.

### 계층 분리 전략
1.  **Domain Layer (도메인 계층)** (내부 원):
    *   **비즈니스 로직** (Use Cases) 및 **도메인 모델**을 포함합니다.
    *   **순수 Kotlin** 라이브러리 (Android 의존성 없음).
    *   Data 계층에서 구현해야 할 **Repository 인터페이스**를 정의합니다.
2.  **Data Layer (데이터 계층)** (외부 원 - 구현):
    *   Domain 계층에 정의된 Repository 인터페이스를 구현합니다.
    *   데이터 소스 처리: **Room Database** (로컬).
    *   **Entities** (데이터베이스 모델) 및 이를 도메인 모델로 변환하는 Mapper를 포함합니다.
3.  **Presentation Layer (프레젠테이션 계층)** (외부 원 - UI):
    *   **MVVM 패턴**: ViewModel이 `StateFlow`를 통해 상태를 노출합니다.
    *   **Jetpack Compose**: 상태를 기반으로 UI를 렌더링합니다.
    *   오직 **Domain Layer**에만 의존합니다.

## 의존성 주입 (Koin)
**Koin**을 사용하여 의존성 주입을 처리하며, 다음과 같은 모듈로 구성합니다:

*   **`databaseModule`**: `RoomDatabase` 인스턴스 및 DAO 제공.
*   **`repositoryModule`**: Repository 구현체(Data 계층)를 인터페이스(Domain 계층)에 바인딩.
*   **`useCaseModule`**: 도메인 Use Case 제공.
*   **`viewModelModule`**: Jetpack Compose ViewModel 제공.

## 제안된 폴더 구조
```text
com.example.simplefocustimer
├── app
│   ├── SimpleFocusTimerApp.kt  // Application 클래스 (Koin 시작)
│   └── di                      // Koin 모듈
│       ├── AppModule.kt
│       └── ...
├── data
│   ├── local                   // Room 로직
│   │   ├── SimpleFocusDatabase.kt
│   │   ├── dao
│   │   │   └── FocusSessionDao.kt
│   │   └── entity
│   │       └── FocusSessionEntity.kt
│   ├── repository              // Repository 구현
│   │   └── FocusRepositoryImpl.kt
│   └── mapper                  // Entity <-> Domain Model 매퍼
│       └── FocusMapper.kt
├── domain
│   ├── model                   // 순수 데이터 클래스
│   │   └── FocusSession.kt
│   ├── repository              // 인터페이스
│   │   └── FocusRepository.kt
│   └── usecase                 // 비즈니스 로직
│       ├── StartTimerUseCase.kt
│       ├── StopTimerUseCase.kt
│       └── SaveSessionUseCase.kt
└── presentation
    ├── theme                   // Material3 테마 & 색상
    ├── navigation              // NavHost & 라우트
    ├── timer                   // 타이머 기능
    │   ├── TimerScreen.kt
    │   ├── TimerViewModel.kt
    │   └── components          // 타이머용 재사용 UI 컴포넌트
    └── history                 // 기록 기능
        ├── HistoryScreen.kt
        ├── HistoryViewModel.kt
        └── components          // 기록용 재사용 UI 컴포넌트
```
