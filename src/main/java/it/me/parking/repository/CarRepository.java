package it.me.parking.repository;

import it.me.parking.model.entity.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for manipulations with cars table in database
 */
@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
}
