package com.example.artcraft;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;  // Import the Toast class
import androidx.appcompat.app.AppCompatActivity;

public class PreviewActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        imageView = findViewById(R.id.imageView);

        Button btnBackToGallery = findViewById(R.id.btnBackToGallery);
        Button btnSaveArtwork = findViewById(R.id.btnSaveArtwork);

        btnBackToGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to GalleryActivity
                Intent intent = new Intent(PreviewActivity.this, GalleryActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnSaveArtwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle save artwork functionality
                // Display a toast message
                Toast.makeText(PreviewActivity.this, "Artwork saved successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
