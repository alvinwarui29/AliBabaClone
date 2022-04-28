package com.example.e_commerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button loginLink,registerLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginLink = findViewById(R.id.main_login_btn);
        registerLink = findViewById(R.id.main_register_btn);

        loginLink.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(i);

        });

        registerLink.setOnClickListener(view -> {

            Intent i = new Intent(MainActivity.this,RegisterActivity.class);
            startActivity(i);

        });



    }
}