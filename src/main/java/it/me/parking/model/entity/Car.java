package it.me.parking.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Car {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String licensePlateNumber;
    private String ownerName;
    private String model;
}
