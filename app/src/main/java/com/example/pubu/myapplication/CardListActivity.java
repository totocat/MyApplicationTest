package com.example.pubu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.pubu.myapplication.adapter.CardListArrayAdapter;
import com.example.pubu.myapplication.domain.Card;

public class CardListActivity extends AppCompatActivity {

    private static final String TAG = "CardListActivity";
    private CardListArrayAdapter cardListArrayAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);

        listView = (ListView) findViewById(R.id.cardList_listView);
//        listView.addHeaderView(new View(this));
//        listView.addFooterView(new View(this));

        cardListArrayAdapter = new CardListArrayAdapter(getApplicationContext(), R.layout.card_list_item);

        for (int i = 0; i < 10; i++) {
            Card card = new Card("Card " + (i+1) + " Line 1", "Card " + (i+1) + " Line 2");
            cardListArrayAdapter.add(card);
        }
        listView.setAdapter(cardListArrayAdapter);
    }
}
