package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.search.request;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.TripSearchCriteria;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search.TripQueryResults;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.ErrorResponseException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.TechnicalErrorException;

/**
 * Project: jumpup_android
 * <p/>
 * This interface describes the service to search for trips.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.02.2016
 */
public interface TripSearchRequest {

    /**
     * Search for trips that fit the given criteria.
     * If a technical error occurs, throw an TechnicalErrorException.
     * On an error response (status code != 200), throw an ErrorResponseException.
     *
     * @param tripSearchCriteria the trip to be stored
     * @return abstract TripQueryResults which can be a SingleTripQueryResult or a OverlappingPartialTripQueryResult
     * @throws TechnicalErrorException on a technical error (e.g. from the network layer)
     * @throws ErrorResponseException on an web service error response
     */
    TripQueryResults searchTrips(TripSearchCriteria tripSearchCriteria) throws TechnicalErrorException, ErrorResponseException;
}
