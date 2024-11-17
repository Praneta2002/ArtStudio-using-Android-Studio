package com.example.artcraft;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CreateActivity extends AppCompatActivity {

    EditText artTitle;
    RadioButton brush, pencil, eraser;
    Button saveButton, clearButton;
    DrawingView drawingView;
    SeekBar brushSizeSeekBar;
    RadioGroup toolSelector; // Add a RadioGroup for tool selection

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // Initialize views
        artTitle = findViewById(R.id.artTitle);
        brush = findViewById(R.id.radioBrush);
        pencil = findViewById(R.id.radioPencil);
        saveButton = findViewById(R.id.btnSave);
        clearButton = findViewById(R.id.btnClear);
        brushSizeSeekBar = findViewById(R.id.brushSizeSeekBar);
        drawingView = findViewById(R.id.drawingView);

        // Setting initial stroke width
        drawingView.setStrokeWidth(10);

        // Brush selection listener
        brush.setOnClickListener(v -> {
            drawingView.setEraserMode(false); // Switch eraser mode off
            drawingView.setToolType(DrawingView.ToolType.BRUSH); // Set to brush tool
            drawingView.setStrokeWidth(10); // Default brush size
            Toast.makeText(CreateActivity.this, "Brush selected", Toast.LENGTH_SHORT).show();
        });

        // Pencil selection listener
        pencil.setOnClickListener(v -> {
            drawingView.setEraserMode(false); // Ensure eraser mode is off
            drawingView.setToolType(DrawingView.ToolType.PENCIL); // Set to pencil tool
            drawingView.setStrokeWidth(2); // Set pencil size
            Toast.makeText(CreateActivity.this, "Pencil selected", Toast.LENGTH_SHORT).show();
        });

        // Eraser selection listener
        findViewById(R.id.radioEraser).setOnClickListener(v -> {
            drawingView.setEraserMode(true); // Set eraser mode
            drawingView.setStrokeWidth(20); // Optionally set a larger size for eraser
            Toast.makeText(CreateActivity.this, "Eraser selected", Toast.LENGTH_SHORT).show();
        });

        // SeekBar listener for brush size
        brushSizeSeekBar.setMax(50);
        brushSizeSeekBar.setProgress(10);
        brushSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                drawingView.setStrokeWidth(progress);
                Toast.makeText(CreateActivity.this, "Brush size: " + progress, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Save and clear button functionality
        saveButton.setOnClickListener(v -> {
            String title = artTitle.getText().toString();
            if (title.isEmpty()) {
                Toast.makeText(CreateActivity.this, "Please enter a title", Toast.LENGTH_SHORT).show();
            } else {
                // Save logic here
                Toast.makeText(CreateActivity.this, "Artwork saved as " + title, Toast.LENGTH_SHORT).show();
            }
        });

        clearButton.setOnClickListener(v -> {
            drawingView.clearCanvas(); // Clear the canvas
            Toast.makeText(CreateActivity.this, "Canvas cleared", Toast.LENGTH_SHORT).show();
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_navigation, menu); // Inflate the menu
        return true;
    }

}