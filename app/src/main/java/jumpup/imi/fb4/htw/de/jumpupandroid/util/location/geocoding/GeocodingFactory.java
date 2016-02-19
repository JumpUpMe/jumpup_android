package jumpup.imi.fb4.htw.de.jumpupandroid.util.location.geocoding;

import android.location.Geocoder;

import java.util.Observer;

import jumpup.imi.fb4.htw.de.jumpupandroid.App;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.search.SearchTripsActivity;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.location.geocoding.adapter.AndroidGeocodingAdapter;

/**
 * Project: jumpup_android
 * <p/>
 * Geocoding factory.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.02.2016
 */
public class GeocodingFactory {

    public static Geocoder newGoogleGeocoder() {
        return new Geocoder(App.getGlobalContext());
    }

    public static AndroidGeocodingAdapter newAndroidGeocodingAdapter() {
        return new AndroidGeocodingAdapter();
    }

    public static GeocodingTask newGeocodingTask(Observer observer) {
        GeocodingTask task = new GeocodingTask();
        task.getObservable().addObserver(observer);

        return task;
    }
}
