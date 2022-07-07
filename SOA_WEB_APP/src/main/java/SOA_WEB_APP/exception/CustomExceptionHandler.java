package SOA_WEB_APP.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {AlreadyExistsException.class, DoesNotExist.class, LimitExceededException.class, NoRightsException.class})
    public ResponseEntity<Object> handlerApiUserException(RuntimeException e) {
        HttpStatus status = HttpStatus.CONFLICT;
        ExceptionEntity apiException = new ExceptionEntity(
                e.getMessage(),
                status
        );
        return new ResponseEntity<>(apiException, status);
    }
}
