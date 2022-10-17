package it.me.parking.model.request;

import java.util.Date;

/**
 * Class representing reservation data abstracted from storing in database
 * Thus it does not have its id and car, lot as objects
 */
public class ReservationRequest {
    private Long carId;
    private Long lotId;
    private Date startTime;
    private Date endTime;

    /**
     * Constructor setting default values to all fields
     */
    public ReservationRequest() {
    }

    /**
     * Constructor setting all fields
     *
     * @param carId     id of the car parked
     * @param lotId     id of the reserved lot
     * @param startTime reservation starting time
     * @param endTime   reservation ending time
     */
    public ReservationRequest(Long carId, Long lotId, Date startTime, Date endTime) {
        this.carId = carId;
        this.lotId = lotId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
