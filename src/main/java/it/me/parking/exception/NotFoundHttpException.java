package it.me.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class NotFoundHttpException extends HttpStatusCodeException {

    public NotFoundHttpException() {
        super(HttpStatus.NOT_FOUND, "Resource not found.");
    }

    public NotFoundHttpException(Throwable cause) {
        super(HttpStatus.NOT_FOUND, "Resource not found. Cause:" + cause.getMessage());
    }

}
