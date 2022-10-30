package it.me.parking.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Class representing Parking lot in database
 */
@Entity
@Table(name = "parking_lot")
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String locationSign;
    @Column(nullable = false)
    private Long hourPrice;

    @OneToMany(mappedBy = "lot", cascade = CascadeType.REMOVE)
    private Set<Reservation> reservations;

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
