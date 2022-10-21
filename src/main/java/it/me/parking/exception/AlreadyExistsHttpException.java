package it.me.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception class thrown when requested to add entity but such entity already exists.
 * Http code:409
 */
public class AlreadyExistsHttpException extends ResponseStatusException {
    /**
     * Constructor setting http code and default message
     */
    public AlreadyExistsHttpException() {
        super(HttpStatus.CONFLICT, "Entity already exists.");
    }

    /**
     * Constructor setting http code and default message extended with cause message
     */
    public AlreadyExistsHttpException(Throwable cause) {
        super(HttpStatus.CONFLICT, "Entity already exists. Cause:" + cause);
    }
}
