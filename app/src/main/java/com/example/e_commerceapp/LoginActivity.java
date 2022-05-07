package com.example.e_commerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_commerceapp.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText inputphone,inputpassword;
    private Button btnlogin;
    private ProgressDialog pd;
    private String dbName = "Users";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pd = new ProgressDialog(this);
        inputphone =findViewById(R.id.phone_login_edtTxt);
        inputpassword = findViewById(R.id.password_login_edtTxt);
        btnlogin = findViewById(R.id.login_btn);

        btnlogin.setOnClickListener(view -> {

            LoginUser();

        });


    }

    private void LoginUser() {

        String password= inputpassword.getText().toString();
        String phone = inputphone.getText().toString();

        if (TextUtils.isEmpty(password)){
//            Toast.makeText(this,"Please fill in the name area",Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Please fill in your password", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phone)){
//            Toast.makeText(this,"Please fill in the name area",Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Please fill in your phone number", Toast.LENGTH_SHORT).show();
        }
        else{
            pd.setTitle("Checking your account");
            pd.setMessage("Please wait as we check your account");
            pd.setCanceledOnTouchOutside(false);
            pd.show();
            AllowLogin(phone,password);




        }




    }

    private void AllowLogin(String phone, String password) {
        final DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.child(dbName).child(phone).exists()){

                    Users users = snapshot.child(dbName).child(phone).getValue(Users.class);

                    if (users.getPhone().equals(phone)){
                        pd.dismiss();
                          Intent i = new Intent(LoginActivity.this,HomeActivity.class);
                          startActivity(i);

//                        if (users.getPassword().equals(password)){
//
//
//                        }else{
//                            Toast.makeText(LoginActivity.this, "nope", Toast.LENGTH_SHORT).show();
//                            pd.dismiss();
//                        }


                    }



                }else{
                    pd.dismiss();
                    Toast.makeText(LoginActivity.this, "Kindly create an acccount before logging in", Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginActivity.this, error + "", Toast.LENGTH_SHORT).show();

            }
        });





    }
}