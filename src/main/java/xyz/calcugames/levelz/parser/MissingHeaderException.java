package xyz.calcugames.levelz.parser;

/**
 * Thrown to indicate a LevelZ Header is missing.
 */
public class MissingHeaderException extends ParseException {

    private static final long serialVersionUID = 3594456258251637068L;

    /**
     * Creates a new Missing Header Exception.
     * @param message Message
     */
    public MissingHeaderException(String message) {
        super(message);
    }

    /**
     * Creates a new Missing Header Exception.
     * @param message Message
     * @param cause Cause
     */
    public MissingHeaderException(String message, Throwable cause) {
        super(message, cause);
    }

}
