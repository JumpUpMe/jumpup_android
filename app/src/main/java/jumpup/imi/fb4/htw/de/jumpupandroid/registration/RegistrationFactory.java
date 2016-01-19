package jumpup.imi.fb4.htw.de.jumpupandroid.registration;

import jumpup.imi.fb4.htw.de.jumpupandroid.registration.request.RegistrationRequest;
import jumpup.imi.fb4.htw.de.jumpupandroid.registration.request.RegistrationRequestImpl;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.01.2016
 */
public class RegistrationFactory {

    public static RegistrationRequest newRegistrationRequest() {
        return new RegistrationRequestImpl();
    }

    public static RegistrationTask newRegistrationTask(RegistrationActivity registrationActivity) {
        RegistrationTask registrationTask = new RegistrationTask();
        registrationTask.getObservable().addObserver(registrationActivity);

        return registrationTask;
    }
}
