package com.example.artcraft;

import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;

public class GalleryActivity extends AppCompatActivity {
    ListView galleryList;
    File[] artworks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        galleryList = findViewById(R.id.galleryList);

        File filePath = Environment.getExternalStorageDirectory();
        File dir = new File(filePath.getAbsolutePath() + "/ArtCraft/");
        artworks = dir.listFiles();

        if (artworks != null) {
            String[] artworkNames = new String[artworks.length];
            for (int i = 0; i < artworks.length; i++) {
                artworkNames[i] = artworks[i].getName();
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, artworkNames);
            galleryList.setAdapter(adapter);
        }
    }
}
