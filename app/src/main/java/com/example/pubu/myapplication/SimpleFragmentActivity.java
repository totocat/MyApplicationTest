package com.example.pubu.myapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.pubu.myapplication.fragment.NewItemFragment;
import com.example.pubu.myapplication.fragment.ToDoListFragment;

import java.util.ArrayList;

public class SimpleFragmentActivity extends AppCompatActivity implements NewItemFragment.OnNewItemAddedListener {

    ArrayList<String> todoItems;
    ArrayAdapter<String> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_fragment);

        //Get the references to the fragments
        FragmentManager fm = getSupportFragmentManager();
        ToDoListFragment toDoListFragment = (ToDoListFragment) fm.findFragmentById(R.id.toDoListFragment);
        NewItemFragment newItemFragment = (NewItemFragment) fm.findFragmentById(R.id.newItemFragment);
        newItemFragment.addOnNewItemAddedListener(this);

        // Create the ArrayList of the to do items
        todoItems = new ArrayList<>();

        // Bind the ArrayAdpater to the ListView
        aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todoItems);

        toDoListFragment.setListAdapter(aa);

    }


    @Override
    public void onNewItemAdded(String newItem) {
        Log.d("Edward", "onNewItemAdded=" + newItem);
        todoItems.add(0, newItem);
        aa.notifyDataSetChanged();
    }
}
