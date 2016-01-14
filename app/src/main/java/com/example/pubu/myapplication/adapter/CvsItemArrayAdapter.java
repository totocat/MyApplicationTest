package com.example.pubu.myapplication.adapter;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pubu.myapplication.BuildConfig;
import com.example.pubu.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pubu on 2016/1/13.
 */
public class CvsItemArrayAdapter extends BaseAdapter {

    private List<String[]> scoreList = new ArrayList();
    private final Context context;
    private int resource;

    public CvsItemArrayAdapter(Context context, int resource) {
        this.context = context;
        this.resource = resource;
    }

    /**
     * get rid of Threading to save time to lock the thread to becaome thread safty. Make sure the adapter is used only from Main thread to improve the performance
     * @param scoreList
     */
    public void updateData(List<String[]> scoreList) {
        ThreadPreconditions.checkOnMainThread();
        this.scoreList = scoreList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return scoreList.size();
    }

    @Override
    public String[] getItem(int position) {
        return scoreList.get(position);
    }

    /**
     * getItemId is often useless, I think this should be the default implementation in BaseAdapter
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CvsItemViewHolder viewHolder;

        Log.d("Edward", "pos = " + position);

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(resource, parent, false);
            viewHolder = new CvsItemViewHolder((TextView)row.findViewById(R.id.name), (TextView)row.findViewById(R.id.score));
            row.setTag(viewHolder);
        } else {
            viewHolder = (CvsItemViewHolder) row.getTag();
        }

        String[] stat = getItem(position);
        Log.d("Edward", stat[0] + " " + stat[1]);
        viewHolder.name.setText(stat[0]);
        viewHolder.score.setText(stat[1]);

        return row;
    }

    /**
     * Make sure that this adapter should be called from the Main Thread!
     */
    public static class ThreadPreconditions {
        public static void checkOnMainThread() {
            if (BuildConfig.DEBUG) {
                if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
                    throw new IllegalStateException("This method should be called from the Main Thread!");
                }
            }
        }
    }

    /**
     * ViewHolder pattern: to limit the number of call to findViewById
     */
    private static class CvsItemViewHolder {
        private TextView name;
        private TextView score;

        public CvsItemViewHolder(TextView name, TextView score) {
            this.name = name;
            this.score = score;
        }

    }
}
