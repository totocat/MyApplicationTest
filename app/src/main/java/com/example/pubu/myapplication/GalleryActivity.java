package com.example.pubu.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class GalleryActivity extends AppCompatActivity {
    String imageURL = "http://192.168.1.75:8080/spring4-sample/resources/img/";
    String[] imageArray = new String[]{"img1.PNG", "img2.PNG", "img3.PNG", "img4.PNG","img5.PNG", "img6.PNG", "img7.PNG", "img8.PNG"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        // Note that Gallery view is deprecated in Android 4.1---
        Gallery gallery = (Gallery) findViewById(R.id.gallery1);
        gallery.setAdapter(new ImageAdapter(this));
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getBaseContext(), "pic" + (position + 1) + " selected",
                        Toast.LENGTH_SHORT).show();
                // display the images selected
                ImageView imageView = (ImageView) findViewById(R.id.image1);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Picasso.with(getApplicationContext()).load(imageURL + imageArray[position] ).placeholder(R.drawable.placeholder).into(imageView);
            }
        });
    }

    public class ImageAdapter extends BaseAdapter {
        private Context context;
        private int itemBackground;
        public ImageAdapter(Context c)
        {
            context = c;
            // sets a grey background; wraps around the images
            TypedArray a =obtainStyledAttributes(R.styleable.MyGallery);
            itemBackground = a.getResourceId(R.styleable.MyGallery_android_galleryItemBackground, 0);
            a.recycle();
        }
        // returns the number of images
        public int getCount() {
            return imageArray.length;
        }
        // returns the ID of an item
        public Object getItem(int position) {
            return position;
        }
        // returns the ID of an item
        public long getItemId(int position) {
            return position;
        }
        // returns an ImageView view
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(context);

            imageView.setLayoutParams(new Gallery.LayoutParams(300, 300));
            imageView.setBackgroundResource(itemBackground);
            Picasso.with(getApplicationContext()).load(imageURL + imageArray[position] ).placeholder(R.drawable.placeholder).into(imageView);
            return imageView;
        }
    }
}
