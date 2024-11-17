package com.example.artcraft;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    Button btnExplore, btnCreate, btnGallery, btnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnExplore = findViewById(R.id.btnExplore);
        btnCreate = findViewById(R.id.btnCreate);
        btnGallery = findViewById(R.id.btnGallery);
        btnProfile = findViewById(R.id.btnProfile);

        // Set up click listeners for each button
        btnExplore.setOnClickListener(this::navigateToExplore);
        btnCreate.setOnClickListener(this::navigateToCreate);
        btnGallery.setOnClickListener(this::navigateToGallery);
        btnProfile.setOnClickListener(this::navigateToProfile);
    }

    private void navigateToExplore(View view) {
        startActivity(new Intent(this, ExploreActivity.class));
    }

    private void navigateToCreate(View view) {
        startActivity(new Intent(this, CreateActivity.class));
    }

    private void navigateToGallery(View view) {
        startActivity(new Intent(this, GalleryActivity.class));
    }

    private void navigateToProfile(View view) {
        startActivity(new Intent(this, ProfileActivity.class));
    }
}
