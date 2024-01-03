package com.ante.LiturgijskaPjesmarica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ante.LiturgijskaPjesmarica.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.drawable.gradient_background);
        setContentView(R.layout.activity_login);

        ImageView backBtn = findViewById(R.id.backButton);
        Button loginBtn = findViewById(R.id.button3);
        EditText logEmail = findViewById(R.id.log_email);
        EditText logPass = findViewById(R.id.log_pass);
        TextView regRed = findViewById(R.id.textView8);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(logEmail.getText().toString(), logPass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Uspješno prijavljen!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT ).show();
                        }
                        }
                    });

            }
        });

        logEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus){
                logEmail.setHint("");
            }else {
                logEmail.setHint("Adresa e-pošte");
            }
            }
        });

        logEmail.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    logPass.requestFocus();
                    return true;
                }
                return false;
            }
        });

        logPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    logPass.setHint("");
                }else {
                    logPass.setHint("Lozinka");
                }
            }

        });

        regRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }



}
