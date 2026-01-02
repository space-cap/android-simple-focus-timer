# 기록(History) 기능 구현 계획

## 목표 설명
완료된 집중 세션 목록을 표시하는 기록 기능을 구현합니다.

## 변경 제안

### ViewModel
#### [NEW] [HistoryViewModel.kt](../app/src/main/java/com/ezlevup/simplefocustimer/presentation/history/HistoryViewModel.kt)
- **State**: `HistoryUiState`
    - `sessions`: `List<FocusSession>` (기본값 빈 리스트)
- **Logic**:
    - `init`: `GetFocusHistoryUseCase` Flow를 수집(collect)하여 상태 업데이트.

### UI 컴포넌트
#### [NEW] [HistoryScreen.kt](../app/src/main/java/com/ezlevup/simplefocustimer/presentation/history/HistoryScreen.kt)
- **구성 요소**:
    - `LazyColumn`: 세션 목록 표시.
    - `HistoryItem` (private composable): 날짜/시간 및 지속 시간을 보여주는 카드.
    - `TopAppBar` (선택 사항, 또는 간단한 텍스트 헤더 "Focus History").
    - `BackButton`: 타이머 화면으로 돌아가기 버튼.

### 의존성 주입 (DI)
#### [MODIFY] [AppModule.kt](../app/src/main/java/com/ezlevup/simplefocustimer/di/AppModule.kt)
- `viewModelModule`에 `viewModel { HistoryViewModel(get()) }` 추가.

### 내비게이션
#### [MODIFY] [SimpleFocusNavHost.kt](../app/src/main/java/com/ezlevup/simplefocustimer/presentation/navigation/SimpleFocusNavHost.kt)
- `HistoryScreen` 플레이스홀더를 실제 `HistoryScreen` Composable로 교체.

## 검증 계획

### 자동화된 테스트
- `./gradlew assembleDebug` 빌드 확인.

### 수동 검증
- 앱 실행.
- 타이머 세션 1회 완료.
- History 화면으로 이동.
- 방금 완료한 세션이 목록에 올바른 시작 시간과 지속 시간으로 표시되는지 확인.
