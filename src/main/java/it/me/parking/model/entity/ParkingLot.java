package it.me.parking.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Class representing Parking lot in database
 */
@Entity
public class ParkingLot {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String locationSign;
    private Long hourPrice;

    /**
     * Constructor not setting any fields
     * Used in Spring data JPA
     */
    public ParkingLot() {
    }

    /**
     * Constructor setting all fields except for parking lot id
     * id is generated automatically
     *
     * @param locationSign sign identifying parking lot in real life
     * @param hourPrice    cost of one hour reservation in rubles
     */
    public ParkingLot(String locationSign, Long hourPrice) {
        this.locationSign = locationSign;
        this.hourPrice = hourPrice;
    }

    /**
     * Constructor setting all fields
     *
     * @param id           id of the parking lot
     * @param locationSign sign identifying parking lot in real life
     * @param hourPrice    cost of one hour reservation in rubles
     */
    public ParkingLot(Long id, String locationSign, Long hourPrice) {
        this.id = id;
        this.locationSign = locationSign;
        this.hourPrice = hourPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationSign() {
        return locationSign;
    }

    public void setLocationSign(String locationSign) {
        this.locationSign = locationSign;
    }

    public Long getHourPrice() {
        return hourPrice;
    }

    public void setHourPrice(Long hourPrice) {
        this.hourPrice = hourPrice;
    }
}
