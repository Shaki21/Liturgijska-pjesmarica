package com.ante.LiturgijskaPjesmarica.izbornik.Dosasce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ante.LiturgijskaPjesmarica.R;

public class VrijemeDosascaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vrijeme_dosasca);

        TextView prvaNed = findViewById(R.id.ned_dos_1);
        TextView drugaNed = findViewById(R.id.ned_dos_2);
        TextView nedDosBDM = findViewById(R.id.ned_dos_BDM);
        TextView trecaNed = findViewById(R.id.ned_dos_3);
        TextView cetvrtaNed = findViewById(R.id.ned_dos_4);


        prvaNed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VrijemeDosascaActivity.this, prvaNedActivity.class);
                startActivity(intent);
            }
        });
    }
}