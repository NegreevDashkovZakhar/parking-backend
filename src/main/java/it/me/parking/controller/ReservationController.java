package it.me.parking.controller;

import it.me.parking.exception.AlreadyExistsHttpException;
import it.me.parking.exception.InvalidRequestHttpException;
import it.me.parking.exception.NotFoundHttpException;
import it.me.parking.model.entity.ParkingLot;
import it.me.parking.model.entity.Reservation;
import it.me.parking.model.request.FreeParkingLotsRequest;
import it.me.parking.model.request.ReservationRequest;
import it.me.parking.model.response.ReservationBillResponse;
import it.me.parking.service.IReservationService;
import org.hibernate.PropertyValueException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Controller passing arguments from requests to reservation service
 */
@RestController
public class ReservationController {
    private final IReservationService reservationService;

    /**
     * Default constructor setting all fields
     *
     * @param reservationService reservation service object used for manipulations
     */
    public ReservationController(IReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * Method getting all reservation entities in database
     *
     * @return list of all reservation entities
     */
    @GetMapping(path = "/reservations")
    public Iterable<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    /**
     * Method getting specific reservation by given id
     *
     * @param id id of the reservation
     * @return reservation entity with specified id
     */
    @GetMapping(path = "/reservations/{id}")
    public Reservation getReservationById(@PathVariable Long id) {
        try {
            return reservationService.getReservationById(id);
        } catch (NoSuchElementException e) {
            throw new NotFoundHttpException(e);
        }
    }

    /**
     * Method updating specific reservation entity properties
     *
     * @param id                 id of the modified reservation
     * @param newReservationData object containing new properties for the reservation
     */
    @PutMapping(path = "/reservations/{id}")
    public void updateReservation(@PathVariable Long id,
                                  @RequestBody ReservationRequest newReservationData) {
        try {
            reservationService.updateReservation(id, newReservationData);
        } catch (NoSuchElementException e) {
            throw new NotFoundHttpException(e);
        } catch (PropertyValueException | IllegalArgumentException e) {
            throw new InvalidRequestHttpException(e);
        }
    }

    /**
     * Method deleting reservation with specified id
     *
     * @param id id of the reservation
     */
    @DeleteMapping(path = "/reservations/{id}")
    public void deleteCarById(@PathVariable Long id) {
        try {
            reservationService.deleteReservationById(id);
        } catch (NoSuchElementException e) {
            throw new NotFoundHttpException(e);
        }
    }

    /**
     * Method adding new reservation to database
     *
     * @param reservation object containing properties of the new reservation
     */
    @PostMapping(path = "/reservation")
    public void addReservation(@RequestBody ReservationRequest reservation) {
        try {
            reservationService.addReservation(reservation);
        } catch (PropertyValueException e) {
            throw new InvalidRequestHttpException(e);
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsHttpException(e);
        }
    }

    /**
     * Method getting available parking lots for specific time
     *
     * @param parkingLotsRequest request specifying time to find available parking lots
     * @return available parking lots for specific time
     */
    @GetMapping(path = "/reservations/available")
    public List<ParkingLot> getFreeParkingLots(@RequestBody FreeParkingLotsRequest parkingLotsRequest) {
        return reservationService.getFreeParkingLots(parkingLotsRequest);
    }

    /**
     * Method getting bill for specified reservation
     *
     * @param id id of the reservation
     * @return bill for specified reservation
     */
    @GetMapping(path = "reservations/{id}/bill")
    public ReservationBillResponse getReservationBill(@PathVariable long id) {
        try {
            return reservationService.getReservationBill(id);
        } catch (NoSuchElementException e) {
            throw new NotFoundHttpException(e);
        }
    }
}
