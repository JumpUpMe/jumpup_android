package jumpup.imi.fb4.htw.de.jumpupandroid.portal.profile;

import android.util.Log;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.profile.request.ProfileRequest;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.task.ObservableAsyncTask;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.ErrorResponseException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.TechnicalErrorException;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 24.01.2016
 */
public class ProfileTask extends ObservableAsyncTask<User, Void, Void> {

    private static final String TAG = ProfileTask.class.getName();
    private User user;
    private ProfileRequest profileRequest = ProfileFactory.newProfileRequest();
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
    protected Void doInBackground(User... users) {
        if (!this.validate(users)) {
            Log.e(TAG, "doInBackground(): invalid parameters given. Make sure to hand in a single user instance.");
        } else {
            this.updateProfile();
        }

        return null;
    }

    private void updateProfile() {
        try {
            this.profileRequest.storeProfile(user);

            Log.d(TAG, "updateProfile(): profile update task was successful.");
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
}
