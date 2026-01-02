# 🎯 Simple Focus Timer

<div align="center">

**집중력 향상을 위한 미니멀한 포모도로 타이머 앱**

[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.21-purple.svg)](https://kotlinlang.org)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-2024.09.00-green.svg)](https://developer.android.com/jetpack/compose)
[![Min SDK](https://img.shields.io/badge/Min%20SDK-24-blue.svg)](https://developer.android.com/about/versions/nougat)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

</div>

## 📱 소개

Simple Focus Timer는 Clean Architecture와 최신 Android 기술 스택을 활용하여 개발된 포모도로 타이머 애플리케이션입니다. 
아름다운 UI와 직관적인 UX로 집중 시간을 효과적으로 관리할 수 있습니다.

### ✨ 주요 기능

- ⏱️ **25분 포모도로 타이머** - 시작, 일시정지, 정지 기능
- 🎨 **현대적인 UI/UX** - 다크 테마, 그라데이션, 부드러운 애니메이션
- 📊 **원형 진행바** - 시각적으로 남은 시간 확인
- 📝 **집중 세션 기록** - 완료된 세션 자동 저장 및 조회
- 💾 **로컬 저장소** - Room Database를 통한 데이터 영구 저장

## 🏗️ 아키텍처

이 프로젝트는 **Clean Architecture** 원칙을 따르며, 다음과 같은 계층으로 구성되어 있습니다:

```
📦 SimpleFocusTimer
├── 🎨 Presentation Layer (UI)
│   ├── TimerScreen - 타이머 화면
│   ├── HistoryScreen - 기록 화면
│   └── ViewModels - 상태 관리
├── 💼 Domain Layer (Business Logic)
│   ├── Models - 도메인 모델
│   ├── UseCases - 비즈니스 로직
│   └── Repository Interface
└── 💾 Data Layer (Data Source)
    ├── Room Database - 로컬 저장소
    ├── Repository Implementation
    └── Mappers - 데이터 변환
```

## 🛠️ 기술 스택

### Core
- **Kotlin** 2.0.21
- **Jetpack Compose** - 선언형 UI
- **Material Design 3** - 최신 디자인 시스템

### Architecture & DI
- **Clean Architecture** - 계층 분리
- **MVVM Pattern** - 프레젠테이션 패턴
- **Koin** 4.0.0 - 의존성 주입

### Async & Data
- **Kotlin Coroutines** 1.9.0 - 비동기 처리
- **Flow** - 반응형 데이터 스트림
- **Room** 2.6.1 - 로컬 데이터베이스

### Navigation
- **Navigation Compose** 2.8.5 - 화면 전환

## 📸 스크린샷

### 타이머 화면
- 다크 그라데이션 배경
- 보라색 원형 진행바
- 실시간 시간 표시 및 퍼센트
- START/PAUSE/STOP 컨트롤

### 기록 화면
- 완료된 집중 세션 목록
- 날짜/시간 및 지속 시간 표시
- Material Design 카드 UI

## 🚀 시작하기

### 요구사항

- Android Studio Hedgehog (2023.1.1) 이상
- JDK 11 이상
- Android SDK 24 (Nougat) 이상

### 설치 및 실행

1. **저장소 클론**
```bash
git clone https://github.com/yourusername/SimpleFocusTimer.git
cd SimpleFocusTimer
```

2. **Android Studio에서 프로젝트 열기**
```
File > Open > SimpleFocusTimer 폴더 선택
```

3. **Gradle 동기화**
```
프로젝트가 열리면 자동으로 Gradle 동기화가 시작됩니다.
```

4. **앱 실행**
```
Run 버튼(▶️) 클릭 또는 Shift + F10
```

### 빌드

```bash
# Debug APK 빌드
./gradlew assembleDebug

# Release APK 빌드
./gradlew assembleRelease
```

## 📁 프로젝트 구조

```
app/src/main/java/com/ezlevup/simplefocustimer/
├── data/
│   ├── local/
│   │   ├── dao/
│   │   │   └── FocusSessionDao.kt
│   │   ├── entity/
│   │   │   └── FocusSessionEntity.kt
│   │   └── SimpleFocusDatabase.kt
│   ├── mapper/
│   │   └── FocusMapper.kt
│   └── repository/
│       └── FocusRepositoryImpl.kt
├── domain/
│   ├── model/
│   │   └── FocusSession.kt
│   ├── repository/
│   │   └── FocusRepository.kt
│   └── usecase/
│       ├── GetFocusHistoryUseCase.kt
│       ├── SaveFocusSessionUseCase.kt
│       └── StartTimerUseCase.kt
├── presentation/
│   ├── history/
│   │   ├── HistoryScreen.kt
│   │   └── HistoryViewModel.kt
│   ├── navigation/
│   │   ├── Screen.kt
│   │   └── SimpleFocusNavHost.kt
│   ├── theme/
│   │   ├── Color.kt
│   │   ├── Theme.kt
│   │   └── Type.kt
│   └── timer/
│       ├── TimerScreen.kt
│       └── TimerViewModel.kt
├── di/
│   └── AppModule.kt
├── MainActivity.kt
└── SimpleFocusTimerApp.kt
```

## 🎓 학습 자료

프로젝트 개발 과정과 아키텍처 설계에 대한 자세한 문서는 `docs/` 폴더에서 확인할 수 있습니다:

- [01-프로젝트-개요](docs/01-project-overview.md)
- [02-아키텍처-계획](docs/02-architecture-plan.md)
- [03-구현-로드맵](docs/03-implementation-roadmap.md)
- [04-프로젝트-설정](docs/04-project-setup-plan.md)
- [05-도메인-계층](docs/05-domain-layer-plan.md)
- [06-데이터-계층](docs/06-data-layer-plan.md)
- [07-의존성-주입](docs/07-di-plan.md)
- [08-프레젠테이션-기본](docs/08-presentation-base-plan.md)
- [09-타이머-기능](docs/09-timer-feature-plan.md)
- [10-기록-기능](docs/10-history-feature-plan.md)
- [11-기술-결정-이유](docs/11-tech-stack-decisions.md)
- [12-트러블슈팅-및-교훈](docs/12-troubleshooting-lessons.md)
- [13-향후-로드맵](docs/13-future-roadmap.md)
- [14-테스트-전략](docs/14-testing-strategy.md)
- [15-컨벤션-가이드](docs/15-convention-guide.md)

## 🤝 기여하기

기여는 언제나 환영합니다! 다음 단계를 따라주세요:

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 라이선스

이 프로젝트는 MIT 라이선스 하에 배포됩니다. 자세한 내용은 [LICENSE](LICENSE) 파일을 참조하세요.

## 👨‍💻 개발자

**Your Name**
- GitHub: [@yourusername](https://github.com/yourusername)
- Email: your.email@example.com

## 🙏 감사의 말

- [Jetpack Compose](https://developer.android.com/jetpack/compose) - 현대적인 Android UI 툴킷
- [Koin](https://insert-koin.io/) - 경량 의존성 주입 프레임워크
- [Material Design 3](https://m3.material.io/) - 디자인 시스템

---

<div align="center">

**⭐ 이 프로젝트가 도움이 되었다면 Star를 눌러주세요! ⭐**

Made with ❤️ and ☕

</div>
