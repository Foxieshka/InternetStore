package Exceptions;

public class InvalidNameOrSurnameException extends RuntimeException {
    public InvalidNameOrSurnameException(String message) {
        super(message);
    }
}
