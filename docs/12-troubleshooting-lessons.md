# 12. 트러블슈팅 및 배운 점 (Troubleshooting & Lessons)

개발 과정에서 발생한 주요 문제들과 그 해결 과정, 그리고 배운 점을 기록합니다.

## 1. Material Icons Unresolved Reference 이슈
- **문제**: `Icons.Default.History` 또는 `Icons.Default.Pause` 등을 사용하려 할 때 "Unresolved reference" 오류가 발생하며 빌드가 실패했습니다.
- **원인**: 기본 `material-icons-core` 라이브러리에는 자주 사용되는 일부 아이콘만 포함되어 있으며, `History` 같은 아이콘은 `material-icons-extended` 라이브러리에 별도로 정의되어 있기 때문입니다.
- **해결**: 프로젝트의 복잡도를 낮추기 위해 의존성을 추가하는 대신, **커스텀 스타일의 텍스트 버튼(STOP, START, HISTORY)**으로 디자인을 변경했습니다.
- **교훈**: 라이브러리의 범위를 정확히 파악하는 것이 중요하며, 기술적 제약이 있을 때는 디자인적으로 대안을 찾아 해결하는 유연함이 필요함을 배웠습니다.

## 2. 타이머 진행바(Circular Progress)의 움직임 최적화
- **문제**: 1초마다 업데이트되는 `currentTime`을 기반으로 `progress`를 계산하니, 진행바가 딱딱하게 끊기듯 움직였습니다.
- **해결**: Compose의 `animateFloatAsState`와 `tween` 애니메이션 스펙을 사용하여 `progress` 값의 변화를 부드럽게 보간했습니다.
- **교훈**: 데이터의 갱신 주기와 사용자 경험(UX) 간의 간극은 애니메이션을 통해 극복할 수 있음을 확인했습니다.

## 3. Clean Architecture 계층 간 데이터 매핑
- **문제**: `FocusSession` (도메인)과 `FocusSessionEntity` (DB) 간의 변환을 어디서 처리할지 고민했습니다.
- **해결**: Data 계층에 `mapper` 패키지를 두고 확장 함수 형식의 매퍼를 구현하여, Repository Implementation 계층에서만 변환이 일어나도록 격리했습니다.
- **교훈**: 데이터를 계층별로 독립시키는 과정에서 발생하는 부가적인 코드(Boilerplate)는 아키텍처의 안정성과 장기적인 유지보수성으로 충분히 보상받는다는 점을 깨달았습니다.

## 4. Koin ViewModel DSL 버전 이슈
- **문제**: 최신 Koin 버전에서 예전 방식의 ViewModel 주입 방식이 Deprecated 되거나 동작이 변경되었습니다.
- **해결**: 최신 문법에 맞는 `viewModelOf(::TimerViewModel)` 등을 검토하고, 가장 안정적인 `viewModel { ... }` DSL을 유지하며 Compose의 `koinViewModel()`로 연동했습니다.
- **교훈**: 외부 라이브러리 도입 시 공식 문서의 최신 변경 사항(Migration Guide)을 꼼꼼히 체크하는 습관이 중요함을 다시 한번 느꼈습니다.
