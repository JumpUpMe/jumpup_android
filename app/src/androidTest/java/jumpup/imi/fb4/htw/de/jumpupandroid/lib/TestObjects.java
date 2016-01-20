package jumpup.imi.fb4.htw.de.jumpupandroid.lib;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 20.01.2016
 */
public class TestObjects {

    public static User newTestUser() {
        User user = new User();

        user.setIdentity(1L);
        user.setCreationTimestamp(0L);
        user.setUpdateTimestamp(0L);
        user.setUsername("sascha");
        user.seteMail("info@groupelite.de");
        user.setPrename("Sascha");
        user.setLastname("Feldmann");
        user.setTown("Berlin");
        user.setCountry("Germany");
        user.setLocale("");
        user.setIsConfirmed(true);
        user.setDateOfBirth(648691200000L);
        user.setPlaceOfBirth("Linz am Rhein");
        user.setGender("MAN");
        user.setMobileNumber("+49 124124 124");
        user.setSkype("cof1990");

        return user;
    }
}
