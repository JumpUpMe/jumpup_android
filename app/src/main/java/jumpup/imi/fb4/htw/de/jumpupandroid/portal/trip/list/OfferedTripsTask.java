package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.list;

import android.util.Log;

import java.util.Observer;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.profile.ProfileFactory;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.profile.request.ProfileRequest;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.TripFactory;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.TripList;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.list.request.TripListRequest;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.task.ObservableAsyncTask;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.ErrorResponseException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.TechnicalErrorException;

/**
 * Project: jumpup_android
 * <p/>
 * This tasks takes a user entity and loads all the offered trips that were offered by him/her using an
 * underlying subsystem (e.g. JumpUp web service).
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 02.02.2016
 */
public class OfferedTripsTask extends ObservableAsyncTask<User, Void, Void> {
    private static final String TAG = OfferedTripsTask.class.getName();
    private User user;
    private TripListRequest tripListRequest = TripFactory.newTripListRequest();

    private boolean hasError = false;
    private int toastMessageId;
    private boolean hasValidationError = false;
    private String validationFailureField;
    private String[] validationFailureErrorMessages;
    private TripList offeredTrips;

    public OfferedTripsTask(Observer observer) {
        super(observer);
    }

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
    protected Void doInBackground(User... users) {
        if (!this.validate(users)) {
            Log.e(TAG, "doInBackground(): invalid parameters given. Make sure to hand in a single user instance.");
        } else {
            this.loadUsersOfferedTrips();
        }

        return null;
    }

    private void loadUsersOfferedTrips() {
        try {
            this.offeredTrips = this.tripListRequest.loadUsersTrips(user);

            Log.d(TAG, "loadUsersOfferedTrips(): load users offered trips task was successful.");
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

    private boolean validate(User[] users) {
        if (users == null || users.length != 1) {
            return false;
        }

        this.user = users[0];
        return true;
    }

    public TripList getOfferedTrips() {
        return offeredTrips;
    }
}