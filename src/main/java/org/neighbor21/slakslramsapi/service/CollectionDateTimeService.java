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
 * description    : Service class to manage the latest collection datetime for each dataset.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 2.        kjg08           Initial creation
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
     * Registers query methods for each repository in a map
     * and loads the latest timestamp from the database at server startup,
     * then saves it to a file.
     */
    @PostConstruct
    public void init() {
        try {
            repositoryFinderMap.put("roadwidth", this::roadwidthLatestCollectionDateTime);
            repositoryFinderMap.put("roughDistri", this::roughDistriLatestCollectionDateTime);
            repositoryFinderMap.put("surface", this::surfaceLatestCollectionDateTime);
            repositoryFinderMap.put("aadt", this::aadtLatestCollectionDateTime);

            // Load timestamps from the database at startup
            timestampMap.put("roadwidth", getLatestCollectionDateTimeFromDb("roadwidth"));
            timestampMap.put("roughDistri", getLatestCollectionDateTimeFromDb("roughDistri"));
            timestampMap.put("surface", getLatestCollectionDateTimeFromDb("surface"));
            timestampMap.put("aadt", getLatestCollectionDateTimeFromDb("aadt"));

            // Update timestamps in the file with values loaded from the database
            TimestampUtil.writeTimestampsToFile(timestampMap);
        } catch (Exception e) {
            logger.error("Initialization failed: {}", e.getMessage(), e);
        }
    }

    /**
     * Retrieves the latest collection datetime from the file.
     *
     * @param key Repository key
     * @return Latest collection datetime
     */
    public Timestamp getLatestCollectionDateTime(String key) {
        return timestampMap.getOrDefault(key, new Timestamp(System.currentTimeMillis() - 86400000));
    }

    /**
     * Retrieves the most recent collection datetime from the database for the given key.
     *
     * @param key Repository key
     * @return Latest collection datetime
     */
    private Timestamp getLatestCollectionDateTimeFromDb(String key) {
        try {
            return repositoryFinderMap.getOrDefault(key, () -> new Timestamp(System.currentTimeMillis() - 86400000)).get();
        } catch (Exception e) {
            logger.error("Failed to fetch the latest collection datetime from the database for {}: {}", key, e.getMessage(), e);
            return new Timestamp(System.currentTimeMillis() - 86400000);
        }
    }

    /**
     * Updates the file with the latest timestamp upon a successful insert.
     *
     * @param key          Repository key
     * @param newTimestamp New timestamp
     */
    public void updateTimestampIfNeeded(String key, Timestamp newTimestamp) {
        try {
            timestampMap.put(key, newTimestamp);
            TimestampUtil.writeTimestampsToFile(timestampMap);
        } catch (Exception e) {
            logger.error("Failed to update timestamp for {}: {}", key, e.getMessage(), e);
        }
    }

    /**
     * Retrieves the most recent collection datetime from the road width repository.
     *
     * @return Latest collection datetime
     */
    public Timestamp roadwidthLatestCollectionDateTime() {
        return roadwidthReposit.findTopByOrderByCollectionDateTimeDesc()
                .orElse(new Timestamp(System.currentTimeMillis() - 86400000));
    }

    /**
     * Retrieves the most recent collection datetime from the roughness distribution repository.
     *
     * @return Latest collection datetime
     */
    public Timestamp roughDistriLatestCollectionDateTime() {
        return roughDistriReposit.findTopByOrderByCollectionDateTimeDesc()
                .orElse(new Timestamp(System.currentTimeMillis() - 86400000));
    }

    /**
     * Retrieves the most recent collection datetime from the surface type repository.
     *
     * @return Latest collection datetime
     */
    public Timestamp surfaceLatestCollectionDateTime() {
        return surfaceReposit.findTopByOrderByCollectionDateTimeDesc()
                .orElse(new Timestamp(System.currentTimeMillis() - 86400000));
    }

    /**
     * Retrieves the most recent collection datetime from the traffic dispersion repository.
     *
     * @return Latest collection datetime
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
