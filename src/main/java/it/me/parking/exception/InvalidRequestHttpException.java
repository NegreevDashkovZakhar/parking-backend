package it.me.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Exception class thrown when invalid request received.
 * Http code:400
 */
public class InvalidRequestHttpException extends ResponseStatusException {
    /**
     * Constructor setting http code and default message
     */
    public InvalidRequestHttpException() {
        super(HttpStatus.BAD_REQUEST, "Invalid Request given.");
    }

    /**
     * Constructor setting http code and default message extended with cause message
     *
     * @param cause cause of this exception
     */
    public InvalidRequestHttpException(Throwable cause) {
        super(HttpStatus.BAD_REQUEST, "Invalid Request given. Cause:" + cause.getMessage());
    }

    /**
     * Constructor setting http code and specified message
     *
     * @param message specified exception message
     */
    public InvalidRequestHttpException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
