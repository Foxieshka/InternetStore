package Exceptions;

public class CartItemCreationException extends RuntimeException {
    public CartItemCreationException(String message) {
        super(message);
    }
}
