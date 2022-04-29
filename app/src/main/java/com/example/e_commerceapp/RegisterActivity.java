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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private Button createAccountbtn;
    private EditText inputName,inputPhoneNumber,inputPassword;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        createAccountbtn = findViewById(R.id.Register_btn);
        inputName = findViewById(R.id.username_register_edtTxt);
        inputPassword = findViewById(R.id.password_register_edtTxt);
        inputPhoneNumber = findViewById(R.id.phone_register_edtTxt);
        pd = new ProgressDialog(this);

        createAccountbtn.setOnClickListener(view -> {
             CreateNewUser();



        });





    }

    private void CreateNewUser() {

        String name = inputName.getText().toString();
        String password= inputPassword.getText().toString();
        String phone = inputPhoneNumber.getText().toString();

        if (TextUtils.isEmpty(name)){
//            Toast.makeText(this,"Please fill in the name area",Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Please fill in your name", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password)){
//            Toast.makeText(this,"Please fill in the name area",Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Please fill in your password", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phone)){
//            Toast.makeText(this,"Please fill in the name area",Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Please fill in your phone number", Toast.LENGTH_SHORT).show();
        }
        else{
            pd.setTitle("Creating your account");
            pd.setMessage("Please wait as we create your account");
            pd.setCanceledOnTouchOutside(false);
            pd.show();
            ValidatePhoneNumber(name,password,phone);
        }



    }

    private void ValidatePhoneNumber(String name, String password, String phone) {
        final DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!(snapshot.child("Users").child(phone).exists())){

                    HashMap<String , Object> hashMap = new HashMap<>();
                    hashMap.put("phone",phone);
                    hashMap.put("userName",name);
                    hashMap.put("Password",password);

                    databaseReference.child("Users").child(phone).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isComplete()){
                                pd.dismiss();
                                Toast.makeText(RegisterActivity.this,"Account created successfully",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                                startActivity(i);
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            pd.dismiss();

                        }
                    });





                }
                else{
                    pd.dismiss();
                    Toast.makeText(RegisterActivity.this,phone + " is already registered",Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                pd.dismiss();

            }
        });









    }
}