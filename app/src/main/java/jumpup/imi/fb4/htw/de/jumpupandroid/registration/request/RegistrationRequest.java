package jumpup.imi.fb4.htw.de.jumpupandroid.registration.request;

import jumpup.imi.fb4.htw.de.jumpupandroid.registration.entity.Registration;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.ErrorResponseException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.TechnicalErrorException;

/**
 * Project: jumpup_android
 * <p/>
 * Service interface for the registration request.
 *
 * The registration request is based on a @see Registration entity.
 *
 * - If a technical error (e.g. network related or an implementation error) occurs, throw a TechnicalErrorException.
 * Implementations of this method should provide log messages for the reason of the TechnicalErrorException.
 * - If the registration itself failed (e.g. due to validation failures) and another response code than
 * 200 OK is returned, throw an ErrorResponseException.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.01.2016
 */
public interface RegistrationRequest {

    /**
     * Try to register the user via a request to the responsible WebService endpoint.
     *
     * - If a technical error (e.g. network related or an implementation error) occurs, throw a TechnicalErrorException.
     * Implementations of this method should provide log messages for the reason of the TechnicalErrorException.
     * - If the registration itself failed (e.g. due to validation failures) and another response code than
     * 200 OK is returned, throw an ErrorResponseException.
     *
     * @param registrationEntity the filled registration entity with all neccessary data
     * @return true if the registration was successful / the web service replied with a success code
     */
    boolean registerUser(Registration registrationEntity) throws TechnicalErrorException, ErrorResponseException;
}
