package org.neighbor21.slakslramsapi.service;

import java.util.List;

/**
 * packageName    : org.neighbor21.slakslramsapi.service
 * fileName       : MaxSqnoProvider.java
 * author         : kjg08
 * date           : 24. 6. 13.
 * description    : Interface for providing the maximum sequence number
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 6. 13.        kjg08           Initial creation
 */
@FunctionalInterface
public interface MaxSqnoProvider {
    List<Object[]> getMaxSqnoBySurveyYear();
}