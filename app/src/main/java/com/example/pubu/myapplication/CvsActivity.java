package com.example.pubu.myapplication;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.pubu.myapplication.adapter.CvsItemArrayAdapter;
import com.example.pubu.myapplication.util.CSVFile;

import java.io.InputStream;
import java.util.List;

public class CvsActivity extends AppCompatActivity {

    private ListView listView;
    private CvsItemArrayAdapter cvsItemArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvs);

        listView = (ListView) findViewById(R.id.cvsListView);
        cvsItemArrayAdapter = new CvsItemArrayAdapter(getApplicationContext(), R.layout.cvs_item_layout);

        Parcelable state = listView.onSaveInstanceState();
        listView.setAdapter(cvsItemArrayAdapter);
        listView.onRestoreInstanceState(state);

        InputStream inputStream = getResources().openRawResource(R.raw.cvs_test);
        CSVFile csvFile = new CSVFile(inputStream);

        cvsItemArrayAdapter.updateData(csvFile.read());

    }
}
