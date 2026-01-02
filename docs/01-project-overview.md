# 프로젝트 개요: Simple Focus Timer

## 소개
**Simple Focus Timer**는 사용자가 집중 시간을 추적할 수 있도록 돕는 안드로이드 애플리케이션입니다. 원형 카운트다운 타이머와 완료된 세션 기록을 보여주는 단순하고 깔끔한 인터페이스를 특징으로 합니다.

## 프로젝트 목표
최신 개발 방식인 **Clean Architecture**와 **Jetpack Compose**를 사용하여 기능적이고 유지보수가 용이한 안드로이드 애플리케이션을 개발합니다.

## 핵심 기능
1.  **집중 타이머**: 카운트다운되는 원형 시각 타이머 (기본값: 25분).
2.  **타이머 제어**: 타이머 시작, 일시 정지, 정지 기능.
3.  **기록 추적**: 시간 경과에 따른 생산성을 추적하기 위해 완료된 집중 세션 목록 표시.

## 기술 스택 및 가이드라인
확장성과 유지보수성을 보장하기 위해 엄격한 기술 가이드라인을 준수합니다.

*   **언어**: Kotlin
*   **UI 프레임워크**: Jetpack Compose (Material3)
*   **아키텍처**: Clean Architecture (Presentation, Domain, Data 계층) + MVVM 패턴
*   **의존성 주입**: **Koin** (어노테이션 또는 모듈 사용)
*   **상태 관리**: Kotlin Coroutines & StateFlow
*   **로컬 저장소**: Room Database
