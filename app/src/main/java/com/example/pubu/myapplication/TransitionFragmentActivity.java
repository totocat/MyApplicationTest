package com.example.pubu.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pubu.myapplication.fragment.EditNameDialogFragment;
import com.example.pubu.myapplication.fragment.Fragment1;
import com.example.pubu.myapplication.fragment.Fragment2;
import com.example.pubu.myapplication.fragment.Fragment3;
import com.example.pubu.myapplication.fragment.StartFragment;

public class TransitionFragmentActivity extends AppCompatActivity implements EditNameDialogFragment.EditNameDialogListener {

    Fragment fragment;
    Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_fragment);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


        StartFragment startFragment = new StartFragment();
        ft.add(R.id.myFragment, startFragment);
        ft.commit();

        btn1.setOnClickListener(btnOnClickListener);
        btn2.setOnClickListener(btnOnClickListener);
        btn3.setOnClickListener(btnOnClickListener);

       // showEditDialog();
    }

    Button.OnClickListener btnOnClickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Fragment newFragment;
            if (v == btn1) {
                newFragment = new Fragment1();
            } else if (v == btn2) {
                newFragment = new Fragment2();
            }else if (v == btn3) {
                newFragment = new Fragment3();
            }else  {
                newFragment = new StartFragment();
            }

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.myFragment, newFragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
        }
    };

    /**
     * It seems that calling addToBackStack() on fragment transaction is not enough. We have to handle popoup  the back stack upon Back button pressed by ouselves/
     * I added this to my activity and it worked as expted.
     */
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0 ){
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    private void showEditDialog() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        EditNameDialogFragment editNameDialog = new EditNameDialogFragment();
        editNameDialog.show(fm, "fragment_edit_name");

    }

    @Override
    public void onFinishEditDialog(String inputText) {
        Toast.makeText(this, "Hi, " + inputText, Toast.LENGTH_SHORT).show();
    }
}
