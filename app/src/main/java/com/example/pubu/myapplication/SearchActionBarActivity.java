package com.example.pubu.myapplication;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SearchEvent;

public class SearchActionBarActivity extends AppCompatActivity {

    public static final String LOG_TAG = "Edward:Search";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_action_bar);

        // Setup Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d(LOG_TAG, "in onCreate()");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_default, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search_default).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
       // searchView.setSubmitButtonEnabled(true);
//        ComponentName cn = new ComponentName(this, SearchResultsActivity.class);
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(cn));

        return true;
    }

    @Override
    public boolean onSearchRequested(SearchEvent searchEvent) {
        Log.d(LOG_TAG, "onSearchRequested() with searchEvent= " + searchEvent.toString() );
        return super.onSearchRequested(searchEvent);
    }

    @Override
    public boolean onSearchRequested() {
        Log.d(LOG_TAG, "onSearchRequested()");
        return super.onSearchRequested();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Log.d(LOG_TAG, "id = " + id);

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        // Search on ActionBar
        if (id == R.id.menu_search_default) {
            Log.d(LOG_TAG, "Start the search....");
//            Intent searchActionBarActivity = new Intent(getApplicationContext(), SearchActionBarActivity.class);
//            startActivity(searchActionBarActivity);
//            return false;
        }

        return super.onOptionsItemSelected(item);
    }

}
