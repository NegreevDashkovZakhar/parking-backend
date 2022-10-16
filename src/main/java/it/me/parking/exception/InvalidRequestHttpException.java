package it.me.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class InvalidRequestHttpException extends HttpStatusCodeException {
    public InvalidRequestHttpException() {
        super(HttpStatus.BAD_REQUEST, "Invalid Request given.");
    }

    public InvalidRequestHttpException(Throwable cause) {
        super(HttpStatus.BAD_REQUEST, "Invalid Request given. Cause:" + cause.getMessage());
    }
}
