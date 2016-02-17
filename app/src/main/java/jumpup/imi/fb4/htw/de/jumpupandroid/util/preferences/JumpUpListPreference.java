package jumpup.imi.fb4.htw.de.jumpupandroid.util.preferences;

/**
 * Project: jumpup_android
 * <p/>
 * Interface for jumpup list preferences. Usually, an enumeration should implement this interface.
 *
 * List preferences are configured in the android preferences configuration file of the app (preferences.xml).
 * For ListPreference elements, an enumeration should be created so that the preference logic (e.g. conversion logic) can be maintained better.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 17.02.2016
 */
public interface JumpUpListPreference<T> {

    /**
     * Get the default preference to be used if either the preference couldn't be determined or wasn't set by the user.
     * @return the default value
     */
    T getDefaultPreference();

    /**
     * Get the label of the prefence to be shown.
     *
     * E.G.: for a distance preference like "METRIC", the label might be "meters", for "IMPERIAL" it might be "miles".
     * @return String
     */
    String getUnitLabel();
}
