package jumpup.imi.fb4.htw.de.jumpupandroid.portal.profile.request;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.ErrorResponseException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.TechnicalErrorException;

/**
 * Project: jumpup_android
 * <p/>
 * Interface for the profile request.
 * <p/>
 * The profile requests targets the JumpUp Web Service endpoint to store profile data.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 24.01.2016
 */
public interface ProfileRequest {

    /**
     * Store the given user's profile.
     * <p/>
     * If a technical error occurs, throw an TechnicalErrorException.
     * On an error response (status code != 200), throw an ErrorResponseException.
     *
     * @param user the user to be stored
     * @return true on succes
     * @throws TechnicalErrorException on a technical error (e.g. from the network layer)
     * @throws ErrorResponseException  on an web service error response
     */
    boolean storeProfile(User user) throws TechnicalErrorException, ErrorResponseException;
}



