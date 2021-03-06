package jumpup.imi.fb4.htw.de.jumpupandroid.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import jumpup.imi.fb4.htw.de.jumpupandroid.BuildConfig;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.preferences.values.DurationUnitTypePreference;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.preferences.PreferencesUtil;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.01.2016
 */
public class AppUtility {

    public static final String PRICE_FORMAT = "%1$.2f";
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    public static final String DATE_TIME_FORMAT = DATE_FORMAT + " HH:mm";
    public static final String NUMBER_FORMAT = "%d";
    public static final String DOUBLE_FORMAT = ".2";

    private static final DecimalFormat DOUBLE_FORMATTER = new DecimalFormat(DOUBLE_FORMAT);
    private static SimpleDateFormat dateFormatter;
    private static SimpleDateFormat dateTimeFormatter;

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

    protected static SimpleDateFormat getDateFormatterWithUsersLocale()
    {
        if (null == dateTimeFormatter) {
            // TODO static caching is used: this might be problematic if the locale is overwritten
            dateTimeFormatter = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        }

        return dateTimeFormatter;
    }

    protected static SimpleDateFormat getDateTimeFormatterWithUsersLocale()
    {
        if (null == dateTimeFormatter) {
            // TODO static caching is used: this might be problematic if the locale is overwritten
            dateTimeFormatter = new SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault());
        }

        return dateTimeFormatter;
    }

    public static String formatDate(Long timestampSeconds) {
        return getDateFormatterWithUsersLocale().format(new Date(timestampSeconds * 1000));
    }

    public static String formatDateTime(Long timestampSeconds) {
        return getDateTimeFormatterWithUsersLocale().format(new Date(timestampSeconds * 1000));
    }

    public static String formatDateToUTC(Long timestampSeconds) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        return dateFormat.format(new Date(timestampSeconds * 1000));
    }

    /**
     * Take a dateTime string such as dd.MM.yyyy and convert it to a UTC timestamp (seconds).
     *
     * JumpUp Web services should always get UTC timestamps.
     * @param dateTime string, e.g. dd.MM.yyyy
     * @return long UTC timestamp in seconds
     */
    public static Long getUTCTimestamp(String dateTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            return dateFormat.parse(dateTime).getTime() / 1000;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Check whether a string has a value.
     * This means, it mustn't be null or empty or consist of whitespaces only..
     * @param field string value
     * @return true if the string has a value (is not empty and doesn't consist of whitespaces only)
     */
    public static boolean isSet(String field) {
        return null != field && field.trim().length() > 0;
    }

    /**
     * Check whether a long has a value such that it isn't null or zero.
     * @param longValue the long value
     * @return true if the long has a value
     */
    public static boolean isSet(Long longValue) {
        return null != longValue && 0 != longValue;
    }

    public static boolean isSet(Integer numberOfSeats) {
        return isSet(Long.valueOf(numberOfSeats));
    }

    /**
     * Format a raw price and produce a string representation.
     * @param price double
     * @return string
     */
    public static String formatPrice(double price) {
        return String.format(PRICE_FORMAT, price);
    }

    public static String formatNumber(Integer numberOfSeats) {
        return String.format(NUMBER_FORMAT, numberOfSeats);
    }

    public static double getPriceAsDouble(String price) {
        return Double.parseDouble(price);
    }

    public static Integer getNumberAsInt(String s) {
        return Integer.parseInt(s);
    }

    /**
     * Get the duration in the preferred unit (default: hours).
     * @param durationSeconds long
     * @param appendUnitLabel boolean set to true to append a label indicating which unit is used (e.g. hours or minutes)
     * @return duration in the users prefered duration unit
     */
    public static String formatDuration(long durationSeconds, boolean appendUnitLabel) {
        String format = DOUBLE_FORMATTER.format(takeCareOfDurationPreference(durationSeconds));

        if (appendUnitLabel) {
            format += " " + PreferencesUtil.readDurationUnitTypPreference().getUnitLabel();
        }

        return format;
    }

    protected static double takeCareOfDurationPreference(long durationSeconds) {
        return PreferencesUtil.readDurationUnitTypPreference().convertFromSeconds(durationSeconds);
    }

    /**
     * Get the distance in the preferred unit (default: kilometers).
     * @param distanceMeters long
     * @param appendUnitLabel boolean set to true to append a label indicating which unit is used (e.g. kilometers or miles)
     * @return distance in the user's prefered distance unit
     */
    public static String formatDistance(long distanceMeters, boolean appendUnitLabel) {
        String format = DOUBLE_FORMATTER.format(takeCareOfDistancePreference(distanceMeters));

        if (appendUnitLabel) {
            format += " " + PreferencesUtil.readDistanceUnitTypPreference().getUnitLabel();
        }

        return format;
    }

    private static double takeCareOfDistancePreference(long distanceMeters) {
        return PreferencesUtil.readDistanceUnitTypPreference().convertFromMeters(distanceMeters);
    }
}
