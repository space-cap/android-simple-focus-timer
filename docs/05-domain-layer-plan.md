# 도메인 계층 구현 계획

## 목표 설명
도메인 계층의 핵심 비즈니스 로직과 모델을 구현합니다. 이 계층은 순수 Kotlin으로 작성되며, Android 프레임워크나 Data 계층에 대한 의존성이 없습니다.

## 사용자 검토 필요 사항
> [!NOTE]
> `FocusSession` 모델에 `id`, `startTime` (시작 시간), `durationSeconds` (집중 지속 시간) 필드를 정의하여 집중 기록을 추적할 예정입니다.

## 변경 제안

### 도메인 모델 (Domain Model)
#### [NEW] [FocusSession.kt](file:///c:/workdir/space-cap/AndroidStudioProjects/SimpleFocusTimer/app/src/main/java/com/ezlevup/simplefocustimer/domain/model/FocusSession.kt)
- 데이터 클래스 `FocusSession`.
- 필드:
    - `id`: Long (새 세션의 경우 기본값 0)
    - `startTime`: Long (타임스탬프)
    - `durationSeconds`: Long (실제 집중한 시간, 초 단위)

### 리포지토리 인터페이스 (Repository Interface)
#### [NEW] [FocusRepository.kt](file:///c:/workdir/space-cap/AndroidStudioProjects/SimpleFocusTimer/app/src/main/java/com/ezlevup/simplefocustimer/domain/repository/FocusRepository.kt)
- 인터페이스 `FocusRepository`.
- 함수:
    - `suspend fun saveSession(session: FocusSession)`
    - `fun getAllSessions(): Flow<List<FocusSession>>`

### 유스케이스 (Use Cases)
#### [NEW] [StartTimerUseCase.kt](file:///c:/workdir/space-cap/AndroidStudioProjects/SimpleFocusTimer/app/src/main/java/com/ezlevup/simplefocustimer/domain/usecase/StartTimerUseCase.kt)
- 카운트다운을 시작하는 로직.
- 남은 시간(초)을 방출하는 `Flow<Long>` 반환.
- 입력으로 `durationSeconds` (목표 시간)를 받음.
- 1초 간격으로 딜레이(`delay(1000)`)를 주며 카운트다운.

#### [NEW] [SaveFocusSessionUseCase.kt](file:///c:/workdir/space-cap/AndroidStudioProjects/SimpleFocusTimer/app/src/main/java/com/ezlevup/simplefocustimer/domain/usecase/SaveFocusSessionUseCase.kt)
- `repository.saveSession` 호출.

#### [NEW] [GetFocusHistoryUseCase.kt](file:///c:/workdir/space-cap/AndroidStudioProjects/SimpleFocusTimer/app/src/main/java/com/ezlevup/simplefocustimer/domain/usecase/GetFocusHistoryUseCase.kt)
- `repository.getAllSessions()` 반환.

## 검증 계획

### 자동화된 테스트
- UseCase에 대한 JUnit 테스트 작성 (순수 Kotlin 테스트).
- `StartTimerUseCase`가 정확한 시간 값을 방출하는지 검증.
- `SaveFocusSessionUseCase`가 리포지토리 메서드를 호출하는지 검증.

### 수동 검증
- 해당 없음 (순수 로직 구현 단계).
