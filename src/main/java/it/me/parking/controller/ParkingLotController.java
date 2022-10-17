package it.me.parking.controller;

import it.me.parking.exception.AlreadyExistsHttpException;
import it.me.parking.exception.InvalidRequestHttpException;
import it.me.parking.exception.NotFoundHttpException;
import it.me.parking.model.entity.ParkingLot;
import it.me.parking.model.request.ParkingLotRequest;
import it.me.parking.service.IParkingLotService;
import org.hibernate.PropertyValueException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class ParkingLotController {
    private final IParkingLotService parkingLotService;

    /**
     * Default constructor setting all fields
     *
     * @param parkingLotService parking lot service object used for manipulations
     */
    public ParkingLotController(IParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    /**
     * Method getting all parking lot entities in database
     *
     * @return list of all parking lot entities
     */
    @GetMapping(path = "/parking-lots")
    public Iterable<ParkingLot> getAllParkingLots() {
        return parkingLotService.getAllParkingLots();
    }

    /**
     * Method getting specific parking lot by given id
     *
     * @param id id of the parking lot
     * @return parking lot entity with specified id
     */
    @GetMapping(path = "/parking-lots/{id}")
    public ParkingLot getParkingLotById(@PathVariable("id") Long id) {
        try {
            return parkingLotService.getParkingLotById(id);
        } catch (NoSuchElementException e) {
            throw new NotFoundHttpException(e);
        }
    }

    /**
     * Method updating specific parking lot entity properties
     *
     * @param id                id of the modified parking lot
     * @param newParkingLotData object containing new properties for the parking lot
     */
    @PutMapping(path = "/parking-lots/{id}")
    public void updateParkingLot(@PathVariable Long id,
                                 @RequestBody ParkingLotRequest newParkingLotData) {
        try {
            parkingLotService.updateParkingLot(id, newParkingLotData);
        } catch (NoSuchElementException e) {
            throw new NotFoundHttpException(e);
        } catch (PropertyValueException e) {
            throw new InvalidRequestHttpException(e);
        }
    }

    /**
     * Method deleting parking lot with specified id
     *
     * @param id id of the parking lot
     */
    @DeleteMapping(path = "/parking-lots/{id}")
    public void deleteParkingLotById(@PathVariable Long id) {
        try {
            parkingLotService.deleteParkingLotById(id);
        } catch (NoSuchElementException e) {
            throw new NotFoundHttpException(e);
        }
    }

    /**
     * Method getting parking lot with specified location
     *
     * @param location location of the parking lot
     * @return parking lot entity with specified location
     */
    @GetMapping(path = "/parking-lots/location/{location}")
    public ParkingLot getParkingLotByLicensePlateNumber(@PathVariable String location) {
        try {
            return parkingLotService.getParkingLotByLocation(location);
        } catch (NoSuchElementException e) {
            throw new NotFoundHttpException(e);
        }
    }

    /**
     * Method adding new parking lot to database
     *
     * @param parkingLot object containing properties of the new parking lot
     */
    @PostMapping(path = "/parking-lots")
    public void addCar(@RequestBody ParkingLotRequest parkingLot) {
        try {
            parkingLotService.addParkingLot(parkingLot);
        } catch (PropertyValueException e) {
            throw new InvalidRequestHttpException(e);
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsHttpException(e);
        }
    }
}
