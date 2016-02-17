package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.list.request;

import android.util.Log;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.TripList;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.preferences.PreferencesUtil;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.ErrorResponseException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.TechnicalErrorException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.JsonMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.MapperFactory;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.request.JumpUpRequest;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.request.Versions;

/**
 * Project: jumpup_android
 * <p/>
 * Implementation to load user's offered trips via WebService.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 02.02.2016
 */
public class TripListRequestImpl extends JumpUpRequest implements TripListRequest {
    private static final String TARGET_VERSION = Versions.V1_0_0.getUrlPart();
    private static final String ENDPOINT_URL = "trip";
    private static final String TAG = TripListRequestImpl.class.getName();

    @Override
    protected String getTargetVersion() {
        return TARGET_VERSION;
    }

    @Override
    protected String getEndpointUrl() {
        return ENDPOINT_URL + this.buildPaginationParameters();
    }

    private String buildPaginationParameters() {
        return "?limit=" + PreferencesUtil.readNumberOfTripsPreference();
    }

    @Override
    protected boolean isPublicAction() {
        return false;
    }

    @Override
    protected JsonMapper getResponseMapper() {
        return MapperFactory.newTripListMapper();
    }

    @Override
    protected int getDefaultErrorMessageId() {
        return R.string.jumpup_request_error_trip_list_load_failed;
    }

    @Override
    public TripList loadUsersTrips(User user) throws TechnicalErrorException, ErrorResponseException {
        this.setCredentialsFromUser(user);

        HttpURLConnection urlConnection = null;

        try {
            URL loadUsersTripsUrl = new URL(this.getUrl());

            try {
                urlConnection = buildGetConnection(loadUsersTripsUrl);
                urlConnection.connect();

                String response = responseReader.read(urlConnection);

                Log.d(TAG, "loadUsersTrips(): got response: " + response);

                return (TripList) (getResponseMapper().mapResponse(response));
            } catch (IOException ioException) {
                Log.e(TAG, "loadUsersTrips(): could not load user's trips. Exception during response buffering: " + ioException.getMessage()
                        + "\nStack trace:\n" + ExceptionUtils.getStackTrace(ioException));

                throw this.newTechnicalErrorException(ioException, MESSAGE_ID_REQUEST_ERROR_IO);
            } catch (ErrorResponseException e) {
                Log.e(TAG, "loadUsersTrips(): load user's trips failed: " + e.getMessage());

                throw e;
            } catch (JSONException | ClassCastException e) {
                Log.e(TAG, "loadUsersTrips(): could not create JSON:" + e.getMessage()
                        + "\nStack trace:\n" + ExceptionUtils.getStackTrace(e));

                throw this.newTechnicalErrorException(e, MESSAGE_ID_REQUEST_ERROR_JSON);
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, "loadUsersTrips(): could not load user's trips. MalformedUrlException: "
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
