package jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.response;

import java.io.IOException;
import java.net.HttpURLConnection;

import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.ErrorResponseException;

/**
 * Project: jumpup_android
 * <p/>
 * ResponseReader implementation take an HttpUrlConnection object, try to read the response and implement an error handling.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 18.01.2016
 */
public interface ResponseReader {

    /**
     * Try to read the response of the given urlConnection.
     *
     * If an error occurs, e.g. by a different HTTP status code than 200, throw an ErrorResponseException
     * by giving relevant information such as the status code and the error response.
     *
     * Otherwise, just return the response body as string.
     *
     * @param urlConnection the prepared HTTPUrlConnection object. Make sure to have everything set, e.g. the headers and request method.
     * @return String the response body on success
     * @throws IOException if an error occured during the buffering of the response
     * @throws ErrorResponseException if an error response was returned (status code is not equal to a Success status code)
     */
    String read(HttpURLConnection urlConnection) throws IOException, ErrorResponseException;
}
