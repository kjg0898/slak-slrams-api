package org.neighbor21.slakslramsapi.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * packageName    : org.neighbor21.slakslramsapi.util
 * fileName       : TimestampUtil.java
 * author         : kjg08
 * date           : 24. 6. 10.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 6. 10.        kjg08           최초 생성
 */

public class TimestampUtil {

    private static final String TIMESTAMP_FILE = "timestamps.txt";

    public static Map<String, Timestamp> readTimestampsFromFile() {
        Map<String, Timestamp> timestamps = new HashMap<>();
        try {
            if (!Files.exists(Paths.get(TIMESTAMP_FILE))) {
                Files.createFile(Paths.get(TIMESTAMP_FILE));
                // 파일이 없으면 현재 시간에서 하루 전 시간을 기본값으로 사용
                Timestamp defaultTimestamp = new Timestamp(System.currentTimeMillis() - 86400000);
                timestamps.put("roadwidth", defaultTimestamp);
                timestamps.put("roughDistri", defaultTimestamp);
                timestamps.put("surface", defaultTimestamp);
                timestamps.put("aadt", defaultTimestamp);
                writeTimestampsToFile(timestamps);
            } else {
                Files.lines(Paths.get(TIMESTAMP_FILE)).forEach(line -> {
                    String[] parts = line.split("=");
                    timestamps.put(parts[0], Timestamp.valueOf(parts[1]));
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return timestamps;
    }

    public static void writeTimestampsToFile(Map<String, Timestamp> timestamps) {
        try {
            String content = timestamps.entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue().toString())
                    .collect(Collectors.joining("\n"));
            Files.write(Paths.get(TIMESTAMP_FILE), content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}