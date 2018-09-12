package com.ac.mdb_mp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intentStartGame = new Intent(SearchActivity.this, ListActivity.class);
        SearchActivity.this.startActivity(intentStartGame);
    }



}
