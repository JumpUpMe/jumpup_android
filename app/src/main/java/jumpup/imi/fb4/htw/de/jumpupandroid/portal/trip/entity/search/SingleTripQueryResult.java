/**
 * JumpUp.Me Car Pooling Application
 *
 * Copyright (c) 2014-2016 Sascha Feldmann
 */
package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search;

import java.io.Serializable;
import java.util.HashSet;
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
        protected Set<String> spokenLanguages = new HashSet<>();
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Driver driver = (Driver) o;

            if (id != null ? !id.equals(driver.id) : driver.id != null) return false;
            if (getUsername() != null ? !getUsername().equals(driver.getUsername()) : driver.getUsername() != null)
                return false;
            if (getEmail() != null ? !getEmail().equals(driver.getEmail()) : driver.getEmail() != null)
                return false;
            if (getPrename() != null ? !getPrename().equals(driver.getPrename()) : driver.getPrename() != null)
                return false;
            if (getLastname() != null ? !getLastname().equals(driver.getLastname()) : driver.getLastname() != null)
                return false;
            if (getTown() != null ? !getTown().equals(driver.getTown()) : driver.getTown() != null)
                return false;
            if (getCountry() != null ? !getCountry().equals(driver.getCountry()) : driver.getCountry() != null)
                return false;
            if (getGender() != null ? !getGender().equals(driver.getGender()) : driver.getGender() != null)
                return false;
            if (getMobileNumber() != null ? !getMobileNumber().equals(driver.getMobileNumber()) : driver.getMobileNumber() != null)
                return false;
            if (getSkype() != null ? !getSkype().equals(driver.getSkype()) : driver.getSkype() != null)
                return false;
            if (getSpokenLanguages() != null ? !getSpokenLanguages().equals(driver.getSpokenLanguages()) : driver.getSpokenLanguages() != null)
                return false;
            return !(getUrl() != null ? !getUrl().equals(driver.getUrl()) : driver.getUrl() != null);

        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
            result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
            result = 31 * result + (getPrename() != null ? getPrename().hashCode() : 0);
            result = 31 * result + (getLastname() != null ? getLastname().hashCode() : 0);
            result = 31 * result + (getTown() != null ? getTown().hashCode() : 0);
            result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
            result = 31 * result + (getGender() != null ? getGender().hashCode() : 0);
            result = 31 * result + (getMobileNumber() != null ? getMobileNumber().hashCode() : 0);
            result = 31 * result + (getSkype() != null ? getSkype().hashCode() : 0);
            result = 31 * result + (getSpokenLanguages() != null ? getSpokenLanguages().hashCode() : 0);
            result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Driver{");
            sb.append("id=").append(id);
            sb.append(", username='").append(username).append('\'');
            sb.append(", email='").append(email).append('\'');
            sb.append(", prename='").append(prename).append('\'');
            sb.append(", lastname='").append(lastname).append('\'');
            sb.append(", town='").append(town).append('\'');
            sb.append(", country='").append(country).append('\'');
            sb.append(", gender='").append(gender).append('\'');
            sb.append(", mobileNumber='").append(mobileNumber).append('\'');
            sb.append(", skype='").append(skype).append('\'');
            sb.append(", spokenLanguages=").append(spokenLanguages);
            sb.append(", url='").append(url).append('\'');
            sb.append('}');
            return sb.toString();
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
