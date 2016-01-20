package jumpup.imi.fb4.htw.de.jumpupandroid.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Project: jumpup_android
 * <p/>
 * Base class for all JumpUp entities.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 18.01.2016
 */
public abstract class AbstractEntity implements Parcelable {
    public static final String FIELD_NAME_IDENTITY = "identity";
    public static final String FIELD_NAME_CREATION_TIMESTAMP = "creationTimestamp";
    public static final String FIELD_NAME_UPDATE_TIMESTAMP = "updateTimestamp";

    private Long identity;
    private Long creationTimestamp;
    private Long updateTimestamp;

    public void setIdentity(Long identity)
    {
        this.identity = identity;
    }

    @SuppressWarnings("WeakerAccess")
    public Long getIdentity()
    {
        return identity;
    }

    public void setCreationTimestamp(Long creationTimestamp)
    {
        this.creationTimestamp = creationTimestamp;
    }

    @SuppressWarnings("WeakerAccess")
    public Long getCreationTimestamp()
    {
        return creationTimestamp;
    }

    public void setUpdateTimestamp(Long updateTimestamp)
    {
        this.updateTimestamp = updateTimestamp;
    }

    @SuppressWarnings("WeakerAccess")
    public Long getUpdateTimestamp()
    {
        return updateTimestamp;
    }

    @SuppressWarnings("SimplifiableIfStatement")
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

    @SuppressWarnings("StringBufferReplaceableByString")
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractEntity{");
        sb.append("identity=").append(identity);
        sb.append(", creationTimestamp=").append(creationTimestamp);
        sb.append(", updateTimestamp=").append(updateTimestamp);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.identity);
        parcel.writeLong(this.creationTimestamp);
        parcel.writeLong(this.updateTimestamp);
    }

    public void initializeFromParcel(Parcel in) {
        this.identity = in.readLong();
        this.creationTimestamp = in.readLong();
        this.updateTimestamp = in.readLong();
    }
}
