package com.epam.phones.exception;

/**
 * Created by Dmitry on 09.07.2014.
 */
public class ParseException extends Exception {
    public ParseException() {
        super();
    }
    public ParseException(String message) {
        super(message);
    }
    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
    public ParseException(Throwable cause) {
        super(cause);
    }
}

