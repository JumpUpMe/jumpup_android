package jumpup.imi.fb4.htw.de.jumpupandroid.registration.entity;

/**
 * Project: jumpup_android
 * <p/>
 * Registration entity that is serialized and sent to the web service endpoint.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.01.2016
 */
public class Registration {
    public static final String FIELD_NAME_USERNAME = "username";
    public static final String FIELD_NAME_EMAIL = "eMail";
    public static final String FIELD_NAME_PRENAME = "prename";
    public static final String FIELD_NAME_LASTNAME = "lastname";
    public static final String FIELD_NAME_PASSWORD = "password";
    public static final String FIELD_NAME_CONFIRM_PASSWORD = "confirmPassword";

    private String username;
    private String eMail;
    private String password;
    private String prename;
    private String lastname;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrename() {
        return prename;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Registration that = (Registration) o;

        if (getUsername() != null ? !getUsername().equals(that.getUsername()) : that.getUsername() != null)
            return false;
        if (geteMail() != null ? !geteMail().equals(that.geteMail()) : that.geteMail() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(that.getPassword()) : that.getPassword() != null)
            return false;
        if (getPrename() != null ? !getPrename().equals(that.getPrename()) : that.getPrename() != null)
            return false;
        return !(getLastname() != null ? !getLastname().equals(that.getLastname()) : that.getLastname() != null);

    }

    @Override
    public int hashCode() {
        int result = getUsername() != null ? getUsername().hashCode() : 0;
        result = 31 * result + (geteMail() != null ? geteMail().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getPrename() != null ? getPrename().hashCode() : 0);
        result = 31 * result + (getLastname() != null ? getLastname().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Registration{");
        sb.append("username='").append(username).append('\'');
        sb.append(", eMail='").append(eMail).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", prename='").append(prename).append('\'');
        sb.append(", lastname='").append(lastname).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
