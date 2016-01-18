package jumpup.imi.fb4.htw.de.jumpupandroid.entity;

/**
 * Project: jumpup_android
 * <p/>
 * Base class for all JumpUp entities.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 18.01.2016
 */
public class AbstractEntity {
    public static final String FIELD_NAME_IDENTITY = "identity";
    public static final String FIELD_NAME_CREATION_TIMESTAMP = "creationTimestamp";
    public static final String FIELD_NAME_UPDATE_TIMESTAMP = "updateTimestamp";

    protected Long identity;
    protected Long creationTimestamp;
    protected Long updateTimestamp;

    public void setIdentity(Long identity)
    {
        this.identity = identity;
    }

    public Long getIdentity()
    {
        return identity;
    }

    public void setCreationTimestamp(Long creationTimestamp)
    {
        this.creationTimestamp = creationTimestamp;
    }

    public Long getCreationTimestamp()
    {
        return creationTimestamp;
    }

    public void setUpdateTimestamp(Long updateTimestamp)
    {
        this.updateTimestamp = updateTimestamp;
    }

    public Long getUpdateTimestamp()
    {
        return updateTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity that = (AbstractEntity) o;

        if (getIdentity() != null ? !getIdentity().equals(that.getIdentity()) : that.getIdentity() != null)
            return false;
        if (getCreationTimestamp() != null ? !getCreationTimestamp().equals(that.getCreationTimestamp()) : that.getCreationTimestamp() != null)
            return false;
        return !(getUpdateTimestamp() != null ? !getUpdateTimestamp().equals(that.getUpdateTimestamp()) : that.getUpdateTimestamp() != null);

    }

    @Override
    public int hashCode() {
        int result = getIdentity() != null ? getIdentity().hashCode() : 0;
        result = 31 * result + (getCreationTimestamp() != null ? getCreationTimestamp().hashCode() : 0);
        result = 31 * result + (getUpdateTimestamp() != null ? getUpdateTimestamp().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractEntity{");
        sb.append("identity=").append(identity);
        sb.append(", creationTimestamp=").append(creationTimestamp);
        sb.append(", updateTimestamp=").append(updateTimestamp);
        sb.append('}');
        return sb.toString();
    }
}
