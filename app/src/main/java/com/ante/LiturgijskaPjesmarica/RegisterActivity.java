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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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

        //Polja za unos podataka
        EditText usernameTxt = findViewById(R.id.reg_username);
        EditText emailTxt = findViewById(R.id.reg_email);
        EditText passwordTxt = findViewById(R.id.reg_lozinka);
        EditText passwordCnf = findViewById(R.id.reg_loz2);
        Button submitBtn = findViewById(R.id.reg_btn);
        ImageView backButton = findViewById(R.id.backButton2);


        //TODO kada se vrati unazad vraca se na app
        //TODO popraviti unos usera na realtime database
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               FirebaseAuth.getInstance()
                       .createUserWithEmailAndPassword(emailTxt.getText().toString(), passwordTxt.getText().toString())
                       .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if(task.isSuccessful()){
                                   FirebaseDatabase.getInstance().getReference("user/" + FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(new User(usernameTxt.getText().toString(), emailTxt.getText().toString(), ""));
                                   Toast.makeText(RegisterActivity.this, "Uspješno registriran!", Toast.LENGTH_SHORT).show();
                                   Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                   startActivity(intent);
                                   finish();
                               }else {
                                   Toast.makeText(RegisterActivity.this, task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT ).show();
                               }
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
