package com.ante.LiturgijskaPjesmarica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;

import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
    DatabaseReference usersDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.drawable.gradient_background);
        setContentView(R.layout.activity_register);

        //Spajanje na bazu
        this.db = FirebaseDatabase.getInstance("https://liturgijska-pjesmarica-default-rtdb.europe-west1.firebasedatabase.app");

        // Inicijalizacija FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Deklaracija DatabaseReference izvan onCreate
        usersDbRef = this.db.getReference("users");

        //Polja za unos podataka
        EditText usernameTxt = findViewById(R.id.reg_username);
        EditText emailTxt = findViewById(R.id.reg_email);
        EditText passwordTxt = findViewById(R.id.reg_lozinka);
        Button submitBtn = findViewById(R.id.reg_btn);
        ImageView backButton = findViewById(R.id.backButton2);
        TextView prijaviSe = findViewById(R.id.reg_prijavi_se);


        prijaviSe.setPaintFlags(prijaviSe.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dobavljanje unesenih podataka
                String email = emailTxt.getText().toString();

                // Provjera ispravnosti e-mail adrese
                if (!isValidEmail(email)) {
                    Toast.makeText(RegisterActivity.this, "Unesite ispravnu e-mail adresu", Toast.LENGTH_SHORT).show();
                    return; // Prekini registraciju ako e-mail adresa nije ispravna
                }
               FirebaseAuth.getInstance()
                       .createUserWithEmailAndPassword(emailTxt.getText().toString(), passwordTxt.getText().toString())
                       .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if(task.isSuccessful()){
                                   usersDbRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                           .setValue(new User(usernameTxt.getText().toString(), emailTxt.getText().toString(), ""));
                                   Toast.makeText(RegisterActivity.this, "Uspješno registriran!", Toast.LENGTH_SHORT).show();
                                   Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                   startActivity(intent);
                                   finishAffinity();
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

        usernameTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    usernameTxt.setHint("");
                }else{
                    usernameTxt.setHint("Korisničko ime");
                }
            }
        });

        usernameTxt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    emailTxt.requestFocus();
                    return true;
                }
                return false;
            }
        });
        emailTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    emailTxt.setHint("");
                } else {
                    emailTxt.setHint("Adresa e-pošte");
                }
            }
        });

        emailTxt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    passwordTxt.requestFocus();
                    return true;
                }
                return false;
            }
        });

        passwordTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    passwordTxt.setHint("");
                } else {
                    passwordTxt.setHint("Lozinka");
                }
            }
        });

        prijaviSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
