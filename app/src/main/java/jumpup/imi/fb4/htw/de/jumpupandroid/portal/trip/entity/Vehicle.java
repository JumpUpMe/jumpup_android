package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity;

import android.os.Parcel;
import android.os.Parcelable;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.AbstractEntity;

/**
 * Project: jumpup_android
 * <p/>
 * A vehicle. This is currently a stub since the webapp doesn't offer vehicles yet. TODO
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 26.01.2016
 */
public class Vehicle extends AbstractEntity {
    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Vehicle> CREATOR = new Parcelable.Creator<Vehicle>() {
        public Vehicle createFromParcel(Parcel in) {
            return new Vehicle(in);
        }

        public Vehicle[] newArray(int size) {
            return new Vehicle[size];
        }
    };

    public Vehicle() {
        super();
    }

    public Vehicle(Parcel in) {
        super();

        this.initializeFromParcel(in);
    }

    @Override
    public int describeContents() {
        // TODO
        return 0;
    }
}
