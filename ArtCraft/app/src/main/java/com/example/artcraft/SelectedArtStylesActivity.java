package com.example.artcraft;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class SelectedArtStylesActivity extends AppCompatActivity {
    ListView selectedStylesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_art_styles);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Selected Art Styles");

        selectedStylesList = findViewById(R.id.selectedStylesList);
        ArrayList<String> selectedStyles = getIntent().getStringArrayListExtra("selectedStyles");

        // Set up adapter for displaying selected styles
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, selectedStyles);
        selectedStylesList.setAdapter(adapter);

        // Set up navigation buttons
        Button btnCreateArt = findViewById(R.id.btnCreateArt);
        Button btnExploreMore = findViewById(R.id.btnExploreMore);
        Button btnViewGallery = findViewById(R.id.btnViewGallery);

        btnCreateArt.setOnClickListener(v -> {
            Intent intent = new Intent(SelectedArtStylesActivity.this, CreateActivity.class);
            startActivity(intent);
        });

        btnExploreMore.setOnClickListener(v -> {
            Intent intent = new Intent(SelectedArtStylesActivity.this, ExploreActivity.class);
            startActivity(intent);
        });

        btnViewGallery.setOnClickListener(v -> {
            Intent intent = new Intent(SelectedArtStylesActivity.this, GalleryActivity.class);
            startActivity(intent);
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_navigation, menu); // Inflate the menu
        return true;
    }
}
