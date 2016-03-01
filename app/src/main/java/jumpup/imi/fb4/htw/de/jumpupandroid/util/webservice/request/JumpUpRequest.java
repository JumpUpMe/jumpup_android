package jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.request;

import android.util.Base64;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import jumpup.imi.fb4.htw.de.jumpupandroid.BuildConfig;
import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.entity.AbstractEntity;
import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.TechnicalErrorException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.JsonMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.response.ResponseReader;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.response.ResponseReaderImpl;

/**
 * Project: jumpup_android
 * <p/>
 * Abstract class for all requests that are sent to the JumpUp mobile API REST service.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since ${date}
 */
public abstract class JumpUpRequest {
    public static final int MESSAGE_ID_REQUEST_ERROR_IO = R.string.jumpup_request_error_io;
    public static final int MESSAGE_ID_REQUEST_ERROR_JSON = R.string.jumpup_request_error_json;
    public static final int MESSAGE_ID_REQUEST_ERROR_URL = R.string.jumpup_request_error_malformed_url;
    private static final String BASE_URL = BuildConfig.JUMPUP_REST_BASE_URL;
    private static final String TAG = JumpUpRequest.class.getName();
    public User user;
    protected ResponseReader responseReader = newResponseReader();
    private String builtUrl;
    private String username;
    private String password;

    @SuppressWarnings("WeakerAccess")
    public void setUsername(String username) {
        this.username = username;
    }

    @SuppressWarnings("WeakerAccess")
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the targeted version URL part. E.g. "v1.0.0".
     * @return the target version URL part, e.g. "v1.0.0"
     */
    protected abstract String getTargetVersion();

    /**
     * Get the endpoint URL part of the targeted service. E.g. "user".
     * @return the endpoint URL part, e.g. "user"
     */
    protected abstract String getEndpointUrl();

    /**
     * Check whether this action is public (does not need an authentication).
     * This means, the service will be available under a "public" URL part. E.G. http://groupelite.de:8080/jumpup/rest/v1.0.0/public/user/1.
     * @return true if this action is public / doesn't require for an authentication
     */
    @SuppressWarnings("SameReturnValue")
    protected abstract boolean isPublicAction();

    /**
     * Get the mapper object which is responsible for creating an AbstractEntity by a given raw String response.
     * @return the JsonMapper instance that handles this request's responses
     */
    protected abstract JsonMapper getResponseMapper();

    /**
     * Get the base URL to the JumpUp REST service.
     * This does not include the version. Use getTargetVersion().
     * @return the base URL to the JumpUp REST endpoint. This should be defined in Gradle's build config so that different environments can be targeted (e.g. localdev, testing,...)
     */
    @SuppressWarnings("WeakerAccess")
    public String getBaseUrl()
    {
        return BASE_URL;
    }

    /**
     * Get the default error message ID to be shown if the request fails due to an ErrorResponse from the web service.
     * @return ID the message ID
     */
    protected abstract int getDefaultErrorMessageId();

    /**
     * Get the complete URL to address the JumpUp REST service.
     * @return the complete URL to call this request's JumpUp REST service endpoint. It will be built by concatenatting all URL parts.
     */
    @SuppressWarnings("WeakerAccess")
    public String getUrl()
    {
        if (null == builtUrl) {
            builtUrl = buildUrl();
        }

        return builtUrl;
    }

    private String buildUrl() {
        return getBaseUrl() + "/" + getTargetVersion() + this.addPublicPart() + "/" + getEndpointUrl();
    }

    private String addPublicPart() {
        if (isPublicAction()) {
            return "/" + "public";
        }

        return "";
    }

    protected HttpURLConnection buildConnection(URL url, @SuppressWarnings("SameParameterValue") String requestMethod) throws IOException {
        Log.v(TAG, "buildConnection(): will connect to URL " + url );

        HttpURLConnection urlConnection;
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(requestMethod);

        if (!this.isPublicAction()) {
            this.addAuthorizationHeader(urlConnection);
        }

        return urlConnection;
    }

    protected HttpURLConnection buildPostConnection(URL url, String jsonRequestBody) throws IOException {
        HttpURLConnection urlConn = buildConnection(url, "POST");
        sendJSONRequest(jsonRequestBody, urlConn);

        return urlConn;
    }

    private void sendJSONRequest(String jsonRequestBody, HttpURLConnection urlConn) throws IOException {
        urlConn.setRequestProperty("Content-Type", "application/json");

        urlConn.getOutputStream().write(jsonRequestBody.getBytes());
        urlConn.getOutputStream().close();
    }

    protected HttpURLConnection buildPutConnection(URL url, String jsonRequestBody) throws IOException {
        HttpURLConnection urlConn = buildConnection(url, "PUT");
        sendJSONRequest(jsonRequestBody, urlConn);

        Log.d(TAG, "buildPutConnection(): will send request body " + jsonRequestBody);
        return urlConn;
    }

    protected HttpURLConnection buildGetConnection(URL url) throws IOException {
        return buildConnection(url, "GET");
    }

    private void addAuthorizationHeader(HttpURLConnection urlConnection) {
        if (null == this.username || null == this.password) {
            throw new IllegalArgumentException("addAuthorizationHeader(): no public action, but you didn't set any username and/or password.");
        }

        byte[] encodedBase64 = Base64.encode((this.username + ":" + this.password).getBytes(), Base64.DEFAULT);
        String strEncodedBase64 = new String(encodedBase64);

        String authorizationHeader = "Basic " + strEncodedBase64;
        Log.d(TAG, "addAuthorizationHeader(): will add Authorization header: " + authorizationHeader);
        urlConnection.setRequestProperty("Authorization", authorizationHeader);
    }

    protected Object mapResponse(String rawResponse) throws JSONException {
        if (null == this.getResponseMapper()) {
            throw new NullPointerException("mapResponse(): getResponseMapper() returns null. Please implement the method and make sure to return a JsonMapper instance.");
        }

        return this.getResponseMapper().mapResponse(rawResponse);
    }

    protected TechnicalErrorException newTechnicalErrorException(Exception t, int messageIdRequestErrorNumber){
        return new TechnicalErrorException(t.getMessage(), messageIdRequestErrorNumber);
    }

    private ResponseReaderImpl newResponseReader() {
        ResponseReaderImpl responseReader = new ResponseReaderImpl();
        responseReader.setDefaultErrorMessageId(this.getDefaultErrorMessageId());
        return responseReader;
    }

    public void setCredentialsFromUser(User userEntity) {
        this.setUsername(userEntity.geteMail());
        this.setPassword(userEntity.getPassword());

        this.user = userEntity;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
