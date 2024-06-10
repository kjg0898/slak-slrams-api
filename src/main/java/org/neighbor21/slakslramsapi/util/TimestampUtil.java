package org.neighbor21.slakslramsapi.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * packageName    : org.neighbor21.slakslramsapi.util
 * fileName       : TimestampUtil.java
 * author         : kjg08
 * date           : 24. 6. 10.
 * description    : 타임스탬프 유틸리티 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 6. 10.        kjg08           최초 생성
 */

public class TimestampUtil {

    private static final String TIMESTAMP_FILE = "timestamps.txt";

    /**
     * 파일에서 타임스탬프를 읽어오는 메서드
     *
     * @return 타임스탬프 맵
     */
    public static Map<String, Timestamp> readTimestampsFromFile() {
        Map<String, Timestamp> timestamps = new HashMap<>();
        try {
            // 파일이 존재하지 않으면 새로 생성하고 기본 타임스탬프를 기록
            if (!Files.exists(Paths.get(TIMESTAMP_FILE))) {
                Files.createFile(Paths.get(TIMESTAMP_FILE));
                Timestamp defaultTimestamp = new Timestamp(System.currentTimeMillis() - 86400000);
                timestamps.put("roadwidth", defaultTimestamp);
                timestamps.put("roughDistri", defaultTimestamp);
                timestamps.put("surface", defaultTimestamp);
                timestamps.put("aadt", defaultTimestamp);
                writeTimestampsToFile(timestamps);
            } else {
                // 파일이 존재하면 내용을 읽어 타임스탬프 맵에 저장
                Files.lines(Paths.get(TIMESTAMP_FILE)).forEach(line -> {
                    String[] parts = line.split("=");
                    timestamps.put(parts[0], Timestamp.valueOf(parts[1]));
                });
            }
        } catch (IOException e) {
            // 예외 발생 시 로그 출력 및 기본 타임스탬프 사용
            e.printStackTrace();
            Timestamp defaultTimestamp = new Timestamp(System.currentTimeMillis() - 86400000);
            timestamps.put("roadwidth", defaultTimestamp);
            timestamps.put("roughDistri", defaultTimestamp);
            timestamps.put("surface", defaultTimestamp);
            timestamps.put("aadt", defaultTimestamp);
        }
        return timestamps;
    }

    /**
     * 타임스탬프 맵을 파일에 저장하는 메서드
     *
     * @param timestamps 타임스탬프 맵
     */
    public static void writeTimestampsToFile(Map<String, Timestamp> timestamps) {
        try {
            String content = timestamps.entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue().toString())
                    .collect(Collectors.joining("\n"));
            Files.write(Paths.get(TIMESTAMP_FILE), content.getBytes());
        } catch (IOException e) {
            // 예외 발생 시 로그 출력
            e.printStackTrace();
        }
    }
}
