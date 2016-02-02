package jumpup.imi.fb4.htw.de.jumpupandroid.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import jumpup.imi.fb4.htw.de.jumpupandroid.BuildConfig;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.01.2016
 */
public class AppUtility {

    public static final String DATE_FORMAT = "dd.MM.yyyy HH:mm";
    private static SimpleDateFormat dateFormatter;

    public static boolean isDevelopmentMode()
    {
        return BuildConfig.BUILD_TYPE.equals("debug") && BuildConfig.DEBUG;
    }

    /**
     * Whether to provide prefilled input fields to simplify app testing.
     *
     * @return true | false
     */
    public static boolean prefillTestData() {
        return BuildConfig.DEBUG && BuildConfig.PREFILL_TEST_DATA;
    }

    protected static SimpleDateFormat getDateFormatter()
    {
        if (null == dateFormatter) {
            // TODO static caching is used: this might be problematic if the locale is overwritten
            dateFormatter = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        }

        return dateFormatter;
    }

    public static String formatDateTime(Long startDateTime) {
        return getDateFormatter().format(new Date(startDateTime));
    }
}
