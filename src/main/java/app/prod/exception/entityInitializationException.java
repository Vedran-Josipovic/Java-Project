package app.prod.exception;

/**
 * Exception thrown when an attempt to initialize a task fails due to invalid or incomplete parameters.
 */
public class entityInitializationException extends Exception {

    public entityInitializationException() {
    }

    public entityInitializationException(String message) {
        super(message);
    }

    public entityInitializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public entityInitializationException(Throwable cause) {
        super(cause);
    }
}
