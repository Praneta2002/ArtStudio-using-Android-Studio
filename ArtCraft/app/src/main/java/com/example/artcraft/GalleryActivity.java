package com.example.artcraft;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class GalleryActivity extends AppCompatActivity {
    LinearLayout artworkContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        artworkContainer = findViewById(R.id.artworkContainer);

        // Set up buttons
        Button previewButton = findViewById(R.id.previewButton);
        Button uploadButton = findViewById(R.id.uploadButton);

        previewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GalleryActivity.this, PreviewActivity.class);
                startActivity(intent);
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle upload action (implement as needed)
                Toast.makeText(GalleryActivity.this, "Upload functionality not implemented yet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Handle toolbar menu item clicks
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_navigation, menu); // Inflate the menu
        return true;
    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_explore:
//                startActivity(new Intent(this, ExploreActivity.class));
//                return true;
//            case R.id.action_create:
//                startActivity(new Intent(this, CreateActivity.class));
//                return true;
//            case R.id.action_gallery:
//                // Current activity, no action needed
//                Toast.makeText(this, "You are already in the Gallery", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.action_profile:
//                startActivity(new Intent(this, ProfileActivity.class));
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
