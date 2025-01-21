package org.neighbor21.slakslramsapi.service;

import java.util.List;

/**
 * packageName    : org.neighbor21.slakslramsapi.service
 * fileName       : BatchInserter.java
 * author         : kjg08
 * date           : 24. 6. 13.
 * description    : Interface for batch inserting entities
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 6. 13.        kjg08           Initial creation
 */
@FunctionalInterface
public interface BatchInserter<E> {
    void batchInsert(List<E> entities);
}