package jumpup.imi.fb4.htw.de.jumpupandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Observable;
import java.util.Observer;

import jumpup.imi.fb4.htw.de.jumpupandroid.login.LoginFactory;
import jumpup.imi.fb4.htw.de.jumpupandroid.login.LoginTask;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.welcome.WelcomeActivity;
import jumpup.imi.fb4.htw.de.jumpupandroid.registration.RegistrationActivity;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.AppUtility;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.ViewHelper;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.activity.JumpUpActivity;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.development.TestData;

@SuppressWarnings("WeakerAccess")
public class MainActivity extends JumpUpActivity implements Observer {
    private static final String TAG = MainActivity.class.getName();

    private LoginTask loginTask = LoginFactory.newLoginTask(this);
    private EditText inputEmail;
    private EditText inputPassword;

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.bindInputs();
        this.registerButtons();

        if (AppUtility.prefillTestData()) {
            fillDevelopmentTestData();
        } else {
            this.revealPassword();
            this.addClickListenersToEmptyInputFieldsOnClick();
        }
    }

    private void fillDevelopmentTestData() {
        Log.d(TAG, "fillDevelopmentTestData(): prefilling input fields with test data");
        inputEmail.setText(TestData.EMAIL);
        inputPassword.setText(TestData.PASSWORD);
    }

    private void addClickListenersToEmptyInputFieldsOnClick() {
        addClickListenerToEmptyInputFieldsOnClick(inputEmail);
    }

    private void revealPassword() {;
        ViewHelper.addRevealPasswordUntilFirstClick(inputPassword);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (!loginTask.getStatus().equals(AsyncTask.Status.FINISHED)) {
            Log.v(TAG, "onDestroy(): cancelling LoginTask...");
            loginTask.cancel(true);
        }
    }

    private void bindInputs() {
        this.inputEmail = (EditText) findViewById(R.id.edEMail);
        this.inputPassword = (EditText) findViewById(R.id.edPassword);
    }

    private void registerButtons() {
        this.registerLoginButton();
        this.registerRegistrationButton();
    }

    private void registerLoginButton() {
        final Button loginButton = (Button) findViewById(R.id.btnLogin);

        loginButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (loginTask.getStatus().equals(AsyncTask.Status.PENDING)) {
                            // task has not been executed yet
                            doLoginInBackground(getEmailFromInput(), getPasswordFromInput());
                        }
                    }
                }
        );
    }

    private void registerRegistrationButton() {
        final Button registrationButton = (Button) findViewById(R.id.btnRegister);

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToRegistration(RegistrationActivity.class, getEmailFromInput(), getPasswordFromInput());
            }
        });
    }

    private void navigateToRegistration(Class<RegistrationActivity> registrationActivityClass, String emailFromInput, String passwordFromInput) {
        Intent intent = new Intent(this, RegistrationActivity.class);

        intent.putExtra(RegistrationActivity.EXTRA_USERNAME, emailFromInput);

        startActivity(intent);
    }

    private void doLoginInBackground(String eMail, String password) {
        loginTask.execute(eMail, password);
    }

    private String getEmailFromInput() {
        return this.inputEmail.getText().toString();
    }

    public String getPasswordFromInput() {
        return this.inputPassword.getText().toString();
    }

    @SuppressWarnings("SameReturnValue")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_map_test) {
            // start map intent test
            startMapIntent();
        }

        return super.onOptionsItemSelected(item);
    }

    private void startMapIntent() {
        Uri geoLocation = Uri.parse("geo:47.6,-122.3");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            Log.i(TAG, "startMapIntent(): starting map intent.");
            startActivity(intent);
        } else {
            Log.e(TAG, "startMapIntent(): No map intent could be resolved.");
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        Log.v(TAG, "update(): MainActivity is notified by observable " + observable);

        if (o instanceof LoginTask) {
            this.handleLoginResult();
        }
    }

    private void handleLoginResult() {
        if (this.loginTask.isHasError()) {
            this.showErrorNotification(this.getResources().getString(this.loginTask.getToastMessageId()));
            this.resetTask();
        } else {
            this.showSuccessNotification(this.getResources().getString(R.string.fragment_main_login_success));
            this.navigateToWithParcelAndClearActivityStack(WelcomeActivity.class, this.loginTask.getUser());
        }
    }

    private void resetTask() {
        loginTask = LoginFactory.newLoginTask(this);
    }

    @Override
    protected String getTag() {
        return TAG;
    }
}
