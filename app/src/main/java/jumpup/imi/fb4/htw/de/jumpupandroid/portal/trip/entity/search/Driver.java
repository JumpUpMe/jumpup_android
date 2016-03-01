package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 24.02.2016
 */
public class Driver implements Serializable, Parcelable {
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

    // this is used to regenerate the object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Driver> CREATOR = new Parcelable.Creator<Driver>() {
        public Driver createFromParcel(Parcel in) {
            return new Driver(in);
        }

        public Driver[] newArray(int size) {
            return new Driver[size];
        }
    };

    public Driver(Parcel in) {
        super();

        this.initializeFromParcel(in);
    }

    public Driver() {
        super();
    }

    private void initializeFromParcel(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.username = in.readString();
        this.email = in.readString();
        this.prename = in.readString();
        this.lastname = in.readString();
        this.town = in.readString();
        this.country = in.readString();
        this.gender = in.readString();
        this.mobileNumber = in.readString();
        this.skype = in.readString();
        this.spokenLanguages = toStringHashSet(in.readArray(String.class.getClassLoader()));
        this.url = in.readString();
    }

    private Set<String> toStringHashSet(Object[] objects) {
        Set<String> stringHashSet = new HashSet<>();

        for (Object stringObj: objects) {
            stringHashSet.add((String) stringObj);
        }

        return stringHashSet;
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
        parcel.writeValue(id);
        parcel.writeString(username);
        parcel.writeString(email);
        parcel.writeString(prename);
        parcel.writeString(lastname);
        parcel.writeString(town);
        parcel.writeString(country);
        parcel.writeString(gender);
        parcel.writeString(mobileNumber);
        parcel.writeString(skype);
        parcel.writeArray(spokenLanguages.toArray(new String[spokenLanguages.size()]));
        parcel.writeString(url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the prename
     */
    public String getPrename() {
        return prename;
    }

    /**
     * @param prename the prename to set
     */
    public void setPrename(String prename) {
        this.prename = prename;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the town
     */
    public String getTown() {
        return town;
    }

    /**
     * @param town the town to set
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * @param mobileNumber the mobileNumber to set
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * @return the skype
     */
    public String getSkype() {
        return skype;
    }

    /**
     * @param skype the skype to set
     */
    public void setSkype(String skype) {
        this.skype = skype;
    }

    /**
     * @return the spokenLanguages
     */
    public Set<String> getSpokenLanguages() {
        return spokenLanguages;
    }

    /**
     * @param spokenLanguages the spokenLanguages to set
     */
    public void setSpokenLanguages(Set<String> spokenLanguages) {
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
