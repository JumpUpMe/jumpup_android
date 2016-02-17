package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;

/**
 * Project: jumpup_android
 * <p/>
 * List of Trip entities.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 26.01.2016
 */
public class TripList extends ArrayList<Trip> implements Parcelable {
    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<TripList> CREATOR = new Parcelable.Creator<TripList>() {
        public TripList createFromParcel(Parcel in) {
            return new TripList(in);
        }

        public TripList[] newArray(int size) {
            return new TripList[size];
        }
    };

    public TripList()
    {
        super();
    }

    public TripList(Parcel in) {
        super();

        this.initializeFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        /*
         * ATTENTION!
         * When changing the sequence of write operations, make sure to adjust
         * the sequence of read operations in initializeFromParcel(), too.
         * Both must be symmetric.
         */
        parcel.writeParcelableArray(this.toArray(new Trip[this.size()]), i);
    }

    public void initializeFromParcel(Parcel in) {
          /*
         * ATTENTION!
         * When changing the sequence of read operations, make sure to adjust
         * the sequence of write operations in writeToParcel(), too.
         * Both must be symmetric.
         */
        Parcelable[] tripArray = in.readParcelableArray(Trip.class.getClassLoader());

        for (Parcelable trip: tripArray) {
            this.add((Trip) trip);
        }
    }

    /**
     * Apply the given limit
     * @param limit the limit to be applied. All indices greater than the limit will be removed.
     */
    public TripList applyLimit(int limit) {
        for (int i = limit; i < this.size(); i++) {
            this.remove(i);
        }

        return this;
    }
}
