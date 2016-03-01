package jumpup.imi.fb4.htw.de.jumpupandroid.util.location.geocoding.adapter.google;

import android.location.Address;
import android.util.Log;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import jumpup.imi.fb4.htw.de.jumpupandroid.App;
import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.ErrorResponseException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.TechnicalErrorException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.MapperFactory;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.response.ResponseReader;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.response.ResponseReaderImpl;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 01.03.2016
 */
public class GoogleGeocodingRequest {
    /**
     * Google Geocoding endpoint URL.
     */
    private static final String ENDPOINT_URL = "https://maps.googleapis.com/maps/api/geocode/%1$2s?address=%2$2s&key=%3$2s";
    /**
     * Expected response format.
     */
    private static final String FORMAT = "json";
    /**
     * Google Maps API key.
     */
    private static final String API_KEY = App.getStringFromResourceId(R.string.google_maps_key);
    private static final String TAG = GoogleGeocodingRequest.class.getName();

    private final GoogleGeocodingResponseAddressListMapper googleGeocodingResponseAddressListMapper = MapperFactory.newGoogleGeocodingResponseAddressListMapper();

    protected ResponseReader responseReader = newResponseReader();

    private ResponseReaderImpl newResponseReader() {
        ResponseReaderImpl responseReader = new ResponseReaderImpl();
        responseReader.setDefaultErrorMessageId(R.string.google_geocoding_request_error);
        return responseReader;
    }

    private URL buildUrl(String address) throws MalformedURLException {
        return new URL(
                String.format(ENDPOINT_URL, FORMAT, address, API_KEY)
        );
    }

    /**
     * Trigger the request to the Google directions service.
     *
     * On success, return the complete response String in the JSON format, see. https://developers.google.com/maps/documentation/geocoding/intro
     * @param address the input address string, e.g. "Musterstraße 10, Berlin" that you need geocoding information for. Typically a user input.
     * @return List of addresses
     */
    public List<Address> execute(String address) throws Exception {
        HttpURLConnection urlConnection = null;

        try {
            URL geocodingUrl = buildUrl(address);

            try {
                urlConnection = (HttpURLConnection) geocodingUrl.openConnection();
                urlConnection.connect();

                String response = responseReader.read(urlConnection);

                Log.d(TAG, "execute(): got response: " + response);

                return googleGeocodingResponseAddressListMapper.mapResponse(response);
            } catch (IOException ioException) {
                Log.e(TAG, "execute(): could not execute Google geocoding request. Exception during response buffering: " + ioException.getMessage()
                        + "\nStack trace:\n" + ExceptionUtils.getStackTrace(ioException));

                throw this.newTechnicalErrorException(ioException, R.string.google_geocoding_request_error);
            } catch (ErrorResponseException e) {
                Log.e(TAG, "execute(): Google geocoding request failed: " + e.getMessage());

                throw this.newTechnicalErrorException(e, R.string.google_geocoding_request_error);
            } catch (JSONException e) {
                Log.e(TAG, "execute(): Google geocoding JSON evaluation failed: " + e.getMessage());

                throw this.newTechnicalErrorException(e, R.string.google_geocoding_request_error);
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, "execute(): Google geocoding request. MalformedUrlException: "
                    + e.getMessage() + "\nStack trace:\n" + ExceptionUtils.getStackTrace(e));

            throw this.newTechnicalErrorException(e, R.string.google_geocoding_request_error);
        } finally {
            if (null != urlConnection) {
                try {
                    urlConnection.disconnect();
                } catch (Exception e) { // ignore }
                }
            }
        }
    }

    private Exception newTechnicalErrorException(Exception e, int messageIdRequestErrorNumber) {
        return new TechnicalErrorException(e.getMessage(), messageIdRequestErrorNumber);
    }
}
