package app.prod.exception;

/**
 * Exception thrown when an attempt to initialize a task fails due to invalid or incomplete parameters.
 */
public class TaskInitializationException extends Exception {

    public TaskInitializationException() {
    }

    public TaskInitializationException(String message) {
        super(message);
    }

    public TaskInitializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskInitializationException(Throwable cause) {
        super(cause);
    }
}
