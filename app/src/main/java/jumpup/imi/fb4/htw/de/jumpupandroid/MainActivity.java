package jumpup.imi.fb4.htw.de.jumpupandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.logging.Logger;

import jumpup.imi.fb4.htw.de.jumpupandroid.login.LoginActivity;

public class MainActivity extends ActionBarActivity {
    private static final String TAG = MainActivity.class.getName();
    private EditText inputeMail;
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
    }

    private void bindInputs() {
        this.inputeMail = (EditText) findViewById(R.id.edEMail);
        this.inputPassword = (EditText) findViewById(R.id.edPassword);
    }

    private void registerButtons() {
        this.registerLoginButton();
    }

    private void registerLoginButton() {
        final Button loginButton = (Button) findViewById(R.id.btnLogin);

        loginButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startLoginActivity();
                    }
                }
        );
    }

    private void startLoginActivity() {
        Intent loginIntent = new Intent(this, LoginActivity.class);

        loginIntent.putExtra(LoginActivity.EXTRA_USERNAME, getEmailFromInput());
        loginIntent.putExtra(LoginActivity.EXTRA_PASSWORD, getPasswordFromInput());

        startActivity(loginIntent);
    }

    private String getEmailFromInput() {
        return this.inputeMail.getText().toString();
    }

    public String getPasswordFromInput() {
        return this.inputPassword.getText().toString();
    }

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
}
