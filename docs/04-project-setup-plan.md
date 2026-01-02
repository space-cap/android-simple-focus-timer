# 프로젝트 설정 구현 계획

## 목표 설명
필요한 라이브러리(Koin, Room, Coroutines) 의존성을 설정하고, Clean Architecture 폴더 구조를 생성합니다.

## 사용자 검토 필요 사항
> [!NOTE]
> `app/build.gradle.kts`에서 확인된 패키지명은 `com.ezlevup.simplefocustimer`입니다. 초기 아키텍처 계획에 있던 `com.example` 대신 이 패키지명을 사용하겠습니다.

## 변경 제안

### Gradle 설정 (`gradle/libs.versions.toml`)
#### [MODIFY] [libs.versions.toml](../gradle/libs.versions.toml)
- 버전 추가:
    - Koin (4.0.0)
    - Room (2.6.1)
    - KSP Plugin (2.0.21-1.0.25 - Kotlin 2.0.21과 일치)
    - Coroutines (1.9.0)
- 라이브러리 추가:
    - `koin-androidx-compose`
    - `androidx-room-runtime`, `androidx-room-compiler`, `androidx-room-ktx`
    - `kotlinx-coroutines-android`
- 플러그인 추가:
    - `ksp` (Room 컴파일러용)

### 앱 빌드 설정 (`app/build.gradle.kts`)
#### [MODIFY] [build.gradle.kts](../app/build.gradle.kts)
- KSP 플러그인 적용.
- Koin, Room(runtime, ktx), Coroutines 구현 의존성 추가.
- Room 컴파일러를 위한 KSP 프로세서 추가.

### 패키지 구조
#### [NEW] 디렉토리
`c:\workdir\space-cap\AndroidStudioProjects\SimpleFocusTimer\app\src\main\java\com\ezlevup\simplefocustimer` 경로 하위에 다음 구조를 생성합니다:
- `data/local`
- `data/repository`
- `data/mapper`
- `domain/model`
- `domain/repository`
- `domain/usecase`
- `presentation/theme`
- `presentation/navigation`
- `presentation/timer`
- `presentation/history`
- `di`

### Application 클래스
#### [NEW] [SimpleFocusTimerApp.kt](../app/src/main/java/com/ezlevup/simplefocustimer/SimpleFocusTimerApp.kt)
- `Application` 클래스 상속.
- `onCreate`에서 Koin 초기화 (현재는 빈 모듈 리스트 사용).
- `AndroidManifest.xml`에 등록.

#### [MODIFY] [AndroidManifest.xml](../app/src/main/AndroidManifest.xml)
- `android:name=".SimpleFocusTimerApp"` 속성 추가.
