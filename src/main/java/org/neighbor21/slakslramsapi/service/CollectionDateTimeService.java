package org.neighbor21.slakslramsapi.service;

import org.neighbor21.slakslramsapi.entity.TL_RIS_ROADWIDTHEntity;
import org.neighbor21.slakslramsapi.entity.TL_RIS_ROUGH_DISTRIEntity;
import org.neighbor21.slakslramsapi.entity.TL_RIS_SURFACEEntity;
import org.neighbor21.slakslramsapi.entity.TL_TIS_AADTEntity;
import org.neighbor21.slakslramsapi.jpRepository.TL_RIS_ROADWIDTHReposit;
import org.neighbor21.slakslramsapi.jpRepository.TL_RIS_ROUGH_DISTRIReposit;
import org.neighbor21.slakslramsapi.jpRepository.TL_RIS_SURFACEReposit;
import org.neighbor21.slakslramsapi.jpRepository.TL_TIS_AADTReposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : org.neighbor21.slakslramsapi.service
 * fileName       : DataProcessingService.java
 * author         : kjg08
 * date           : 24. 5. 8.
 * description    : 수집일시데이터 처리 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 8.        kjg08           최초 생성
 */
@Service
public class CollectionDateTimeService {

    //시간값 공통 관리
    private final Map<String, RepositoryFinder> repositoryFinderMap = new HashMap<>();
    @Autowired
    private TL_RIS_ROADWIDTHReposit tlRisRoadwidthReposit;
    @Autowired
    private TL_RIS_ROUGH_DISTRIReposit tlRisRoughDistriReposit;
    @Autowired
    private TL_RIS_SURFACEReposit tlRisSurfaceReposit;
    @Autowired
    private TL_TIS_AADTReposit tlTisAadtReposit;

    //해당하는 키 값에 각각 조회한 시간값을 넣는다
    public CollectionDateTimeService() {
        repositoryFinderMap.put("roadwidth", this::roadwidthLatestCollectionDateTime);
        repositoryFinderMap.put("roughDistri", this::roughDistriLatestCollectionDateTime);
        repositoryFinderMap.put("surface", this::surfaceLatestCollectionDateTime);
        repositoryFinderMap.put("aadt", this::aadtLatestCollectionDateTime);
    }

    //timestamp 키값에 해당하는 시간값을 찾고 없으면 기본값(현재 - 1day )
    public Timestamp getLatestCollectionDateTime(String key) {
        return repositoryFinderMap.getOrDefault(key, () -> new Timestamp(System.currentTimeMillis() - 86400000)).get();
    }

    /**
     * Fetches the most recent collection date time from the repository. (가장 최근 수집일시 가져오기)
     *
     * @return The most recent collection date time.
     */
    public Timestamp roadwidthLatestCollectionDateTime() {
        return tlRisRoadwidthReposit.findTopByOrderByCollectionDateTimeDesc()
                .map(TL_RIS_ROADWIDTHEntity::getCollectionDateTime)
                .orElseGet(() -> new Timestamp(System.currentTimeMillis() - 86400000)); // Default to 1 day ago if none found
    }

    public Timestamp roughDistriLatestCollectionDateTime() {
        return tlRisRoughDistriReposit.findTopByOrderByCollectionDateTimeDesc()
                .map(TL_RIS_ROUGH_DISTRIEntity::getCollectionDateTime)
                .orElseGet(() -> new Timestamp(System.currentTimeMillis() - 86400000)); // Default to 1 day ago if none found
    }

    public Timestamp surfaceLatestCollectionDateTime() {
        return tlRisSurfaceReposit.findTopByOrderByCollectionDateTimeDesc()
                .map(TL_RIS_SURFACEEntity::getCollectionDateTime)
                .orElseGet(() -> new Timestamp(System.currentTimeMillis() - 86400000)); // Default to 1 day ago if none found
    }

    public Timestamp aadtLatestCollectionDateTime() {
        return tlTisAadtReposit.findTopByOrderByCollectionDateTimeDesc()
                .map(TL_TIS_AADTEntity::getCollectionDateTime)
                .orElseGet(() -> new Timestamp(System.currentTimeMillis() - 86400000)); // Default to 1 day ago if none found
    }

    @FunctionalInterface
    private interface RepositoryFinder {
        Timestamp get();
    }

}
