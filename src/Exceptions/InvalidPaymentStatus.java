package Exceptions;

public class InvalidPaymentStatus extends RuntimeException {
    public InvalidPaymentStatus(String message) {
        super(message);
    }
}
