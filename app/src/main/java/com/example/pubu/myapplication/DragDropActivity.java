package com.example.pubu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class DragDropActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_drop);

        findViewById(R.id.dragDropTextView1).setOnTouchListener(this);
        findViewById(R.id.dragDropPinkLayout).setOnDragListener(this);
        findViewById(R.id.dragDropYellowLayout).setOnDragListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(null, shadowBuilder, v, 0);
            v.setVisibility(View.INVISIBLE);
        }
        return false;
    }

    @Override
    public boolean onDrag(View layoutView, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                Log.d("DragDrop" , "ACTION_DRAG_STARTED from " + layoutView.toString());
                break;

            case DragEvent.ACTION_DRAG_EXITED:
                Log.d("DragDrop" , "ACTION_DRAG_EXITED from " + layoutView.toString());
                break;

            case DragEvent.ACTION_DRAG_ENTERED:
                Log.d("DragDrop" , "ACTION_DRAG_ENTERED into " + layoutView.toString());
                break;

            case DragEvent.ACTION_DROP:
                // get the original view
                View dragView = (View) event.getLocalState();
                // remove the dragView from its original layout container

                ViewGroup dragViewGrop = (ViewGroup) dragView.getParent();
                dragViewGrop.removeView(dragView);

                // add the drag View into the dropped layout container
                LinearLayout dropViewGroup = (LinearLayout) layoutView;
                dropViewGroup.addView(dragView);

                // show the view
                dragView.setVisibility(View.VISIBLE);

                break;

            case DragEvent.ACTION_DRAG_ENDED:
                Log.d("DragDrop", "ACTION_DRAG_ENDED  " );
                break;
        }

        return true;
    }
}
