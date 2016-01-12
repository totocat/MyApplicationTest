package com.example.pubu.myapplication.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pubu.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pubu on 2016/1/8.
 */
public class Fragment2 extends Fragment {

    private Spinner spinner1, spinner2;
    private Button btnSubmit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("Edward", " frg 2 onActivityCreated()....");

        spinner1 = (Spinner) getView().findViewById(R.id.spinner1);
        spinner2 = (Spinner) getView().findViewById(R.id.spinner2);
        btnSubmit = (Button) getView().findViewById(R.id.btnSubmit);

        // add items into spinner2 dynamically
        List<String> list = new ArrayList<>();
        list.add("List 1");
        list.add("List 2");
        list.add("List 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item , list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        getContext(),
                        "spinner1=" + String.valueOf(spinner1.getSelectedItem()) + ", spinner2=" + String.valueOf(spinner2.getSelectedItem()),
                        Toast.LENGTH_LONG
                ).show();
            }
        });


    }

}
