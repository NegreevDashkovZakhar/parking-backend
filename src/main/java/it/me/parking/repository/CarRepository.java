package it.me.parking.repository;

import it.me.parking.model.entity.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for manipulations with cars table in database
 */
@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    /**
     * Method getting car with specified license plate number
     *
     * @param licensePlateNumber license plate number of the car
     * @return car with specified license plate number
     */
    @Query(value = "SELECT * FROM Car WHERE license_plate_number = ?1", nativeQuery = true)
    Car getCarByLicensePlateNumber(String licensePlateNumber);
}
