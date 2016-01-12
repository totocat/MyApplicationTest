package com.example.pubu.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pubu.myapplication.adapter.MyRecyclerAdapter;
import com.example.pubu.myapplication.domain.FeedItem;

import java.util.ArrayList;
import java.util.List;


public class DesignFragment extends Fragment {
    private static final String TAB_POSITION = "tab_position";

    public DesignFragment() {

    }

    public static DesignFragment newInstance(int tabPosition) {
        DesignFragment fragment = new DesignFragment();
        Bundle args = new Bundle();
        args.putInt(TAB_POSITION, tabPosition);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();
        int tabPosition = args.getInt(TAB_POSITION);
        //
//        TextView textView = new TextView(getActivity());
//        textView.setGravity(Gravity.CENTER);
//        textView.setText("Text in Tab #" + tabPosition);

        // prepare MyRecyclerAdapter's feedItemList
        List<FeedItem> feedsList = new ArrayList<>();
        for (int i=1; i<20; i++) {
            feedsList.add( new FeedItem("Title " + i, "http://javatechig.com/wp-content/uploads/2015/03/logo.png"));
        }

        // prepare the view
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_design);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new MyRecyclerAdapter(getActivity(), feedsList));

        return view;
    }
}

