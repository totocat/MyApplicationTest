package com.example.pubu.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pubu.myapplication.R;

import java.util.ArrayList;

/**
 * Created by pubu on 2016/1/11.
 */
public class ToDoItemAdapter extends ArrayAdapter<String> {
    int resource;
    public ToDoItemAdapter(Context context, int resource, ArrayList items) {
        super(context, resource, items);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // return super.getView(position, convertView, parent);
        LinearLayout todoView;

        String taskString = getItem(position);

        if (convertView == null) {
            todoView = new LinearLayout(getContext());
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            li.inflate(resource, todoView, true);
        } else {
            todoView = (LinearLayout) convertView;
        }

        TextView taskView = (TextView) todoView.findViewById(R.id.row);
        TextView crtDateDateView = (TextView) todoView.findViewById(R.id.rowDate);
        taskView.setText(taskString);
        crtDateDateView.setText("12/01");

        return todoView;
    }
}
