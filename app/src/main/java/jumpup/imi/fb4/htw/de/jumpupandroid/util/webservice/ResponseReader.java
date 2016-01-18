package jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice;

import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;

/**
 * Project: jumpup_android
 * <p>
 * The reader takes an URLConnection, reads the buffered response and returns the complete response String.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since ${date}
 */
public class ResponseReader {

    @Nullable
    public static String read(URLConnection urlConnection) throws IOException {
        // Read the input stream into a String
        InputStream inputStream = urlConnection.getInputStream();

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
                buffer.append(line + "\n");
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
}
