package it.me.parking.service;

import it.me.parking.model.entity.ParkingLot;
import it.me.parking.model.request.ParkingLotRequest;
import it.me.parking.repository.ParkingLotRepository;
import org.springframework.stereotype.Service;

/**
 * Default parking lot service interface implementation
 *
 * @see it.me.parking.service.IParkingLotService
 */
@Service
public class ParkingLotService implements IParkingLotService {
    private final ParkingLotRepository repository;

    /**
     * Constructor setting used parking lot repository
     *
     * @param repository parking lot repository used for manipulations
     */
    public ParkingLotService(ParkingLotRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<ParkingLot> getAllParkingLots() {
        return repository.findAll();
    }

    @Override
    public ParkingLot getParkingLotById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void updateParkingLot(Long id, ParkingLotRequest newParkingLotData) {
        ParkingLot oldParkingLot = repository.findById(id).orElseThrow();
        oldParkingLot.setLocationSign(newParkingLotData.getLocationSign());
        oldParkingLot.setHourPrice(newParkingLotData.getHourPrice());
        repository.save(oldParkingLot);
    }

    @Override
    public void deleteParkingLotById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ParkingLot getParkingLotByLocation(String location) {
        return repository.getParkingLotByLocation(location);
    }

    @Override
    public void addParkingLot(ParkingLotRequest parkingLotRequest) {
        repository.save(new ParkingLot(
                parkingLotRequest.getLocationSign(),
                parkingLotRequest.getHourPrice()
        ));
    }
}
