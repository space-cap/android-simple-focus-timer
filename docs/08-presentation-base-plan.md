# 프레젠테이션 계층 기본 구현 계획

## 목표 설명
UI의 기초 구성 요소인 내비게이션과 테마를 설정합니다.

## 사용자 검토 필요 사항
> [!NOTE]
> 화면 간 이동을 처리하기 위해 `androidx.navigation:navigation-compose` 라이브러리를 추가하겠습니다.

## 변경 제안

### Gradle 설정 (`gradle/libs.versions.toml`)
#### [MODIFY] [libs.versions.toml](file:///c:/workdir/space-cap/AndroidStudioProjects/SimpleFocusTimer/gradle/libs.versions.toml)
- 버전 추가: `navigationCompose = "2.8.0"` (또는 안정적인 최신 버전).
- 라이브러리 추가: `androidx-navigation-compose`.

### 앱 빌드 설정 (`app/build.gradle.kts`)
#### [MODIFY] [build.gradle.kts](file:///c:/workdir/space-cap/AndroidStudioProjects/SimpleFocusTimer/app/build.gradle.kts)
- `implementation(libs.androidx.navigation.compose)` 의존성 추가.

### 내비게이션 (Navigation)
#### [NEW] [Screen.kt](file:///c:/workdir/space-cap/AndroidStudioProjects/SimpleFocusTimer/app/src/main/java/com/ezlevup/simplefocustimer/presentation/navigation/Screen.kt)
- Sealed 클래스 `Screen(val route: String)`.
- 객체: `Timer`, `History`.

#### [NEW] [SimpleFocusNavHost.kt](file:///c:/workdir/space-cap/AndroidStudioProjects/SimpleFocusTimer/app/src/main/java/com/ezlevup/simplefocustimer/presentation/navigation/SimpleFocusNavHost.kt)
- Composable `SimpleFocusNavHost`.
- `NavHost`를 정의하고 시작 목적지를 `Timer`로 설정.
- `TimerScreen` 및 `HistoryScreen`을 위한 플레이스홀더 (빈 Composable 또는 텍스트) 추가.

### 메인 액티비티 (Main Activity)
#### [MODIFY] [MainActivity.kt](file:///c:/workdir/space-cap/AndroidStudioProjects/SimpleFocusTimer/app/src/main/java/com/ezlevup/simplefocustimer/MainActivity.kt)
- `Scaffold` 내부에서 `SimpleFocusNavHost` 호출.

## 검증 계획

### 자동화된 테스트
- `./gradlew assembleDebug`를 실행하여 의존성 및 Compose 설정 확인.

### 수동 검증
- 앱 실행 (에뮬레이터가 있는 경우) - 타이머 화면 플레이스홀더가 표시되어야 함.
