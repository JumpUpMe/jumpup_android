package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity;

import android.os.Parcel;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.AbstractEntity;
import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.AppUtility;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 26.01.2016
 */
public class TripForPassenger extends Trip {

    public static final String FIELD_NAME_BOOKING_URL = "bookingUrl";
    public static final String FIELD_NAME_DISTANCE_FROM_PASSENGERS_START = "distanceFromPassengersLocation";
    public static final String FIELD_NAME_DISTANCE_FROM_PASSENGERS_DESTINATION = "distanceFromPassengersDestination";

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Creator<TripForPassenger> CREATOR = new Creator<TripForPassenger>() {
        public TripForPassenger createFromParcel(Parcel in) {
            return new TripForPassenger(in);
        }

        public TripForPassenger[] newArray(int size) {
            return new TripForPassenger[size];
        }
    };

    /**
     * Fields for Trip search responses. They shouldn't be serialized when sending to web service.
     */
    protected String bookingUrl;
    protected double distanceFromPassengersLocation;
    protected double distanceFromPassengersDestination;

    public TripForPassenger()
    {
        super();
    }

    public TripForPassenger(Parcel in) {
        super();

        this.initializeFromParcel(in);
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);

        /*
         * ATTENTION!
         * When changing the sequence of write operations, make sure to adjust
         * the sequence of read operations in initializeFromParcel(), too.
         * Both must be symmetric.
         */
        parcel.writeString(bookingUrl);
        parcel.writeDouble(distanceFromPassengersLocation);
        parcel.writeDouble(distanceFromPassengersDestination);
    }

    @Override
    public void initializeFromParcel(Parcel in) {
        super.initializeFromParcel(in);

          /*
         * ATTENTION!
         * When changing the sequence of read operations, make sure to adjust
         * the sequence of write operations in writeToParcel(), too.
         * Both must be symmetric.
         */
        this.bookingUrl = in.readString();
        this.distanceFromPassengersLocation = in.readDouble();
        this.distanceFromPassengersDestination = in.readDouble();
    }

    /**
     * @return the distanceFromPassengersLocation
     */
    public double getDistanceFromPassengersLocation()
    {
        return distanceFromPassengersLocation;
    }

    /**
     * @param d the distanceFromPassengersLocation to set
     */
    public void setDistanceFromPassengersLocation(
            double d)
    {
        this.distanceFromPassengersLocation = d;
    }

    /**
     * @return the distanceFromPassengersDestination
     */
    public double getDistanceFromPassengersDestination()
    {
        return distanceFromPassengersDestination;
    }

    /**
     * @param d the distanceFromPassengersDestination to set
     */
    public void setDistanceFromPassengersDestination(
            double d)
    {
        this.distanceFromPassengersDestination = d;
    }

    public void setBookingUrl(String bookingUrl) {
        this.bookingUrl = bookingUrl;
    }

    public String getBookingUrl() {
        return bookingUrl;
    }

    @Override
    public int describeContents() {
        // TODO
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TripForPassenger that = (TripForPassenger) o;

        if (Double.compare(that.getDistanceFromPassengersLocation(), getDistanceFromPassengersLocation()) != 0)
            return false;
        if (Double.compare(that.getDistanceFromPassengersDestination(), getDistanceFromPassengersDestination()) != 0)
            return false;
        return !(getBookingUrl() != null ? !getBookingUrl().equals(that.getBookingUrl()) : that.getBookingUrl() != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (getBookingUrl() != null ? getBookingUrl().hashCode() : 0);
        temp = Double.doubleToLongBits(getDistanceFromPassengersLocation());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getDistanceFromPassengersDestination());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Trip{");
        sb.append("startpoint='").append(startpoint).append('\'');
        sb.append(", endpoint='").append(endpoint).append('\'');
        sb.append(", latStartpoint=").append(latStartpoint);
        sb.append(", longStartpoint=").append(longStartpoint);
        sb.append(", latEndpoint=").append(latEndpoint);
        sb.append(", longEndpoint=").append(longEndpoint);
        sb.append(", startDateTime=").append(startDateTime);
        sb.append(", endDateTime=").append(endDateTime);
        sb.append(", price=").append(price);
        sb.append(", overViewPath='").append(overViewPath).append('\'');
        sb.append(", viaWaypoints='").append(viaWaypoints).append('\'');
        sb.append(", numberOfSeats=").append(numberOfSeats);
        sb.append(", vehicle=").append(vehicle);
        sb.append(", driver=").append(driver);
        sb.append(", cancelationDateTime=").append(cancelationDateTime);
        sb.append(", distanceMeters=").append(distanceMeters);
        sb.append(", durationSeconds=").append(durationSeconds);
        sb.append(", bookingUrl='").append(bookingUrl).append('\'');
        sb.append(", distanceFromPassengersLocation=").append(distanceFromPassengersLocation);
        sb.append(", distanceFromPassengersDestination=").append(distanceFromPassengersDestination);
        sb.append('}');
        return sb.toString();
    }
}
