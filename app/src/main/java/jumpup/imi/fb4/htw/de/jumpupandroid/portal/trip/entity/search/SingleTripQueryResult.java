/**
 * JumpUp.Me Car Pooling Application
 *
 * Copyright (c) 2014-2016 Sascha Feldmann
 */
package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search;

import java.io.Serializable;
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
public class SingleTripQueryResult implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -3179074383171618295L;
    public static final String FIELD_NAME_DRIVER = "driver";
    public static final String FIELD_NAME_VEHICLE = "vehicle";
    public static final String FIELD_NAME_TRIP = "trip";

    public class Driver implements Serializable
    {
        /**
         * 
         */
        private static final long serialVersionUID = 5450451948907569162L;
        public static final String FIELD_NAME_USERNAME = "username";
        public static final String FIELD_NAME_EMAIL = "email";
        public static final String FIELD_NAME_PRENAME = "prename";
        public static final String FIELD_NAME_LASTNAME = "lastname";
        public static final String FIELD_NAME_TOWN = "town";
        public static final String FIELD_NAME_COUNTRY = "country";
        public static final String FIELD_NAME_GENDER = "gender";
        public static final String FIELD_NAME_MOBILE_NUMBER = "mobileNumber";
        public static final String FIELD_NAME_SKYPE = "skype";
        public static final String FIELD_NAME_SPOKEN_LANGUAGES = "spokenLanguages";
        public static final String FIELD_NAME_URL = "url";

        protected Integer id;
        protected String username;
        protected String email;
        protected String prename;   
        protected String lastname;
        protected String town;
        protected String country;
        protected String gender;
        protected String mobileNumber;
        protected String skype;
        protected Set<String> spokenLanguages;
        protected String url;

        public void setUrl(String url)
        {
            this.url = url;
        }

        public String getUrl()
        {
            return url;
        }

        /**
         * @return the username
         */
        public String getUsername()
        {
            return username;
        }

        /**
         * @param username
         *            the username to set
         */
        public void setUsername(String username)
        {
            this.username = username;
        }

        /**
         * @return the email
         */
        public String getEmail()
        {
            return email;
        }

        /**
         * @param email
         *            the email to set
         */
        public void setEmail(String email)
        {
            this.email = email;
        }

        /**
         * @return the prename
         */
        public String getPrename()
        {
            return prename;
        }

        /**
         * @param prename
         *            the prename to set
         */
        public void setPrename(String prename)
        {
            this.prename = prename;
        }

        /**
         * @return the lastname
         */
        public String getLastname()
        {
            return lastname;
        }

        /**
         * @param lastname
         *            the lastname to set
         */
        public void setLastname(String lastname)
        {
            this.lastname = lastname;
        }

        /**
         * @return the town
         */
        public String getTown()
        {
            return town;
        }

        /**
         * @param town
         *            the town to set
         */
        public void setTown(String town)
        {
            this.town = town;
        }

        /**
         * @return the country
         */
        public String getCountry()
        {
            return country;
        }

        /**
         * @param country
         *            the country to set
         */
        public void setCountry(String country)
        {
            this.country = country;
        }

        /**
         * @return the gender
         */
        public String getGender()
        {
            return gender;
        }

        /**
         * @param gender
         *            the gender to set
         */
        public void setGender(String gender)
        {
            this.gender = gender;
        }

        /**
         * @return the mobileNumber
         */
        public String getMobileNumber()
        {
            return mobileNumber;
        }

        /**
         * @param mobileNumber
         *            the mobileNumber to set
         */
        public void setMobileNumber(String mobileNumber)
        {
            this.mobileNumber = mobileNumber;
        }

        /**
         * @return the skype
         */
        public String getSkype()
        {
            return skype;
        }

        /**
         * @param skype
         *            the skype to set
         */
        public void setSkype(String skype)
        {
            this.skype = skype;
        }

        /**
         * @return the spokenLanguages
         */
        public Set<String> getSpokenLanguages()
        {
            return spokenLanguages;
        }

        /**
         * @param spokenLanguages
         *            the spokenLanguages to set
         */
        public void setSpokenLanguages(Set<String> spokenLanguages)
        {
            this.spokenLanguages = spokenLanguages;
        }
    }

    protected Driver driver = new Driver();
    protected Vehicle vehicle;
    protected Trip trip;

    /**
     * @return the driver
     */
    public Driver getDriver()
    {
        return driver;
    }

    /**
     * @return the vehicle
     */
    public Vehicle getVehicle()
    {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * @return the trip
     */
    public Trip getTrip()
    {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
