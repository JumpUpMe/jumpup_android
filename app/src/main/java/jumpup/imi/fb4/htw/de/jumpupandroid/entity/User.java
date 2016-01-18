package jumpup.imi.fb4.htw.de.jumpupandroid.entity;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since ${date}
 */
@SuppressWarnings("WeakerAccess")
public class User extends AbstractEntity {
    public static final String FIELD_NAME_USERNAME = "username";
    public static final String FIELD_EMAIL = "eMail";
    public static final String FIELD_PRENAME = "prename";
    public static final String FIELD_LASTNAME = "lastname";
    public static final String FIELD_TOWN = "town";
    public static final String FIELD_COUNTRY = "country";
    public static final String FIELD_DATE_OF_BIRTH = "dateOfBirth";
    public static final String FIELD_PLACE_OF_BIRTH = "placeOfBirth";
    public static final String FIELD_MOBILE_NUMBER = "mobileNumber";
    public static final String FIELD_SKYPE = "skype";
    public static final String FIELD_LOCALE = "locale";
    public static final String FIELD_IS_CONFIRMED = "isConfirmed";
    public static final String FIELD_GENDER = "gender";

    private String username;
    private String eMail;
    private String prename;
    private String lastname;
    private String town;
    private String country;
    private String locale;
    private Boolean isConfirmed;
    private Long dateOfBirth;
    private String placeOfBirth;
    private String gender;
    private String mobileNumber;
    private String skype;

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }

    public void seteMail(String eMail)
    {
        this.eMail = eMail;
    }

    public String geteMail()
    {
        return eMail;
    }

    public void setPrename(String prename)
    {
        this.prename = prename;
    }

    public String getPrename()
    {
        return prename;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setTown(String town)
    {
        this.town = town;
    }

    public String getTown()
    {
        return town;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getCountry()
    {
        return country;
    }

    public void setLocale(String locale)
    {
        this.locale = locale;
    }

    public String getLocale()
    {
        return locale;
    }

    public void setIsConfirmed(Boolean isConfirmed)
    {
        this.isConfirmed = isConfirmed;
    }

    public Boolean getIsConfirmed()
    {
        return isConfirmed;
    }

    public void setDateOfBirth(Long dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth)
    {
        this.placeOfBirth = placeOfBirth;
    }

    public String getPlaceOfBirth()
    {
        return placeOfBirth;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getGender()
    {
        return gender;
    }

    public void setMobileNumber(String mobileNumber)
    {
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber()
    {
        return mobileNumber;
    }

    public void setSkype(String skype)
    {
        this.skype = skype;
    }

    public String getSkype()
    {
        return skype;
    }

    @SuppressWarnings({"ConstantConditions", "SimplifiableIfStatement"})
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (getUsername() != null ? !getUsername().equals(user.getUsername()) : user.getUsername() != null)
            return false;
        if (geteMail() != null ? !geteMail().equals(user.geteMail()) : user.geteMail() != null)
            return false;
        if (getPrename() != null ? !getPrename().equals(user.getPrename()) : user.getPrename() != null)
            return false;
        if (getLastname() != null ? !getLastname().equals(user.getLastname()) : user.getLastname() != null)
            return false;
        if (getTown() != null ? !getTown().equals(user.getTown()) : user.getTown() != null)
            return false;
        if (getCountry() != null ? !getCountry().equals(user.getCountry()) : user.getCountry() != null)
            return false;
        if (getLocale() != null ? !getLocale().equals(user.getLocale()) : user.getLocale() != null)
            return false;
        if (getIsConfirmed() != null ? !getIsConfirmed().equals(user.getIsConfirmed()) : user.getIsConfirmed() != null)
            return false;
        if (getDateOfBirth() != null ? !getDateOfBirth().equals(user.getDateOfBirth()) : user.getDateOfBirth() != null)
            return false;
        if (getPlaceOfBirth() != null ? !getPlaceOfBirth().equals(user.getPlaceOfBirth()) : user.getPlaceOfBirth() != null)
            return false;
        if (getGender() != null ? !getGender().equals(user.getGender()) : user.getGender() != null)
            return false;
        if (getMobileNumber() != null ? !getMobileNumber().equals(user.getMobileNumber()) : user.getMobileNumber() != null)
            return false;
        return !(getSkype() != null ? !getSkype().equals(user.getSkype()) : user.getSkype() != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (geteMail() != null ? geteMail().hashCode() : 0);
        result = 31 * result + (getPrename() != null ? getPrename().hashCode() : 0);
        result = 31 * result + (getLastname() != null ? getLastname().hashCode() : 0);
        result = 31 * result + (getTown() != null ? getTown().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + (getLocale() != null ? getLocale().hashCode() : 0);
        result = 31 * result + (getIsConfirmed() != null ? getIsConfirmed().hashCode() : 0);
        result = 31 * result + (getDateOfBirth() != null ? getDateOfBirth().hashCode() : 0);
        result = 31 * result + (getPlaceOfBirth() != null ? getPlaceOfBirth().hashCode() : 0);
        result = 31 * result + (getGender() != null ? getGender().hashCode() : 0);
        result = 31 * result + (getMobileNumber() != null ? getMobileNumber().hashCode() : 0);
        result = 31 * result + (getSkype() != null ? getSkype().hashCode() : 0);
        return result;
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("username='").append(username).append('\'');
        sb.append(", eMail='").append(eMail).append('\'');
        sb.append(", prename='").append(prename).append('\'');
        sb.append(", lastname='").append(lastname).append('\'');
        sb.append(", town='").append(town).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", locale='").append(locale).append('\'');
        sb.append(", isConfirmed=").append(isConfirmed);
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append(", placeOfBirth='").append(placeOfBirth).append('\'');
        sb.append(", gender='").append(gender).append('\'');
        sb.append(", mobileNumber='").append(mobileNumber).append('\'');
        sb.append(", skype='").append(skype).append('\'');
        sb.append('}');
        sb.append(super.toString());

        return sb.toString();
    }
}
