package jumpup.imi.fb4.htw.de.jumpupandroid.util.preferences;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import jumpup.imi.fb4.htw.de.jumpupandroid.App;
import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.preferences.values.DistanceUnitTypePreference;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.preferences.values.DurationUnitTypePreference;

/**
 * Project: jumpup_android
 * <p/>
 * Util class to deal with all preference-related tasks.
 *
 * This adapter method makes sure that the correct context is used.
 *
 * Please use this class here instead of dealing with the PreferenceManager directly.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 17.02.2016
 */
public class PreferencesUtil {
    /**
     * Get the android SharedPreferences instance with the correct context.
     * @return
     */
    public static SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(App.getGlobalContext());
    }

    /**
     * Adapter to the PreferenceManager facade.
     *
     * This adapter method makes sure that the correct context is used.
     *
     * Please use this method here instead of dealing witht the PreferenceManager directly.
     *
     * @param resId resourceId of the default preferences (e.g. an XML file).
     * @param again see PreferenceManager.setDefaultValues
     */
    public static void setDefaultPreferences(int resId, boolean again) {
        PreferenceManager.setDefaultValues(App.getGlobalContext(), resId, again);
    }

    /**
     * Read the duration unit type and return the enumeration that contains the logic.
     * @return the DurationUnitType or the default unit type if it couldn't be mapped.
     */
    public static DurationUnitTypePreference readDurationUnitTypPreference() {
        String unitType = getSharedPreferences().getString(
                App.getStringFromResourceId(R.string.preferences_duration_unit_key),
                App.getStringFromResourceId(R.string.preferences_duration_value_hours));

        return DurationUnitTypePreference.getByAndroidPreferenceValue(unitType);
    }

    /**
     * Read the distance unit type and return the enumeration that contains the logic.
     * @return the DistanceUnitTypePreference or the default unit type if it couldn't be mapped.
     */
    public static DistanceUnitTypePreference readDistanceUnitTypPreference() {
        String unitType = getSharedPreferences().getString(
                App.getStringFromResourceId(R.string.preferences_distance_unit_key),
                App.getStringFromResourceId(R.string.preferences_distance_value_metric));

        return DistanceUnitTypePreference.getByAndroidPreferenceValue(unitType);
    }
}
