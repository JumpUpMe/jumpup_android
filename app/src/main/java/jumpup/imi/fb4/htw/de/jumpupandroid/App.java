package jumpup.imi.fb4.htw.de.jumpupandroid;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;

/**
 * Project: jumpup_android
 * <p/>
 * Main class of JumpUp application. Maintain global application state here.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 17.02.2016
 */
public class App extends Application {
    private static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationContext = getApplicationContext();
    }

    /**
     * Get the global application context.
     *
     * Warning: it might be that the context is null, e.g. if the app lifecycle was not properly executed.
     *
     * @return the application context
     */
    @Nullable
    public static Context getGlobalContext() {
        return applicationContext;
    }

    /**
     * Get the string value of the given resourceId.
     * @param resourceId
     * @return String
     */
    public static String getStringFromResourceId(int resourceId) {
        return getGlobalContext().getString(resourceId);
    }
}
