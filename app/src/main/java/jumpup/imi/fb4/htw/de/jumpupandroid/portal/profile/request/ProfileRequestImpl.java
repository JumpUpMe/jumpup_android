package jumpup.imi.fb4.htw.de.jumpupandroid.portal.profile.request;

import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.ErrorResponseException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.TechnicalErrorException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.JsonMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.request.JumpUpRequest;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.request.Versions;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 24.01.2016
 */
public class ProfileRequestImpl extends JumpUpRequest implements ProfileRequest {
    private static final String TARGET_VERSION = Versions.V1_0_0.getUrlPart();
    private static final String ENDPOINT_URL = "user";

    @Override
    protected String getTargetVersion() {
        return TARGET_VERSION;
    }

    @Override
    protected String getEndpointUrl() {
        return ENDPOINT_URL;
    }

    @Override
    protected boolean isPublicAction() {
        return false;
    }

    @Override
    protected JsonMapper getResponseMapper() {
        return null;
    }

    @Override
    protected int getDefaultErrorMessageId() {
        return R.string.jumpup_request_error_update_profile_failed;
    }

    @Override
    public boolean storeProfile(User user) throws TechnicalErrorException, ErrorResponseException {

        return false;
    }
}
