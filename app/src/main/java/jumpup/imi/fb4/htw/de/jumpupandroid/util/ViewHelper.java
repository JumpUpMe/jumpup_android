package jumpup.imi.fb4.htw.de.jumpupandroid.util;

import android.widget.EditText;

/**
 * Project: jumpup_android
 * <p/>
 * View util class for convenience methods.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 20.01.2016
 */
public class ViewHelper {

    /**
     * Takes an input element, resets the text to the empty string for exactly one time.
     * <p/>
     * Use case: we show input field labels on a field. If the user wants to do an input,
     * the label is removed (input field is reset).
     *
     * @param edInput the input element
     */
    public static void addClickListenerToEmptyInputFieldsOnClick(final EditText edInput) {
        edInput.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(android.view.View view, boolean hasFocus) {
                if (hasFocus) {
                    edInput.setText("");
                    edInput.setOnClickListener(null);
                }
            }
        });
    }
}
