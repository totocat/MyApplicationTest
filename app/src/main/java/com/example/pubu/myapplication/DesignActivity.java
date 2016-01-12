package com.example.pubu.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.pubu.myapplication.adapter.DesignPagerAdapter;

public class DesignActivity extends AppCompatActivity {
    public static final String TAG = "Edward:Design";
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // use the toolbar instead
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        setNaviationViewListener();
        setFABLinstener();

        // Set tab/pagers
        DesignPagerAdapter pagerAdapter = new DesignPagerAdapter(getSupportFragmentManager());
        ViewPager viewPage = (ViewPager)  findViewById(R.id.viewpager);
        viewPage.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPage);

    }

    ////
    private void setFABLinstener() {

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "FAB onClick()");
                // Snackbar will attempt to find an appropriate parent to enuse that it is anchored to the bottom. So, we use "findViewById(R.id.drawer_layout)" instead of "v".
                Snackbar.make(findViewById(R.id.coordinator), "I am a Snackbar", Snackbar.LENGTH_LONG).setAction("Action", new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(DesignActivity.this, "Pressed FAB", Toast.LENGTH_LONG).show();
                    }
                }).show();
            }
        });
    }

    /**
     *  Listener for the NavigationView
     */
    private void setNaviationViewListener() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                Toast.makeText(DesignActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_design, menu);

       return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

            case R.id.action_settings_design:
                Log.d(TAG, "Action Setting Design.....");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
