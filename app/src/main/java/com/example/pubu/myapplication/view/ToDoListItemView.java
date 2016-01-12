package com.example.pubu.myapplication.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.pubu.myapplication.R;

/**
 * Created by pubu on 2016/1/11.
 */
public class ToDoListItemView extends TextView {

    private Paint marginPaint;
    private Paint linePaint;
    private int paperColor;
    private float margin;

    public ToDoListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public ToDoListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public ToDoListItemView(Context context) {
        super(context);
        init();
    }

    private void init() {
        // Get a reference to our resource table
        Resources resources = getResources();

        // Create  a paint brushes we will use in the onDraw()
        marginPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //marginPaint.setColor(resources.getColor(R.color.notepad_margin));
        marginPaint.setColor(ContextCompat.getColor(getContext(), R.color.notepad_margin ));
        //linePaint.setColor(resources.getColor(R.color.notepad_lines));
        linePaint.setColor(ContextCompat.getColor(getContext(), R.color.notepad_lines ));

        // Get the paper background color and the margin width
        paperColor = ContextCompat.getColor(getContext(), R.color.notepad_paper );
        margin = resources.getDimension(R.dimen.notepad_margin);

    }

    /**
     * Use the base TextView to render the text
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {

        //Color as paper
        canvas.drawColor(paperColor);

        // draw ruled lines
        canvas.drawLine(0, 0, 0, getMeasuredHeight(), linePaint);
        canvas.drawLine(0, getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight(), linePaint);

        // draq margin
        canvas.drawLine(margin, 0, margin, getMeasuredHeight(), marginPaint);

        // move the text across from the margin
        canvas.save();
        canvas.translate(margin, 0);

        // use teh TExtView to render the text
        super.onDraw(canvas);
        canvas.restore();


    }
}
