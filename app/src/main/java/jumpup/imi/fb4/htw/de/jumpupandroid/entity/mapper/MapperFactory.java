package jumpup.imi.fb4.htw.de.jumpupandroid.entity.mapper;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 18.01.2016
 */
public class MapperFactory {

    public static JsonMapper newUserMapper()
    {
        return new UserMapper();
    }
}
