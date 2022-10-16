package it.me.parking.repository;

import it.me.parking.model.entity.ParkingLot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for manipulations with parking lots table in database
 */
@Repository
public interface ParkingLotRepository extends CrudRepository<ParkingLot, Long> {
}
