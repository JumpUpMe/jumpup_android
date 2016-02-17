package jumpup.imi.fb4.htw.de.jumpupandroid.util.preferences.values;

import android.util.Log;

import jumpup.imi.fb4.htw.de.jumpupandroid.App;
import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.preferences.JumpUpListPreference;

/**
 * Project: jumpup_android
 * <p/>
 * Available unit types for the duration preference.
 *
 * Workflow: if a new duration unit type should be supported, first extend the enumeration, and then refer to the preferences.xml.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 17.02.2016
 */
public enum DurationUnitTypePreference implements JumpUpListPreference<DurationUnitTypePreference> {
    HOURS {
        @Override
        public String getUnitLabel() {
            return App.getStringFromResourceId(R.string.preferences_duration_value_hours_label);
        }

        public double convertFromSeconds(long durationSeconds) {
            return durationSeconds / 60 / 60;
        }
    },
    MINUTES {
        @Override
        public String getUnitLabel() {
            return App.getStringFromResourceId(R.string.preferences_duration_value_minutes_label);
        }

        public double convertFromSeconds(long durationSeconds) {
            return durationSeconds / 60;
        }
    };

    private static final String TAG = DurationUnitTypePreference.class.getName();

    /**
     * Resolve the corresponding enumeration by the preferences string value.
     * @param androidPreferencesValue the string value of the given selected preference.
     * @return the enumeration
     */
    public static DurationUnitTypePreference getByAndroidPreferenceValue(String androidPreferencesValue) {
        if (androidPreferencesValue.equals(App.getStringFromResourceId(R.string.preferences_duration_value_hours))) {
            return HOURS;
        }
        else if (androidPreferencesValue.equals(App.getStringFromResourceId(R.string.preferences_duration_value_minutes))) {
            return MINUTES;
        } else {
            Log.e(TAG, "getByAndroidPreferenceValue(): couldn't map duration unit preference value '" + androidPreferencesValue + "' to an DurationUnitType. Will return the default value" );
            return HOURS.getDefaultPreference();
        }
    }

    @Override
    public DurationUnitTypePreference getDefaultPreference() {
        return HOURS;
    }

    /**
     * Calculate the converted value.
     * @param durationSeconds (raw) duration value in seconds
     * @return double
     */
    public double convertFromSeconds(long durationSeconds) {
        throw new IllegalAccessError("convertFromSeconds() must be called on an enumeration value and the method must be implemented by each value!");
    }
}
