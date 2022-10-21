package it.me.parking.service;

import it.me.parking.model.entity.Reservation;
import it.me.parking.model.request.ReservationRequest;
import it.me.parking.repository.CarRepository;
import it.me.parking.repository.ParkingLotRepository;
import it.me.parking.repository.ReservationRepository;
import org.springframework.stereotype.Service;

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
        oldReservation.setCarId(newReservationData.getCarId());
        oldReservation.setLotId(newReservationData.getLotId());
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
        repository.save(new Reservation(
                carRepository.findById(reservation.getCarId()).orElseThrow(),
                lotRepository.findById(reservation.getLotId()).orElseThrow(),
                reservation.getStartTime(),
                reservation.getEndTime()
        ));
    }
}
