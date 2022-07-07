package SOA_WEB_APP.exception;

public class DoesNotExist extends RuntimeException {
    public DoesNotExist(String message) {
        super(message);
    }
}