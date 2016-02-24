/**
 * JumpUp.Me Car Pooling Application
 * <p/>
 * Copyright (c) 2014-2016 Sascha Feldmann
 */
package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Vehicle;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href="mailto:me@saschafeldmann.de">Sascha Feldmann</a>
 * @since 25.01.2015
 *
 */
public class SingleTripQueryResult implements Serializable, Parcelable {
    /**
     *
     */
    private static final long serialVersionUID = -3179074383171618295L;
    public static final String FIELD_NAME_DRIVER = "driver";
    public static final String FIELD_NAME_VEHICLE = "vehicle";
    public static final String FIELD_NAME_TRIP = "trip";

    protected Driver driver = new Driver();
    protected Vehicle vehicle;
    protected Trip trip;

    // this is used to regenerate the object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<SingleTripQueryResult> CREATOR = new Parcelable.Creator<SingleTripQueryResult>() {
        public SingleTripQueryResult createFromParcel(Parcel in) {
            return new SingleTripQueryResult(in);
        }

        public SingleTripQueryResult[] newArray(int size) {
            return new SingleTripQueryResult[size];
        }
    };

    public SingleTripQueryResult(Parcel in) {
        super();

        this.initializeFromParcel(in);
    }

    public SingleTripQueryResult() {
        super();
    }

    private void initializeFromParcel(Parcel in) {
        driver = in.readParcelable(Driver.class.getClassLoader());
        vehicle = in.readParcelable(Vehicle.class.getClassLoader());
        trip = in.readParcelable(Trip.class.getClassLoader());
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
        parcel.writeParcelable(driver, 0);
        parcel.writeParcelable(vehicle, 0);
        parcel.writeParcelable(trip, 0);
    }

    /**
     * @return the driver
     */
    public Driver getDriver() {
        return driver;
    }

    /**
     * @return the vehicle
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * @return the trip
     */
    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleTripQueryResult that = (SingleTripQueryResult) o;

        if (getDriver() != null ? !getDriver().equals(that.getDriver()) : that.getDriver() != null)
            return false;
        if (getVehicle() != null ? !getVehicle().equals(that.getVehicle()) : that.getVehicle() != null)
            return false;
        return !(getTrip() != null ? !getTrip().equals(that.getTrip()) : that.getTrip() != null);

    }

    @Override
    public int hashCode() {
        int result = getDriver() != null ? getDriver().hashCode() : 0;
        result = 31 * result + (getVehicle() != null ? getVehicle().hashCode() : 0);
        result = 31 * result + (getTrip() != null ? getTrip().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SingleTripQueryResult{");
        sb.append("driver=").append(driver);
        sb.append(", vehicle=").append(vehicle);
        sb.append(", trip=").append(trip);
        sb.append('}');
        return sb.toString();
    }
}
