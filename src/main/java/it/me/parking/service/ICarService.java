package it.me.parking.service;

import it.me.parking.model.entity.Car;
import it.me.parking.model.request.CarRequest;

/**
 * Interface for service manipulating car entities in database
 */
public interface ICarService {
    /**
     * Method getting all cars from database
     *
     * @return list of all cars
     */
    Iterable<Car> getAllCars();

    /**
     * Method getting car with specified id
     *
     * @param id id of the car
     * @return car with specified id
     */
    Car getCarById(Long id);

    /**
     * Method updating car properties with specified
     *
     * @param id         id of the changed car
     * @param newCarData object with new car properties
     */
    void updateCar(Long id, CarRequest newCarData);

    /**
     * Method deleting car with specified id
     *
     * @param id id of the car
     */
    void deleteCarById(Long id);

    /**
     * Method getting car with specified license plate number
     *
     * @param licensePlateNumber license plate number of the car
     * @return car with specified license plate number
     */
    Car getCarByLicensePlateNumber(String licensePlateNumber);

    /**
     * Method adding car to database
     *
     * @param car object with properties of the new car
     */
    void addCar(CarRequest car);
}
