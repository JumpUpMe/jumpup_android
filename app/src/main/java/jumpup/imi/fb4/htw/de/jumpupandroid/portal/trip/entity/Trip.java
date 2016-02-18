package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity;

import android.os.Parcel;
import android.os.Parcelable;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.AbstractEntity;
import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 26.01.2016
 */
public class Trip extends AbstractEntity {

    public static final String FIELD_NAME_START_POINT = "startpoint";
    public static final String FIELD_NAME_END_POINT = "endpoint";
    public static final String FIELD_NAME_START_LAT = "latStartpoint";
    public static final String FIELD_NAME_START_LNG = "longStartpoint";
    public static final String FIELD_NAME_END_LAT = "latEndpoint";
    public static final String FIELD_NAME_END_LNG = "longEndpoint";
    public static final String FIELD_NAME_START_DATETIME = "startDateTime";
    public static final String FIELD_NAME_END_DATETIME = "endDateTime";
    public static final String FIELD_NAME_PRICE = "price";
    public static final String FIELD_NAME_OVERVIEW_PATH = "overViewPath";
    public static final String FIELD_NAME_VIA_WAYPOINTS = "viaWaypoints";
    public static final String FIELD_NAME_NUMBER_OF_SEATS = "numberOfSeats";
    public static final String FIELD_NAME_DRIVER = "driver";
    public static final String FIELD_NAME_CANCELATION_DATETIME = "cancelationDateTime";
    public static final String FIELD_NAME_DISTANCE_METERS = "distanceMeters";
    public static final String FIELD_NAME_DURATION_SECONDS = "durationSeconds";
    public static final String FIELD_NAME_VEHICLE = "vehicle";

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Trip> CREATOR = new Parcelable.Creator<Trip>() {
        public Trip createFromParcel(Parcel in) {
            return new Trip(in);
        }

        public Trip[] newArray(int size) {
            return new Trip[size];
        }
    };

    protected String startpoint;
    protected String endpoint;
    protected double latStartpoint;
    protected double longStartpoint;
    protected double latEndpoint;
    protected double longEndpoint;
    protected Long startDateTime;
    protected Long endDateTime;
    protected double price;
    protected String overViewPath;
    protected String viaWaypoints;
    protected Integer numberOfSeats;
    protected Vehicle vehicle;
    protected User driver;
    protected Long cancelationDateTime;
    protected long distanceMeters;
    protected long durationSeconds;

    public Trip()
    {
        super();
    }

    public Trip(Parcel in) {
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
        parcel.writeString(startpoint);
        parcel.writeString(endpoint);
        parcel.writeDouble(latStartpoint);
        parcel.writeDouble(longStartpoint);
        parcel.writeDouble(latEndpoint);
        parcel.writeDouble(longEndpoint);
        parcel.writeLong(startDateTime);
        parcel.writeLong(endDateTime);
        parcel.writeDouble(price);
        parcel.writeString(overViewPath);
        parcel.writeString(viaWaypoints);
        parcel.writeInt(numberOfSeats);
        parcel.writeValue(cancelationDateTime); // may be null, therefore use generic writeValue()
        parcel.writeLong(distanceMeters);
        parcel.writeLong(durationSeconds);
        parcel.writeParcelable(vehicle, 0);
        parcel.writeParcelable(driver, 0);
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
        this.startpoint = in.readString();
        this.endpoint = in.readString();
        this.latStartpoint = in.readDouble();
        this.longStartpoint = in.readDouble();
        this.latEndpoint = in.readDouble();
        this.longEndpoint = in.readDouble();
        this.startDateTime = in.readLong();
        this.endDateTime = in.readLong();
        this.price = in.readDouble();
        this.overViewPath = in.readString();
        this.viaWaypoints = in.readString();
        this.numberOfSeats = in.readInt();
        this.cancelationDateTime = (Long) in.readValue(Long.class.getClassLoader()); // may be null, therefore use generic readValue()
        this.distanceMeters = in.readLong();
        this.durationSeconds = in.readLong();
        this.vehicle = in.readParcelable(Vehicle.class.getClassLoader());
        this.driver = in.readParcelable(User.class.getClassLoader());
    }

    /**
     * @return the startpoint
     */
    public String getStartpoint()
    {
        return startpoint;
    }

    /**
     * @param startpoint
     *            the startpoint to set
     */
    public void setStartpoint(String startpoint)
    {
        this.startpoint = startpoint;
    }

    /**
     * @return the endpoint
     */
    public String getEndpoint()
    {
        return endpoint;
    }

    /**
     * @param endpoint
     *            the endpoint to set
     */
    public void setEndpoint(String endpoint)
    {
        this.endpoint = endpoint;
    }

    /**
     * @return the latStartpoint
     */
    public double getLatStartpoint()
    {
        return latStartpoint;
    }

    /**
     * @param latStartpoint
     *            the latStartpoint to set
     */
    public void setLatStartpoint(double latStartpoint)
    {
        this.latStartpoint = latStartpoint;
    }

    /**
     * @return the longStartpoint
     */
    public double getLongStartpoint()
    {
        return longStartpoint;
    }

    /**
     * @param longStartpoint
     *            the longStartpoint to set
     */
    public void setLongStartpoint(double longStartpoint)
    {
        this.longStartpoint = longStartpoint;
    }

    /**
     * @return the latEntpoint
     */
    public double getLatEndpoint()
    {
        return latEndpoint;
    }

    /**
     * @param latEndpoint
     *            the latEntpoint to set
     */
    public void setLatEndpoint(double latEndpoint)
    {
        this.latEndpoint = latEndpoint;
    }

    /**
     * @return the longEndpoint
     */
    public double getLongEndpoint()
    {
        return longEndpoint;
    }

    /**
     * @param longEndpoint
     *            the longEndpoint to set
     */
    public void setLongEndpoint(double longEndpoint)
    {
        this.longEndpoint = longEndpoint;
    }

    public void setStartDateTime(Long startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Long getStartDateTime() {
        return startDateTime;
    }

    public void setEndDateTime(Long endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Long getEndDateTime() {
        return endDateTime;
    }

    /**
     * @return the price
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * @param price
     *            the price to set
     */
    public void setPrice(double price)
    {
        this.price = price;
    }

    /**
     * @return the overViewPath
     */
    public String getOverViewPath()
    {
        return overViewPath;
    }

    /**
     * @param overViewPath
     *            the overViewPath to set
     */
    public void setOverViewPath(String overViewPath)
    {
        this.overViewPath = overViewPath;
    }

    /**
     * @return the viaWaypoints
     */
    public String getViaWaypoints()
    {
        return viaWaypoints;
    }

    /**
     * @param viaWaypoints
     *            the viaWaypoints to set
     */
    public void setViaWaypoints(String viaWaypoints)
    {
        this.viaWaypoints = viaWaypoints;
    }

    /**
     * @return the numberOfSeats
     */
    public Integer getNumberOfSeats()
    {
        return numberOfSeats;
    }

    /**
     * @param numberOfSeats
     *            the numberOfSeats to set
     */
    public void setNumberOfSeats(Integer numberOfSeats)
    {
        this.numberOfSeats = numberOfSeats;
    }

    /**
     * @return the vehicle
     */
    public Vehicle getVehicle()
    {
        return vehicle;
    }

    /**
     * @param vehicle
     *            the vehicle to set
     */
    public void setVehicle(Vehicle vehicle)
    {
        this.vehicle = vehicle;
    }

    /**
     * @return the driver
     */
    public User getDriver()
    {
        return driver;
    }

    /**
     * @param driver
     *            the driver to set
     */
    public void setDriver(User driver)
    {
        this.driver = driver;
    }

    public void setCancelationDateTime(Long cancelationDateTime) {
        this.cancelationDateTime = cancelationDateTime;
    }

    public Long getCancelationDateTime() {
        return cancelationDateTime;
    }

    /**
     * @return the distanceMeters
     */
    public long getDistanceMeters()
    {
        return distanceMeters;
    }

    /**
     * @param distanceMeters
     *            the distanceMeters to set
     */
    public void setDistanceMeters(long distanceMeters)
    {
        this.distanceMeters = distanceMeters;
    }

    /**
     * @return the durationSeconds
     */
    public long getDurationSeconds()
    {
        return durationSeconds;
    }

    /**
     * @param durationSeconds
     *            the durationSeconds to set
     */
    public void setDurationSeconds(long durationSeconds)
    {
        this.durationSeconds = durationSeconds;
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

        Trip trip = (Trip) o;

        if (Double.compare(trip.getLatStartpoint(), getLatStartpoint()) != 0) return false;
        if (Double.compare(trip.getLongStartpoint(), getLongStartpoint()) != 0) return false;
        if (Double.compare(trip.getLatEndpoint(), getLatEndpoint()) != 0) return false;
        if (Double.compare(trip.getLongEndpoint(), getLongEndpoint()) != 0) return false;
        if (Double.compare(trip.getPrice(), getPrice()) != 0) return false;
        if (getDistanceMeters() != trip.getDistanceMeters()) return false;
        if (getDurationSeconds() != trip.getDurationSeconds()) return false;
        if (getStartpoint() != null ? !getStartpoint().equals(trip.getStartpoint()) : trip.getStartpoint() != null)
            return false;
        if (getEndpoint() != null ? !getEndpoint().equals(trip.getEndpoint()) : trip.getEndpoint() != null)
            return false;
        if (getStartDateTime() != null ? !getStartDateTime().equals(trip.getStartDateTime()) : trip.getStartDateTime() != null)
            return false;
        if (getEndDateTime() != null ? !getEndDateTime().equals(trip.getEndDateTime()) : trip.getEndDateTime() != null)
            return false;
        if (getOverViewPath() != null ? !getOverViewPath().equals(trip.getOverViewPath()) : trip.getOverViewPath() != null)
            return false;
        if (getViaWaypoints() != null ? !getViaWaypoints().equals(trip.getViaWaypoints()) : trip.getViaWaypoints() != null)
            return false;
        if (getNumberOfSeats() != null ? !getNumberOfSeats().equals(trip.getNumberOfSeats()) : trip.getNumberOfSeats() != null)
            return false;
        if (getVehicle() != null ? !getVehicle().equals(trip.getVehicle()) : trip.getVehicle() != null)
            return false;
        if (getDriver() != null ? !getDriver().equals(trip.getDriver()) : trip.getDriver() != null)
            return false;
        return !(getCancelationDateTime() != null ? !getCancelationDateTime().equals(trip.getCancelationDateTime()) : trip.getCancelationDateTime() != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (getStartpoint() != null ? getStartpoint().hashCode() : 0);
        result = 31 * result + (getEndpoint() != null ? getEndpoint().hashCode() : 0);
        temp = Double.doubleToLongBits(getLatStartpoint());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getLongStartpoint());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getLatEndpoint());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getLongEndpoint());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getStartDateTime() != null ? getStartDateTime().hashCode() : 0);
        result = 31 * result + (getEndDateTime() != null ? getEndDateTime().hashCode() : 0);
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getOverViewPath() != null ? getOverViewPath().hashCode() : 0);
        result = 31 * result + (getViaWaypoints() != null ? getViaWaypoints().hashCode() : 0);
        result = 31 * result + (getNumberOfSeats() != null ? getNumberOfSeats().hashCode() : 0);
        result = 31 * result + (getVehicle() != null ? getVehicle().hashCode() : 0);
        result = 31 * result + (getDriver() != null ? getDriver().hashCode() : 0);
        result = 31 * result + (getCancelationDateTime() != null ? getCancelationDateTime().hashCode() : 0);
        result = 31 * result + (int) (getDistanceMeters() ^ (getDistanceMeters() >>> 32));
        result = 31 * result + (int) (getDurationSeconds() ^ (getDurationSeconds() >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "startpoint='" + startpoint + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", latStartpoint=" + latStartpoint +
                ", longStartpoint=" + longStartpoint +
                ", latEndpoint=" + latEndpoint +
                ", longEndpoint=" + longEndpoint +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", price=" + price +
                ", overViewPath='...'" +
                ", viaWaypoints='...'" +
                ", numberOfSeats=" + numberOfSeats +
                ", vehicle=" + vehicle +
                ", driver=" + driver +
                ", cancelationDateTime=" + cancelationDateTime +
                ", distanceMeters=" + distanceMeters +
                ", durationSeconds=" + durationSeconds +
                "} " + super.toString();
    }
}
