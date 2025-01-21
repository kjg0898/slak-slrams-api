package org.neighbor21.slakslramsapi.service;

import java.util.Map;

/**
 * packageName    : org.neighbor21.slakslramsapi.service
 * fileName       : EntityConverter.java
 * author         : kjg08
 * date           : 24. 6. 13.
 * description    : Interface for converting DTO to entity
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 6. 13.        kjg08           Initial creation
 */
@FunctionalInterface
public interface EntityConverter<T, E> {
    E convert(T dto, Map<String, Integer> maxSqnoMap);
}