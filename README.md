# SLAK SLRAMS API 프로젝트 문서

## 소개
SLAK SLRAMS API 프로젝트는 도로 인프라 데이터를 처리하기 위해 Spring Boot를 기반으로 한 애플리케이션입니다. 이 애플리케이션은 정기적으로 외부 API를 호출하여 데이터를 가져오고, 이를 처리하여 PostgreSQL 데이터베이스에 저장합니다. 이 문서는 프로젝트의 아키텍처, 주요 구성 요소 및 사용된 기술에 대한 상세한 개요를 제공합니다. 기술적 및 비기술적 독자 모두 이해할 수 있도록 설계되었으며, 프로젝트의 작업 흐름과 설계 선택의 이유를 명확히 설명합니다.

## 프로젝트 구조
프로젝트는 각기 다른 목적을 가진 여러 패키지로 구성되어 있습니다:

- `config`: 설정 클래스
- `dto`: 데이터 전송 객체 (DTO)
- `entity`: JPA 엔티티
- `handler`: 정기 작업을 처리하는 핸들러
- `jpRepository`: 데이터베이스 작업을 위한 리포지토리
- `service`: 비즈니스 로직을 포함한 서비스 레이어
- `util`: 유틸리티 클래스

## 주요 기술
- **Spring Boot**: 자바 애플리케이션을 구축하기 위한 프레임워크
- **Hibernate**: 데이터베이스 상호작용을 관리하는 ORM 도구
- **PostgreSQL**: 관계형 데이터베이스 관리 시스템
- **Unirest**: API 호출을 위한 경량 HTTP 클라이언트
- **Jackson**: JSON 처리 라이브러리
- **Lombok**: 보일러플레이트 코드를 줄이기 위한 라이브러리
- **QueryDSL**: JPA를 위한 타입 안전 쿼리
- **Resilience4j**: 결함 허용 라이브러리

## 구성

### 상수
애플리케이션 전반에서 사용되는 상수 값을 정의합니다.

### Unirest 설정
Unirest HTTP 클라이언트를 타임아웃 설정과 함께 구성합니다.

### 데이터 전송 객체 (DTO)
DTO는 계층 간 데이터 전송을 위해 사용됩니다. 이들은 JSON 직렬화 및 역직렬화를 위한 어노테이션이 포함된 단순 POJO입니다.

#### 도로 너비 DTO
도로 너비 데이터를 나타냅니다.

다른 DTO (RISRoughnessDTO, RISSurfaceTypeDTO, TISTrafficDispersionDTO)도 유사한 패턴을 따릅니다.

### 엔티티
엔티티는 데이터베이스 테이블을 나타내며 JPA 어노테이션을 사용해 매핑됩니다.

#### 도로 너비 엔티티
TL_RIS_ROADWIDTH 테이블을 나타냅니다.

다른 엔티티 (TL_RIS_ROUGH_DISTRIEntity, TL_RIS_SURFACEEntity, TL_TIS_AADTEntity)도 유사합니다.

### 정기 작업 핸들러
Spring의 `@Scheduled` 어노테이션을 사용하여 주기적인 API 호출과 데이터 처리를 담당합니다.

#### ScheduledTasksHandler
정기적으로 API를 호출하고 데이터를 처리하는 메서드를 포함합니다.

### 리포지토리
리포지토리는 Spring Data JPA와 JdbcTemplate을 사용하여 데이터베이스 작업을 처리합니다.

#### 도로 너비 리포지토리
최대 순번 및 최근 수집 일시를 조회하는 메서드를 포함합니다.

다른 리포지토리 (TL_RIS_ROUGH_DISTRIReposit, TL_RIS_SURFACEReposit, TL_TIS_AADTReposit)도 유사한 패턴을 따릅니다.

### 서비스 레이어
#### 배치 서비스
엔티티를 데이터베이스에 배치 삽입합니다.

#### SaveAfterDtoToEntity
DTO를 엔티티로 변환하여 데이터베이스에 저장합니다.

### 유틸리티 클래스
#### Common Utility
프로젝트 전반에서 재사용되는 공통 처리 로직을 제공합니다.

#### Timestamp Util
타임스탬프를 파일에서 읽고 쓰는 기능을 담당합니다.

### API 서비스
SlamsApiService 클래스는 API 호출을 담당하며 데이터를 DTO로 반환합니다. 현재 이 클래스는 임의의 테스트 데이터를 생성하지만 실제 API 호출로 수정할 수 있습니다.

#### SlamsApiService
API 호출을 처리하고 임의의 테스트 데이터를 생성하는 메서드를 포함합니다.

### 메인 애플리케이션
Spring Boot 애플리케이션을 초기화하고 스케줄링을 활성화합니다.

## 결론
이 문서는 SLAK SLRAMS API 프로젝트의 전반적인 개요를 제공하며, 아키텍처, 주요 구성 요소 및 사용된 기술을 설명합니다. 이 문서를 통해 기술적 및 비기술적 독자는 프로젝트의 작업 흐름과 설계 선택의 이유를 이해할 수 있으며, 필요에 따라 프로젝트를 확장하거나 수정할 수 있습니다.
