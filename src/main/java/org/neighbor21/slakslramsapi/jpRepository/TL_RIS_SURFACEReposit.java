package org.neighbor21.slakslramsapi.jpRepository;

import org.neighbor21.slakslramsapi.entity.TL_RIS_SURFACEEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * packageName    : org.neighbor21.slakslramsapi.jpRepository
 * fileName       : TL_RIS_SURFACEReposit.java
 * author         : kjg08
 * date           : 24. 5. 3.
 * description    : TL_RIS_SURFACE 리파지토리
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 3.        kjg08           최초 생성
 */
public interface TL_RIS_SURFACEReposit extends JpaRepository<TL_RIS_SURFACEEntity, String> {
    Optional<TL_RIS_SURFACEEntity> findTopByOrderByCollectionDateTimeDesc(); // 가장 최근 수집일시 조회
}
