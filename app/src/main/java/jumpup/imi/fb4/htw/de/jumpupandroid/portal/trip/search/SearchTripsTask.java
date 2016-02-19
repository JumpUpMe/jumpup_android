package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.search;

import android.util.Log;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.TripSearchCriteria;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.task.ObservableAsyncTask;

/**
 * Project: jumpup_android
 * <p/>
 * This task triggers the search for trips offered by drivers done by a passenger.
 *
 * It connects the activity and the underlying web service.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.02.2016
 */
public class SearchTripsTask extends ObservableAsyncTask<TripSearchCriteria, Void, Void> {
    private static final String TAG = SearchTripsTask.class.getName();
    private TripSearchCriteria tripSearchCriteria;

    private boolean hasError = false;
    private int toastMessageId;
    private boolean hasValidationError = false;
    private String validationFailureField;
    private String[] validationFailureErrorMessages;

    public boolean isHasError() {
        return hasError;
    }

    public boolean isHasValidationError() {
        return hasValidationError;
    }

    public int getToastMessageId() {
        return toastMessageId;
    }

    public String getValidationFailureField() {
        return validationFailureField;
    }

    public String[] getValidationFailureErrorMessages() {
        return validationFailureErrorMessages;
    }


    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected Void doInBackground(TripSearchCriteria... tripSearchCriterias) {
        if (!this.validate(tripSearchCriterias)) {
            Log.e(TAG, "doInBackground(): invalid parameters given. Make sure to hand in a single user instance.");
        } else {
            this.searchForTrips();
        }

        return null;
    }

    private void searchForTrips() {

    }

    private boolean validate(TripSearchCriteria[] tripSearchCriterias) {
        if (tripSearchCriterias == null || tripSearchCriterias.length != 1) {
            return false;
        }

        this.tripSearchCriteria = tripSearchCriterias[0];
        return true;
    }
}
