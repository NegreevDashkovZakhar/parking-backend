package it.me.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class AlreadyExistsHttpException extends HttpStatusCodeException {
    public AlreadyExistsHttpException() {
        super(HttpStatus.CONFLICT, "Entity already exists.");
    }

    public AlreadyExistsHttpException(Throwable cause) {
        super(HttpStatus.CONFLICT, "Entity already exists. Cause:" + cause);
    }
}
