package org.neighbor21.slakslramsapi.service;

import java.util.List;

/**
 * packageName    : org.neighbor21.slakslramsapi.service
 * fileName       : MaxSqnoProvider.java
 * author         : kjg08
 * date           : 24. 6. 13.
 * description    : 최대 순번을 제공하는 인터페이스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 6. 13.        kjg08           최초 생성
 */
@FunctionalInterface
public interface MaxSqnoProvider {
    List<Object[]> getMaxSqnoBySurveyYear();
}