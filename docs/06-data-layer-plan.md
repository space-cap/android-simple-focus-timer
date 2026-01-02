# 데이터 계층 구현 계획

## 목표 설명
**Room Database**를 사용하여 데이터 계층을 구현합니다. 이 계층은 로컬 데이터 저장을 담당하며 도메인 계층의 리포지토리 인터페이스를 실제 데이터 소스와 연결합니다.

## 변경 제안

### Room Entity
#### [NEW] [FocusSessionEntity.kt](file:///c:/workdir/space-cap/AndroidStudioProjects/SimpleFocusTimer/app/src/main/java/com/ezlevup/simplefocustimer/data/local/entity/FocusSessionEntity.kt)
- `@Entity(tableName = "focus_sessions")`
- 필드:
    - `@PrimaryKey(autoGenerate = true) val id: Long = 0`
    - `val startTime: Long`
    - `val durationSeconds: Long`

### Room DAO
#### [NEW] [FocusSessionDao.kt](file:///c:/workdir/space-cap/AndroidStudioProjects/SimpleFocusTimer/app/src/main/java/com/ezlevup/simplefocustimer/data/local/dao/FocusSessionDao.kt)
- `@Dao` 인터페이스.
- 함수:
    - `@Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insertSession(session: FocusSessionEntity)`
    - `@Query("SELECT * FROM focus_sessions ORDER BY startTime DESC") fun getAllSessions(): Flow<List<FocusSessionEntity>>`

### Room Database
#### [NEW] [SimpleFocusDatabase.kt](file:///c:/workdir/space-cap/AndroidStudioProjects/SimpleFocusTimer/app/src/main/java/com/ezlevup/simplefocustimer/data/local/SimpleFocusDatabase.kt)
- `@Database(entities = [FocusSessionEntity::class], version = 1)`
- `RoomDatabase`를 상속받는 추상 클래스.
- 추상 메서드 `abstract fun focusSessionDao(): FocusSessionDao`

### 매퍼 (Mapper)
#### [NEW] [FocusMapper.kt](file:///c:/workdir/space-cap/AndroidStudioProjects/SimpleFocusTimer/app/src/main/java/com/ezlevup/simplefocustimer/data/mapper/FocusMapper.kt)
- `FocusSession` (Domain)과 `FocusSessionEntity` (Data) 간의 변환을 위한 확장 함수.
- `FocusSessionEntity.toDomainModel(): FocusSession`
- `FocusSession.toEntity(): FocusSessionEntity`

### 리포지토리 구현 (Repository Implementation)
#### [NEW] [FocusRepositoryImpl.kt](file:///c:/workdir/space-cap/AndroidStudioProjects/SimpleFocusTimer/app/src/main/java/com/ezlevup/simplefocustimer/data/repository/FocusRepositoryImpl.kt)
- `FocusRepository` 인터페이스 구현.
- `FocusSessionDao` 주입.
- `FocusMapper`를 사용하여 데이터 변환 수행.

## 검증 계획

### 자동화된 테스트
- `./gradlew assembleDebug`를 실행하여 Room의 KSP 처리 및 의존성 주입 코드가 정상적으로 컴파일되는지 확인합니다.
- (선택 사항) 계측 테스트(androidTest)를 통해 실제 DB 입출력 검증 가능.

### 수동 검증
- 해당 없음 (비즈니스 로직 및 데이터 처리).
