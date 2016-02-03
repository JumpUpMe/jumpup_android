package jumpup.imi.fb4.htw.de.jumpupandroid.portal.welcome;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.PortalActivity;

public class WelcomeActivity extends PortalActivity {
    private static final String TAG = WelcomeActivity.class.getName();

    private TextView txtWelcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        this.bindViewElements();
        this.fillInUserDataIntoText();
    }

    private void bindViewElements() {
        this.txtWelcomeText = (TextView) findViewById(R.id.txtWelcomeText);
    }

    private void fillInUserDataIntoText() {
        this.txtWelcomeText.setText(String.format(this.txtWelcomeText.getText().toString(), this.fillUsernameForSalutation()));
    }

    private String fillUsernameForSalutation() {
        return user.getPrename();
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AsyncTask getTask() {
        return null;
    }
}
