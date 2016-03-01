package jumpup.imi.fb4.htw.de.jumpupandroid.util.location.geocoding.adapter.google;

import android.location.Address;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import jumpup.imi.fb4.htw.de.jumpupandroid.util.location.geocoding.GeocodingFactory;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.location.geocoding.adapter.GeocodingAdapter;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.TechnicalErrorException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.MapperFactory;

/**
 * Project: jumpup_android
 * <p/>
 * Fallback implementation to use Googles geocoding Service via HTTP directly.
 * <p/>
 * See: https://developers.google.com/maps/documentation/geocoding/intro
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 01.03.2016
 */
public class GoogleGeocodingWebServiceAdapter implements GeocodingAdapter {
    private final GoogleGeocodingRequest request = GeocodingFactory.newGoogleGeocodingRequest();


    @Override
    public List<Address> resolveLocationName(String locationName) throws TechnicalErrorException {
        return request.execute(locationName);
    }
}
