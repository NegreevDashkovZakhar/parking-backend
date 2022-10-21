package it.me.parking.model.request;

/**
 * Class representing car data abstracted from storing in database
 * Thus it does not have its id
 */
public class CarRequest {
    private String licensePlateNumber;
    private String ownerName;
    private String model;

    /**
     * Constructor setting default values to all fields
     */
    public CarRequest() {
    }

    /**
     * Constructor setting all fields
     *
     * @param licensePlateNumber license plate number of the car
     * @param ownerName          name of the owner of the car
     * @param model              model of the car
     */
    public CarRequest(String licensePlateNumber, String ownerName, String model) {
        this.licensePlateNumber = licensePlateNumber;
        this.ownerName = ownerName;
        this.model = model;
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
}
