package jumpup.imi.fb4.htw.de.jumpupandroid.login;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Toast;

import jumpup.imi.fb4.htw.de.jumpupandroid.R;

public class LoginActivity extends ActionBarActivity {

    public static final String EXTRA_USERNAME = "username";
    public static final String EXTRA_PASSWORD = "password";

    private final LoginTask loginTask = new LoginTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.doLoginInBackground();
    }

    private void doLoginInBackground() {
        String eMail = this.getIntent().getStringExtra(EXTRA_USERNAME);
        String password = this.getIntent().getStringExtra(EXTRA_PASSWORD);

        Toast.makeText(LoginActivity.this, "Logging in " + eMail, Toast.LENGTH_SHORT).show();

        loginTask.execute(eMail, password);
    }
}
