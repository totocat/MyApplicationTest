package com.example.pubu.myapplication.adapter;

//import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.pubu.myapplication.DesignFragment;

/**
 * Created by pubu on 2016/1/4.
 */
public class DesignPagerAdapter extends FragmentStatePagerAdapter {

    public DesignPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return DesignFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Tab " + position;
    }
}
