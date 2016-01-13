package jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice;

import android.util.Base64;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import jumpup.imi.fb4.htw.de.jumpupandroid.BuildConfig;

/**
 * Project: jumpup_android
 * <p/>
 * Abstract class for all requests that are sent to the JumpUp mobile API REST service.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since ${date}
 */
public abstract class JumpUpRequest {
    private static final String BASE_URL = BuildConfig.JUMPUP_REST_BASE_URL;
    private static final String TAG = JumpUpRequest.class.getName();
    private String builtUrl;
    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the targeted version URL part. E.g. "v1.0.0".
     * @return
     */
    protected abstract String getTargetVersion();

    /**
     * Get the endpoint URL part of the targeted service. E.g. "user".
     * @return
     */
    protected abstract String getEndpointUrl();

    /**
     * Check whether this action is public (does not need an authentication).
     * This means, the service will be available under a "public" URL part. E.G. http://groupelite.de:8080/jumpup/rest/v1.0.0/public/user/1.
     * @return
     */
    protected abstract boolean isPublicAction();

    /**
     * Get the base URL to the JumpUp REST service.
     * This does not include the version. Use getTargetVersion().
     * @return
     */
    public String getBaseUrl()
    {
        return BASE_URL;
    }

    /**
     * Get the complete URL to address the JumpUp REST service.
     * @return
     */
    public String getUrl()
    {
        if (null == builtUrl) {
            builtUrl = buildUrl();
        }

        return builtUrl;
    }

    private String buildUrl() {
        return getBaseUrl() + "/" + getTargetVersion() + this.addPublicPart() + "/" + getEndpointUrl();
    }

    private String addPublicPart() {
        if (isPublicAction()) {
            return "/" + "public";
        }

        return "";
    }

    protected HttpURLConnection buildConnection(URL url, String requestMethod) throws IOException {
        Log.v(TAG, "buildConnection(): will connect to URL " + url );

        HttpURLConnection urlConnection;
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(requestMethod);

        if (!this.isPublicAction()) {
            this.addAuthorizationHeader(urlConnection);
        }

        return urlConnection;
    }

    private void addAuthorizationHeader(HttpURLConnection urlConnection) {
        if (null == this.username || null == this.password) {
            throw new IllegalArgumentException("addAuthorizationHeader(): no public action, but you didn't set any username and/or password.");
        }

        byte[] encodedBase64 = Base64.encode((this.username + ":" + this.password).getBytes(), Base64.DEFAULT);
        String strEncodedBase64 = new String(encodedBase64);

        urlConnection.setRequestProperty("Authorization", "Basic " + strEncodedBase64);
    }


}
