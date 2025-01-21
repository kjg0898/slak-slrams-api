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
 * description    : Timestamp utility class
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 6. 10.        kjg08           Initial creation
 */

public class TimestampUtil {

    private static final String TIMESTAMP_FILE = "timestamps.txt";

    /**
     * Method to read timestamps from a file
     *
     * @return timestamp map
     */
    public static Map<String, Timestamp> readTimestampsFromFile() {
        Map<String, Timestamp> timestamps = new HashMap<>();
        try {
            // If the file does not exist, create it and record the default timestamps
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
            // If an exception occurs, log it and use default timestamps
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
     * Method to save the timestamp map to a file
     *
     * @param timestamps timestamp map
     */
    public static void writeTimestampsToFile(Map<String, Timestamp> timestamps) {
        try {
            String content = timestamps.entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue().toString())
                    .collect(Collectors.joining("\n"));
            Files.write(Paths.get(TIMESTAMP_FILE), content.getBytes());
        } catch (IOException e) {
            // If an exception occurs, print the log
            e.printStackTrace();
        }
    }
}
