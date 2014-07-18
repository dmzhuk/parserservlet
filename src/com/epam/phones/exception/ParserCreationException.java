package com.epam.phones.exception;

/**
 * Created by Dmitry on 14.07.2014.
 */
public class ParserCreationException extends Exception {
    public ParserCreationException() {
        super();
    }
    public ParserCreationException(String message) {
        super(message);
    }
    public ParserCreationException(String message, Throwable cause) {
        super(message, cause);
    }
    public ParserCreationException(Throwable cause) {
        super(cause);
    }
}
