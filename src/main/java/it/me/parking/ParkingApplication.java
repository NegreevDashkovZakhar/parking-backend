package it.me.parking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application entry point class
 */
@SpringBootApplication
public class ParkingApplication {

    /**
     * Application entry point method
     *
     * @param args arguments given to application(not used)
     */
    public static void main(String[] args) {
        SpringApplication.run(ParkingApplication.class, args);
    }

}
