package it.me.parking.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

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
}
