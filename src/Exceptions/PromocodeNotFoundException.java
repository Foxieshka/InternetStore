package Exceptions;

public class PromocodeNotFoundException extends RuntimeException {
    public PromocodeNotFoundException(String message) {
        super(message);
    }
}
