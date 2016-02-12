package jumpup.imi.fb4.htw.de.jumpupandroid.util.map.adapter;

import android.view.View;
import android.widget.TextView;

/**
 * Project: jumpup_android
 * <p/>
 * Simple object to define options that the MapAdapter uses.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 10.02.2016
 */
public class MapOptions {
    private String startLocationLabel;
    private String destinationLocationLabel;
    int color;
    boolean showInfoWindows;
    private View infoWindowView;

    public MapOptions setInfoWindowView(View infoWindowView) {
        this.infoWindowView = infoWindowView;

        return this;
    }

    public View getInfoWindowView() {
        return infoWindowView;
    }

    public MapOptions setStartLocationLabel(String startLocationLabel) {
        this.startLocationLabel = startLocationLabel;

        return this;
    }

    public String getStartLocationLabel() {
        return startLocationLabel;
    }

    public MapOptions setDestinationLocationLabel(String destinationLocationLabel) {
        this.destinationLocationLabel = destinationLocationLabel;

        return this;
    }

    public String getDestinationLocationLabel() {
        return destinationLocationLabel;
    }

    public MapOptions setColor(int color) {
        this.color = color;

        return this;
    }

    public int getColor() {
        return color;
    }

    public MapOptions setShowInfoWindows(boolean showInfoWindows) {
        this.showInfoWindows = showInfoWindows;

        return this;
    }

    public boolean isShowInfoWindows() {
        return showInfoWindows;
    }
}
