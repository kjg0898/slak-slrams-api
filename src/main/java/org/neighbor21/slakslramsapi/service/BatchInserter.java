package org.neighbor21.slakslramsapi.service;

import java.util.List;

/**
 * packageName    : org.neighbor21.slakslramsapi.service
 * fileName       : BatchInserter.java
 * author         : kjg08
 * date           : 24. 6. 13.
 * description    : 엔티티를 배치 삽입하는 인터페이스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 6. 13.        kjg08           최초 생성
 */
@FunctionalInterface
public interface BatchInserter<E> {
    void batchInsert(List<E> entities);
}