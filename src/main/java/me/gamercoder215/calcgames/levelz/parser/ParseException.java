package me.gamercoder215.calcgames.levelz.parser;

/**
 * Thrown to indicate errors in parsing a LevelZ file.
 */
public class ParseException extends RuntimeException {

    private static final long serialVersionUID = 3594456258251637068L;

    /**
     * Creates a new Parse Exception.
     * @param message Message
     */
    public ParseException(String message) {
        super(message);
    }

    /**
     * Creates a new Parse Exception.
     * @param message Message
     * @param cause Cause
     */
    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }

}
