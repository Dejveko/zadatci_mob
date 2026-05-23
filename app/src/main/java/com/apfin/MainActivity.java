package com.apfin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText surnameEditText;
    private EditText oibEditText;
    private EditText phoneEditText;
    private EditText emailEditText;
    private Spinner citySpinner;
    private RadioGroup genderGroup;

    private ActivityResultLauncher<Intent> detailLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.edit_name);
        surnameEditText = findViewById(R.id.edit_surname);
        oibEditText = findViewById(R.id.edit_oib);
        phoneEditText = findViewById(R.id.edit_phone);
        emailEditText = findViewById(R.id.edit_email);
        citySpinner = findViewById(R.id.spinner_city);
        genderGroup = findViewById(R.id.radio_group_gender);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.cities,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(adapter);

        detailLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        boolean shouldClear = result.getData().getBooleanExtra("clear", false);
                        if (shouldClear) {
                            clearInputs();
                        }
                    }
                }
        );

        Button saveButton = findViewById(R.id.button_save);
        saveButton.setOnClickListener(this::onSaveClicked);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Toast.makeText(MainActivity.this, R.string.toast_thanks, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void onSaveClicked(View view) {
        String name = textOrDash(nameEditText);
        String surname = textOrDash(surnameEditText);
        String oib = textOrDash(oibEditText);
        String phone = textOrDash(phoneEditText);
        String email = textOrDash(emailEditText);
        String city = String.valueOf(citySpinner.getSelectedItem());

        String gender = getGenderText();

        String message = "Ime: " + name
                + "\nPrezime: " + surname
                + "\nOIB: " + oib
                + "\nTelefon: " + phone
                + "\nEmail: " + email
                + "\nSpol: " + gender
                + "\nGrad: " + city;

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("surname", surname);
        intent.putExtra("oib", oib);
        intent.putExtra("phone", phone);
        intent.putExtra("email", email);
        intent.putExtra("gender", gender);
        intent.putExtra("city", city);

        detailLauncher.launch(intent);
    }

    private String textOrDash(EditText editText) {
        String text = editText.getText().toString().trim();
        return TextUtils.isEmpty(text) ? "-" : text;
    }

    private String getGenderText() {
        int checkedId = genderGroup.getCheckedRadioButtonId();
        if (checkedId == -1) {
            return "-";
        }
        RadioButton selected = findViewById(checkedId);
        return selected.getText().toString();
    }

    private void clearInputs() {
        nameEditText.setText("");
        surnameEditText.setText("");
        oibEditText.setText("");
        phoneEditText.setText("");
        emailEditText.setText("");
        genderGroup.clearCheck();
        citySpinner.setSelection(0);
    }
}
