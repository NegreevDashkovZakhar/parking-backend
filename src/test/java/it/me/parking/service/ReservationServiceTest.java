package it.me.parking.service;

import it.me.parking.exception.InvalidRequestHttpException;
import it.me.parking.model.entity.Reservation;
import it.me.parking.model.request.ReservationRequest;
import it.me.parking.repository.CarRepository;
import it.me.parking.repository.ParkingLotRepository;
import it.me.parking.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ReservationServiceTest {
    private ReservationRepository reservationRepository;
    private CarRepository carRepository;
    private ParkingLotRepository parkingLotRepository;
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        this.reservationRepository = Mockito.mock(ReservationRepository.class);
        this.carRepository = Mockito.mock(CarRepository.class);
        this.parkingLotRepository = Mockito.mock(ParkingLotRepository.class);
        this.reservationService = new ReservationService(
                reservationRepository,
                carRepository,
                parkingLotRepository
        );
    }

    @Test
    public void testInvalidStartingDateReservation() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        Date startingTime = formatter.parse("2021-10-11 12:00:13");
        Date endingTime = formatter.parse("2021-10-11 12:10:00");
        ReservationRequest reservationRequest = new ReservationRequest(
                1L,
                1L,
                startingTime,
                endingTime
        );
        try {
            reservationService.addReservation(reservationRequest);
            fail();
        } catch (InvalidRequestHttpException e) {
            assertEquals("400 BAD_REQUEST \"Reservation time must not contain seconds time\"", e.getMessage());
        }
    }

    @Test
    public void testInvalidEndingDateReservation() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        Date startingTime = formatter.parse("2021-10-11 12:00:00");
        Date endingTime = formatter.parse("2021-10-11 12:10:24");
        ReservationRequest reservationRequest = new ReservationRequest(
                1L,
                1L,
                startingTime,
                endingTime
        );
        try {
            reservationService.addReservation(reservationRequest);
            fail();
        } catch (InvalidRequestHttpException e) {
            assertEquals("400 BAD_REQUEST \"Reservation time must not contain seconds time\"", e.getMessage());
        }
    }

    @Test
    public void testInvalidStartingDateUpdateReservation() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        Date startingTime = formatter.parse("2021-10-11 12:00:13");
        Date endingTime = formatter.parse("2021-10-11 12:10:00");
        ReservationRequest reservationRequest = new ReservationRequest(
                1L,
                1L,
                startingTime,
                endingTime
        );
        Reservation reservation = Mockito.mock(Reservation.class);
        Mockito.when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        try {
            reservationService.updateReservation(1L, reservationRequest);
            fail();
        } catch (InvalidRequestHttpException e) {
            assertEquals("400 BAD_REQUEST \"Reservation time must not contain seconds time\"", e.getMessage());
        }
    }

    @Test
    public void testInvalidEndingDateUpdateReservation() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        Date startingTime = formatter.parse("2021-10-11 12:00:00");
        Date endingTime = formatter.parse("2021-10-11 12:10:24");
        ReservationRequest reservationRequest = new ReservationRequest(
                1L,
                1L,
                startingTime,
                endingTime
        );
        Reservation reservation = Mockito.mock(Reservation.class);
        Mockito.when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        try {
            reservationService.updateReservation(1L, reservationRequest);
            fail();
        } catch (InvalidRequestHttpException e) {
            assertEquals("400 BAD_REQUEST \"Reservation time must not contain seconds time\"", e.getMessage());
        }
    }

    @Test
    public void testInvalidTimeOrderReservation() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        Date startingTime = formatter.parse("2021-10-11 12:25:00");
        Date endingTime = formatter.parse("2021-10-11 12:10:00");
        ReservationRequest reservationRequest = new ReservationRequest(
                1L,
                1L,
                startingTime,
                endingTime
        );
        try {
            reservationService.addReservation(reservationRequest);
            fail();
        } catch (InvalidRequestHttpException e) {
            assertEquals("400 BAD_REQUEST \"Reservation starting time must go after ending time\"", e.getMessage());
        }
    }

    @Test
    public void testInvalidTimeOrderUpdateReservation() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        Date startingTime = formatter.parse("2021-10-11 12:25:00");
        Date endingTime = formatter.parse("2021-10-11 12:10:00");
        ReservationRequest reservationRequest = new ReservationRequest(
                1L,
                1L,
                startingTime,
                endingTime
        );
        Reservation reservation = Mockito.mock(Reservation.class);
        Mockito.when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        try {
            reservationService.updateReservation(1L, reservationRequest);
            fail();
        } catch (InvalidRequestHttpException e) {
            assertEquals("400 BAD_REQUEST \"Reservation starting time must go after ending time\"", e.getMessage());
        }
    }

    @Test
    public void testAlreadyReservedParkingLotReservation() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        Date startingTime = formatter.parse("2021-10-11 12:10:00");
        Date endingTime = formatter.parse("2021-10-11 12:25:00");
        ReservationRequest reservationRequest = new ReservationRequest(
                1L,
                1L,
                startingTime,
                endingTime
        );

        List<Long> availableParkingLots = new ArrayList<>();
        availableParkingLots.add(2L);
        availableParkingLots.add(4L);
        Mockito.when(reservationRepository.getAvailableParkingLots(startingTime, endingTime)).thenReturn(availableParkingLots);
        try {
            reservationService.addReservation(reservationRequest);
            fail();
        } catch (InvalidRequestHttpException e) {
            assertEquals("400 BAD_REQUEST \"Time is already reserved\"", e.getMessage());
        }
    }
}