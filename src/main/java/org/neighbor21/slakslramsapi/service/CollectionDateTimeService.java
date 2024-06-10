package org.neighbor21.slakslramsapi.service;

import jakarta.annotation.PostConstruct;
import org.neighbor21.slakslramsapi.jpRepository.TL_RIS_ROADWIDTHReposit;
import org.neighbor21.slakslramsapi.jpRepository.TL_RIS_ROUGH_DISTRIReposit;
import org.neighbor21.slakslramsapi.jpRepository.TL_RIS_SURFACEReposit;
import org.neighbor21.slakslramsapi.jpRepository.TL_TIS_AADTReposit;
import org.neighbor21.slakslramsapi.util.TimestampUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : org.neighbor21.slakslramsapi.service
 * fileName       : CollectionDateTimeService.java
 * author         : kjg08
 * date           : 24. 5. 2.
 * description    : 각 데이터의 최신 수집 일시를 관리하는 서비스 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 2.        kjg08           최초 생성
 */
@Service
public class CollectionDateTimeService {

    // 시간값 공통 관리
    private final Map<String, RepositoryFinder> repositoryFinderMap = new HashMap<>();
    private final Map<String, Timestamp> timestampMap = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(CollectionDateTimeService.class);

    @Autowired
    private TL_RIS_ROADWIDTHReposit roadwidthReposit;
    @Autowired
    private TL_RIS_ROUGH_DISTRIReposit roughDistriReposit;
    @Autowired
    private TL_RIS_SURFACEReposit surfaceReposit;
    @Autowired
    private TL_TIS_AADTReposit aadtReposit;

    /**
     * 생성자에서 각 리포지토리에 대응하는 조회 메서드를 맵에 등록
     * 서버 시작 시 DB에서 최신 타임스탬프를 로드하고 파일에 저장
     */
    @PostConstruct
    public void init() {
        try {
            repositoryFinderMap.put("roadwidth", this::roadwidthLatestCollectionDateTime);
            repositoryFinderMap.put("roughDistri", this::roughDistriLatestCollectionDateTime);
            repositoryFinderMap.put("surface", this::surfaceLatestCollectionDateTime);
            repositoryFinderMap.put("aadt", this::aadtLatestCollectionDateTime);

            // 시작 시 데이터베이스에서 시간값 로드
            timestampMap.put("roadwidth", getLatestCollectionDateTimeFromDb("roadwidth"));
            timestampMap.put("roughDistri", getLatestCollectionDateTimeFromDb("roughDistri"));
            timestampMap.put("surface", getLatestCollectionDateTimeFromDb("surface"));
            timestampMap.put("aadt", getLatestCollectionDateTimeFromDb("aadt"));

            // 파일에 디비에서 로드한 시간값 업데이트
            TimestampUtil.writeTimestampsToFile(timestampMap);
        } catch (Exception e) {
            logger.error("Error during initialization: {}", e.getMessage(), e);
        }
    }

    /**
     * 파일에서 최신 수집 일시를 가져오는 메서드
     *
     * @param key 리포지토리 키
     * @return 최신 수집 일시
     */
    public Timestamp getLatestCollectionDateTime(String key) {
        return timestampMap.getOrDefault(key, new Timestamp(System.currentTimeMillis() - 86400000));
    }

    /**
     * 키에 해당하는 가장 최근 수집 일시를 DB에서 가져오는 메서드
     *
     * @param key 리포지토리 키
     * @return 최신 수집 일시
     */
    private Timestamp getLatestCollectionDateTimeFromDb(String key) {
        try {
            return repositoryFinderMap.getOrDefault(key, () -> new Timestamp(System.currentTimeMillis() - 86400000)).get();
        } catch (Exception e) {
            logger.error("Error fetching latest collection datetime from DB for {}: {}", key, e.getMessage(), e);
            return new Timestamp(System.currentTimeMillis() - 86400000);
        }
    }

    /**
     * 인서트 성공 시 최신 타임스탬프를 파일에 업데이트
     *
     * @param key          리포지토리 키
     * @param newTimestamp 새로운 타임스탬프
     */
    public void updateTimestampIfNeeded(String key, Timestamp newTimestamp) {
        try {
            timestampMap.put(key, newTimestamp);
            TimestampUtil.writeTimestampsToFile(timestampMap);
        } catch (Exception e) {
            logger.error("Error updating timestamp for {}: {}", key, e.getMessage(), e);
        }
    }

    /**
     * 도로너비 리포지토리에서 가장 최근 수집 일시를 가져오는 메서드
     *
     * @return 최신 수집 일시
     */
    public Timestamp roadwidthLatestCollectionDateTime() {
        return roadwidthReposit.findTopByOrderByCollectionDateTimeDesc()
                .orElse(new Timestamp(System.currentTimeMillis() - 86400000));
    }

    /**
     * 거칠기분포 리포지토리에서 가장 최근 수집 일시를 가져오는 메서드
     *
     * @return 최신 수집 일시
     */
    public Timestamp roughDistriLatestCollectionDateTime() {
        return roughDistriReposit.findTopByOrderByCollectionDateTimeDesc()
                .orElse(new Timestamp(System.currentTimeMillis() - 86400000));
    }

    /**
     * 표면유형 리포지토리에서 가장 최근 수집 일시를 가져오는 메서드
     *
     * @return 최신 수집 일시
     */
    public Timestamp surfaceLatestCollectionDateTime() {
        return surfaceReposit.findTopByOrderByCollectionDateTimeDesc()
                .orElse(new Timestamp(System.currentTimeMillis() - 86400000));
    }

    /**
     * 교통분산 리포지토리에서 가장 최근 수집 일시를 가져오는 메서드
     *
     * @return 최신 수집 일시
     */
    public Timestamp aadtLatestCollectionDateTime() {
        return aadtReposit.findTopByOrderByCollectionDateTimeDesc()
                .orElse(new Timestamp(System.currentTimeMillis() - 86400000));
    }

    @FunctionalInterface
    private interface RepositoryFinder {
        Timestamp get();
    }
}
