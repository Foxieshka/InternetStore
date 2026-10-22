package Exceptions;

public class PriceBelowZeroException extends RuntimeException {
    public PriceBelowZeroException(String message) {
        super(message);
    }
}
