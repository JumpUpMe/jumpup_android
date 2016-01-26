package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity;

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


}
