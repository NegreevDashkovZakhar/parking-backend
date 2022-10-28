package it.me.parking.repository;

import it.me.parking.model.entity.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Repository for manipulations with reservations table in database
 */
@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    /**
     * Method querying for all parking lots ids reserved within specified time
     *
     * @param startTime start time of the reservations to search
     * @param endTime   end time of the reservations to search
     * @return all parking lots ids reserved within specified time
     */
    @Query(value = "SELECT lot_id FROM reservation WHERE start_time >= ?1 OR end_time <= ?2", nativeQuery = true)
    List<BigInteger> getReservedLotsId(Date startTime, Date endTime);
}
