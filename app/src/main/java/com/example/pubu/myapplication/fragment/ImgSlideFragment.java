package com.example.pubu.myapplication.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.pubu.myapplication.R;

/**
 * Created by pubu on 2016/1/13.
 */
public class ImgSlideFragment extends Fragment {

    private final static String POSITION = "position";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View imgSlideView = inflater.inflate(R.layout.img_slide_fragment, container, false);
        ImageView imageView = (ImageView) imgSlideView.findViewById(R.id.imgSlideView);
        Bundle bundle = getArguments();
        int positon = bundle.getInt(POSITION);
        int imgResId;
        if (positon == 0) {
            imgResId = getResources().getIdentifier("img", "drawable", "com.example.pubu.myapplication");
        } else {
            imgResId = getResources().getIdentifier("placeholder", "drawable", "com.example.pubu.myapplication");
        }
        Log.d("Edward", "imgResId=" + imgResId);
        imageView.setImageResource(imgResId);
        return imgSlideView;
    }

    public static ImgSlideFragment newInstance(int position) {
        Log.d("Edward", "position=" + position);
        ImgSlideFragment imgSlideFragment = new ImgSlideFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION, position);
        imgSlideFragment.setArguments(bundle);
        return imgSlideFragment;
    }
}
