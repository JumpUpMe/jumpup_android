package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.edit.request;

import android.util.Log;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.ErrorResponseException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.TechnicalErrorException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.JsonMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.MapperFactory;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.request.JumpUpRequest;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.request.Versions;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 03.02.2016
 */
public class EditTripRequestImpl extends JumpUpRequest implements EditTripRequest {
    private static final String TARGET_VERSION = Versions.V1_0_0.getUrlPart();
    private static final String ENDPOINT_URL = "trip";
    private static final String TAG = EditTripRequestImpl.class.getName();

    private Trip trip;

    @Override
    protected String getTargetVersion() {
        return TARGET_VERSION;
    }

    @Override
    protected String getEndpointUrl() {
        return ENDPOINT_URL + "/" + this.trip.getIdentity();
    }

    @Override
    protected boolean isPublicAction() {
        return false;
    }

    @Override
    protected JsonMapper getResponseMapper() {
        return MapperFactory.newTripMapper();
    }

    @Override
    protected int getDefaultErrorMessageId() {
        return R.string.jumpup_request_error_update_profile_failed;
    }

    @Override
    public void setUser(User user) {
        this.setCredentialsFromUser(user);
    }

    @Override
    public boolean storeTrip(Trip trip) throws TechnicalErrorException, ErrorResponseException {
        this.trip = trip;

        HttpURLConnection urlConnection = null;

        try {
            URL editTripUrl = new URL(this.getUrl());

            try {
                urlConnection = buildPutConnection(editTripUrl, getResponseMapper().marshalEntity(trip));
                urlConnection.connect();

                String response = responseReader.read(urlConnection);

                Log.d(TAG, "storeTrip(): got response: " + response);

                return true;
            } catch (IOException ioException) {
                Log.e(TAG, "storeTrip(): could not update trip. Exception during response buffering: " + ioException.getMessage()
                        + "\nStack trace:\n" + ExceptionUtils.getStackTrace(ioException));

                throw this.newTechnicalErrorException(ioException, MESSAGE_ID_REQUEST_ERROR_IO);
            } catch (ErrorResponseException e) {
                Log.e(TAG, "storeTrip(): update trip failed: " + e.getMessage());

                throw e;
            } catch (JSONException e) {
                Log.e(TAG, "storeTrip(): could not create JSON:" + e.getMessage()
                        + "\nStack trace:\n" + ExceptionUtils.getStackTrace(e));

                throw this.newTechnicalErrorException(e, MESSAGE_ID_REQUEST_ERROR_JSON);
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, "storeTrip(): could not update trip. MalformedUrlException: "
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
