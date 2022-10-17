package it.me.parking.service;

import it.me.parking.model.entity.Reservation;
import it.me.parking.model.request.ReservationRequest;

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
    Reservation getReservationById(Long id);

    /**
     * Method updating reservation properties with specified
     *
     * @param id                 id of the changed reservation
     * @param newReservationData object with new reservation properties
     */
    void updateReservation(Long id, ReservationRequest newReservationData);

    /**
     * Method deleting reservation with specified id
     *
     * @param id id of the reservation
     */
    void deleteReservationById(Long id);

    /**
     * Method adding reservation to database
     *
     * @param reservation object with properties of the new car
     */
    void addReservation(ReservationRequest reservation);
}