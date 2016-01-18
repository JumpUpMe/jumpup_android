package jumpup.imi.fb4.htw.de.jumpupandroid.registration;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import jumpup.imi.fb4.htw.de.jumpupandroid.R;

public class RegistrationActivity extends ActionBarActivity {
    private static final String TAG = RegistrationActivity.class.getName();
    public static final String EXTRA_USERNAME = "extra_username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        this.prepareView();
    }

    private void prepareView() {
        fillUsernameIfNotDefault();
        fillEmailIfNotDefault();

        revealPassword();
        revealConfirmPassword();
    }

    private void fillUsernameIfNotDefault() {
        String extraUsername = this.getIntent().getStringExtra(EXTRA_USERNAME);
        if (null != extraUsername
                && ! extraUsername.equals(
                this.getString(R.string.fragment_main_email))
                && !isEmail(extraUsername)
        ) {
            EditText edUsername = getEditText(R.id.edUsername);
            edUsername.setText(extraUsername);
        }
    }

    private void fillEmailIfNotDefault() {
        String extraEMail = this.getIntent().getStringExtra(EXTRA_USERNAME);
        if (null != extraEMail
                && ! extraEMail.equals(
                this.getString(R.string.fragment_main_email))
                && isEmail(extraEMail)
        ) {
            EditText edMail = getEditText(R.id.edEMail);
            edMail.setText(extraEMail);
        }
    }

    private boolean isEmail(String stringExtra) {
        return Patterns.EMAIL_ADDRESS.matcher(stringExtra).matches();
    }

    private void revealPassword() {
        final EditText edPassword = getEditText(R.id.edPassword);
        edPassword.setTransformationMethod(null);

        edPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    Log.v(TAG, "revealPassword(): edPassword on focus listener...");

                    edPassword.setText("");
                    edPassword.setTransformationMethod(new PasswordTransformationMethod());
                    edPassword.setOnClickListener(null);
                }
            }
        });
    }

    private void revealConfirmPassword() {
        final EditText edConfirmPassword = getEditText(R.id.edConfirmPassword);
        edConfirmPassword.setTransformationMethod(null);

        edConfirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    Log.v(TAG, "revealPassword(): edConfirmPassword on focus listener...");

                    edConfirmPassword.setText("");
                    edConfirmPassword.setTransformationMethod(new PasswordTransformationMethod());
                    edConfirmPassword.setOnClickListener(null);
                }
            }
        });
    }

    private EditText getEditText(int edId) {
        return (EditText) this.findViewById(edId);
    }
}
