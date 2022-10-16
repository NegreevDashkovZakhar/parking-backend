package it.me.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

/**
 * Exception class thrown when requested resource was not found.
 * Http code:404
 */
public class NotFoundHttpException extends HttpStatusCodeException {
    /**
     * Constructor setting http code and default message
     */
    public NotFoundHttpException() {
        super(HttpStatus.NOT_FOUND, "Resource not found.");
    }

    /**
     * Constructor setting http code and default message extended with cause message
     */
    public NotFoundHttpException(Throwable cause) {
        super(HttpStatus.NOT_FOUND, "Resource not found. Cause:" + cause.getMessage());
    }

}
