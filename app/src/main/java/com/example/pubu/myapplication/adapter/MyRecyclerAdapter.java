package com.example.pubu.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pubu.myapplication.Design2Activity;
import com.example.pubu.myapplication.R;
import com.example.pubu.myapplication.domain.FeedItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by pubu on 2015/12/31.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.CustomViewHolder> {
    private List<FeedItem> feedItemList;
    private Context mContext;
    private static final String TAG = "Edward:RecyclerAdapter";

    public MyRecyclerAdapter(Context context, List<FeedItem> feedItemList) {
        this.mContext = context;
        this.feedItemList = feedItemList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int position) {
        FeedItem feedItem = feedItemList.get(position);

        Log.d(TAG, "onBindViewHolder() -> Position :" + position);

        // D/L image using picasso library
        Picasso.with(mContext).load(feedItem.getThumbnail())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(customViewHolder.imageView);

        // Setting text view title
        customViewHolder.textView.setText(Html.fromHtml(feedItem.getTitle()));

        // Handle RecyclerView click event
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomViewHolder holder = (CustomViewHolder) view.getTag();
                int position = holder.getPosition();

                FeedItem feedItem = feedItemList.get(position);
                Toast.makeText(mContext, feedItem.getTitle(), Toast.LENGTH_SHORT).show();

                // forward to Design2Activity
                Context context = view.getContext();
                context.startActivity(new Intent(context, Design2Activity.class));
            }
        };
        customViewHolder.textView.setOnClickListener(clickListener);
        customViewHolder.imageView.setOnClickListener(clickListener);
        customViewHolder.textView.setTag(customViewHolder);
        customViewHolder.imageView.setTag(customViewHolder);

    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView textView;

        public CustomViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
            this.textView = (TextView) view.findViewById(R.id.title);
        }
    }
}
