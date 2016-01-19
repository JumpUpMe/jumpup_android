package jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception;

import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.response.ErrorResponse;

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
    private final String errorResponseString;
    private final ErrorResponse errorResponse;
    private final int userMessageId;

    /**
     *
     * @param responseStatusCode the HTTP status code of the response
     * @param errorResponseString the raw error response as returned by the WebService
     * @param errorResponse the built ErrorResponse object
     * @param defaultUserMessageId the ID of the default user message to be shown (e.g. if the webservice error response shouldn't be presented to the user)
     */
    public ErrorResponseException(int responseStatusCode, String errorResponseString, ErrorResponse errorResponse, int defaultUserMessageId) {
        super();

        this.responseStatusCode = responseStatusCode;
        this.errorResponseString = errorResponseString;
        this.userMessageId = defaultUserMessageId;
        this.errorResponse = errorResponse;
    }

    @Override
    public String getMessage() {
        return "Got response: Status code " + responseStatusCode + ". Response: " + errorResponseString;
    }

    @Override
    public String getLocalizedMessage() {
        return getMessage();
    }

    public int getResponseStatusCode() {
        return responseStatusCode;
    }

    public String getErrorResponseString() {
        return errorResponseString;
    }

    public ErrorResponse getErrorResponseObject() {
        return errorResponse;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorResponseException{");
        sb.append("responseStatusCode=").append(responseStatusCode);
        sb.append(", errorResponseString='").append(errorResponseString).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public int getUserMessageId() {
        return this.userMessageId;
    }
}
