package it.me.parking.repository;

import it.me.parking.model.entity.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for manipulations with reservations table in database
 */
@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
}
