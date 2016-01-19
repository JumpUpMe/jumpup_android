package jumpup.imi.fb4.htw.de.jumpupandroid.registration.request;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.mapper.JsonMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.entity.mapper.MapperFactory;
import jumpup.imi.fb4.htw.de.jumpupandroid.registration.entity.Registration;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.ErrorResponseException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.TechnicalErrorException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.request.Endpoints;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.request.JumpUpRequest;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.request.Versions;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.01.2016
 */
public class RegistrationRequestImpl extends JumpUpRequest implements RegistrationRequest {
    private final String TAG = RegistrationRequestImpl.class.getName();
    private final String TARGET_VERSION = Versions.V1_0_0.getUrlPart();
    private final String ENDPOINT = Endpoints.REGISTRATION;

    @Override
    protected boolean isPublicAction() {
        return false;
    }

    @Override
    protected String getTargetVersion() {
        return TARGET_VERSION;
    }

    @Override
    protected String getEndpointUrl() {
        return ENDPOINT;
    }

    @Override
    protected JsonMapper getResponseMapper() {
        return MapperFactory.newRegistrationMapper();
    }

    @Override
    public boolean registerUser(Registration registrationEntity) throws TechnicalErrorException, ErrorResponseException {
        return false;
    }
}
