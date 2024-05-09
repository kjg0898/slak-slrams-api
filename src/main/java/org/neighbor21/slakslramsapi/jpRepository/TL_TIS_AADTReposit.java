package org.neighbor21.slakslramsapi.jpRepository;

import org.neighbor21.slakslramsapi.entity.TL_TIS_AADTEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * packageName    : org.neighbor21.slakslramsapi.jpRepository
 * fileName       : TL_TIS_AADTReposit.java
 * author         : kjg08
 * date           : 24. 5. 3.
 * description    : TL_TIS_AADT 리파지토리
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 3.        kjg08           최초 생성
 */
public interface TL_TIS_AADTReposit extends JpaRepository<TL_TIS_AADTEntity, String> {
    Optional<TL_TIS_AADTEntity> findTopByOrderByCollectionDateTimeDesc(); // 가장 최근 수집일시 조회
}
