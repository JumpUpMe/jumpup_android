package jumpup.imi.fb4.htw.de.jumpupandroid.portal.profile;

import java.util.Observer;

import jumpup.imi.fb4.htw.de.jumpupandroid.portal.profile.request.ProfileRequest;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.profile.request.ProfileRequestImpl;

/**
 * Project: jumpup_android
 * <p/>
 * Factory for all profile - related tasks.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 24.01.2016
 */
public class ProfileFactory {
    public static ProfileRequest newProfileRequest() {
        return new ProfileRequestImpl();
    }

    public static ProfileTask newProfileTask(Observer observer) {
        ProfileTask profileTask = new ProfileTask();
        profileTask.getObservable().addObserver(observer);

        return profileTask;
    }
}
