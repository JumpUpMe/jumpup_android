package jumpup.imi.fb4.htw.de.jumpupandroid.util.activity;

import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

/**
 * Project: jumpup_android
 * <p/>
 * Abstract class for all JumpUp android app activites.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.01.2016
 */
public class JumpUpActivity extends ActionBarActivity {
    protected void showSuccessNotification(String string) {
        Toast toast = Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT);
        toast.show();
    }

    protected void showErrorNotification(String string) {
        Toast toast = Toast.makeText(getApplicationContext(), string, Toast.LENGTH_LONG);
        toast.show();
    }

    protected void showValidationFailureNotification(String validationFailureField, String[] validationFailureErrorMessages) {
        for (String validationFailure : validationFailureErrorMessages) {
            Toast toast = Toast.makeText(getApplicationContext(), validationFailureField + ": " + validationFailure, Toast.LENGTH_LONG);
            toast.show();
        }
    }
}

