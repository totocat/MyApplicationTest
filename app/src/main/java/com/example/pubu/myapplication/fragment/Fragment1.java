package com.example.pubu.myapplication.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.pubu.myapplication.R;
import com.example.pubu.myapplication.adapter.ToDoItemAdapter;

import java.util.ArrayList;

/**
 * Created by pubu on 2016/1/8.
 */
public class Fragment1 extends Fragment implements NewItemFragment.OnNewItemAddedListener {

    ArrayList<String> todoItems;
    ArrayAdapter<String> aa;
    ToDoItemAdapter bb;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        Log.d("Edward" , "onCreateView()....");

//
//        // Get references to UI widget
//        ListView listView = (ListView) view.findViewById(R.id.myListView);
//        final EditText editText = (EditText) view.findViewById(R.id.myEditText);
//
//        // Create the ArrayList of the to do items
//        final ArrayList<String> todoItems = new ArrayList<>();
//
//
//        // Create the Array Adapter to bind the array to the list view
//        final ArrayAdapter<String> aa = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, todoItems);
//
//        // Bind the ArrayAdpater to the ListView
//        listView.setAdapter(aa);
//
//        // add the input string into the todolist  after the ure presses [Enter]
//        editText.setOnKeyListener(new View.OnKeyListener(){
//
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (event.getAction() == KeyEvent.ACTION_DOWN) {
//                    if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER) {
//                        todoItems.add(0, editText.getText().toString());
//                        aa.notifyDataSetChanged();
//                        editText.setText("");
//                        return true;
//                    }
//                }
//                return false;
//            }
//        });



        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("Edward", "onActivityCreated()....");
        //Get the references to the fragments
        //FragmentManager fm = getFragmentManager();
        //FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentManager fm = getChildFragmentManager(); // It is a nested fragment....It seems that a fragment manager does not have "nested" concept, so you have to use it specifically.
        ToDoListFragment toDoListFragment = (ToDoListFragment) fm.findFragmentById(R.id.toDoListFragment);
        NewItemFragment newItemFragment = (NewItemFragment) fm.findFragmentById(R.id.newItemFragment);
        newItemFragment.addOnNewItemAddedListener(this);

        // Create the ArrayList of the to do items
        todoItems = new ArrayList<>();

        // Bind the ArrayAdpater to the ListView
        //aa = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, todoItems);
        // aa = new ArrayAdapter<>(getActivity(), R.layout.todolist_item, todoItems);
        bb = new ToDoItemAdapter(getActivity(), R.layout.todolist_item, todoItems);

        //toDoListFragment.setListAdapter(aa);
        toDoListFragment.setListAdapter(bb);



    }

    @Override
    public void onNewItemAdded(String newItem) {

        todoItems.add(0, newItem);
        //aa.notifyDataSetChanged();
        bb.notifyDataSetChanged();
    }
}

