package jumpup.imi.fb4.htw.de.jumpupandroid.login;

import android.app.Activity;

import jumpup.imi.fb4.htw.de.jumpupandroid.MainActivity;
import jumpup.imi.fb4.htw.de.jumpupandroid.login.request.LoginRequest;
import jumpup.imi.fb4.htw.de.jumpupandroid.login.request.LoginRequestImpl;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 18.01.2016
 */
public class LoginFactory {

    public static LoginRequest newLoginRequest() {
        return new LoginRequestImpl();
    }

    public static LoginTask newLoginTask(MainActivity activity) {
        LoginTask loginTask = new LoginTask();

        loginTask.getObservable().addObserver(activity);

        return loginTask;
    }
}
