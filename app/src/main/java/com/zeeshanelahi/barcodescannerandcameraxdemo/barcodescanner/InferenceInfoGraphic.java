package com.zeeshanelahi.barcodescannerandcameraxdemo.barcodescanner;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.annotation.Nullable;

/**
 * Graphic instance for rendering inference info (latency, FPS, resolution) in an overlay view.
 */
public class InferenceInfoGraphic extends GraphicOverlay.Graphic {

    private static final int TEXT_COLOR = Color.WHITE;
    private static final float TEXT_SIZE = 60.0f;

    private final Paint textPaint;
    private final GraphicOverlay overlay;
    private final double latency;

    // Only valid when a stream of input images is being processed. Null for single image mode.
    @Nullable
    private final Integer framesPerSecond;

    public InferenceInfoGraphic(
            GraphicOverlay overlay, double latency, @Nullable Integer framesPerSecond) {
        super(overlay);
        this.overlay = overlay;
        this.latency = latency;
        this.framesPerSecond = framesPerSecond;
        textPaint = new Paint();
        textPaint.setColor(TEXT_COLOR);
        textPaint.setTextSize(TEXT_SIZE);
        postInvalidate();
    }

    @Override
    public synchronized void draw(Canvas canvas) {
        float x = TEXT_SIZE * 0.5f;
        float y = TEXT_SIZE * 1.5f;

        canvas.drawText(
                "InputImage size: " + overlay.getImageWidth() + "x" + overlay.getImageHeight(),
                x,
                y,
                textPaint);

        // Draw FPS (if valid) and inference latency
        if (framesPerSecond != null) {
            canvas.drawText(
                    "FPS: " + framesPerSecond + ", latency: " + latency + " ms", x, y + TEXT_SIZE, textPaint);
        } else {
            canvas.drawText("Latency: " + latency + " ms", x, y + TEXT_SIZE, textPaint);
        }
    }
}