package com.ante.LiturgijskaPjesmarica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RegisterActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.drawable.gradient_background);
        setContentView(R.layout.activity_register);
    }
}
