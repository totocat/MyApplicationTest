package com.example.pubu.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.pubu.myapplication.R;
import com.squareup.picasso.Picasso;

/**
 * Created by pubu on 2016/1/8.
 */
public class DemoM3Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout resource that'll be returned
        View rootView = inflater.inflate(R.layout.pager_item, container, false);

        // Get the arguments that was supplied when
        // the fragment was instantiated in the
        // CustomPagerAdapter
        Bundle args = getArguments();
        int position = args.getInt("page_position");
        String[] urlAry = args.getStringArray("url_ary");
        //((TextView) rootView.findViewById(R.id.text)).setText("Page " + args.getInt("page_position"));

        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.with(getActivity()).load(urlAry[0] ).placeholder(R.drawable.placeholder).into(imageView);

        ImageView imageView2 = (ImageView) rootView.findViewById(R.id.imageView2);
        imageView2.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.with(getActivity()).load(urlAry[1]).placeholder(R.drawable.placeholder).into(imageView2);

        return rootView;
    }
}


