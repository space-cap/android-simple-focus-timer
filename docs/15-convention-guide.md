# 15. 코딩 컨벤션 및 스타일 가이드 (Convention Guide)

팀 협업 또는 1인 개발 시 프로젝트 품질 유지를 위한 규칙입니다.

## 1. 네이밍 컨벤션 (Naming)
- **클래스/인터페이스**: `PascalCase` 사용 (예: `TimerViewModel`, `FocusRepository`).
- **함수/변수**: `camelCase` 사용 (예: `startTimer()`, `currentTime`).
- **도메인 별칭**:
    - UseCase: 반드시 `UseCase` 접미사 사용 (예: `StartTimerUseCase`).
    - RepositoryImpl: 인터페이스 구현체는 반드시 `Impl` 접미사 사용.
- **Composable**: 반드시 명사형 `PascalCase` 사용 (예: `TimerScreen`, `HistoryItem`).

## 2. 패키지 구조 (Package Structure)
- 기능(Feature) 단위가 아닌 **계층(Layer)** 단위로 최상위 패키지를 구성합니다.
    - `data/`: DB, API, Repository 구현, Mapper.
    - `domain/`: Model, Repository Interface, UseCase.
    - `presentation/`: UI(Component/Screen), ViewModel, Navigation.

## 3. Jetpack Compose 규칙
- **Stateless/Stateful 분리**: 가급적 비즈니스 로직이 없는 Stateless Composable을 유지하고, 상위 Stateful Composable에서 상태를 주입(Hoisting)합니다.
- **Modifier 전달**: 모든 재사용 가능한 Composable은 첫 번째 인자로 기본값이 지정된 `modifier: Modifier = Modifier`를 받아야 합니다.
- **Private Components**: 한 화면에서만 쓰는 작은 컴포넌트는 해당 파일 내부에서 `private`으로 선언합니다.

## 4. 의존성 주입 (Koin)
- 모듈 선언은 `di/AppModule.kt`에서 일괄 관리하되, 프로젝트가 커지면 각 계층별로 파일을 나눕니다 (`DataModule.kt`, `ViewModelModule.kt` 등).

## 5. 코틀린 스타일
- 불변성 선호: 가능한 모든 변수는 `val`로 선언합니다.
- 확장 함수 적극 활용: 매퍼나 유효성 검사 등은 확장 함수를 사용하여 가독성을 높입니다.
- 단일 표현식 함수: 짧은 함수는 `=`을 사용한 단일 표현식으로 작성합니다.
