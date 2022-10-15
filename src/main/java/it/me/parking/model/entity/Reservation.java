package it.me.parking.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Class representing Parking lot reservation in database
 */
@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(targetEntity = Car.class)
    private Long carId;
    @ManyToOne(targetEntity = ParkingLot.class)
    private Long lotId;
    @Temporal(TemporalType.TIME)
    private Date startTime;
    @Temporal(TemporalType.TIME)
    private Date endTime;

    /**
     * Constructor not setting any fields
     * Used in Spring data JPA
     */
    public Reservation() {
    }

    /**
     * Constructor setting all fields except for reservation id
     * id is generated automatically
     *
     * @param carId     id of the car that will be parked
     * @param lotId     id of the lot used for parking
     * @param startTime lot reservation starting time
     * @param endTime   lot reservation ending time
     */
    public Reservation(Long carId, Long lotId, Date startTime, Date endTime) {
        this.carId = carId;
        this.lotId = lotId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructor setting all fields
     *
     * @param id        id of the reservation entity
     * @param carId     id of the car that will be parked
     * @param lotId     id of the lot used for parking
     * @param startTime lot reservation starting time
     * @param endTime   lot reservation ending time
     */
    public Reservation(Long id, Long carId, Long lotId, Date startTime, Date endTime) {
        this.id = id;
        this.carId = carId;
        this.lotId = lotId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
