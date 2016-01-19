package jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.response;

import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.ErrorResponseException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.ErrorResponseMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.JsonMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.MapperFactory;

/**
 * Project: jumpup_android
 * <p>
 * The reader takes an URLConnection, reads the buffered response and returns the complete response String.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since ${date}
 */
public class ResponseReaderImpl implements ResponseReader {
    private static final JsonMapper<ErrorResponse> ERROR_RESPONSE_MAPPER = MapperFactory.newErrorResponseMapper();
    private static final String TAG = ResponseReaderImpl.class.getName();
    private int defaultErrorMessageId;

    public void setDefaultErrorMessageId(int defaultErrorMessageId) {
        this.defaultErrorMessageId = defaultErrorMessageId;
    }

    @SuppressWarnings({"CaughtExceptionImmediatelyRethrown", "StringBufferMayBeStringBuilder"})
    @Nullable
    @Override
    public String read(HttpURLConnection urlConnection) throws IOException, ErrorResponseException {
        InputStream inputStream = null;

        try {
            // Read the input stream into a String
            inputStream = urlConnection.getInputStream();
        } catch (IOException e) {
            throw this.throwErrorResponseException(urlConnection);
        }

        return buildStringFromInputStream(inputStream);
    }

    private String buildStringFromInputStream(InputStream inputStream) throws IOException {
        StringBuffer buffer = new StringBuffer();

        if (inputStream == null) {
            // Nothing to do.
            return null;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line).append("\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }

            return buffer.toString();
        } catch (IOException e) {
            throw e;
        } finally {
            try { reader.close(); } catch (Exception e2 ){ /*ignore*/ }
        }
    }

    private ErrorResponseException throwErrorResponseException(HttpURLConnection urlConnection) throws IOException {
        InputStream errorInputStream = urlConnection.getErrorStream();

        String errorResponseString = this.buildStringFromInputStream(errorInputStream);
        int responseStatusCode = urlConnection.getResponseCode();

        // buildErrorResponse object
        try {
            ErrorResponse errorResponseObject = ERROR_RESPONSE_MAPPER.mapResponse(errorResponseString);

            return this.buildErrorResponseException(responseStatusCode, errorResponseString, errorResponseObject);
        } catch (JSONException e) {
            Log.e(TAG, "throwErrorResponseException(): couldn't build an error response object from raw response: " + errorResponseString);
            // we can't build an errorResponseObject
            return this.buildErrorResponseException(responseStatusCode, errorResponseString);
        }
    }

    private ErrorResponseException buildErrorResponseException(int responseStatusCode, String errorResponseString, ErrorResponse errorResponseObject) {
        return new ErrorResponseException(responseStatusCode, errorResponseString, errorResponseObject, defaultErrorMessageId);
    }

    private ErrorResponseException buildErrorResponseException(int responseStatusCode, String errorResponse) {
        return new ErrorResponseException(responseStatusCode, errorResponse, new ErrorResponse(), defaultErrorMessageId);
    }
}
