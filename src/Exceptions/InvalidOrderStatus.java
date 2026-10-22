package Exceptions;

public class InvalidOrderStatus extends RuntimeException {
    public InvalidOrderStatus(String message) {
        super(message);
    }
}
