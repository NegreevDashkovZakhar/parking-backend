package it.me.parking.repository;

import it.me.parking.model.entity.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Repository for manipulations with reservations table in database
 */
@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    /**
     * Method getting available for reservation during specified time parking lots ids
     *
     * @param startTime starting time of possible reservation
     * @param endTime   ending time of possible reservation
     * @return available for reservation during specified time parking lots ids
     */
    @Query(value = "SELECT PL.id FROM parking_lot PL " +
            "EXCEPT " +
            "SELECT PL.id FROM parking_lot PL " +
            "INNER JOIN reservation R ON PL.id = R.lot_id " +
            "WHERE R.start_time >= ?1 OR R.end_time <= ?2", nativeQuery = true)
    List<Long> getFreeParkingLots(Date startTime, Date endTime);
}
