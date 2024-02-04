package app.prod.exception;

import java.security.PrivilegedActionException;

public class TransactionAmountException extends Exception{

    public TransactionAmountException() {
    }


    public TransactionAmountException(String message) {
        super(message);
    }


    public TransactionAmountException(String message, Throwable cause) {
        super(message, cause);
    }


    public TransactionAmountException(Throwable cause) {
        super(cause);
    }
}
