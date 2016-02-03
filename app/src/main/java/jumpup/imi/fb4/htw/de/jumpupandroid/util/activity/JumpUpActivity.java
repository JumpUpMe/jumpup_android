package jumpup.imi.fb4.htw.de.jumpupandroid.util.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.Toast;

import jumpup.imi.fb4.htw.de.jumpupandroid.util.ViewHelper;

/**
 * Project: jumpup_android
 * <p/>
 * Abstract class for all JumpUp android app activites.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.01.2016
 */
public abstract class JumpUpActivity extends ActionBarActivity {
    public static final String EXTRA_PARCELABLE = "extra_parcelable";

    /**
     * Get tag for logger instance.
     *
     * @return the TAG used for logging
     */
    protected abstract String getTag();

    /**
     * Get the task that each activity should have a 1:1 relation to.
     *
     * If this activity doesn't have a task, return null.
     *
     * @return null or the task that this activity is related to
     */
    protected abstract AsyncTask getTask();


    @Override
    protected void onStop() {
        this.stopTaskIfRelated();

        super.onStop();
    }

    private void stopTaskIfRelated() {
        if (null != this.getTask()) {
            // force the task thread to be interrupted
            this.getTask().cancel(true);
        }
    }

    protected void showSuccessNotification(String string) {
        Toast toast = Toast.makeText(getApplicationContext(), string, Toast.LENGTH_LONG);
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

    protected void addClickListenerToEmptyInputFieldsOnClick(final EditText edInput) {
        ViewHelper.addClickListenerToEmptyInputFieldsOnClick(edInput);
    }

    protected void navigateToWithParcel(Class expliciteClass, Parcelable parcelable) {
        Intent intent = buildParcelIntent(expliciteClass, parcelable);

        startActivity(intent);
    }

    protected void navigateTo(Class expliciteClass) {
        Intent intent = getExpliciteIntent(expliciteClass);
        startActivity(intent);
    }

    @NonNull
    private Intent buildParcelIntent(Class expliciteClass, Parcelable parcelable) {
        Intent intent = getExpliciteIntent(expliciteClass);
        putExtraParcelable(parcelable, intent);
        return intent;
    }

    private void putExtraParcelable(Parcelable parcelable, Intent intent) {
        intent.putExtra(EXTRA_PARCELABLE, parcelable);
    }

    @NonNull
    private Intent getExpliciteIntent(Class expliciteClass) {
        return new Intent(this, expliciteClass);
    }

    protected void navigateToWithParcelAndClearActivityStack(Class expliciteClass, Parcelable parcelable) {
        Intent intent = buildParcelIntent(expliciteClass, parcelable);
        setClearIntentFlags(intent);

        startActivity(intent);
    }

    protected void navigateToAndClearActivityStack(Class expliciteClass) {
        Intent intent = getExpliciteIntent(expliciteClass);
        setClearIntentFlags(intent);

        startActivity(intent);
    }

    private void setClearIntentFlags(Intent intent) {
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    }
}
