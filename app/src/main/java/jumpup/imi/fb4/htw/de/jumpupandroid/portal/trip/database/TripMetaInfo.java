package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.database;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;

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
    private static final long SYNC_DELTA_MILLISECONDS = 60 * 60 * 1000; // 60 minutes

    protected long lastSyncTimestampSeconds;
    protected long userId;

    public void setLastSyncTimestampSeconds(long lastSyncTimestampSeconds) {
        this.lastSyncTimestampSeconds = lastSyncTimestampSeconds;
    }

    public long getLastSyncTimestampSeconds() {
        return lastSyncTimestampSeconds;
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

        if (getLastSyncTimestampSeconds() != that.getLastSyncTimestampSeconds()) return false;
        return getUserId() == that.getUserId();

    }

    @Override
    public int hashCode() {
        int result = (int) (getLastSyncTimestampSeconds() ^ (getLastSyncTimestampSeconds() >>> 32));
        result = 31 * result + (int) (getUserId() ^ (getUserId() >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TripMetaInfo{");
        sb.append("lastSyncTimestampSeconds=").append(lastSyncTimestampSeconds);
        sb.append(", userId=").append(userId);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Check whether the given user's trip list needs to be synchronized.
     * @param user
     * @return
     */
    public boolean needsToBeSynchronized(User user) {
        if (user.getIdentity().equals(getUserId()) || Math.abs(System.currentTimeMillis() / 1000 - getLastSyncTimestampSeconds()) > SYNC_DELTA_MILLISECONDS) {
            return true;
        }

        return false;
    }

    public static TripMetaInfo forUserAndTimestamp(User user) {
        TripMetaInfo tripMetaInfo = new TripMetaInfo();

        tripMetaInfo.setUserId(user.getIdentity());
        tripMetaInfo.setLastSyncTimestampSeconds(System.currentTimeMillis() / 1000);

        return tripMetaInfo;
    }
}
