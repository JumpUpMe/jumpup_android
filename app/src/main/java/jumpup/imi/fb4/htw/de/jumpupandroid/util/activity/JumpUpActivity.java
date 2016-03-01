package jumpup.imi.fb4.htw.de.jumpupandroid.util.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.view.ViewTripActivity;
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
    public static final String EXTRA_PARCELABLE_USER = "extra_parcelable_user";

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
        showSuccessNotification(getApplicationContext(), string);
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

    protected void navigateToWithUserParcel(Class expliciteClass, Parcelable parcelable) {
        Intent intent = buildUserParcelIntent(expliciteClass, parcelable);

        startActivity(intent);
    }

    protected void navigateToWithUserAndAnotherExtraParcel(Class expliciteClass, Parcelable user, String anotherParcelableKey, Parcelable another) {
        Intent intent = buildUserParcelIntent(expliciteClass, user);
        intent.putExtra(anotherParcelableKey, another);

        startActivity(intent);
    }

    protected void navigateTo(Class expliciteClass) {
        Intent intent = getExpliciteIntent(this, expliciteClass);
        startActivity(intent);
    }

    @NonNull
    protected Intent buildUserParcelIntent(Class expliciteClass, Parcelable parcelable) {
        Intent intent = getExpliciteIntent(this, expliciteClass);
        putExtraUserParcelable(parcelable, intent);
        return intent;
    }

    private void putExtraUserParcelable(Parcelable parcelable, Intent intent) {
        intent.putExtra(EXTRA_PARCELABLE_USER, parcelable);
    }

    @NonNull
    private static Intent getExpliciteIntent(Activity activity, Class expliciteClass) {
        return new Intent(activity, expliciteClass);
    }

    protected void navigateToWithUserParcelAndClearActivityStack(Class expliciteClass, Parcelable parcelable) {
        Intent intent = buildUserParcelIntent(expliciteClass, parcelable);
        setClearIntentFlags(intent);

        startActivity(intent);
    }

    protected void navigateToAndClearActivityStack(Class expliciteClass) {
        Intent intent = buildIntentWithClearFlags(this, expliciteClass);

        startActivity(intent);
    }

    @NonNull
    public static Intent buildIntentWithClearFlags(Activity activity, Class expliciteClass) {
        Intent intent = getExpliciteIntent(activity, expliciteClass);
        setClearIntentFlags(intent);

        return intent;
    }

    private static void setClearIntentFlags(Intent intent) {
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    }

    /*
     * ######################################################
     * #
     * # STATICS
     * #
     * ######################################################
     */
    /**
     * Util function to get the user from intent or bundle.
     * @param intent the intent that was created to start the activity
     * @param savedInstanceState the bundle
     * @param tag the log tag
     * @return null if the user couldn't be fetched: you should then trigger some action to reload the user to prevent errors
     */
    public static User getUserFromIntentOrBundle(Intent intent, Bundle savedInstanceState, String tag) {
        if (intent.hasExtra(EXTRA_PARCELABLE_USER)) {
            // activity was started via intent
            return intent.getParcelableExtra(EXTRA_PARCELABLE_USER);
        } else if (null != savedInstanceState && savedInstanceState.containsKey(EXTRA_PARCELABLE_USER)) {
            // activity was restored
            return savedInstanceState.getParcelable(EXTRA_PARCELABLE_USER);
        } else {
            Log.e(tag, "getUserFromIntentOrBundle(): can't get user entity, neither from intent extra nor from the saved instance state bundle." +
                    "Will trigger logout to prevent errors.");
            return null;
        }
    }

    public static void showSuccessNotification(Context context, String string) {
        Toast toast = Toast.makeText(context, string, Toast.LENGTH_LONG);
        toast.show();
    }

}
