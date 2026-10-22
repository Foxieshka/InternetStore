package Exceptions;

public class EmptyOrderItemsException extends RuntimeException {
    public EmptyOrderItemsException(String message) {
        super(message);
    }
}
