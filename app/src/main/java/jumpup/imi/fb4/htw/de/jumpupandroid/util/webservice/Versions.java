package jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice;

/**
 * Project: jumpup_android
 * <p/>
 *
 * Maintain available API versions here.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since ${date}
 */
public enum Versions {
    V1_0_0 {
        public String getUrlPart() {
            return "v1.0.0";
        }
    };

    /**
     * Get the version URL part of the current version (e.g. only "v1.0.0" in http://groupelite.de:8080/jumpup/rest/v1.0.0/public/user/1.
     * @return  URL part of the curren version (e.g. only "v1.0.0")
     */
    public String getUrlPart() {
        return "";
    }
}
