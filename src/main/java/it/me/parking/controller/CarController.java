package it.me.parking.controller;

import it.me.parking.exception.AlreadyExistsHttpException;
import it.me.parking.exception.InvalidRequestHttpException;
import it.me.parking.exception.NotFoundHttpException;
import it.me.parking.model.entity.Car;
import it.me.parking.model.request.CarRequest;
import it.me.parking.service.ICarService;
import org.hibernate.PropertyValueException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

/**
 * Controller passing arguments from requests to car service
 */
@RestController
public class CarController {
    private final ICarService carService;

    /**
     * Default constructor setting all fields
     *
     * @param carService car service object used for manipulations
     */
    public CarController(ICarService carService) {
        this.carService = carService;
    }

    /**
     * Method getting all car entities in database
     *
     * @return list of all car entities
     */
    @GetMapping(path = "/cars")
    public Iterable<Car> getAllCars() {
        return carService.getAllCars();
    }

    /**
     * Method getting specific car by given id
     *
     * @param id id of the car
     * @return car entity with specified id
     */
    @GetMapping(path = "/cars/{id}")
    public Car getCarById(@PathVariable("id") Long id) {
        try {
            return carService.getCarById(id);
        } catch (NoSuchElementException e) {
            throw new NotFoundHttpException(e);
        }
    }

    /**
     * Method updating specific car entity properties
     *
     * @param id         id of the modified car
     * @param newCarData object containing new properties for the car
     */
    @PutMapping(path = "/cars/{id}")
    public void updateCar(@PathVariable("id") Long id,
                          @RequestBody CarRequest newCarData) {
        try {
            carService.updateCar(id, newCarData);
        } catch (NoSuchElementException e) {
            throw new NotFoundHttpException(e);
        } catch (PropertyValueException e) {
            throw new InvalidRequestHttpException(e);
        }
    }

    /**
     * Method deleting car with specified id
     *
     * @param id id of the car
     */
    public void deleteCarById(@PathVariable Long id) {
        try {
            carService.deleteCarById(id);
        } catch (NoSuchElementException e) {
            throw new NotFoundHttpException(e);
        }
    }

    /**
     * Method getting car with specified license plate number
     *
     * @param licensePlateNumber license plate number of the car
     * @return car entity with specified license plate number
     */
    public Car getCarByLicensePlateNumber(String licensePlateNumber) {
        return carService.getCarByLicensePlateNumber(licensePlateNumber);
    }

    /**
     * Method adding new car to database
     *
     * @param car object containing properties of the new car
     */
    @PostMapping(path = "car")
    public void addCar(@RequestBody CarRequest car) {
        try {
            carService.addCar(car);
        } catch (PropertyValueException e) {
            throw new InvalidRequestHttpException(e);
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsHttpException(e);
        }
    }
}
