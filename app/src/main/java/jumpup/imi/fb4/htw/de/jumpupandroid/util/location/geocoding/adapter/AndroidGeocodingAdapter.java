package jumpup.imi.fb4.htw.de.jumpupandroid.util.location.geocoding.adapter;

import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jumpup.imi.fb4.htw.de.jumpupandroid.util.location.geocoding.GeocodingFactory;

/**
 * Project: jumpup_android
 * <p/>
 * Adapter to Google's Geocoding service that can be used via the Geocder class.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.02.2016
 */
public class AndroidGeocodingAdapter implements GeocodingAdapter {
    private static final Geocoder geocoder = GeocodingFactory.newGoogleGeocoder();
    private static final int MAX_RESULTS = 10;
    private static final String TAG = AndroidGeocodingAdapter.class.getName();

    @Override
    public List<Address> resolveLocationName(String locationName) {
        try {
            return geocoder.getFromLocationName(locationName, MAX_RESULTS);
        } catch (IOException e) {
            Log.e(TAG, "resolveLocationName(): exception during geocoding. Stack trace:\n " + ExceptionUtils.getStackTrace(e));
            return new ArrayList<>();
        }
    }
}
