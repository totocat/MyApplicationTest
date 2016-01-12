package com.example.pubu.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.pubu.myapplication.view.DurationTextView;

public class MyviewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myview);

        DurationTextView durationTextView = (DurationTextView) findViewById(R.id.durationview);
        durationTextView.setDuration(1398);

        DurationTextView durationTextView2 = (DurationTextView) findViewById(R.id.durationview2);
        durationTextView2.setDuration(600);
    }
}
