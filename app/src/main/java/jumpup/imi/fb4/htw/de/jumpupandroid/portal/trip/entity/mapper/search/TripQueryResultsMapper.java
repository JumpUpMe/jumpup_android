package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.mapper.search;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.mapper.search.SingleTripQueryResultMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search.OverlappingPartialTripQueryResult;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search.SingleTripQueryResult;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search.TripQueryNoResults;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search.TripQueryResults;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.math.Vertex;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.JsonMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.MapperFactory;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.02.2016
 */
public class TripQueryResultsMapper extends JsonMapper<TripQueryResults> {
    private SingleTripQueryResultMapper singleTripQueryResultMapper = MapperFactory.newSingleTripQueryResultMapper();

    @Override
    public TripQueryResults mapResponse(String response) throws JSONException {
        JSONObject jsonResponse = new JSONObject(response);

        TripQueryResults queryResults = determineTypeOfQueryResults(jsonResponse);

        parseResponseAndFillTrip(queryResults, jsonResponse);

        return queryResults;
    }

    private TripQueryResults determineTypeOfQueryResults(JSONObject response) throws JSONException {
        String type = response.getString(TripQueryResults.FIELD_NAME_TYPE);

        if (type.equals(TripQueryResults.Type.NO_RESULT.toString())) {
            return new TripQueryNoResults();
        } else if (type.equals(TripQueryResults.Type.DIRECT_TRIP_RESULT.toString())) {
            return new TripQueryResults();
        } else if (type.equals(TripQueryResults.Type.MULTIPLE_PARTIAL_TRIP_RESULT.toString())) {
            return new OverlappingPartialTripQueryResult();
        }

        throw new JSONException("Invalid type given: " + type);
    }

    private void parseResponseAndFillTrip(TripQueryResults queryResults, JSONObject response) throws JSONException {
        if (queryResults instanceof TripQueryNoResults) {
            parseNoResultsResponse((TripQueryNoResults) queryResults, response);
        } else if (queryResults instanceof TripQueryResults) {
            parseDirectTripsResponse(queryResults, response);
        } else {
            parseMultiplePartialTripsResponse((OverlappingPartialTripQueryResult) queryResults, response);
        }
    }

    private void parseNoResultsResponse(TripQueryNoResults queryResults, JSONObject response) throws JSONException {
        queryResults.setMessage(response.getString(TripQueryNoResults.FIELD_NAME_MESSAGE));
    }


    private void parseDirectTripsResponse(TripQueryResults queryResults, JSONObject response) throws JSONException {
        queryResults.setTrips(parseSingleTripQueryResults(queryResults, response));
    }

    private List<SingleTripQueryResult> parseSingleTripQueryResults(TripQueryResults queryResults, JSONObject response) throws JSONException {
        List<SingleTripQueryResult> singleTripQueryResults = new ArrayList<>();

        JSONArray tripsArray = response.getJSONArray(TripQueryResults.FIELD_NAME_TRIPS);

        for (int i=0; i < tripsArray.length(); i++) {
            String tripOuterJson = tripsArray.getJSONObject(i).toString();

            SingleTripQueryResult singleTripQueryResult = singleTripQueryResultMapper.mapResponse(tripOuterJson);

            singleTripQueryResults.add(singleTripQueryResult);
        }

        return singleTripQueryResults;
    }

    private void parseMultiplePartialTripsResponse(OverlappingPartialTripQueryResult queryResults, JSONObject response) throws JSONException {
        queryResults.setIntersections(parseIntersections(response));
        queryResults.setMessage(response.getString(OverlappingPartialTripQueryResult.FIELD_NAME_MESSAGE));
    }

    private Set<Vertex> parseIntersections(JSONObject response) {
        HashSet<Vertex> intersections = new HashSet<>();

        // TODO implement

        return intersections;
    }

    @Override
    public String marshalEntity(TripQueryResults entity) throws JSONException {
        return null;
    }
}
