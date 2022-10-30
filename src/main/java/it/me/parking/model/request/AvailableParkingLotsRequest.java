package it.me.parking.model.request;

import java.util.Date;

/**
 * Request class containing data to get available parking lots for specific time
 */
public class AvailableParkingLotsRequest {
    private Date startTime;
    private Date endTime;

    /**
     * Empty constructor
     */
    public AvailableParkingLotsRequest() {
    }

    /**
     * Constructor setting specified time to search for available parking lots
     *
     * @param startTime start time for future reservation
     * @param endTime   end time for future reservation
     */
    public AvailableParkingLotsRequest(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
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
