package roomescape.repository;

import java.time.LocalTime;
import java.util.List;
import roomescape.domain.ReservationTime;

public interface ReservationTimeRepository {

    Long save(ReservationTime reservationTime);

    List<ReservationTime> findAll();

    ReservationTime findById(Long id);

    void delete(Long id);

    Boolean existId(Long id);

    Boolean existTime(LocalTime time);
}
