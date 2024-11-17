package com.example.artcraft;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

public class ExploreActivity extends AppCompatActivity {
    ListView artStylesList;
    String[] artStyles = {"Painting", "Sketching", "Digital Art", "Sculpture"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        artStylesList = findViewById(R.id.artStylesList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, artStyles);
        artStylesList.setAdapter(adapter);

        artStylesList.setOnItemClickListener((parent, view, position, id) -> {
            String selectedStyle = artStyles[position];
            Toast.makeText(ExploreActivity.this, selectedStyle + " Selected", Toast.LENGTH_SHORT).show();
        });
    }
}
