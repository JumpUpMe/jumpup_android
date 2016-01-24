package jumpup.imi.fb4.htw.de.jumpupandroid.portal.profile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.PortalActivity;

public class ProfileActivity extends PortalActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.bindInputElements();
        this.addClickListenersToEmptyInputFieldsOnClick();
        this.registerButton();
    }

    private void registerButton() {
        Button btnSave = (Button) this.findViewById(R.id.btnUpdateProfile);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO start task

            }
        });
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
}
