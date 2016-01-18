package jumpup.imi.fb4.htw.de.jumpupandroid.login;

import android.os.AsyncTask;
import android.util.Log;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;

/**
 * Project: jumpup_android
 *
 * This tasks takes two arguments (username/eMail and password), triggers an HTTP request to check
 * the authentication state and responds with a message or similar.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 13.01.2016
 */

class LoginTask extends AsyncTask<String, Void, Void> {
    private static final String TAG = LoginTask.class.getName();

    private final LoginRequest loginRequest = new LoginRequest();
    private boolean hasError = false;
    private String username;
    private String password;

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

    private boolean validate(String[] strings) {
        if (strings.length != 2) {
            return false;
        }

        this.username = strings[0];
        this.password = strings[1];

        return true;
    }

    private void triggerLoginByHttpRequest() {
        User user = loginRequest.triggerLogin(this.username, this.password);

        Log.v(TAG, "triggerLoginByHttpRequest(): successfully received user: " + user);
    }
}
