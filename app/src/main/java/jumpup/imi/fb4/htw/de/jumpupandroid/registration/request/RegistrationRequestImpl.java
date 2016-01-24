package jumpup.imi.fb4.htw.de.jumpupandroid.registration.request;

import android.util.Log;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.registration.entity.Registration;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.ErrorResponseException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.TechnicalErrorException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.JsonMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.MapperFactory;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.request.Endpoints;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.request.JumpUpRequest;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.request.Versions;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.01.2016
 */
public class RegistrationRequestImpl extends JumpUpRequest implements RegistrationRequest {
    private final String TAG = RegistrationRequestImpl.class.getName();
    private final String TARGET_VERSION = Versions.V1_0_0.getUrlPart();
    private final String ENDPOINT = Endpoints.REGISTRATION;

    @Override
    protected boolean isPublicAction() {
        return true;
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
        return MapperFactory.newRegistrationMapper();
    }

    @Override
    protected int getDefaultErrorMessageId() {
        return R.string.jumpup_request_error_registration;
    }

    @Override
    public boolean registerUser(Registration registrationEntity) throws TechnicalErrorException, ErrorResponseException {
        HttpURLConnection urlConnection = null;

        try {
            URL registrationUrl = new URL(this.getUrl());

            try {
                urlConnection = buildPostConnection(registrationUrl, getResponseMapper().marshalEntity(registrationEntity));
                urlConnection.connect();

                String response = responseReader.read(urlConnection);

                Log.d(TAG, "registerUser(): got response: " + response);

                return true;
            } catch (IOException ioException) {
                Log.e(TAG, "registerUser(): could not register user. Exception during response buffering: " + ioException.getMessage()
                        + "\nStack trace:\n" + ExceptionUtils.getStackTrace(ioException));

                throw this.newTechnicalErrorException(ioException, MESSAGE_ID_REQUEST_ERROR_IO);
            } catch (ErrorResponseException e) {
                Log.e(TAG, "registerUser(): registration failed: " + e.getMessage());

                throw e;
            } catch (JSONException e) {
                Log.e(TAG, "registerUser(): could not create JSON:" + e.getMessage()
                        + "\nStack trace:\n" + ExceptionUtils.getStackTrace(e));

                throw this.newTechnicalErrorException(e, MESSAGE_ID_REQUEST_ERROR_JSON);
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, "registerUser(): could not register user. MalformedUrlException: "
                    + e.getMessage() + "\nStack trace:\n" + ExceptionUtils.getStackTrace(e));

            throw this.newTechnicalErrorException(e, MESSAGE_ID_REQUEST_ERROR_URL);
        } finally {
            if (null != urlConnection) {
                try {
                    urlConnection.disconnect();
                } catch (Exception e) { // ignore }
                }
            }
        }
    }
}
