package it.me.parking.repository;

import it.me.parking.model.entity.ParkingLot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for manipulations with parking lots table in database
 */
@Repository
public interface ParkingLotRepository extends CrudRepository<ParkingLot, Long> {
    /**
     * Method getting parking lot with specified location sign
     *
     * @param location location sign of the parking lot
     * @return parking lot with specified location sign
     */
    @Query(value = "SELECT * FROM parking_lot WHERE location_sign = ?1", nativeQuery = true)
    ParkingLot getParkingLotByLocation(String location);
}
