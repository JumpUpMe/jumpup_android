package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.search;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search.SingleTripQueryResult;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search.TripQueryResults;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.AppUtility;

/**
 * Project: jumpup_android
 * <p/>
 * Custom array adapter for single trip query results.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 24.02.2016
 */
public class TripsSingleTripQueryResultAdapter extends ArrayAdapter<SingleTripQueryResult> {
    public TripsSingleTripQueryResultAdapter(Context context) {
        super(context, R.layout.list_item_trip);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        setTripEntryLabel(position, view);

        return view;
    }

    protected void setTripEntryLabel(int position, View view) {
        Trip currentTrip = getItem(position).getTrip();

        TextView tripEntry = (TextView) view.findViewById(R.id.txtOfferedTripEntry);
        tripEntry.setText(new StringBuilder().append("#").append(position).append(": ")
                .append(AppUtility.formatDateTime(currentTrip.getStartDateTime())).append(" ")
                .append(currentTrip.getStartpoint()).append(" -> ").append(currentTrip.getEndpoint()).toString());
    }
}
