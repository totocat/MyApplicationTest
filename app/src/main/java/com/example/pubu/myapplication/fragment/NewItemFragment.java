package com.example.pubu.myapplication.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.pubu.myapplication.R;

/**
 * Created by pubu on 2016/1/8.
 */
public class NewItemFragment extends Fragment  {

    public interface OnNewItemAddedListener {
        public void onNewItemAdded(String newItem);
    }

    private OnNewItemAddedListener onNewItemAddedListener;

    public void addOnNewItemAddedListener(OnNewItemAddedListener onNewItemAddedListener) {
        this.onNewItemAddedListener = onNewItemAddedListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_item, container, false);

        Log.d("Edward", "onCreateView()..");
        // Get references to UI widget
        final EditText editText = (EditText) view.findViewById(R.id.myEditText);

        // add the input string into the todolist  after the ure presses [Enter]
        editText.setOnKeyListener(new View.OnKeyListener(){

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.d("Edward", "Key pressed..");
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER) {
                        Log.d("Edward", "input=" + editText.getText().toString());
                        onNewItemAddedListener.onNewItemAdded(editText.getText().toString());
                        editText.setText("");
                        return true;
                    }
                }
                return false;
            }
        });


        return view;
    }



}

