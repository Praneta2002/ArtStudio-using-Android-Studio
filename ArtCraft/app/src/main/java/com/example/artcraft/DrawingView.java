package com.example.artcraft;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DrawingView extends View {

    private Paint paint;
    private Path path;
    private Canvas canvas;
    private Bitmap bitmap;
    private int color;

    public void setStrokeWidth(int progress) {
        paint.setStrokeWidth(progress);
    }

    // Tool types
    public enum ToolType {
        BRUSH, PENCIL, ERASER
    }

    private ToolType currentTool = ToolType.BRUSH; // Default to brush

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        path = new Path();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(Color.BLACK); // Default color
        paint.setStrokeWidth(10); // Default size for brush
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                canvas.drawPath(path, paint);
                path.reset();
                break;
        }

        invalidate();
        return true;
    }

    // Switch tool type (from the buttons or radio group)
    public void setToolType(ToolType toolType) {
        currentTool = toolType;
        switch (toolType) {
            case BRUSH:
                setBrushSize(10); // Set size for brush
                setColor(Color.BLACK); // Brush color
                paint.setXfermode(null); // Regular drawing mode
                break;
            case PENCIL:
                setBrushSize(2); // Set size for pencil
                setColor(Color.DKGRAY); // Pencil color
                paint.setXfermode(null); // Regular drawing mode
                break;
            case ERASER:
                setBrushSize(20); // Larger stroke for eraser
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR)); // Eraser mode
                break;
        }
    }

    // Clear the canvas
    public void clearCanvas() {
        bitmap.eraseColor(Color.WHITE); // Clear the canvas by filling it with white color
        invalidate(); // Request to redraw the view
    }

    // Set brush size
    public void setBrushSize(float size) {
        paint.setStrokeWidth(size);
    }

    // Set color
    public void setColor(int newColor) {
        color = newColor; // Update the color
        paint.setColor(color); // Set the paint color to the new color
    }

    // Set eraser mode
    public void setEraserMode(boolean isEraser) {
        if (isEraser) {
            setToolType(ToolType.ERASER); // Switch to eraser tool
        } else {
            setToolType(ToolType.BRUSH); // Switch back to brush (or default tool)
        }
    }

    // Method to save the drawing
    public void saveDrawing(String fileName) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
            values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);

            // Get the ContentResolver and insert the image
            Uri uri = getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            try {
                if (uri != null) {
                    OutputStream outputStream = getContext().getContentResolver().openOutputStream(uri);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream); // Save bitmap as PNG
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Fallback for devices below Android 10
            File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File file = new File(directory, fileName);

            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream); // Save bitmap as PNG
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}