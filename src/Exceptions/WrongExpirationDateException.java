package Exceptions;

public class WrongExpirationDateException extends RuntimeException {
    public WrongExpirationDateException(String message) {
        super(message);
    }
}
