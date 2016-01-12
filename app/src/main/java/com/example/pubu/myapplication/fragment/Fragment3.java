package com.example.pubu.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pubu.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pubu on 2016/1/8.
 */
public class Fragment3 extends Fragment {

    LinearLayout myGallery;
    String imageURL = "http://192.168.1.75:8080/spring4-sample/resources/img/";
    String[] imageArray = new String[]{"img1.PNG", "img2.PNG", "img3.PNG", "img4.PNG","img5.PNG", "img6.PNG", "img7.PNG", "img8.PNG"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_3, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("Edward", " frg 2 onActivityCreated()....");

        // method 1
        populateImages();

        // Method 2:
        CustomPagerAdapter mCustomPagerAdapter = new CustomPagerAdapter(this.getActivity());
        ViewPager mViewPager = (ViewPager) getView().findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);

        // Method 3: Use this way to save time to do the "ViewHolder" pattern by youslef because the fragment manager will help to do the similar thing.
        CustomPagerM3Adapter mCustomM3PagerAdapter = new CustomPagerM3Adapter(getChildFragmentManager(), this.getActivity());

        ViewPager mViewPager3 = (ViewPager) getView().findViewById(R.id.pager3);
        mViewPager3.setAdapter(mCustomM3PagerAdapter);
    }

    private void populateImages() {
        myGallery = (LinearLayout) getView().findViewById(R.id.gallerylayout);
        for (int i = 0; i < imageArray.length; i++) {
            myGallery.addView(insertPhoto(imageURL + imageArray[i]));
        }
    }

    public View insertPhoto(String path){
        LinearLayout layout = new LinearLayout(getActivity().getApplicationContext());
        layout.setGravity(Gravity.CENTER);
        ImageView imageView = new ImageView(getActivity().getApplicationContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.with(getActivity()).load(path).placeholder(R.drawable.placeholder).into(imageView);
        layout.addView(imageView);
        return layout;
    }

    class CustomPagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mLayoutInflater;

        public CustomPagerAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return imageArray.length/2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.d("Edward", "position = " + position);

            View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.with(getActivity()).load(imageURL + imageArray[position*2] ).placeholder(R.drawable.placeholder).into(imageView);

            ImageView imageView2 = (ImageView) itemView.findViewById(R.id.imageView2);
            imageView2.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.with(getActivity()).load(imageURL + imageArray[position*2 + 1] ).placeholder(R.drawable.placeholder).into(imageView2);


            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }


    public class CustomPagerM3Adapter extends FragmentStatePagerAdapter {

        protected Context mContext;

        public CustomPagerM3Adapter(FragmentManager fm, Context context) {
            super(fm);
            mContext = context;
        }

        /**
         * Method 3
         * @param position
         * @return
         */
        @Override
        // This method returns the fragment associated with
        // the specified position.
        //
        // It is called when the Adapter needs a fragment
        // and it does not exists.
        public Fragment getItem(int position) {

            // Create fragment object
            Fragment fragment = new DemoM3Fragment();

            // Attach some data to it that we'll
            // use to populate our fragment layouts
            Bundle args = new Bundle();
            args.putInt("page_position", position + 1);
            String[] urlAry = new String[2];
            urlAry[0] = imageURL + imageArray[position*2];
            urlAry[1] = imageURL + imageArray[position*2 + 1];
            args.putStringArray("url_ary", urlAry);

            // Set the arguments on the fragment
            // that will be fetched in DemoFragment@onCreateView
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public int getCount() {
            return imageArray.length/2;
        }
    }

}
