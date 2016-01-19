package jumpup.imi.fb4.htw.de.jumpupandroid.registration;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.login.LoginTask;
import jumpup.imi.fb4.htw.de.jumpupandroid.registration.entity.Registration;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.AppUtility;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.activity.JumpUpActivity;

/**
 * Project: jumpup_android
 * <p/>
 * RegistrationActivity presenter class
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 18.01.2016
 */
public class RegistrationActivity extends JumpUpActivity implements Observer {
    private static final String TAG = RegistrationActivity.class.getName();
    public static final String EXTRA_USERNAME = "extra_username";
    private RegistrationTask registrationTask = RegistrationFactory.newRegistrationTask(this);

    private EditText edUsername;
    private EditText edMail;
    private EditText edPassword;
    private EditText edConfirmPassword;
    private EditText edPrename;
    private EditText edLastname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        this.bindInputs();
        this.registerButtons();
        this.prepareView();
    }

    private void bindInputs() {
        edUsername = getEditText(R.id.edUsername);
        edMail = getEditText(R.id.edEMail);
        edPassword = getEditText(R.id.edPassword);
        edPrename = getEditText(R.id.edPrename);
        edLastname = getEditText(R.id.edLastName);
        edConfirmPassword = getEditText(R.id.edConfirmPassword);
    }

    private void registerButtons() {
        this.registerRegistrationButton();
    }

    private void registerRegistrationButton() {
        final Button registrationButton = (Button) findViewById(R.id.btnRegister);

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRegistrationTask();
            }
        });
    }

    private void startRegistrationTask() {
        Registration registrationEntity = buildRegistrationEntity();

        registrationTask.execute(registrationEntity);
    }

    private Registration buildRegistrationEntity() {
        Registration registrationEntity = new Registration();

        registrationEntity.setUsername(this.edUsername.getText().toString());
        registrationEntity.seteMail(this.edMail.getText().toString());
        registrationEntity.setPrename(this.edPrename.getText().toString());
        registrationEntity.setLastname(this.edLastname.getText().toString());
        registrationEntity.setPassword(this.edPassword.getText().toString());

        return registrationEntity;
    }

    private void prepareView() {
        if (AppUtility.isDevelopmentMode()) {
            fillDevelopmentTestData();
        } else {
            fillUsernameIfNotDefault();
            fillEmailIfNotDefault();

            revealPassword();
            revealConfirmPassword();
        }
    }

    private void fillDevelopmentTestData() {
        edPrename.setText("Android");
        edLastname.setText("Developer");
        edMail.setText("info@groupelite.de");
        edPassword.setText("$test1234");
        edConfirmPassword.setText("$test1234");
        edUsername.setText("androiddeveloper");
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

    @Override
    public void update(Observable observable, Object o) {
        Log.v(TAG, "update(): RegistrationActivity is notified by observable " + observable);

        if (o instanceof RegistrationTask) {
            this.handleRegistrationResult();
        }
    }

    private void handleRegistrationResult() {
        if (this.registrationTask.isHasValidationError()) {
            showValidationFailure();
        } else if (this.registrationTask.isHasError()) {
            this.showErrorNotification(this.getResources().getString(this.registrationTask.getToastMessageId()));
        } else {
            this.showSuccessNotification(this.getResources().getString(R.string.activity_registration_registration_success));
        }
    }

    private void showValidationFailure() {
        this.showValidationFailureNotification(this.registrationTask.getValidationFailureField(), this.registrationTask.getValidationFailureErrorMessages());
    }
}
