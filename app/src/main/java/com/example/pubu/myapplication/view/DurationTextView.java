package com.example.pubu.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.example.pubu.myapplication.R;

/**
 * Created by pubu on 2016/1/7.
 */
public class DurationTextView extends TextView {

    public static final String TAG = "Edward:DurationTextView";

    //private static final String TEMPLATE = "Duration: <strong>%s</strong>";
    private String mTemplate;

    public DurationTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.TemplateTextView);
        mTemplate = attributes.getString(R.styleable.TemplateTextView_template);
        if (mTemplate == null || !mTemplate.contains("%s")) {
            mTemplate = "%s";
        }
    }

    /**
     * Updates the text of the view with the new duration, properly
     * formatted
     *
     * @param duration
     * The duration in seconds
     */
    public void setDuration(float duration) {
        int durationInMinutes = Math.round(duration / 60);
        int hours = durationInMinutes / 60;
        int minutes = durationInMinutes % 60;

        String hourText = "";
        String minuteText = "";

        if (hours > 0) {
            hourText = hours + (hours == 1 ? " hour " : " hours ");
        }
        if (minutes > 0) {
            minuteText = minutes + (minutes == 1 ? " minute" : " minutes");
        }
        if (hours == 0 && minutes == 0) {
            minuteText = "less than 1 minute";
        }

       // String durationText = String.format(TEMPLATE, hourText + minuteText);
       // String durationText = String.format(getResources().getString(R.string.duration_format), hourText + minuteText);
        String durationText = String.format(mTemplate, hourText + minuteText);
        setText(Html.fromHtml(durationText), BufferType.SPANNABLE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(0xFF33B5B5);
        paint.setStrokeWidth(4);

        Log.d(TAG, "Org: 0, 0, " + getWidth() + ", " + getHeight());

        // take the padding into consideration
        int left = getPaddingLeft();
        int top = getPaddingTop();
        int right = getWidth() - getPaddingRight();
        int bottom = getHeight() - getPaddingBottom();

        Log.d(TAG, "New: "+left+", "+top+", " + right + ", " + bottom);

        canvas.drawLine(left, top, right, bottom, paint);

    }
}
