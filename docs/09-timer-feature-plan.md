# 타이머 기능 구현 계획

## 목표 설명
핵심 기능인 타이머를 구현합니다. 상태 관리를 위한 ViewModel과 원형 카운트다운 UI를 포함한 화면을 개발합니다.

## 변경 제안

### ViewModel
#### [NEW] [TimerViewModel.kt](../app/src/main/java/com/ezlevup/simplefocustimer/presentation/timer/TimerViewModel.kt)
- **State**: `TimerUiState`
    - `currentTime`: Long (남은 시간, 초 단위)
    - `totalTime`: Long (기본값 25 * 60)
    - `isTimerRunning`: Boolean
    - `progress`: Float (0.0 ~ 1.0)
- **Functions**:
    - `startTimer()`: `StartTimerUseCase` 실행.
    - `pauseTimer()`: 카운트다운 작업 취소 (상태 유지).
    - `stopTimer()`: 작업 취소 및 상태 초기화.
    - `onTimerFinished()`: `SaveFocusSessionUseCase` 호출하여 세션 저장.

### UI 컴포넌트
#### [NEW] [TimerScreen.kt](../app/src/main/java/com/ezlevup/simplefocustimer/presentation/timer/TimerScreen.kt)
- **구성 요소**:
    - `Box`: 중앙 정렬용.
    - `CircularProgressIndicator`: 원형 진행바.
    - `Text`: 남은 시간 표시 (MM:SS 형식).
    - `Row`: 버튼 모음 (시작, 일시정지, 정지).
- **내비게이션**:
    - 기록(History) 화면으로 이동하는 기능 필요 (UI 상단이나 하단 버튼).

#### [NEW] [TimerComponents.kt](../app/src/main/java/com/ezlevup/simplefocustimer/presentation/timer/components/TimerComponents.kt)
- (선택 사항) UI가 복잡해지면 재사용 가능한 컴포넌트로 분리. (일단은 `TimerScreen.kt`에 단순하게 유지).

### 의존성 주입 (DI)
#### [MODIFY] [AppModule.kt](../app/src/main/java/com/ezlevup/simplefocustimer/di/AppModule.kt)
- `viewModelModule`에 `viewModel { TimerViewModel(get(), get()) }` 추가.

### 내비게이션
#### [MODIFY] [SimpleFocusNavHost.kt](../app/src/main/java/com/ezlevup/simplefocustimer/presentation/navigation/SimpleFocusNavHost.kt)
- `TimerScreen` 플레이스홀더를 실제 `TimerScreen` Composable로 교체.

## 검증 계획

### 자동화된 테스트
- `./gradlew assembleDebug` 빌드 확인.

### 수동 검증
- 앱 실행:
    - **시작** 버튼: 타이머 카운트다운 시작, 진행바 업데이트 확인.
    - **일시정지** 버튼: 타이머 멈춤 확인.
    - **시작** 버튼 (재개): 멈춘 시간부터 다시 시작 확인.
    - **정지** 버튼: 시간 초기화(25:00) 확인.
    - **완료 시**: 세션 저장 확인 (로그 확인 또는 이후 History 기능에서 확인).
