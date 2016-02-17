package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.database;

/**
 * Project: jumpup_android
 * <p/>
 * Simple model for trips meta info.
 *
 * The meta info row in the trips database contains meta information about the synchronization, e.g.
 * timestamp of the last synchronization, the user, etc.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 17.02.2016
 */
public class TripMetaInfo {
    protected long lastSyncDateTime;
    protected long userId;

    public void setLastSyncDateTime(long lastSyncDateTime) {
        this.lastSyncDateTime = lastSyncDateTime;
    }

    public long getLastSyncDateTime() {
        return lastSyncDateTime;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TripMetaInfo that = (TripMetaInfo) o;

        if (getLastSyncDateTime() != that.getLastSyncDateTime()) return false;
        return getUserId() == that.getUserId();

    }

    @Override
    public int hashCode() {
        int result = (int) (getLastSyncDateTime() ^ (getLastSyncDateTime() >>> 32));
        result = 31 * result + (int) (getUserId() ^ (getUserId() >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TripMetaInfo{");
        sb.append("lastSyncDateTime=").append(lastSyncDateTime);
        sb.append(", userId=").append(userId);
        sb.append('}');
        return sb.toString();
    }
}
