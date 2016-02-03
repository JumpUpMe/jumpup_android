package jumpup.imi.fb4.htw.de.jumpupandroid.portal.profile;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.PortalActivity;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.welcome.WelcomeActivity;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.AppUtility;

/**
 * Project: jumpup_android
 * <p/>
 * ProfileActivity presenter class
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 24.01.2016
 */
public class ProfileActivity extends PortalActivity implements Observer {

    private static final String TAG = ProfileActivity.class.getName();
    private RadioButton inputGenderMale;
    private RadioButton inputGenderFemale;
    private EditText inputDateOfBirth;
    private EditText inputPlaceOfBirth;
    private EditText inputTown;
    private EditText inputLanguage;
    private EditText inputCountry;
    private EditText inputSkype;
    private EditText inputMobileNumber;

    private ProfileTask profileTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.bindInputElements();
        this.addClickListenersToEmptyInputFieldsOnClick();
        this.fillInputFieldsByUserIfNotEmpty();
        this.registerButton();
    }


    private void registerButton() {
        Button btnSave = (Button) this.findViewById(R.id.btnUpdateProfile);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fillUserByInputFields();

                if (getTask() == null || getTask().getStatus().equals(AsyncTask.Status.FINISHED)) {
                    // no task is running in parallel, so start new one
                    resetTask();
                    profileTask.execute(user);
                }
            }
        });
    }

    private void fillUserByInputFields() {
        this.user.setGender(getSelectedGender());
        this.user.setPlaceOfBirth(this.inputPlaceOfBirth.getText().toString());
        this.user.setTown(this.inputTown.getText().toString());
        this.user.setCountry(this.inputCountry.getText().toString());
        this.user.setLocale(this.inputLanguage.getText().toString());
        this.user.setMobileNumber(this.inputMobileNumber.getText().toString());
        this.user.setSkype(this.inputSkype.getText().toString());
        this.user.setDateOfBirth(AppUtility.getUTCTimestamp(this.inputDateOfBirth.getText().toString()));
    }

    private String getSelectedGender() {
        if (this.inputGenderMale.isChecked()) {
            return User.Gender.MAN.toString();
        }

        return User.Gender.WOMAN.toString();
    }

    private void bindInputElements() {
        this.inputGenderMale = (RadioButton) this.findViewById(R.id.radioMale);
        this.inputGenderFemale = (RadioButton) this.findViewById(R.id.radioFemale);
        this.inputDateOfBirth = (EditText) this.findViewById(R.id.edDateOfBirth);
        this.inputPlaceOfBirth = (EditText) this.findViewById(R.id.edPlaceOfBirth);
        this.inputTown = (EditText) this.findViewById(R.id.edTown);
        this.inputLanguage = (EditText) this.findViewById(R.id.edLanguage);
        this.inputCountry = (EditText) this.findViewById(R.id.edCountry);
        this.inputSkype = (EditText) this.findViewById(R.id.edSkype);
        this.inputMobileNumber = (EditText) this.findViewById(R.id.edPhone);
    }

    private void fillInputFieldsByUserIfNotEmpty() {
        if (AppUtility.isSet(this.user.getGender())) {
            Log.d(TAG, "fillInputFieldsByUserIfNotEmpty(): Gender is set");
            if (this.user.getGenderType().equals(User.Gender.MAN)) {
                Log.d(TAG, "fillInputFieldsByUserIfNotEmpty(): is MAN");
                this.inputGenderMale.setChecked(true);
            } else if (this.user.getGenderType().equals(User.Gender.WOMAN)) {
                Log.d(TAG, "fillInputFieldsByUserIfNotEmpty(): is WOMAN");
                this.inputGenderFemale.setChecked(true);
            }
        }

        if (AppUtility.isSet(this.user.getDateOfBirth())) {
            this.inputDateOfBirth.setText(AppUtility.formatDateToUTC(this.user.getDateOfBirth()));
        }

        if (AppUtility.isSet(this.user.getPlaceOfBirth())) {
            this.inputPlaceOfBirth.setText(this.user.getPlaceOfBirth());
        }

        if (AppUtility.isSet(this.user.getTown())) {
            this.inputTown.setText(this.user.getTown());
        }

        if (AppUtility.isSet(this.user.getLocale())) {
            this.inputLanguage.setText(this.user.getLocale());
        }

        if (AppUtility.isSet(this.user.getCountry())) {
            this.inputCountry.setText(this.user.getCountry());
        }

        if (AppUtility.isSet(this.user.getSkype())) {
            this.inputSkype.setText(this.user.getSkype());
        }

        if (AppUtility.isSet(this.user.getMobileNumber())) {
            this.inputMobileNumber.setText(this.user.getMobileNumber());
        }
    }

    private void addClickListenersToEmptyInputFieldsOnClick() {
        addClickListenerToEmptyInputFieldsOnClick(inputDateOfBirth);
        addClickListenerToEmptyInputFieldsOnClick(inputPlaceOfBirth);
        addClickListenerToEmptyInputFieldsOnClick(inputTown);
        addClickListenerToEmptyInputFieldsOnClick(inputLanguage);
        addClickListenerToEmptyInputFieldsOnClick(inputCountry);
        addClickListenerToEmptyInputFieldsOnClick(inputSkype);
        addClickListenerToEmptyInputFieldsOnClick(inputMobileNumber);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AsyncTask getTask() {
        return profileTask;
    }

    @Override
    public void update(Observable observable, Object o) {
        Log.d(TAG, "update(): getting notified...");

        if (o instanceof ProfileTask) {
            this.handleProfileResult();
        }
    }

    private void handleProfileResult() {
        if (this.profileTask.isHasValidationError()) {
            Log.d(TAG, "handleProfileResult(): validation error");
            // TODO fix display of validation failures
            showValidationFailure();
        } else if (this.profileTask.isHasError()) {
            Log.d(TAG, "handleProfileResult(): error");
            this.showErrorNotification(this.getResources().getString(this.profileTask.getToastMessageId()));
        } else {
            Log.d(TAG, "handleProfileResult(): success");
            this.showSuccessNotification(this.getResources().getString(R.string.activity_profile_success));
            navigateToWithParcel(WelcomeActivity.class, this.user);
        }
    }

    private void resetTask() {
        this.profileTask = ProfileFactory.newProfileTask(this);
    }

    private void showValidationFailure() {
        this.showValidationFailureNotification(this.profileTask.getValidationFailureField(), this.profileTask.getValidationFailureErrorMessages());
    }
}
