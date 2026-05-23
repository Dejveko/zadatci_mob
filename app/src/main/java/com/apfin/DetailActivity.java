package com.apfin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String surname = intent.getStringExtra("surname");
        String oib = intent.getStringExtra("oib");
        String phone = intent.getStringExtra("phone");
        String email = intent.getStringExtra("email");
        String gender = intent.getStringExtra("gender");
        String city = intent.getStringExtra("city");

        ((TextView) findViewById(R.id.text_name)).setText(getString(R.string.label_value_format, getString(R.string.label_name), safeText(name)));
        ((TextView) findViewById(R.id.text_surname)).setText(getString(R.string.label_value_format, getString(R.string.label_surname), safeText(surname)));
        ((TextView) findViewById(R.id.text_oib)).setText(getString(R.string.label_value_format, getString(R.string.label_oib), safeText(oib)));
        ((TextView) findViewById(R.id.text_phone)).setText(getString(R.string.label_value_format, getString(R.string.label_phone), safeText(phone)));
        ((TextView) findViewById(R.id.text_email)).setText(getString(R.string.label_value_format, getString(R.string.label_email), safeText(email)));
        ((TextView) findViewById(R.id.text_gender)).setText(getString(R.string.label_value_format, getString(R.string.label_gender), safeText(gender)));
        ((TextView) findViewById(R.id.text_city)).setText(getString(R.string.label_value_format, getString(R.string.label_city), safeText(city)));

        Button yesButton = findViewById(R.id.button_yes);
        Button noButton = findViewById(R.id.button_no);

        yesButton.setOnClickListener(v -> {
            Intent result = new Intent();
            result.putExtra("clear", true);
            setResult(RESULT_OK, result);
            finish();
        });

        noButton.setOnClickListener(v -> {
            Toast.makeText(this, R.string.toast_thanks, Toast.LENGTH_SHORT).show();
            finishAffinity();
        });
    }

    private String safeText(String value) {
        return value == null ? "-" : value;
    }
}
