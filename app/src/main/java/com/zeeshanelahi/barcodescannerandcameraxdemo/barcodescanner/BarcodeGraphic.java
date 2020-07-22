package com.zeeshanelahi.barcodescannerandcameraxdemo.barcodescanner;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.google.mlkit.vision.barcode.Barcode;
import com.zeeshanelahi.barcodescannerandcameraxdemo.barcodescanner.GraphicOverlay.Graphic;

/**
 * Graphic instance for rendering Barcode position and content information in an overlay view.
 */
public class BarcodeGraphic extends Graphic {

    //private static final int TEXT_COLOR = Color.BLACK;
    //private static final int MARKER_COLOR = Color.WHITE;
    //private static final float TEXT_SIZE = 54.0f;
    private static final float STROKE_WIDTH = 4.0f;

    private static final int BOX_COLOR = Color.GREEN;

    private final Paint rectPaint;
    //private final Paint barcodePaint;
    private final Barcode barcode;
    //private final Paint labelPaint;

    BarcodeGraphic(GraphicOverlay overlay, Barcode barcode) {
        super(overlay);

        this.barcode = barcode;

        rectPaint = new Paint();
        rectPaint.setColor(BOX_COLOR);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeWidth(STROKE_WIDTH);

        /*barcodePaint = new Paint();
        barcodePaint.setColor(TEXT_COLOR);
        barcodePaint.setTextSize(TEXT_SIZE);

        labelPaint = new Paint();
        labelPaint.setColor(MARKER_COLOR);
        labelPaint.setStyle(Paint.Style.FILL);*/
    }

    /**
     * Draws the barcode block annotations for position, size, and raw value on the supplied canvas.
     */
    @Override
    public void draw(Canvas canvas) {
        if (barcode == null) {
            throw new IllegalStateException("Attempting to draw a null barcode.");
        }

        // Draws the bounding box around the BarcodeBlock.
        RectF rect = new RectF(barcode.getBoundingBox());
        rect.left = translateX(rect.left);
        rect.top = translateY(rect.top);
        rect.right = translateX(rect.right);
        rect.bottom = translateY(rect.bottom);
        canvas.drawRect(rect, rectPaint);

        // Draws other object info.
        /*float lineHeight = TEXT_SIZE + (2 * STROKE_WIDTH);
        float textWidth = barcodePaint.measureText(barcode.getRawValue());
        float left = isImageFlipped() ? rect.right : rect.left;
        canvas.drawRect(
                left - STROKE_WIDTH,
                rect.top - lineHeight,
                left + textWidth + (2 * STROKE_WIDTH),
                rect.top,
                labelPaint);
        //Renders the barcode at the bottom of the box.
        canvas.drawText(barcode.getRawValue(), left, rect.top - STROKE_WIDTH, barcodePaint);*/
    }
}