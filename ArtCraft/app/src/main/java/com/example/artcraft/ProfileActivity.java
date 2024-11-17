package com.example.artcraft;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ProfileActivity extends AppCompatActivity {
    EditText username, bio;
    Button btnSaveProfile;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        username = findViewById(R.id.editUsername);
        bio = findViewById(R.id.editBio);
        btnSaveProfile = findViewById(R.id.btnSaveProfile);

        sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);

        // Load saved data
        loadProfile();

        btnSaveProfile.setOnClickListener(v -> {
            String newUsername = username.getText().toString();
            String newBio = bio.getText().toString();
            saveProfile(newUsername, newBio);
        });
    }

    private void saveProfile(String username, String bio) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("bio", bio);
        editor.apply();
        Toast.makeText(ProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
    }

    private void loadProfile() {
        String savedUsername = sharedPreferences.getString("username", "");
        String savedBio = sharedPreferences.getString("bio", "");
        username.setText(savedUsername);
        bio.setText(savedBio);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_navigation, menu); // Inflate the menu
        return true;
    }
}
