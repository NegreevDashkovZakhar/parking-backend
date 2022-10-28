package it.me.parking.service;

import it.me.parking.exception.InvalidRequestHttpException;
import it.me.parking.model.entity.ParkingLot;
import it.me.parking.model.entity.Reservation;
import it.me.parking.model.request.FreeParkingLotsRequest;
import it.me.parking.model.request.ReservationRequest;
import it.me.parking.repository.CarRepository;
import it.me.parking.repository.ParkingLotRepository;
import it.me.parking.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Default reservation service interface implementation
 *
 * @see it.me.parking.service.IReservationService
 */
@Service
public class ReservationService implements IReservationService {
    private final ReservationRepository repository;
    private final CarRepository carRepository;
    private final ParkingLotRepository lotRepository;

    /**
     * Constructor setting all used repositories
     * Uses car and lot repository to get corresponding car and lot to reservation from their id
     *
     * @param repository    used reservation repository
     * @param carRepository used car repository
     * @param lotRepository used parking lot repository
     */
    public ReservationService(ReservationRepository repository, CarRepository carRepository, ParkingLotRepository lotRepository) {
        this.repository = repository;
        this.carRepository = carRepository;
        this.lotRepository = lotRepository;
    }

    @Override
    public Iterable<Reservation> getAllReservations() {
        return repository.findAll();
    }

    @Override
    public Reservation getReservationById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void updateReservation(Long id, ReservationRequest newReservationData) {
        Reservation oldReservation = repository.findById(id).orElseThrow();
        validateRequest(newReservationData);
        oldReservation.setCar(
                carRepository.findById(newReservationData.getCarId()).orElseThrow()
        );
        oldReservation.setLot(
                lotRepository.findById(newReservationData.getLotId()).orElseThrow()
        );
        oldReservation.setStartTime(newReservationData.getStartTime());
        oldReservation.setEndTime(newReservationData.getEndTime());
        repository.save(oldReservation);
    }

    @Override
    public void deleteReservationById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void addReservation(ReservationRequest reservation) {
        validateRequest(reservation);
        repository.save(new Reservation(
                carRepository.findById(reservation.getCarId()).orElseThrow(),
                lotRepository.findById(reservation.getLotId()).orElseThrow(),
                reservation.getStartTime(),
                reservation.getEndTime()
        ));
    }

    @Override
    public List<ParkingLot> getFreeParkingLots(FreeParkingLotsRequest freeParkingLotsRequest) {
        //TODO simplify algorithm
        List<BigInteger> usedReservations = repository.getReservedLotsId(
                freeParkingLotsRequest.getStartTime(),
                freeParkingLotsRequest.getEndTime()
        );
        Iterable<ParkingLot> allParkingLots = lotRepository.findAll();
        List<ParkingLot> freeParkingLots = new ArrayList<>();
        for (ParkingLot parkingLot : allParkingLots) {
            if (!usedReservations.contains(BigInteger.valueOf(parkingLot.getId()))) {
                freeParkingLots.add(parkingLot);
            }
        }
        return freeParkingLots;
    }

    private void validateRequest(ReservationRequest reservationRequest) {
        int startTimeSeconds = (int) (reservationRequest.getStartTime().getTime() % 60000);
        int endTimeSeconds = (int) (reservationRequest.getEndTime().getTime() % 60000);
        if (startTimeSeconds != 0 || endTimeSeconds != 0)
            throw new InvalidRequestHttpException("Reservation time must not contain seconds time");
    }
}
