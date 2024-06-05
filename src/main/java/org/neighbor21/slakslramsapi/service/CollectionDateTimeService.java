package org.neighbor21.slakslramsapi.service;

import org.neighbor21.slakslramsapi.jpRepository.TL_RIS_ROADWIDTHReposit;
import org.neighbor21.slakslramsapi.jpRepository.TL_RIS_ROUGH_DISTRIReposit;
import org.neighbor21.slakslramsapi.jpRepository.TL_RIS_SURFACEReposit;
import org.neighbor21.slakslramsapi.jpRepository.TL_TIS_AADTReposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Service
public class CollectionDateTimeService {

    // 시간값 공통 관리
    private final Map<String, RepositoryFinder> repositoryFinderMap = new HashMap<>();

    @Autowired
    private TL_RIS_ROADWIDTHReposit roadwidthReposit;
    @Autowired
    private TL_RIS_ROUGH_DISTRIReposit roughDistriReposit;
    @Autowired
    private TL_RIS_SURFACEReposit surfaceReposit;
    @Autowired
    private TL_TIS_AADTReposit aadtReposit;

    // 생성자에서 각 리포지토리에 대응하는 조회 메서드를 맵에 등록
    public CollectionDateTimeService() {
        repositoryFinderMap.put("roadwidth", this::roadwidthLatestCollectionDateTime);
        repositoryFinderMap.put("roughDistri", this::roughDistriLatestCollectionDateTime);
        repositoryFinderMap.put("surface", this::surfaceLatestCollectionDateTime);
        repositoryFinderMap.put("aadt", this::aadtLatestCollectionDateTime);
    }

    // 키에 해당하는 가장 최근 수집 일시를 가져오는 메서드
    public Timestamp getLatestCollectionDateTime(String key) {
        return repositoryFinderMap.getOrDefault(key, () -> new Timestamp(System.currentTimeMillis() - 86400000)).get();
    }

    // 각 리포지토리별로 가장 최근 수집 일시를 가져오는 메서드
    public Timestamp roadwidthLatestCollectionDateTime() {
        return roadwidthReposit.findTopByOrderByCollectionDateTimeDesc()
                .orElseGet(() -> new Timestamp(System.currentTimeMillis() - 86400000)); // 데이터 없으면 기본값으로 하루 전
    }

    public Timestamp roughDistriLatestCollectionDateTime() {
        return roughDistriReposit.findTopByOrderByCollectionDateTimeDesc()
                .orElseGet(() -> new Timestamp(System.currentTimeMillis() - 86400000)); // 데이터 없으면 기본값으로 하루 전
    }

    public Timestamp surfaceLatestCollectionDateTime() {
        return surfaceReposit.findTopByOrderByCollectionDateTimeDesc()
                .orElseGet(() -> new Timestamp(System.currentTimeMillis() - 86400000)); // 데이터 없으면 기본값으로 하루 전
    }

    public Timestamp aadtLatestCollectionDateTime() {
        return aadtReposit.findTopByOrderByCollectionDateTimeDesc()
                .orElseGet(() -> new Timestamp(System.currentTimeMillis() - 86400000)); // 데이터 없으면 기본값으로 하루 전
    }

    @FunctionalInterface
    private interface RepositoryFinder {
        Timestamp get();
    }
}
