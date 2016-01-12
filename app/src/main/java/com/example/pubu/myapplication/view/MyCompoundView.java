package com.example.pubu.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.pubu.myapplication.R;

/**
 * Created by pubu on 2016/1/11.
 */
public class MyCompoundView extends LinearLayout {

    EditText editText;
    Button clearBtn;

    public MyCompoundView(Context context) {
        super(context);

        Log.d("Edward", "MyCompoundView(Context context)");
        init();


    }

    public MyCompoundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d("Edward", "MyCompoundView(Context context, AttributeSet attrs)");
        init();
    }

    private void init() {
        // inflate the view from the layout resource
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        li.inflate(R.layout.clearable_edit_text, this, true);

        // get references t othe child controls
        editText = (EditText) findViewById(R.id.editText);
        clearBtn = (Button) findViewById(R.id.clearButton);
        clearBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });


    }
}
