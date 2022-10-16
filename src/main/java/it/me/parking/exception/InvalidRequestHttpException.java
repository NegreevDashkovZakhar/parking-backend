package it.me.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

/**
 * Exception class thrown when invalid request received.
 * Http code:400
 */
public class InvalidRequestHttpException extends HttpStatusCodeException {
    /**
     * Constructor setting http code and default message
     */
    public InvalidRequestHttpException() {
        super(HttpStatus.BAD_REQUEST, "Invalid Request given.");
    }

    /**
     * Constructor setting http code and default message extended with cause message
     */
    public InvalidRequestHttpException(Throwable cause) {
        super(HttpStatus.BAD_REQUEST, "Invalid Request given. Cause:" + cause.getMessage());
    }
}
