package com.ante.LiturgijskaPjesmarica;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.ante.LiturgijskaPjesmarica.izbornik.BozicnoVrijemeActivity;
import com.ante.LiturgijskaPjesmarica.izbornik.KorizmenoVrijemeActivity;
import com.ante.LiturgijskaPjesmarica.izbornik.SpomendaniActivity;
import com.ante.LiturgijskaPjesmarica.izbornik.UskrsnoVrijemeActivity;
import com.ante.LiturgijskaPjesmarica.izbornik.Dosasce.VrijemeDosascaActivity;
import com.ante.LiturgijskaPjesmarica.izbornik.VrijemeKrozGodinu;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView imageView4;
    private ImageView iconProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imageView4 = findViewById(R.id.imageView4);
        iconProfile = findViewById(R.id.userIcon);
        TextView vrijemeDosasca = findViewById(R.id.textView11);
        TextView bozicnoVrijeme = findViewById(R.id.textView12);
        TextView korizmenoVrijeme = findViewById(R.id.textView13);
        TextView uskrsnoVrijeme = findViewById(R.id.textView14);
        TextView vrijemeKrozGodinu = findViewById(R.id.textView15);
        TextView spomendani = findViewById(R.id.textView16);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        imageView4.setVisibility(View.VISIBLE);
        setupDrawer();

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pozovi metodu za otvaranje/zatvaranje izbornika
                onHamburgerClick(v);
            }
        });
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);

                // Ako je izbornik otvoren, postavlja se nevidljivost ikone
                imageView4.setVisibility(slideOffset > 0 ? View.INVISIBLE : View.VISIBLE);
            }
        });


        iconProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        vrijemeDosasca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, VrijemeDosascaActivity.class);
                startActivity(intent);
            }
        });

        bozicnoVrijeme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BozicnoVrijemeActivity.class);
                startActivity(intent);
            }
        });
        korizmenoVrijeme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, KorizmenoVrijemeActivity.class);
                startActivity(intent);
            }
        });
        uskrsnoVrijeme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, UskrsnoVrijemeActivity.class);
                startActivity(intent);
            }
        });

        vrijemeKrozGodinu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, VrijemeKrozGodinu.class);
                startActivity(intent);
            }
        });

        spomendani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SpomendaniActivity.class);
                startActivity(intent);
            }
        });

    }


    public void onHamburgerClick(View view) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    private void setupDrawer(){
        drawerLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Provjeri je li izbornik trenutno otvoren
                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        // Ako je otvoren, zatvori ga
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true; // Potroši događaj kako ne bi došlo do daljnjeg rukovanja
                    }
                }
                return false;
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

}
