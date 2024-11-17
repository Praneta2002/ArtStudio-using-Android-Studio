package com.example.artcraft;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.Stack;

public class DrawingView extends View {
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint bitmapPaint;
    private Paint paint;
    private Stack<Bitmap> history = new Stack<>();
    private float x, y;

    public DrawingView(Context context) {
        super(context);
        bitmapPaint = new Paint(Paint.DITHER_FLAG);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE); // Set initial background color
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, bitmapPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                // Save the current bitmap before drawing
                history.push(Bitmap.createBitmap(bitmap));
                canvas.drawCircle(x, y, paint.getStrokeWidth() / 2, paint);
                invalidate();
                break;
        }
        return true;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void clearCanvas() {
        canvas.drawColor(Color.WHITE);
        invalidate();
    }

    public void undo() {
        if (!history.isEmpty()) {
            bitmap = history.pop();
            invalidate();
        }
    }

    public void setPaintColor(int color) {
        paint.setColor(color);
    }

    public void setStrokeWidth(float width) {
        paint.setStrokeWidth(width);
    }
}
