/**
 * JumpUp.Me Car Pooling Application
 *
 * Copyright (c) 2014-2015 Sebastian Renner, Marco Seidler, Sascha Feldmann
 */
package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search;


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
