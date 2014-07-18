package com.epam.phones.exception;

/**
 * Created by Dmitry on 18.07.2014.
 */
public class ValidatorException extends Exception{
    public ValidatorException() {
        super();
    }
    public ValidatorException(String message) {
        super(message);
    }
    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }
    public ValidatorException(Throwable cause) {
        super(cause);
    }
}
