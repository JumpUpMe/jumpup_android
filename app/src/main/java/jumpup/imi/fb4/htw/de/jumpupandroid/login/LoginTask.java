package jumpup.imi.fb4.htw.de.jumpupandroid.login;

import android.util.Log;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;
import jumpup.imi.fb4.htw.de.jumpupandroid.login.request.LoginRequest;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.ErrorResponseException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.TechnicalErrorException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.task.ObservableAsyncTask;

/**
 * Project: jumpup_android
 *
 * This tasks takes two arguments (username/eMail and password), triggers an HTTP request to check
 * the authentication state and responds with a message or similar.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 13.01.2016
 */

public class LoginTask extends ObservableAsyncTask<String, Void, Void> {
    private static final String TAG = LoginTask.class.getName();

    private final LoginRequest loginRequest = LoginFactory.newLoginRequest();

    private String username;
    private String password;
    private boolean hasError = false;
    private int toastMessageId;
    private User user;

    @Override
    /**
     * Start the login task by handing in two parameters in the following order:
     * @param strings 1. the username or eMail 2. the password
     */
    protected Void doInBackground(String... strings) {
        if (!this.validate(strings)) {
            Log.e(TAG, "doInBackground(): invalid parameters given. Make sure to hand in two string parameters");
        } else {
            this.triggerLoginByHttpRequest();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        // notify observers about the completion of the task
        Log.v(TAG, "onPostExecute(): notifying " + this.getObservable().countObservers() + " observers...");

        this.triggerChangedAndNotifyObservers(this);
    }

    private boolean validate(String[] strings) {
        if (strings.length != 2) {
            return false;
        }

        this.username = strings[0];
        this.password = strings[1];

        return true;
    }

    private void triggerLoginByHttpRequest() {
        try {
            this.user = loginRequest.triggerLogin(this.username, this.password);

            Log.v(TAG, "triggerLoginByHttpRequest(): successfully received user: " + user);

            this.user.setPassword(this.password);
        } catch (TechnicalErrorException e) {
            this.hasError = true;
            this.toastMessageId = e.getUserMessageId();
        } catch (ErrorResponseException e) {
            this.hasError = true;
            this.toastMessageId = e.getUserMessageId();
        }
    }

    public boolean isHasError() {
        return hasError;
    }

    public int getToastMessageId() {
        return toastMessageId;
    }
}
