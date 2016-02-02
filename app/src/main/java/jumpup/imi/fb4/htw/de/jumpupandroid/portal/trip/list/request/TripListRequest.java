package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.list.request;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.TripList;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.ErrorResponseException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.TechnicalErrorException;

/**
 * Project: jumpup_android
 * <p/>
 * Service interface for the user's trips.
 * This requests targets the JumpUpWebService endpoint to load the user's trips.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 02.02.2016
 */
public interface TripListRequest {

    /**
     * Load the given user's offered trips.
     * @param user the user whose offered trips should be loaded
     * @return the TripList on success
     *
     * @throws TechnicalErrorException on a technical error (e.g. from the network layer)
     * @throws ErrorResponseException  on an web service error response
     */
    TripList loadUsersTrips(User user) throws TechnicalErrorException, ErrorResponseException;
}
