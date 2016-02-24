/**
 * JumpUp.Me Car Pooling Application
 *
 * Copyright (c) 2014-2015 Sebastian Renner, Marco Seidler, Sascha Feldmann
 */
package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashSet;
import java.util.Set;

import jumpup.imi.fb4.htw.de.jumpupandroid.util.math.Vertex;

/**
 * <p>POJO for indicating empty results.</p>
 *
 * @author <a href="mailto:me@saschafeldmann.de">Sascha Feldmann</a>
 * @since 26.01.2015
 *
 */
public class TripQueryNoResults extends TripQueryResults
{
    public static final String FIELD_NAME_MESSAGE = "message";
    protected String message;

    public TripQueryNoResults(Parcel in) {
        super();

        this.initializeFromParcel(in);
    }

    protected void initializeFromParcel(Parcel in) {
       super.initializeFromParcel(in);

       message = in.readString();
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
        parcel.writeString(message.toString());
    }

    public TripQueryNoResults()
    {
        this.type = Type.NO_RESULT;
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
