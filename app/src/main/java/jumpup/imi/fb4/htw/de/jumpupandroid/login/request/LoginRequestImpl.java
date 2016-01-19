package jumpup.imi.fb4.htw.de.jumpupandroid.login.request;

import android.net.Uri;
import android.util.Log;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;
import jumpup.imi.fb4.htw.de.jumpupandroid.entity.mapper.JsonMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.entity.mapper.MapperFactory;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.ErrorResponseException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.TechnicalErrorException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.request.Endpoints;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.request.JumpUpRequest;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.response.ResponseReader;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.response.ResponseReaderImpl;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.request.Versions;

/**
 * Project: jumpup_android
 * <p/>
 * This class models a LoginRequestImpl that is sent to the JumpUp mobile REST service.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since ${date}
 */
public class LoginRequestImpl extends JumpUpRequest implements LoginRequest {
    private final String TAG = LoginRequestImpl.class.getName();
    private final String TARGET_VERSION = Versions.V1_0_0.getUrlPart();
    @SuppressWarnings("FieldCanBeLocal")
    private final String ENDPOINT = Endpoints.USER;

    @Override
    protected boolean isPublicAction() {
        return false;
    }

    @Override
    protected String getTargetVersion() {
        return TARGET_VERSION;
    }

    @Override
    protected String getEndpointUrl() {
        return ENDPOINT;
    }

    @Override
    protected JsonMapper getResponseMapper() {
        return MapperFactory.newUserMapper();
    }

    /**
     * Trigger the login.
     * @param username the username
     * @param password the password
     * @return UserMapper or null on connection or response handling errors
     */
    @Override
    public User triggerLogin(String username, String password) throws TechnicalErrorException, ErrorResponseException {
        this.setUsername(username);
        this.setPassword(password);

        Uri loginUri = Uri.parse(this.getUrl());
        HttpURLConnection urlConnection = null;

        try {
            URL loginUrl = new URL(loginUri.toString());

            try {
                urlConnection = buildConnection(loginUrl, "GET");
                urlConnection.connect();

                String response = responseReader.read(urlConnection);

                Log.d(TAG, "triggerLogin(): got response: " + response);
                return (User) mapResponse(response);
            } catch (IOException responseException) {
                Log.v(TAG, "triggerLogin(): could not login user. Exception during response buffering: " + responseException.getMessage()
                        + "\nStack trace:\n" + ExceptionUtils.getStackTrace(responseException));

                throw this.newTechnicalErrorException(responseException, MESSAGE_ID_REQUEST_ERROR_IO);
            } catch (JSONException e) {
                Log.e(TAG, "triggerLogin(): could not login user. Got an JSONException: " + e.getMessage()
                        + "\nStack trace:\n" + ExceptionUtils.getStackTrace(e));

                throw this.newTechnicalErrorException(e, MESSAGE_ID_REQUEST_ERROR_JSON);
            } catch (ErrorResponseException e) {
                Log.v(TAG, "triggerLogin(): login failed: " + e.getMessage());

                throw e;
            } finally {
                if (null != urlConnection) {  try { urlConnection.disconnect(); } catch (Exception e2) { /* ignore */ } }
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, "triggerLogin(): could not login user. MalformedUrlException: "
                    + e.getMessage() + "\nStack trace:\n" + ExceptionUtils.getStackTrace(e));

            throw this.newTechnicalErrorException(e, MESSAGE_ID_REQUEST_ERROR_URL);
        }
    }
}
