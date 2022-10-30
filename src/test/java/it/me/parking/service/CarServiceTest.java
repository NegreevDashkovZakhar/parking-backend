package it.me.parking.service;

import it.me.parking.exception.InvalidRequestHttpException;
import it.me.parking.model.entity.Car;
import it.me.parking.model.request.CarRequest;
import it.me.parking.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class CarServiceTest {
    private CarRepository carRepository;
    private CarService carService;

    @BeforeEach
    void setUp() {
        assertEquals(1, 1);
        this.carRepository = Mockito.mock(CarRepository.class);
        this.carService = new CarService(carRepository);
    }

    @Test
    public void testInvalidLicensePlateNumberCarAddition() {
        CarRequest carRequest = Mockito.mock(CarRequest.class);
        String licensePlateNumber = "не валидный номер";
        Mockito.when(carRequest.getLicensePlateNumber()).thenReturn(licensePlateNumber);
        try {
            carService.addCar(carRequest);
            fail();
        } catch (InvalidRequestHttpException e) {
            assertEquals("400 BAD_REQUEST \"Invalid license plate number\"", e.getMessage());
        }
    }

    @Test
    public void testValidLicensePlateNumberCarAddition() {
        CarRequest carRequest = new CarRequest(
                "ДЛ419Г",
                "Захар Дашков",
                "Lada granta"
        );
        carService.addCar(carRequest);
        Mockito.verify(carRepository, Mockito.times(1)).save(new Car(
                carRequest.getLicensePlateNumber(),
                carRequest.getOwnerName(),
                carRequest.getModel()
        ));
    }
}