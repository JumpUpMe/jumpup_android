/**
 * JumpUp.Me Car Pooling Application
 *
 * Copyright (c) 2014-2015 Sascha Feldmann
 */
package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jumpup.imi.fb4.htw.de.jumpupandroid.util.math.Vertex;


/**
 * <p>Record returned via AJAX on overlapping partial trip query results.</p>
 *
 * @author <a href="mailto:me@saschafeldmann.de">Sascha Feldmann</a>
 * @since 09.08.2015
 *
 */
public class OverlappingPartialTripQueryResult extends TripQueryResults
{
    public static final String FIELD_NAME_MESSAGE = "message";
    public static final String FIELD_NAME_INTERSECTIONS = "intersections";

    public class Intersections {
        protected Set<Vertex> intersectionVertices;

        /**
         * @return the intersectionVertices
         */
        public Set<Vertex> getIntersectionVertices()
        {
            return intersectionVertices;
        }

        /**
         * @param intersectionVertices the intersectionVertices to set
         */
        public void setIntersectionVertices(Set<Vertex> intersectionVertices)
        {
            this.intersectionVertices = intersectionVertices;
        }       
        
    }
    
    protected Set<Vertex> intersections;
    protected String message;

    public OverlappingPartialTripQueryResult(Parcel in) {
        super();

        this.initializeFromParcel(in);
    }

    protected void initializeFromParcel(Parcel in) {
        super.initializeFromParcel(in);

        intersections = toIntersectionsSet(in.readParcelableArray(Vertex.class.getClassLoader()));
        message = in.readString();
    }

    private Set<Vertex> toIntersectionsSet(Parcelable[] parcelables) {
        Set<Vertex> intersections = new HashSet<>();

        for (Parcelable vertex: parcelables) {
            intersections.add((Vertex) vertex);
        }

        return intersections;
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
        super.writeToParcel(parcel, i);
        parcel.writeParcelableArray(intersections.toArray(new Vertex[trips.size()]), 0);
        parcel.writeString(message.toString());
    }
    
    public OverlappingPartialTripQueryResult()
    {
        this.type = Type.MULTIPLE_PARTIAL_TRIP_RESULT;
    }

    /**
     * @return the intersections
     */
    public Set<Vertex> getIntersections()
    {
        return intersections;
    }

    /**
     * @param intersections the intersections to set
     */
    public void setIntersections(Set<Vertex> intersections)
    {
        this.intersections = intersections;
    }    
    

    /**
     * @return the message
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message)
    {
        this.message = message;
    }
}
