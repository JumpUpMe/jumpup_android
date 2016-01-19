package jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.mapper.UserMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.registration.entity.mapper.RegistrationMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.response.ErrorResponse;

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

    public static JsonMapper newRegistrationMapper() {
        return new RegistrationMapper();
    }

    public static JsonMapper<ErrorResponse> newErrorResponseMapper() {
        return new ErrorResponseMapper();
    }
}
