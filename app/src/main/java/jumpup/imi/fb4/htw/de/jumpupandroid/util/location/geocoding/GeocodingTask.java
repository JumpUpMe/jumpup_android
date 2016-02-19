package jumpup.imi.fb4.htw.de.jumpupandroid.util.location.geocoding;

import android.location.Address;
import android.util.Log;

import java.util.List;

import jumpup.imi.fb4.htw.de.jumpupandroid.util.location.geocoding.adapter.GeocodingAdapter;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.task.ObservableAsyncTask;

/**
 * Project: jumpup_android
 * <p/>
 * Global geocoding task which uses an geocoding adapter.
 *
 * Takes a location entered by a user and resolves the geocoding information such as coordinates.
 *
 * After the task is finished, call getResolvedAddresses() to get the enriched geocoding info.
 *
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.02.2016
 */
public class GeocodingTask extends ObservableAsyncTask<String, Void, Void> {
    private static final String TAG = GeocodingTask.class.getName();
    private String locationString;
    private GeocodingAdapter geocodingAdapter = GeocodingFactory.newAndroidGeocodingAdapter();
    private List<Address> resolvedAdresses;
    private boolean hasError = true;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected Void doInBackground(String... locationStrings) {
        if (!this.validate(locationStrings)) {
            Log.e(TAG, "doInBackground(): invalid parameters given. Make sure to hand in a single string instance.");
        } else {
            this.searchGeoLocation();
        }

        return null;
    }

    private boolean validate(String... locationStrings) {
        if (locationStrings == null || locationStrings.length != 1) {
            return false;
        }

        this.locationString = locationStrings[0];
        return true;
    }

    private void searchGeoLocation() {
        this.resolvedAdresses = geocodingAdapter.resolveLocationName(this.locationString);

        Log.d(TAG, "searchGeoLocation(): geocoding results for input " + locationString + ": \n" + this.resolvedAdresses);

        if (0 != this.resolvedAdresses.size()) {
            this.hasError = false;
        }
    }

    /**
     * Get the resolved addresses.
     * The observer should call this method after the task was finished (so the observers update() method was called).
     * @return the list of resolved addresses
     */
    public List<Address> getResolvedAdresses() {
        return resolvedAdresses;
    }

    /**
     * Check whether an error occured. This might be a technical error or the location couldn't be mapped to any geocoding information.
     * @return boolean true if an error occured
     */
    public boolean isHasError() {
        return hasError;
    }

    /**
     * Get the input location string.
     * @return the string that was given to the execute() method.
     */
    public String getInputLocationString() {
        return locationString;
    }
}
