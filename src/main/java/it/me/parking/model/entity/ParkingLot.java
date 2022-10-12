package it.me.parking.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ParkingLot {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String locationSign;
    private Long hourPrice;
}
