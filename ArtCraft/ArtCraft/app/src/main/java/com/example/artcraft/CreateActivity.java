package com.example.artcraft;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Stack;

public class CreateActivity extends AppCompatActivity {
    EditText artTitle;
    RadioButton brush, pencil;
    Button saveButton, clearButton;
    DrawingView drawingView;
    SeekBar brushSizeSeekBar;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        artTitle = findViewById(R.id.artTitle);
        brush = findViewById(R.id.radioBrush);
        pencil = findViewById(R.id.radioPencil);
        saveButton = findViewById(R.id.btnSave);
        clearButton = findViewById(R.id.btnClear);
        brushSizeSeekBar = findViewById(R.id.brushSizeSeekBar);
        drawingView = findViewById(R.id.drawingView);

        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);

        brush.setOnClickListener(v -> paint.setStrokeWidth(10));
        pencil.setOnClickListener(v -> paint.setStrokeWidth(5));

        // Set up the brush size SeekBar
        brushSizeSeekBar.setMax(50);
        brushSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                drawingView.setStrokeWidth(progress); // Use the drawingView to set stroke width
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        saveButton.setOnClickListener(v -> {
            String title = artTitle.getText().toString();
            Bitmap bitmap = drawingView.getBitmap();

            if (title.isEmpty()) {
                Toast.makeText(CreateActivity.this, "Please enter a title for your artwork", Toast.LENGTH_SHORT).show();
            } else {
                saveArtwork(bitmap, title);
            }
        });

        clearButton.setOnClickListener(v -> drawingView.clearCanvas());
    }

    private void saveArtwork(Bitmap bitmap, String title) {
        File filePath = Environment.getExternalStorageDirectory();
        File dir = new File(filePath.getAbsolutePath() + "/ArtCraft/");
        dir.mkdirs();
        File file = new File(dir, title + ".png");

        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            Toast.makeText(CreateActivity.this, "Artwork Saved as: " + title, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(CreateActivity.this, "Failed to save artwork", Toast.LENGTH_SHORT).show();
        }
    }
}
