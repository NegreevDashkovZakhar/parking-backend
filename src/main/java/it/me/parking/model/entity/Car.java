package it.me.parking.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

/**
 * Class representing Client/Car in database
 */
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String licensePlateNumber;
    @Column(nullable = false)
    private String ownerName;
    @Column(nullable = false)
    private String model;

    @OneToMany(mappedBy = "car", cascade = CascadeType.REMOVE)
    private Set<Reservation> reservations;

    /**
     * Constructor not setting any fields
     * Used in Spring data JPA
     */
    public Car() {
    }

    /**
     * Constructor setting all fields except for id
     * id is generated automatically
     *
     * @param licensePlateNumber license plate number of the car
     * @param ownerName          name of the owner of the car
     * @param model              model of the car
     */
    public Car(String licensePlateNumber, String ownerName, String model) {
        this.licensePlateNumber = licensePlateNumber;
        this.ownerName = ownerName;
        this.model = model;
    }

    /**
     * Constructor setting all fields
     *
     * @param id                 id of the car
     * @param licensePlateNumber license plate number of the car
     * @param ownerName          name of the owner of the car
     * @param model              model of the car
     */
    public Car(Long id, String licensePlateNumber, String ownerName, String model) {
        this.id = id;
        this.licensePlateNumber = licensePlateNumber;
        this.ownerName = ownerName;
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) &&
                Objects.equals(licensePlateNumber, car.licensePlateNumber) &&
                Objects.equals(ownerName, car.ownerName) && Objects.equals(model, car.model) &&
                Objects.equals(reservations, car.reservations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, licensePlateNumber, ownerName, model, reservations);
    }
}
