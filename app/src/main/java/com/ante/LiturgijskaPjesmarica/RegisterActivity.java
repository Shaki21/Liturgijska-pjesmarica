package com.ante.LiturgijskaPjesmarica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.ante.LiturgijskaPjesmarica.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity{

    FirebaseDatabase db;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.drawable.gradient_background);
        setContentView(R.layout.activity_register);

        //Spajanje na bazu
        this.db = FirebaseDatabase.getInstance("https://liturgijska-pjesmarica-default-rtdb.europe-west1.firebasedatabase.app");
        // Inicijalizacija FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        EditText firstNameTxt = findViewById(R.id.button1);
        EditText lastNameTxt = findViewById(R.id.button2);
        EditText usernameTxt = findViewById(R.id.button3);
        EditText passwordTxt = findViewById(R.id.button4);
        Button submitBtn = findViewById(R.id.button5);
        ImageView backButton = findViewById(R.id.backButton2);
        DatabaseReference usersDbRef = this.db.getReference("users");



        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User u = new User(
                        firstNameTxt.getText().toString(),
                        lastNameTxt.getText().toString(),
                        usernameTxt.getText().toString(),
                        passwordTxt.getText().toString()
                );
                usersDbRef.setValue(u)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(RegisterActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(RegisterActivity.this, "Error registering user", Toast.LENGTH_SHORT).show();
                            }
                        });
            }

        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });





    }
}
