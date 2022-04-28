package com.example.e_commerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    private Button createAccountbtn;
    private EditText inputName,inputPhoneNumber,inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        createAccountbtn = findViewById(R.id.Register_btn);
        inputName = findViewById(R.id.username_register_edtTxt);
        inputPassword = findViewById(R.id.password_register_edtTxt);
        inputPhoneNumber = findViewById(R.id.phone_register_edtTxt);





    }
}