package it.me.parking.service;

import it.me.parking.model.entity.Car;
import it.me.parking.model.request.CarRequest;
import it.me.parking.repository.CarRepository;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Service;

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
    public Car getCarById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void updateCar(Long id, CarRequest newCarData) {
        Car oldCar = repository.findById(id).orElseThrow();
        oldCar.setModel(newCarData.getModel());
        oldCar.setLicensePlateNumber(newCarData.getLicensePlateNumber());
        oldCar.setOwnerName(newCarData.getOwnerName());
        repository.save(oldCar);
    }

    @Override
    public void deleteCarById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Car getCarByLicensePlateNumber(String licensePlateNumber) {
        throw new NotYetImplementedException();
    }

    @Override
    public void addCar(CarRequest car) {
        repository.save(new Car(
                car.getLicensePlateNumber(),
                car.getOwnerName(),
                car.getModel()
        ));
    }
}
