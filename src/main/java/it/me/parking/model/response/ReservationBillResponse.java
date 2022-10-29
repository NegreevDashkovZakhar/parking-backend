package it.me.parking.model.response;

import it.me.parking.model.entity.Reservation;

import java.util.Date;

/**
 * Response class representing bill for parking lot reservation
 */
public class ReservationBillResponse {
    private final float totalPrice;
    private final Reservation reservation;

    /**
     * Constructor setting reservation and calculating total price from specified reservation
     * @param reservation the billed reservation
     */
    public ReservationBillResponse(Reservation reservation) {
        this.reservation = reservation;
        Date startTime = reservation.getStartTime();
        Date endTime = reservation.getEndTime();
        long totalTimeMinutes = (endTime.getTime() - startTime.getTime()) / 60000;
        float minutePrice = reservation.getLot().getHourPrice() / 60f;
        this.totalPrice = totalTimeMinutes * minutePrice;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public Reservation getReservation() {
        return reservation;
    }
}
