package jumpup.imi.fb4.htw.de.jumpupandroid.util.location.geocoding.adapter;

import android.location.Address;

import java.util.List;

import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.TechnicalErrorException;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.02.2016
 */
public interface GeocodingAdapter {

    /**
     * Resolve the given location name as filled in by a user.
     * @param locationName as filled in by a user.
     * @return a list of addresses - there might be several potential matches. An empty list if no match can be found.
     * @throws TechnicalErrorException on technical errors within the underlying subsystem
     */
    List<Address> resolveLocationName(String locationName) throws TechnicalErrorException;
}
