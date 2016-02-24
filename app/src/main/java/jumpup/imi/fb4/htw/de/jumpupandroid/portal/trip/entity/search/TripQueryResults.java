/**
 * JumpUp.Me Car Pooling Application
 *
 * Copyright (c) 2014-2015 Sebastian Renner, Marco Seidler, Sascha Feldmann
 */
package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;


/**
 * <p></p>
 *
 * @author <a href="mailto:me@saschafeldmann.de">Sascha Feldmann</a>
 * @since 26.01.2015
 *
 */
public class TripQueryResults implements Parcelable
{
    public static final String FIELD_NAME_TYPE = "type";
    public static final String FIELD_NAME_TRIPS = "trips";

    // this is used to regenerate the object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<TripQueryResults> CREATOR = new Parcelable.Creator<TripQueryResults>() {
        public TripQueryResults createFromParcel(Parcel in) {
            return new TripQueryResults(in);
        }

        public TripQueryResults[] newArray(int size) {
            return new TripQueryResults[size];
        }
    };

    public TripQueryResults(Parcel in) {
        super();

        this.initializeFromParcel(in);
    }

    protected void initializeFromParcel(Parcel in) {
        trips = toSingleTripQueryList(in.readParcelableArray(SingleTripQueryResult.class.getClassLoader()));
        type = Type.valueOf(in.readString());
    }

    private List<SingleTripQueryResult> toSingleTripQueryList(Parcelable[] parcelables) {
        List<SingleTripQueryResult> queryResults = new ArrayList<>();

        for (Parcelable trip: parcelables) {
            queryResults.add((SingleTripQueryResult) trip);
        }

        return queryResults;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        /*
         * ATTENTION!
         * When changing the sequence of write operations, make sure to adjust
         * the sequence of read operations in initializeFromParcel(), too.
         * Both must be symmetric.
         */
        parcel.writeParcelableArray(trips.toArray(new SingleTripQueryResult[trips.size()]), 0);
        parcel.writeString(type.toString());
    }

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
