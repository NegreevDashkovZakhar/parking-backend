package it.me.parking.model.request;

/**
 * Class representing parking lot data abstracted from storing in database
 * Thus it does not have its id
 */
public class ParkingLotRequest {
    private String locationSign;
    private Long hourPrice;

    /**
     * Constructor setting default values to all fields
     */
    public ParkingLotRequest() {
    }

    /**
     * Constructor setting all fields
     *
     * @param locationSign sign identifying parking lot in real life
     * @param hourPrice    cost of one hour reservation in rubles
     */
    public ParkingLotRequest(String locationSign, Long hourPrice) {
        this.locationSign = locationSign;
        this.hourPrice = hourPrice;
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
