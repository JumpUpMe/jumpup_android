package jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception;

/**
 * Project: jumpup_android
 * <p/>
 * Technical error exceptions.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 18.01.2016
 */
public class TechnicalErrorException extends Exception {
    private final int userMessageId;

    public TechnicalErrorException(String exceptionMessage, int userMessageId) {
        super(exceptionMessage);

        this.userMessageId = userMessageId;
    }

    public int getUserMessageId() {
        return userMessageId;
    }
}

