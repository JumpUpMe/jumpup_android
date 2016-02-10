package jumpup.imi.fb4.htw.de.jumpupandroid.util.map;

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

    public void setShowInfoWindows(boolean showInfoWindows) {
        this.showInfoWindows = showInfoWindows;
    }

    public boolean isShowInfoWindows() {
        return showInfoWindows;
    }
}
