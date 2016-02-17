package jumpup.imi.fb4.htw.de.jumpupandroid.portal;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.List;

import jumpup.imi.fb4.htw.de.jumpupandroid.MainActivity;
import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.activity.JumpUpActivity;

/**
 * Project: jumpup_android
 * <p/>
 * The preference activity which extends Androids preferences API, see http://developer.android.com/guide/topics/ui/settings.html
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 17.02.2016
 */
public class SettingsActivity extends PreferenceActivity {

    private static final String TAG = SettingsActivity.class.getName();
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.user = JumpUpActivity.getUserFromIntentOrBundle(getIntent(), savedInstanceState, TAG);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        Log.d(TAG, "isValidFragment(): fragmentName is " + fragmentName);

        if (fragmentName.equals(SettingsFragment.class.getName())) {
            Log.d(TAG, "isValidFragment(): given fragment is valid");
            return true;
        }

        Log.d(TAG, "isValidFragment(): given fragment isn't valid");
        return false;
    }

    protected void logoutUser() {
        Log.i(TAG, "logoutUser()");

        Intent logoutIntent = JumpUpActivity.buildIntentWithClearFlags(
                this,
                MainActivity.class
        );

        startActivity(logoutIntent);

        JumpUpActivity.showSuccessNotification(this.getApplicationContext(), getString(R.string.portal_activities_logout_success));
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.preference_headers, target);
    }

    public static class SettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // make sure that default preferences are set correctly
            PreferenceManager.setDefaultValues(getActivity(), R.xml.preferences, false);

            // Load the preferences from the XML file
            addPreferencesFromResource(R.xml.preferences);
        }
    }
}
