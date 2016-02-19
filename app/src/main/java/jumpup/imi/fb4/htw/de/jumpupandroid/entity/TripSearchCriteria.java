package jumpup.imi.fb4.htw.de.jumpupandroid.entity;

/**
 * Project: jumpup_android
 * <p/>
 * TripSearch criteria that is sent within the look for trips web service request.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.02.2016
 */
public class TripSearchCriteria extends AbstractEntity {
    protected String startPoint;
    protected Double latStartPoint;
    protected Double longStartPoint;
    protected Double latEndPoint;
    protected Double longEndPoint;
    protected String endPoint;

    protected String dateFrom;
    protected String dateTo;

    protected Float priceFrom;
    protected Float priceTo;

    protected Integer maxDistance;
    protected User passenger;

    /**
     * @return the startPoint
     */
    public String getStartPoint()
    {
        return startPoint;
    }

    /**
     * @param startPoint
     *            the startPoint to set
     */
    public void setStartPoint(String startPoint)
    {
        this.startPoint = startPoint;
    }

    /**
     * @return the latStartPoint
     */
    public Double getLatStartPoint()
    {
        return latStartPoint;
    }

    /**
     * @param latStartPoint
     *            the latStartPoint to set
     */
    public void setLatStartPoint(Double latStartPoint)
    {
        this.latStartPoint = latStartPoint;
    }

    /**
     * @return the longStartPoint
     */
    public Double getLongStartPoint()
    {
        return longStartPoint;
    }

    /**
     * @param longStartPoint
     *            the longStartPoint to set
     */
    public void setLongStartPoint(Double longStartPoint)
    {
        this.longStartPoint = longStartPoint;
    }

    /**
     * @return the latEndPoint
     */
    public Double getLatEndPoint()
    {
        return latEndPoint;
    }

    /**
     * @param latEndPoint
     *            the latEndPoint to set
     */
    public void setLatEndPoint(Double latEndPoint)
    {
        this.latEndPoint = latEndPoint;
    }

    /**
     * @return the longEndPoint
     */
    public Double getLongEndPoint()
    {
        return longEndPoint;
    }

    /**
     * @param longEndPoint
     *            the longEndPoint to set
     */
    public void setLongEndPoint(Double longEndPoint)
    {
        this.longEndPoint = longEndPoint;
    }

    /**
     * @return the endPoint
     */
    public String getEndPoint()
    {
        return endPoint;
    }

    /**
     * @param endPoint
     *            the endPoint to set
     */
    public void setEndPoint(String endPoint)
    {
        this.endPoint = endPoint;
    }

    /**
     * @return the dateFrom
     */
    public String getDateFrom()
    {
        return dateFrom;
    }

    /**
     * @param dateFrom
     *            the dateFrom to set
     */
    public void setDateFrom(String dateFrom)
    {
       this.dateFrom = dateFrom;
    }

    /**
     * @return the dateTo
     */
    public String getDateTo()
    {
        return dateTo;
    }

    /**
     * @param dateTo
     *            the dateTo to set
     */
    public void setDateTo(String dateTo)
    {
       this.dateTo = dateTo;
    }

    /**
     * @return the priceFrom
     */
    public Float getPriceFrom()
    {
        return priceFrom;
    }

    /**
     * @param priceFrom
     *            the priceFrom to set
     */
    public void setPriceFrom(Float priceFrom)
    {
        this.priceFrom = priceFrom;
    }

    /**
     * @return the priceTo
     */
    public Float getPriceTo()
    {
        return priceTo;
    }

    /**
     * @param priceTo
     *            the priceTo to set
     */
    public void setPriceTo(Float priceTo)
    {
        this.priceTo = priceTo;
    }

    /**
     * @return the maxDistance in kilometers
     */
    public Integer getMaxDistance()
    {
        return maxDistance;
    }

    /**
     * @param maxDistance
     *            the maxDistance in kilometers to set
     */
    public void setMaxDistance(Integer maxDistance)
    {
        this.maxDistance = maxDistance;
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
