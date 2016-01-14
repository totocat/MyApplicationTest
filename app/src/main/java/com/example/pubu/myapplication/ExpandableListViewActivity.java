package com.example.pubu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.pubu.myapplication.adapter.MyExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListViewActivity extends AppCompatActivity {

    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    ExpandableListView expandableListView;
    MyExpandableListAdapter myExpandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view);

        setupData();
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        myExpandableListAdapter = new MyExpandableListAdapter(this, expandableListTitle, expandableListDetail);

        expandableListView.setAdapter(myExpandableListAdapter);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });
    }

    /**
     *  Setting up some testing data
     */
    private void setupData() {
        expandableListDetail = new HashMap<>();

        List technology = new ArrayList();
        technology.add("Beats sued for noise-cancelling tech");
        technology.add("Wikipedia blocks US Congress edits");
        technology.add("Google quizzed over deleted links");
        technology.add("Nasa seeks aid with Earth-Mars links");
        technology.add("The Good, the Bad and the Ugly");

        List entertainment = new ArrayList();
        entertainment.add("Goldfinch novel set for big screen");
        entertainment.add("Anderson stellar in Streetcar");
        entertainment.add("Ronstadt receives White House honour");
        entertainment.add("Toronto to open with The Judge");
        entertainment.add("British dancer return from Russia");

        List science = new ArrayList();
        science.add("Eggshell may act like sunblock");
        science.add("Brain hub predicts negative events");
        science.add("California hit by raging wildfires");
        science.add("Rosetta's comet seen in close-up");
        science.add("Secret of sandstone shapes revealed");

        expandableListDetail.put("TECHNOLOGY NEWS", technology);
        expandableListDetail.put("ENTERTAINMENT NEWS", entertainment);
        expandableListDetail.put("SCIENCE & ENVIRONMENT NEWS", science);

        expandableListTitle = new ArrayList(expandableListDetail.keySet());

    }
}
