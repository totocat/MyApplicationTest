package com.example.pubu.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.pubu.myapplication.fragment.ImgSlideFragment;

/**
 * Created by pubu on 2016/1/13.
 */
public class ImgSlideFragmentPagerAdapter extends FragmentPagerAdapter {

    public ImgSlideFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return ImgSlideFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
