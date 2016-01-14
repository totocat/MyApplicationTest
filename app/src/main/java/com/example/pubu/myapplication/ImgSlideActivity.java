package com.example.pubu.myapplication;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pubu.myapplication.adapter.ImgSlideFragmentPagerAdapter;

public class ImgSlideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_slide);

        ImgSlideFragmentPagerAdapter mgSlideFragmentPagerAdapter = new ImgSlideFragmentPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.imgSlidePager);
        viewPager.setAdapter(mgSlideFragmentPagerAdapter);


    }
}
