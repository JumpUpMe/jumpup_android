/**
 * JumpUp.Me Car Pooling Application
 *
 * Copyright (c) 2014-2015 Sebastian Renner, Marco Seidler, Sascha Feldmann
 */
package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search;

import java.util.ArrayList;
import java.util.List;


/**
 * <p></p>
 *
 * @author <a href="mailto:me@saschafeldmann.de">Sascha Feldmann</a>
 * @since 26.01.2015
 *
 */
public class TripQueryResults
{
    public static final String FIELD_NAME_TYPE = "type";
    public static final String FIELD_NAME_TRIPS = "trips";

    /**
     * 
     * <p>Type to simplify the client (e.g. frontend) to handle the polymorphistic query result (e.g. direct trips list, partial overlap trips with intersections, no result).</p>
     *
     * @author <a href="mailto:me@saschafeldmann.de">Sascha Feldmann</a>
     * @since 09.08.2015
     *
     */
    public enum Type {
        DIRECT_TRIP_RESULT,
        MULTIPLE_PARTIAL_TRIP_RESULT,
        NO_RESULT
    }
    
    protected List<SingleTripQueryResult> trips = new ArrayList<SingleTripQueryResult>();
    protected Type type = Type.DIRECT_TRIP_RESULT;
    
    public TripQueryResults()
    {
        this.type = Type.DIRECT_TRIP_RESULT;
    }
    
    /**
     * @return the trips
     */
    public List<SingleTripQueryResult> getTrips()
    {
        return trips;
    }

    /**
     * @param trips the trips to set
     */
    public void setTrips(List<SingleTripQueryResult> trips)
    {
        this.trips = trips;
    }

    /**
     * @return the type
     */
    public Type getType()
    {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Type type)
    {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TripQueryResults that = (TripQueryResults) o;

        if (getTrips() != null ? !getTrips().equals(that.getTrips()) : that.getTrips() != null)
            return false;
        return getType() == that.getType();

    }

    @Override
    public int hashCode() {
        int result = getTrips() != null ? getTrips().hashCode() : 0;
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TripQueryResults{");
        sb.append("trips=").append(trips);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
