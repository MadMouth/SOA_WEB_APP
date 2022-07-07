package SOA_WEB_APP.exception;

import org.springframework.http.HttpStatus;

public class ExceptionEntity {
    private final String error_message;
    private final HttpStatus httpStatus;

    public ExceptionEntity(String message,
                           HttpStatus httpStatus) {
        this.error_message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return error_message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
