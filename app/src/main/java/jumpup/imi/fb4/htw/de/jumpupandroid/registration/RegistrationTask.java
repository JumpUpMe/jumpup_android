package jumpup.imi.fb4.htw.de.jumpupandroid.registration;

import android.util.Log;

import jumpup.imi.fb4.htw.de.jumpupandroid.registration.entity.Registration;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.task.ObservableAsyncTask;

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

    private Registration registrationEntity;

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
    }
}
