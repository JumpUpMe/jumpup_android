/**
 * JumpUp.Me Car Pooling Application
 *
 * Copyright (c) 2014-2015 Sebastian Renner, Marco Seidler, Sascha Feldmann
 */
package jumpup.imi.fb4.htw.de.jumpupandroid.util.math;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashSet;
import java.util.Set;

import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;

/**
 * <p>Simple model of coordinates.</p>
 *
 * @author <a href="mailto:me@saschafeldmann.de">Sascha Feldmann</a>
 * @since 24.01.2015
 *
 */
public class Coordinates implements Parcelable
{
    protected double latitude;
    protected double longitude;

    // this is used to regenerate the object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Coordinates> CREATOR = new Parcelable.Creator<Coordinates>() {
        public Coordinates createFromParcel(Parcel in) {
            return new Coordinates(in);
        }

        public Coordinates[] newArray(int size) {
            return new Coordinates[size];
        }
    };

    public Coordinates(Parcel in) {
        super();

        this.initializeFromParcel(in);
    }

    private void initializeFromParcel(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
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
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
    }

    /**
     * 
     * @param latitudeDegrees
     * @param longitudeDegrees
     */
    public Coordinates(double latitudeDegrees, double longitudeDegrees)
    {
        this.setLatitudeDegrees(latitudeDegrees);
        this.setLongitude(longitudeDegrees); 
    }
    
    /**
     * @return the latitude
     */
    public double getLatitudeDegrees()
    {
        return latitude;
    }
    /**
     * @param latitude the latitude to set
     */
    public void setLatitudeDegrees(double latitude)
    {
        this.latitude = latitude;
    }
    
    /**
     * Get Longitude in radians.
     * @return
     */
    public double getLatitudeRadians()
    {
        return Math.toRadians(this.getLatitudeDegrees());
    }
    
    /**
     * @return the longitude
     */
    public double getLongitudeDegrees()
    {
        return longitude;
    }
    
    /**
     * Get Longitude in radians.
     * @return
     */
    public double getLongitudeRadians()
    {
        return Math.toRadians(this.getLongitudeDegrees());
    }
    
    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coordinates other = (Coordinates) obj;
        if (Double.doubleToLongBits(latitude) != Double
                .doubleToLongBits(other.latitude))
            return false;
        if (Double.doubleToLongBits(longitude) != Double
                .doubleToLongBits(other.longitude))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Coordinate [getLatitudeDegrees()=");
        builder.append(getLatitudeDegrees());
        builder.append(", getLongitudeDegrees()=");
        builder.append(getLongitudeDegrees());
        builder.append(", hashCode()=");
        builder.append(hashCode());
        builder.append("]");
        return builder.toString();
    }    
}
