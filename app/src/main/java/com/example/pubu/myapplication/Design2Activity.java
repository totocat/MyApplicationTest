package com.example.pubu.myapplication;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class Design2Activity extends AppCompatActivity {
    public static final String TAG = "Edward:Design2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design2);

        // will have a Up caret on the Toolbar and then set a title fo rthe collapsingToolbarLayout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Second Activity");

        EditText editText = (EditText) findViewById(R.id.editText1);
        // DONT use it together with app:layout_behavior="@string/appbar_scrolling_view_behavior"....Otherwise
        //editText.setError("Error Test!!!");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_design2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Log.d(TAG, "Action Setting Design.....");
            return true;
        }
        if (id == android.R.id.home) {
            Log.d(TAG, "NavUtils.navigateUpFromSameTask() " + id);
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }

        Log.d(TAG, "super.onOptionsItemSelected() " + id);
        return super.onOptionsItemSelected(item);
    }
}
