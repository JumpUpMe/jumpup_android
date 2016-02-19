package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.search.request;

import android.util.Log;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.entity.TripSearchCriteria;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search.TripQueryResults;
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
 * @since 19.02.2016
 */
public class TripSearchRequestImpl extends JumpUpRequest implements TripSearchRequest {
    private static final String TARGET_VERSION = Versions.V1_0_0.getUrlPart();
    private static final String ENDPOINT_URL = "searchtrips";
    private static final String TAG = TripSearchRequestImpl.class.getName();

    @Override
    protected String getTargetVersion() {
        return TARGET_VERSION;
    }

    @Override
    protected String getEndpointUrl() {
        return ENDPOINT_URL;
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
        return R.string.jumpup_request_error_trip_search_failed;
    }

    @Override
    public TripQueryResults searchTrips(TripSearchCriteria tripSearchCriteria) throws TechnicalErrorException, ErrorResponseException {
        HttpURLConnection urlConnection = null;

        try {
            URL searchForTrips = new URL(this.getUrl());

            try {
                urlConnection = buildPostConnection(searchForTrips, getResponseMapper().marshalEntity(tripSearchCriteria));
                urlConnection.connect();

                String response = responseReader.read(urlConnection);

                Log.d(TAG, "loadUsersTrips(): got response: " + response);

                return (TripQueryResults) (getResponseMapper().mapResponse(response));
            } catch (IOException ioException) {
                Log.e(TAG, "searchTrips(): could not search for trips. Exception during response buffering: " + ioException.getMessage()
                        + "\nStack trace:\n" + ExceptionUtils.getStackTrace(ioException));

                throw this.newTechnicalErrorException(ioException, MESSAGE_ID_REQUEST_ERROR_IO);
            } catch (ErrorResponseException e) {
                Log.e(TAG, "searchTrips(): search for trips failed: " + e.getMessage());

                throw e;
            } catch (JSONException | ClassCastException e) {
                Log.e(TAG, "searchTrips(): could not create JSON:" + e.getMessage()
                        + "\nStack trace:\n" + ExceptionUtils.getStackTrace(e));

                throw this.newTechnicalErrorException(e, MESSAGE_ID_REQUEST_ERROR_JSON);
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, "searchTrips(): could not search for trips. MalformedUrlException: "
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
