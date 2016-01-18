package jumpup.imi.fb4.htw.de.jumpupandroid.login;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;
import jumpup.imi.fb4.htw.de.jumpupandroid.entity.mapper.JsonMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.entity.mapper.MapperFactory;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.Endpoints;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.JumpUpRequest;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.ResponseReader;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.Versions;

/**
 * Project: jumpup_android
 * <p/>
 * This class models a LoginRequest that is sent to the JumpUp mobile REST service.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since ${date}
 */
public class LoginRequest extends JumpUpRequest {
    private final String TAG = LoginRequest.class.getName();
    private final String TARGET_VERSION = Versions.V1_0_0.getUrlPart();
    private final String ENDPOINT = Endpoints.USER;

    @Override
    protected boolean isPublicAction() {
        return false;
    }

    @Override
    protected String getTargetVersion() {
        return TARGET_VERSION;
    }

    @Override
    protected String getEndpointUrl() {
        return ENDPOINT;
    }

    @Override
    protected JsonMapper getResponseMapper() {
        return MapperFactory.newUserMapper();
    }

    /**
     * Trigger the login.
     * @param username the username
     * @param password the password
     * @return UserMapper or null on connection or response handling errors
     */
    @Nullable
    public User triggerLogin(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);

        Uri loginUri = Uri.parse(this.getUrl());
        HttpURLConnection urlConnection = null;

        try {
            URL loginUrl = new URL(loginUri.toString());

            try {
                urlConnection = buildConnection(loginUrl, "GET");
                urlConnection.connect();

                String response = ResponseReader.read(urlConnection);

                Log.d(TAG, "triggerLogin(): got response: " + response);
                return (User) mapResponse(response);
            } catch (IOException | JSONException connectionException) {
                Log.e(TAG, "triggerLogin(): could not login user. Exception: " + connectionException.getMessage()
                + "\nStack trace:\n" + ExceptionUtils.getStackTrace(connectionException));
                return null;
            } finally {
                if (null != urlConnection) {  try { urlConnection.disconnect(); } catch (Exception e2) { /* ignore */ } }
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, "triggerLogin(): could not login user. Exception: " + e.getMessage());
        }

        return null;
    }
}
