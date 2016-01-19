package jumpup.imi.fb4.htw.de.jumpupandroid.registration;

import android.util.Log;

import jumpup.imi.fb4.htw.de.jumpupandroid.registration.entity.Registration;
import jumpup.imi.fb4.htw.de.jumpupandroid.registration.request.RegistrationRequest;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.task.ObservableAsyncTask;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.ErrorResponseException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.TechnicalErrorException;

/**
 * Project: jumpup_android
 * <p/>
 * A RegistrationTasks implements an AsyncTask so that it can be run in the background.
 * It takes the registration arguments and delegates to the JumpUpRequest implementation.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.01.2016
 */
public class RegistrationTask extends ObservableAsyncTask<Registration, Void, Void> {
    private static final String TAG = RegistrationTask.class.getName();

    private final RegistrationRequest registrationRequest = RegistrationFactory.newRegistrationRequest();

    private Registration registrationEntity;
    private boolean hasError = false;
    private int toastMessageId;
    private boolean hasValidationError;
    private String validationFailureField;
    private String[] validationFailureErrorMessages;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected Void doInBackground(Registration... registrationModels) {
        if (!this.validate(registrationModels)) {
            Log.e(TAG, "doInBackground(): invalid parameters given. Make sure to hand in a single registration model.");
        } else {
            this.triggerRegistrationByHttpRequest();
        }

        return null;
    }

    private boolean validate(Registration[] registrationModels) {
        if (registrationModels.length != 1) {
            return false;
        }

        // TODO validate registration model
        return true;
    }

    private void triggerRegistrationByHttpRequest() {
        try {
            registrationRequest.registerUser(registrationEntity);

            Log.d(TAG, "triggerRegistrationByHttpRequest(): successfully registered user");
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

    public boolean isHasError() {
        return hasError;
    }

    public int getToastMessageId() {
        return toastMessageId;
    }

    public boolean isHasValidationError() {
        return hasValidationError;
    }

    public String getValidationFailureField() {
        return validationFailureField;
    }

    public String[] getValidationFailureErrorMessages() {
        return validationFailureErrorMessages;
    }
}
