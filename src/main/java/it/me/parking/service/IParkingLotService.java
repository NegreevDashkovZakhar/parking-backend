package it.me.parking.service;

import it.me.parking.model.entity.ParkingLot;
import it.me.parking.model.request.ParkingLotRequest;

/**
 * Interface for service manipulating parking lot entities in database
 */
public interface IParkingLotService {
    /**
     * Method getting all parking lots from database
     *
     * @return list of all parking lots
     */
    Iterable<ParkingLot> getAllParkingLots();

    /**
     * Method getting parking lot with specified id
     *
     * @param id id of the parking lot
     * @return parking lot with specified id
     */
    ParkingLot getParkingLotById(Long id);

    /**
     * Method updating parking lot properties with specified
     *
     * @param id                id of the changed parking lot
     * @param newParkingLotData object with new parking lot properties
     */
    void updateParkingLot(Long id, ParkingLotRequest newParkingLotData);

    /**
     * Method deleting parking lot with specified id
     *
     * @param id id of the parking lot
     */
    void deleteParkingLotById(Long id);

    /**
     * Method getting parking lot with specified location
     *
     * @param location location of the parking lot
     * @return parking lot with specified location
     */
    ParkingLot getParkingLotByLocation(String location);

    /**
     * Method adding parking lot to database
     *
     * @param newParkingLotData object with properties of the new parking lot
     */
    void addParkingLot(ParkingLotRequest newParkingLotData);
}
