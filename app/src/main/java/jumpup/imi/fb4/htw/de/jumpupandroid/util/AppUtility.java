package jumpup.imi.fb4.htw.de.jumpupandroid.util;

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
}