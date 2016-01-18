package jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception;

/**
 * Project: jumpup_android
 * <p/>
 * Exception class for all error responses sent by JumpUp REST service.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 18.01.2016
 */
public class ErrorResponseException extends Exception {

    private final int responseStatusCode;
    private final String errorResponse;
    private int userMessageId;

    /**
     *
     * @param responseStatusCode the HTTP status code of the response
     * @param errorResponse the raw error response as returned by the WebService
     * @param defaultUserMessageId the ID of the default user message to be shown (e.g. if the webservice error response shouldn't be presented to the user)
     */
    public ErrorResponseException(int responseStatusCode, String errorResponse, int defaultUserMessageId) {
        super();

        this.responseStatusCode = responseStatusCode;
        this.errorResponse = errorResponse;
        this.userMessageId = defaultUserMessageId;
    }

    @Override
    public String getMessage() {
        return "Got response: Status code " + responseStatusCode + ". Response: " + errorResponse;
    }

    @Override
    public String getLocalizedMessage() {
        return getMessage();
    }

    public int getResponseStatusCode() {
        return responseStatusCode;
    }

    public String getErrorResponse() {
        return errorResponse;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorResponseException{");
        sb.append("responseStatusCode=").append(responseStatusCode);
        sb.append(", errorResponse='").append(errorResponse).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public int getUserMessageId() {
        return this.userMessageId;
    }
}
