package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.edit.request;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.ErrorResponseException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.TechnicalErrorException;

/**
 * Project: jumpup_android
 * <p/>
 * Service interface for the edit trip request.
 *
 * The edit trip requests targets the JumpUp Web Service endpoint to update a single trip.
 *
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 03.02.2016
 */
public interface EditTripRequest {

    /**
     * Set the user / offeror of the trip that should be updated.
     *
     * @param user the owner / offeror of the trip
     */
    void setUser(User user);

    /**
     * Store the given trip.
     * If a technical error occurs, throw an TechnicalErrorException.
     * On an error response (status code != 200), throw an ErrorResponseException.
     *
     * @param trip the trip to be stored
     * @return true on success
     * @throws TechnicalErrorException on a technical error (e.g. from the network layer)
     * @throws ErrorResponseException on an web service error response
     */
    boolean storeTrip(Trip trip) throws TechnicalErrorException, ErrorResponseException;
}
