package org.neighbor21.slakslramsapi.service;

import java.util.Map;

/**
 * packageName    : org.neighbor21.slakslramsapi.service
 * fileName       : EntityConverter.java
 * author         : kjg08
 * date           : 24. 6. 13.
 * description    : DTO를 엔티티로 변환하는 인터페이스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 6. 13.        kjg08           최초 생성
 */
@FunctionalInterface
public interface EntityConverter<T, E> {
    E convert(T dto, Map<String, Integer> maxSqnoMap);
}