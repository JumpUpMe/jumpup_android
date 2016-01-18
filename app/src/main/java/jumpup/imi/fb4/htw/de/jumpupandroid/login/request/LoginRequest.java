package jumpup.imi.fb4.htw.de.jumpupandroid.login.request;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.ErrorResponseException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.TechnicalErrorException;

/**
 * Project: jumpup_android
 * <p/>
 * Service interface for the LoginRequest logic.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 18.01.2016
 */
public interface LoginRequest {

    /**
     * Trigger the login request to the web service backend.
     *
     * - If a technical error (e.g. network related or an implementation error) occurs, throw a TechnicalErrorException.
     * Implementations of this method should provide log messages for the reason of the TechnicalErrorException.
     * - If the login itself failed (e.g. due to invalid credentials) and another response code than
     * 200 OK is returned, throw an ErrorResponseException.
     *
     * @param username the username (or in general: identifier) that will be sent as credential
     * @param password the plain password that will be sent as credential
     * @return the built User entity on successful authentication
     *
     * @throws TechnicalErrorException (e.g. network related or an implementation error)
     * @throws ErrorResponseException (e.g. due to invalid credentials)
     */
    User triggerLogin(String username, String password) throws TechnicalErrorException, ErrorResponseException;
}
