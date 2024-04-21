package xyz.calcugames.levelz.parser;

/**
 * Thrown when a point is malformed.
 */
public class MalformedPointException extends ParseException {

    private static final long serialVersionUID = 3594456258251637068L;

    /**
     * Creates a new Malformed Point Exception.
     * @param message Message
     */
    public MalformedPointException(String message) {
        super(message);
    }

    /**
     * Creates a new Malformed Point Exception.
     * @param message Message
     * @param cause Cause
     */
    public MalformedPointException(String message, Throwable cause) {
        super(message, cause);
    }

}
