package it.me.parking.service;

import it.me.parking.exception.InvalidRequestHttpException;
import it.me.parking.model.entity.Car;
import it.me.parking.model.request.CarRequest;
import it.me.parking.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.regex.Pattern;

/**
 * Default car service interface implementation
 *
 * @see it.me.parking.service.ICarService
 */
@Service
public class CarService implements ICarService {
    private final CarRepository repository;

    /**
     * Constructor setting used car repository
     *
     * @param repository car repository used for manipulations
     */
    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Car> getAllCars() {
        return repository.findAll();
    }

    @Override
    public Car getCarById(long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void updateCar(long id, CarRequest newCarData) {
        if (!isValidLicensePlateNumber(newCarData.getLicensePlateNumber())) {
            throw new InvalidRequestHttpException("Invalid license plate number");
        }
        Car oldCar = repository.findById(id).orElseThrow();
        oldCar.setModel(newCarData.getModel());
        oldCar.setLicensePlateNumber(newCarData.getLicensePlateNumber());
        oldCar.setOwnerName(newCarData.getOwnerName());
        repository.save(oldCar);
    }

    @Override
    public void deleteCarById(long id) {
        repository.deleteById(id);
    }

    @Override
    public Car getCarByLicensePlateNumber(String licensePlateNumber) {
        Car specifiedCar = repository.getCarByLicensePlateNumber(licensePlateNumber);
        if (specifiedCar == null) {
            throw new NoSuchElementException("Car with specified license plate number does not exist.");
        }
        return specifiedCar;
    }

    @Override
    public void addCar(CarRequest car) {
        if (!isValidLicensePlateNumber(car.getLicensePlateNumber())) {
            throw new InvalidRequestHttpException("Invalid license plate number");
        }
        repository.save(new Car(
                car.getLicensePlateNumber(),
                car.getOwnerName(),
                car.getModel()
        ));
    }

    private boolean isValidLicensePlateNumber(String licensePlateNumber) {
        return Pattern.matches("[А-Я][А-Я]\\d\\d\\d[А-Я]", licensePlateNumber);
    }
}
