package com.ante.LiturgijskaPjesmarica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class RestartPasswordActivity extends AppCompatActivity {
    Button resBtn;
    ImageView backBtn;
    FirebaseAuth mAuth;
    String strEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restart_password);

        EditText resEmail = findViewById(R.id.res_email);
        resBtn = findViewById(R.id.res_btn);
        backBtn = findViewById(R.id.backButton);

        mAuth = FirebaseAuth.getInstance();


        resBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strEmail = resEmail.getText().toString().trim();
                if (!TextUtils.isEmpty(strEmail)){
                    ResetPassword();
                }else {
                    resEmail.setError("Polje ne može biti prazno!");
                }
            }
        });

        resEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    resEmail.setHint("");
                }else {
                    resEmail.setHint("Adresa e-pošte");
                }
            }
        });
        resEmail.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    resBtn.requestFocus();
                    return true;
                }
                return false;
            }
        });
    }
    private void ResetPassword(){

        mAuth.sendPasswordResetEmail(strEmail)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(RestartPasswordActivity.this,"Zahtjev je uspješno poslan! Provjerite vašu e-mail adresu", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RestartPasswordActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finishAffinity();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RestartPasswordActivity.this,"Error :- " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}