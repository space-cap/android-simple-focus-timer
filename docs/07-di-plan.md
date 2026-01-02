# 의존성 주입 구현 계획

## 목표 설명
Database, Data Layer (Repository), Domain Layer (Use Cases), Presentation Layer (ViewModels)에 대한 의존성을 제공하기 위해 Koin 모듈을 구성합니다.

## 변경 제안

### Koin 모듈
#### [NEW] [AppModule.kt](file:///c:/workdir/space-cap/AndroidStudioProjects/SimpleFocusTimer/app/src/main/java/com/ezlevup/simplefocustimer/di/AppModule.kt)
- `databaseModule`: `SimpleFocusDatabase` 및 `FocusSessionDao` 제공.
- `repositoryModule`: `FocusRepositoryImpl`을 `FocusRepository`에 바인딩.
- `useCaseModule`: `StartTimerUseCase`, `SaveFocusSessionUseCase`, `GetFocusHistoryUseCase` 제공.
- `viewModelModule`: 현재는 비워 둠 (나중에 채워질 예정).

### Application 클래스 업데이트
#### [MODIFY] [SimpleFocusTimerApp.kt](file:///c:/workdir/space-cap/AndroidStudioProjects/SimpleFocusTimer/app/src/main/java/com/ezlevup/simplefocustimer/SimpleFocusTimerApp.kt)
- 정의된 모듈들을 `startKoin { modules(...) }`에 추가.

## 검증 계획

### 자동화된 테스트
- `./gradlew assembleDebug`를 실행하여 Koin DSL 문법 및 임포트 확인.
- (런타임) 앱 실행 시 크래시가 발생하면 DI 실패를 의미하지만, 아직 UI 구현 전이므로 완전한 런타임 테스트는 나중에 진행.

### 수동 검증
- 해당 없음.
