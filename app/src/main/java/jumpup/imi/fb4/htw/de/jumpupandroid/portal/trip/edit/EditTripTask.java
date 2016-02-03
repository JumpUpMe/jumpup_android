package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.edit;

import android.util.Log;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.TripFactory;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.edit.request.EditTripRequest;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.task.ObservableAsyncTask;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.ErrorResponseException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.TechnicalErrorException;

/**
 * Project: jumpup_android
 * <p/>
 * Task to edit a single trip.
 * The task will use the underlying subsystem (JumpUp Web Service) to store the updated trip.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 03.02.2016
 */
public class EditTripTask extends ObservableAsyncTask<Trip, Void, Void> {
    private static final String TAG = EditTripTask.class.getName();

    private boolean hasError = false;
    private int toastMessageId;
    private boolean hasValidationError = false;
    private String validationFailureField;
    private String[] validationFailureErrorMessages;
    private Trip trip;
    private EditTripRequest editTripRequest = TripFactory.newEditTripRequest();
    private User user;

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

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected Void doInBackground(Trip... trips) {
        if (!this.validate(trips)) {
            Log.e(TAG, "doInBackground(): invalid parameters given. Make sure to hand in a single Trip instance and have set the user.");
        } else {
            this.editTrip();
        }

        return null;
    }

    private boolean validate(Trip[] trips) {
        if (trips == null || trips.length != 1 || user == null) {
            return false;
        }

        this.trip = trips[0];

        return true;
    }

    private void editTrip() {
        try {
            this.editTripRequest.setUser(user);
            this.editTripRequest.storeTrip(trip);

            Log.d(TAG, "editTrip(): edit trip task was successful.");
        } catch (TechnicalErrorException e) {
            this.hasError = true;
            this.toastMessageId = e.getUserMessageId();
        } catch (ErrorResponseException e) {
            if (e.getErrorResponseObject().isValidationFailure()) {
                this.hasValidationError = true;
                this.validationFailureField = e.getErrorResponseObject().getFailedValidationFieldName();
                this.validationFailureErrorMessages = e.getErrorResponseObject().getErrorMessages();
            } else {
                this.hasError = true;
                this.toastMessageId = e.getUserMessageId();
            }
        }
    }
}
