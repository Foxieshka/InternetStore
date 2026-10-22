package Exceptions;

public class QuantityBelowZeroException extends RuntimeException {
    public QuantityBelowZeroException(String message) {
        super(message);
    }
}
