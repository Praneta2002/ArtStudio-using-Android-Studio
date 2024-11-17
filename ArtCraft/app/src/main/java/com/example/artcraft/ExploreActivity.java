package com.example.artcraft;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class ExploreActivity extends AppCompatActivity {
    ListView artStylesList;
    String[] artStyles = {"Painting", "Sketching", "Digital Art", "Sculpture"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Explore Art"); // Set the title

        artStylesList = findViewById(R.id.artStylesList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, artStyles);
        artStylesList.setAdapter(adapter);

        // Set up item click listener to show toast messages for selections
        artStylesList.setOnItemClickListener((parent, view, position, id) -> {
            String selectedStyle = artStyles[position];
            if (artStylesList.isItemChecked(position)) {
                Toast.makeText(ExploreActivity.this, selectedStyle + " Selected", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ExploreActivity.this, selectedStyle + " Deselected", Toast.LENGTH_SHORT).show();
            }
        });

        // Button to confirm selection
        Button btnConfirmSelection = findViewById(R.id.btnConfirmSelection);
        btnConfirmSelection.setOnClickListener(v -> {
            SparseBooleanArray selectedItems = artStylesList.getCheckedItemPositions();
            ArrayList<String> selectedStyles = new ArrayList<>();

            for (int i = 0; i < selectedItems.size(); i++) {
                if (selectedItems.valueAt(i)) {
                    selectedStyles.add(artStyles[selectedItems.keyAt(i)]);
                }
            }

            // Check if any styles are selected
            if (selectedStyles.isEmpty()) {
                Toast.makeText(ExploreActivity.this, "No styles selected!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Pass the selected styles to the next activity
            Intent intent = new Intent(ExploreActivity.this, SelectedArtStylesActivity.class);
            intent.putStringArrayListExtra("selectedStyles", selectedStyles);
            startActivity(intent);
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_navigation, menu); // Inflate the menu
        return true;
    }
}
