package it.me.parking.service;

import it.me.parking.model.entity.ParkingLot;
import it.me.parking.model.entity.Reservation;
import it.me.parking.model.request.AvailableParkingLotsRequest;
import it.me.parking.model.request.ReservationRequest;
import it.me.parking.model.response.ReservationBillResponse;

import java.util.List;

/**
 * Interface for service manipulating reservation entities in database
 */
public interface IReservationService {
    /**
     * Method getting all reservations from database
     *
     * @return list of all reservations
     */
    Iterable<Reservation> getAllReservations();

    /**
     * Method getting reservation with specified id
     *
     * @param id id of the reservation
     * @return reservation with specified id
     */
    Reservation getReservationById(long id);

    /**
     * Method updating reservation properties with specified
     *
     * @param id                 id of the changed reservation
     * @param newReservationData object with new reservation properties
     */
    void updateReservation(long id, ReservationRequest newReservationData);

    /**
     * Method deleting reservation with specified id
     *
     * @param id id of the reservation
     */
    void deleteReservationById(long id);

    /**
     * Method adding reservation to database
     *
     * @param reservation object with properties of the new car
     */
    void addReservation(ReservationRequest reservation);

    /**
     * Method getting available parking lots for specified time
     *
     * @param availableParkingLotsRequest request with specified time to search available parking lots
     * @return available parking lots for specified time
     */
    List<ParkingLot> getAvailableParkingLots(AvailableParkingLotsRequest availableParkingLotsRequest);

    /**
     * Method getting bill for specified reservation
     *
     * @param id id of the reservation
     * @return bill for specified reservation
     */
    ReservationBillResponse getReservationBill(long id);
}
