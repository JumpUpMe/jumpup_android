package jumpup.imi.fb4.htw.de.jumpupandroid.util.preferences.values;

import android.util.Log;

import jumpup.imi.fb4.htw.de.jumpupandroid.App;
import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.preferences.JumpUpListPreference;

/**
 * Project: jumpup_android
 * <p/>
 * Available unit types for the distance preference.
 *
 * Workflow: if a new distance unit type should be supported, first extend the enumeration, and then refer to the preferences.xml.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 17.02.2016
 */
public enum DistanceUnitTypePreference implements JumpUpListPreference<DistanceUnitTypePreference> {
    METRIC {
        @Override
        public String getUnitLabel() {
            return App.getStringFromResourceId(R.string.preferences_distance_value_metric_label);
        }

        public double convertFromMeters(long distanceMeters) {
            return distanceMeters / 1000.0;
        }
    },
    IMPERIAL {
        @Override
        public String getUnitLabel() {
            return App.getStringFromResourceId(R.string.preferences_distance_value_imperial_label);
        }

        public double convertFromMeters(long distanceMeters) {
            return distanceMeters * METER_TO_MILE_FACTOR;
        }
    };

    private static final String TAG = DistanceUnitTypePreference.class.getName();
    private static final Double METER_TO_MILE_FACTOR = 0.000621371;

    /**
     * Resolve the corresponding enumeration by the preferences string value.
     * @param androidPreferencesValue the string value of the given selected preference.
     * @return the enumeration
     */
    public static DistanceUnitTypePreference getByAndroidPreferenceValue(String androidPreferencesValue) {
        if (androidPreferencesValue.equals(App.getStringFromResourceId(R.string.preferences_distance_value_metric))) {
            return METRIC;
        }
        else if (androidPreferencesValue.equals(App.getStringFromResourceId(R.string.preferences_distance_value_imperial))) {
            return IMPERIAL;
        } else {
            Log.e(TAG, "getByAndroidPreferenceValue(): couldn't map duration unit preference value '" + androidPreferencesValue + "' to an DistanceUnitTypePreference. Will return the default value" );
            return METRIC.getDefaultPreference();
        }
    }

    @Override
    public DistanceUnitTypePreference getDefaultPreference() {
        return METRIC;
    }

    /**
     * Calculate the converted value.
     * @param distanceMeters (raw) distance value in meters
     * @return double
     */
    public double convertFromMeters(long distanceMeters) {
        throw new IllegalAccessError("convertFromMeters() must be called on an enumeration value and the method must be implemented by each value!");
    }
}
